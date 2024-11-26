<template>
  <div class="common-layout">
    <!---侧边栏--->
    <el-container>
      <el-aside class="aside" >
        <!---切换--->
        <el-menu
            background-color="#f6f1ea"
            class="el-menu-vertical-demo"
            text-color="#090A0B"
        >
          <!---头像--->
          <el-row class="demo-avatar demo-basic">
            <el-col :span="20">
              <div class="demo-basic--circle">
                <div class="block">
                  <el-avatar :size="50" :src="circleUrl" />
                </div>
                <div class="sub-title">admin</div>
              </div>
            </el-col>
          </el-row>
          <div class="menu-list">
            <el-menu-item index="1">
              <el-icon><icon-menu /></el-icon>
              <span >首页</span>
            </el-menu-item>
            <router-link to="/CLubMessage">
              <el-menu-item index="2" >
                <el-icon><document /></el-icon>
                <span>社团信息管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ClNumberReview">
              <el-menu-item index="3">
                <el-icon><setting /></el-icon>
                <span>成员审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ClNumberControl">
              <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>成员管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/PostControl">
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>发帖管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ExitControl">
              <el-menu-item index="6">
                <el-icon><setting /></el-icon>
                <span>退出审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/clContentControl">
              <el-menu-item index="7">
                <el-icon><setting /></el-icon>
                <span>评论管理</span>
              </el-menu-item>
            </router-link>
          </div>
        </el-menu>
      </el-aside>
      <!---上边栏--->
      <el-container >
        <el-header class="header">
          <span class="text-large font-600 mr-3"><h2>发帖管理 </h2></span>
        </el-header>
        <!---主界面--->
        <el-main>
          <!---搜索框--->
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="发布日期">
              <div class="demo-datetime-picker" style="margin-top: -30px;">
                <div class="block">
                  <el-date-picker
                      v-model="formInline.date"
                      type="datetime"
                      placeholder="Pick a Date"
                      format="YYYY-MM-DD"
                      date-format="MMM DD, YYYY"
                      
                  />
                </div>
              </div>
            </el-form-item>
            <el-form-item style="margin-top: -30px;" >
              <el-input v-model="formInline.title" placeholder="发帖标题" clearable />
            </el-form-item>
            <el-form-item style="margin-top: -30px;">
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%; margin-top: -30px;">
            <el-table-column prop="title" label="发帖标题" width="220" />
            <el-table-column prop="registerTime" label="发布日期" width="200" />
            <el-table-column prop="essence" label="精华" width="100" >
              <template v-slot="scope" >{{ isEssences[scope.row.essence].value }}
              </template>
            </el-table-column>
            <el-table-column prop="right" label="帖子详情" min-width="50">
              <template #default="scope">
                <el-button @click="viewDetails(scope.row.context)">
                  查看详情
                </el-button>
              </template>
            </el-table-column>
            <el-table-column prop="username" label="发帖人" width="120" />
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-popconfirm
                    confirm-button-text="Yes"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认要删除吗?"
                    @confirm="cancel(scope.row)"
                >
                  <template #reference>
                    <el-button  plain link type=danger size="small" >
                      删除
                    </el-button>
                  </template>
                </el-popconfirm>
                <el-button link type="success" v-if="scope.row.essence=='0'" size="small" @click="essence(scope.row)">设为精华</el-button>
                <el-button link type="success" v-if="scope.row.essence=='1'" size="small" @click="notessence(scope.row)">取消精华</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!---分页--->
          <div class="changePage">
            <el-pagination
                @current-change="handleCurrentChange"
                :current-page.sync="pages.currentPage"
                :page-size="pages.pageSize"
                layout="prev, pager, next, jumper"
                :total="pages.total"
            />
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import {computed, reactive, toRefs,watch} from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import request from'@/request/request'
import {useWebStore} from '@/stores/web'

const webStore = useWebStore()

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const formInline = reactive({
  title:'',
  context:'',
  name:'',
  date: '',
  isEssence:'',
  registerTime:'',
  tableData:[],
})
const pages = reactive({
  currentPage: 1, // 当前页码
  pageSize: 10, // 每页显示的条目数
  total: 100, // 总条目数
});
const viewDetails = (url) => {
  window.open(url, '_blank');
};
// 监听页码变化
watch(() => pages.currentPage, (newPage) => {
  // 这里可以请求数据或使用计算属性更新数据
  // 例如: fetchData(newPage);
});

// 分页变化事件处理
const handleCurrentChange = (newPage) => {
  pages.currentPage = newPage;
  load();
};
const load=()=>{
  request.get(`/articles/club/${webStore.web.clubId}`,{
    params:{
      pageNo:pages.currentPage,
      pageSize:pages.pageSize
    }
  }).then(res=>{
    formInline.tableData=res.data.data.records
  })
}
load()
const onSubmit = () => {
  console.log('submit!')
}
// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return formInline.tableData.filter(item => {
    if (formInline.isEssence && !item.isEssence.includes(formInline.isEssence)) {
      return false;
    }
    if (formInline.registerTime && item.registerTime !== formInline.registerTime) {
      return false;
    }
    return true;
  });
});

const isEssences=[
  {
    value:'普通'
  },
  {
    value: '精华'
  }
]



const addTypeDialogVisible = ref(false);
addTypeDialogVisible.value = false;

const cancel = (row) => {
  request.delete(`/articles/delete/${row.id}`).then(res=>{
    if(res.data.code=='200'){
      load()
      ElMessage.success('删除成功')
    }else ElMessage.error(res.data.msg)
  })
};

const essence=(row)=>{
  request.put(`/articles/setet/${row.id}`).then(res=>{
    if(res.data.code=='200'){
      load()
      ElMessage.success('设置成功')
    }else ElMessage.error(res.data.msg)
  })
}

const notessence =(row)=>{
  request.put(`/articles/setef/${row.id}`).then(res=>{
    if(res.data.code=='200'){
      load()
      ElMessage.success('取消成功')
    }else ElMessage.error(res.data.msg)
  })
}
import { InfoFilled } from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
</script>

<style scoped>
.demo-basic {
  text-align: center;
}
.demo-basic .sub-title {
  margin-bottom: 10px;
  font-size: 18px;
  color: var(--el-text-color-secondary);
}
.demo-basic .demo-basic--circle{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 50px;
  margin-bottom: 20px;
}
.demo-basic .block {
  flex: 1;
}
.aside{
  width:200px;
  height:750px;
  background-color: #f6f1ea;
}
.menu-list{
  margin-left: 15px;
}
.header{
  background: rgba(90, 122, 154, 0.15);
  height:80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.demo-form-inline .el-input {
  --el-input-width:120px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
.changePage{
  margin-top: 50px;
  position: absolute;
  right: 50px;
}
.common-layout{
  background: rgba(153, 217, 202, 0.08);
}
.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: stretch;
}
.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
}
.line {
  width: 1px;
  background-color: var(--el-border-color);
}
</style>