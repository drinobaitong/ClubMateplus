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
              <span class="text-large font-600 mr-3"><h2>评论管理</h2></span>
        </el-header>
        <!---主界面--->
        <el-main>
          <!---搜索框--->
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item >
              <el-input v-model="formInline.user"  placeholder="搜索用户" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">
            <el-table-column prop="username" label="用户" width="220" ></el-table-column>
            <el-table-column prop="registerTime" label="评论时间" width="220" />
            <el-table-column prop="content" label="评论内容" width="420" />
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-popconfirm
                    confirm-button-text="Yes"
                    cancel-button-text="No"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认要删除吗"
                    @confirm="cancel(scope.row)"
                >
                  <template #reference>
                    <el-button plain >
                      删除
                    </el-button>
                  </template>
                </el-popconfirm>
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
import request from '@/request/request'
import {useWebStore} from '@/stores/web'

const webStore = useWebStore()

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const formInline = reactive({
  clubName:'',
  college: '',
  category: '',
  state:'',
  user:'',
  tableData:[{
    id:'',
    content:'',
    createUserId:'',
    articleId:'',
    username:'',
  }]
})

const onSubmit = () => {
  console.log('submit!')
}

// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return formInline.tableData.filter(item => {
    // 如果输入成员名称，也进行名称筛选
    if (formInline.user && !item.username.includes(formInline.user)) {
      return false;
    }
    return true;
  });
});
import { InfoFilled } from '@element-plus/icons-vue'


//初始数据
const pages = reactive({
  currentPage: 1, // 当前页码
  pageSize: 10, // 每页显示的条目数
  total: 100, // 总条目数
});

// 监听页码变化
watch(() => pages.currentPage, (newPage) => {
  // 这里可以请求数据或使用计算属性更新数据
  // 例如: fetchData(newPage);
});

// 分页变化事件处理
const handleCurrentChange = (newPage) => {
  pages.currentPage = newPage;

};

import { ref } from 'vue'
import {ElMessage} from "element-plus";
const dialogVisible = ref(false)


const load= ()=>{
  request.get(`/comments/club/${webStore.web.clubId}`).then(res=>{
    formInline.tableData = res.data.data;
  })
  console.log(formInline.tableData)
}
load()
const url =
    'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'

// 用于存储当前正在编辑的社团的索引
const currentEditingIndex = ref(-1);
const index = ref(-1);

// 注销成员的方法
const cancel = (row) => {
  // 找到要删除的社团的索引
  request.delete(`/comments/delete/${row.id}`).then(res=>{
    if(res.data.code=='200'){
      load()
      ElMessage.success('删除成功')
    }else{
      ElMessage.error(res.data.msg)
    }
  })
};
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
</style>