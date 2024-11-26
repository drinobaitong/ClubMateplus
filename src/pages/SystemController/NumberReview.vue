<template>
  <div class="common-layout">
    <!---侧边栏--->
    <el-container>
      <el-aside class="aside" >
        <!---切换--->
        <el-menu
            background-color="#545c64"
            class="el-menu-vertical-demo"
            text-color="#fff"
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
              <router-link to="/Chart">
            <el-menu-item index="1">
              <el-icon><icon-menu /></el-icon>
              <span >首页</span>
            </el-menu-item>
              </router-link>
            <router-link to="/ClubReview">
              <el-menu-item index="2" >
                <el-icon><document /></el-icon>
                <span>社团审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/Cancel">
              <el-menu-item index="3">
                <el-icon><setting /></el-icon>
                <span>社团注销</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ChangeN">
              <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>社团信息变更</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ClubControl">
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>社团信息管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/NumberR">
              <el-menu-item index="6">
                <el-icon><setting /></el-icon>
                <span>成员审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/PostR">
              <el-menu-item index="7">
                <el-icon><setting /></el-icon>
                <span>社团发帖审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ContentControl">
              <el-menu-item index="8">
                <el-icon><setting /></el-icon>
                <span>社团评论管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/TypeControl">
              <el-menu-item index="9">
                <el-icon><setting /></el-icon>
                <span>社团类型管理</span>
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
                <el-option label="计算机学院" value="计算机学院" />
                <el-option label="哲学学院" value="哲学学院" />
              </el-select>
            </el-form-item>
            <el-form-item label="审核状态">
              <el-select
                  v-model="formInline.state"
                  placeholder="未审核"
                  clearable
              >
                <el-option label="未审核" value="未审核" />
                <el-option label="已审核" value="已审核" />
              </el-select>
            </el-form-item>
            <el-form-item >
              <el-input v-model="formInline.clubName" placeholder="社团名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">
            <el-table-column fixed prop="clubName" label="社团名称"  width="180" />
            <el-table-column prop="Sno" label="学号" width="240" />
            <el-table-column prop="name" label="姓名" width="120" />
            <el-table-column prop="college" label="学院" width="150" />
            <el-table-column prop="phoneNumber" label="联系电话" width="240" />
            <el-table-column prop="polOutlook" label="政治面貌" width="120" />
            <el-table-column prop="date" label="申请时间" width="180" />
            <el-table-column prop="state" label="审核状态" width="120" />
            <el-table-column fixed="right" label="操作" min-width="150">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="handleClick(scope.row)"  v-if="scope.row.state==='未审核'">
                  同意
                </el-button>
                <el-button link type="primary" size="small" @click="rejectClick(scope.row)" v-if="scope.row.state==='未审核'">拒绝</el-button>
                <el-button link type="primary" size="small" v-if="scope.row.state==='已审核'&&scope.row.flag==='0'">已同意</el-button>
                <el-button link type="primary" size="small" v-if="scope.row.state==='已审核'&&scope.row.flag==='1'">已拒绝</el-button>
                <el-button link type="primary" size="small" @click="recoverClick(scope.row)" v-if="scope.row.state==='已审核'&&(scope.row.flag==='0'||scope.row.flag==='1')">还原</el-button>
                <el-button link type="primary" size="small" @click="reAgreeClick(scope.row)" v-if="scope.row.state==='已审核'&&scope.row.flag==='0'">永久同意</el-button>
                <el-button link type="primary" size="small" @click="reForeverClick(scope.row)" v-if="scope.row.state==='已审核'&&scope.row.flag==='1'">永久拒绝</el-button>
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
import axios from 'axios';

const tableData = reactive([])

//获取所有待审核成员
async function getNumberList(){
  const response = await axios.get('http://localhost:8080/join/user/all')
  console.log('获取的待审核列表',response.data.data)
  for( let record of response.data.data){
     //获取社团详细信息
    const clubDetail = await axios.get(`http://localhost:8080/club/get/${record.clubId}`);
    console.log('社团详细信息',clubDetail)
    //获取申请人详细信息
    const personDetail = await axios.get(`http://localhost:8080/user/getInfo/${record.userId}`);
    console.log('申请人详细信息',personDetail)

    let tempstatus
    if(record.status === 0){
      tempstatus = '未审核'
    }
    else if(record.status === 1){
      tempstatus = '审核通过'
    }
    else{
      tempstatus = '审核未通过'
    }
    let piece = {
      clubName: clubDetail.data.data.name,
      Sno:personDetail.data.data.id,
      name:personDetail.data.data.name,
      college:personDetail.data.data.department,
      phoneNumber:personDetail.data.data.phone,
      polOutlook:personDetail.data.data.politicalAffiliation,
      date:record.joinTime,
      state:tempstatus
    }
    tableData.push(piece)
  }
}

getNumberList()


const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const formInline = reactive({
  clubName:'',//社团名称
  college: '',//
  category: '',//
  state:'',//
})

const onSubmit = () => {
  console.log('submit!')
}

// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return tableData.filter(item => {
    // 如果输入社团名称，也进行名称筛选
    if (formInline.clubName && !item.clubName.includes(formInline.clubName)) {
      return false;
    }
    // 如果选择了学院，只显示该学院的社团
    if (formInline.college && item.college !== formInline.college) {
      return false;
    }
    //审核状态
    if (formInline.state && item.state !== formInline.state) {
      return false;
    }
    return true;
  });
});

//审核同意
const handleClick = (row) => {
  //通过 row 来获取当前行数据
  if (row.state === '未审核') {
    // 将当前行的审核状态改为 '已审核'
    row.state = '已审核';
    row.flag='0';
    console.log('状态更新为已审核：', row);
  }
};
//审核拒绝
const rejectClick = (row) => {
  //通过 row 来获取当前行数据
  if (row.state === '未审核') {
    // 将当前行的审核状态改为 '已审核'
    row.state = '已审核';
    row.flag='1';
    console.log('状态更新为已审核：', row);
  }
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
  if (row.state === '已审核') {
    // 将当前行的审核状态改为 '已审核'
    row.state = '未审核';
    row.flag='';
    console.log('状态更新为已审核：', row);
  }
  // 可以添加更多的逻辑来处理其他状态的变更
};

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
  // 这里可以请求新页的数据
  // fetchData(newPage);
};

// 假设的请求数据方法，需要根据实际情况实现
// const fetchData = (page) => {
//   // 根据 page 请求数据
// };

// 假设初始加载第一页数据
// fetchData(state.currentPage);

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
  background-color:#545c64;
}
.menu-list{
  margin-left: 15px;
}
.header{
  background: rgba(83, 88, 93, 0.15);
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
  background: rgba(217, 153, 217, 0.08);
}
</style>