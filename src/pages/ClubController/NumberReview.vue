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
          <span class="text-large font-600 mr-3"><h2>成员审核 </h2></span>
        </el-header>
        <!---主界面--->
        <el-main>
          <!---搜索框--->
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="学院">
              <el-select
                  v-model="formInline.college"
                  placeholder="计算机学院"
                  clearable
              >
                <el-option
                    v-for="item in formInline.department"
                    :label="item.name"
                    :value="item.name"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="审核状态">
              <el-select
                  v-model="formInline.state"
                  placeholder="未审核"
                  clearable
              >
                <el-option
                    v-for="item in states"
                    :label="item.value"
                    :value="item.status"
                />
              </el-select>
            </el-form-item>
            <el-form-item >
              <el-input v-model="formInline.name" placeholder="成员名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">
            <el-table-column prop="user.sno" label="学号" width="220" />
            <el-table-column prop="user.name" label="姓名" width="120" />
            <el-table-column prop="user.department" label="学院" width="120" />
            <el-table-column prop="user.phone" label="联系电话" width="120" />
            <el-table-column prop="user.politicalAffiliation" label="政治面貌" width="120" />
            <el-table-column prop="user.grade" label="年级" width="120" />
            <el-table-column prop="joinTime" label="申请时间" width="120" />
            <el-table-column prop="status" label="审核状态" width="120" >
              <template v-slot="scope">{{states[scope.row.status].value}}</template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="handleClick(scope.row)"  v-if="scope.row.status=='0'">
                  同意
                </el-button>
                <el-button link type="primary" size="small" @click="rejectClick(scope.row)" v-if="scope.row.status=='0'">拒绝</el-button>
                <el-button link type="primary" size="small" v-if="scope.row.status=='2'">已同意</el-button>
                <el-button link type="primary" size="small" v-if="scope.row.status=='1'">已拒绝</el-button>
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
import {ElMessage} from "element-plus";
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
  name:'',
  tableData:[],
  department:[]
})
const pages = reactive({
  currentPage: 1, // 当前页码
  pageSize: 10, // 每页显示的条目数
  total: 100, // 总条目数
});

// 监听页码变化
watch(() => pages.currentPage, (newPage) => {
});

// 分页变化事件处理
const handleCurrentChange = (newPage) => {
  pages.currentPage = newPage;
  load()
};

const states=[
  {
    status:0,
    value:'未审核'
  },
  {
    status: 1,
    value:'拒绝'
  },
  {
    status: 2,
    value: '已审核'
  }
]

const onSubmit = () => {
  console.log('submit!')
}

// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return formInline.tableData.filter(item => {
    // 如果输入社团名称，也进行名称筛选
    if (formInline.name && !item.name.includes(formInline.name)) {
      return false;
    }
    // 如果选择了学院，只显示该学院的社团
    if (formInline.college && item.college !== formInline.college) {
      return false;
    }
    //审核状态
    if (formInline.state && item.status !== formInline.state) {
      return false;
    }
    return true;
  });
});

const load =()=>{
  request.get(`/join/users/${webStore.web.clubId}`,{
    params:{
      pageNo:pages.currentPage,
      pageSize:pages.pageSize
    }
  }).then(res=>{
    formInline.tableData=res.data.data.records
    console.log(formInline.tableData)
  })
  request.get('/department/getAll').then(res=>{
    console.log(res)
    formInline.department=res.data.data
  })
}
load();
//审核同意
const handleClick = (row) => {
  //通过 row 来获取当前行数据
  request.put('/join/audit',{},{
    params:{
      clubId:webStore.web.clubId,
      userId:row.userId,
      status:2
    }
  }).then(res=>{
    if(res.data.code=='200'){
      load();
      ElMessage.success('同意加入')
    }else ElMessage.error(res.data.msg)
  })
};
//审核拒绝
const rejectClick = (row) => {
  //通过 row 来获取当前行数据
  request.put('/join/audit',{},{
    params:{
      clubId:webStore.web.clubId,
      userId:row.userId,
      status:1
    }
  }).then(res=>{
    if(res.data.code=='200'){
      load();
      ElMessage.success('拒绝加入')
    }else ElMessage.error(res.data.msg)
  })
};
//永久同意
const reAgreeClick = (row) => {
  row.flag='';
}
//永久拒绝
const reForeverClick = (row) => {
  row.flag='';
}
//未审核
const recoverClick = (row) => {
  //通过 row 来获取当前行数据
  if (row.status === '2'||row.status==='3') {
    // 将当前行的审核状态改为 '已审核'
    row.status = '0';
    row.flag='';
    console.log('状态更新为已审核：', row);
  }
  // 可以添加更多的逻辑来处理其他状态的变更
};

//初始数据

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