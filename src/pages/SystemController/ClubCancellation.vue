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
            <el-menu-item index="1">
              <el-icon><icon-menu /></el-icon>
              <span >首页</span>
            </el-menu-item>
            <router-link to="/">
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
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>成员审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/PostR">
              <el-menu-item index="6">
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
          <span class="text-large font-600 mr-3"><h2>社团注销</h2></span>
        </el-header>
        <!---主界面--->
        <el-main>
          <!---搜索框--->
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="学院">
              <el-select
                  v-model="formInline.collage"
                  placeholder="计算机学院"
                  clearable
              >
                <el-option label="计算机学院" value="计算机学院" />
                <el-option label="哲学学院" value="哲学学院" />
              </el-select>
            </el-form-item>
            <el-form-item label="类别">
              <el-select
                  v-model="formInline.tags"
                  placeholder="创新创业类"
                  clearable
              >
                <el-option label="思想政治类" value="0" />
                <el-option label="学术科技类" value="1" />
                <el-option label="文化体育类" value="2" />
                <el-option label="创新创业类" value="3" />
                <el-option label="志愿公益类" value="4" />
                <el-option label="自律互助类" value="5" />
              </el-select>
            </el-form-item>
            <el-form-item label="审核状态">
              <el-select
                  v-model="formInline.status"
                  placeholder="未审核"
                  clearable
              >
                <el-option label="未审核"    value="0" />
                <el-option label="审核通过"   value="1" />
                <el-option label="审核不通过" value="2" />
              </el-select>
            </el-form-item>
            <el-form-item >
              <el-input v-model="formInline.name" placeholder="社团名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">

            <el-table-column fixed prop="name" label="社团名称"  width="150" />
            <el-table-column prop="tags" label="类别" width="120" >
              <template #default="scope">
                <!-- 根据 tags 的值显示不同的类别名称 -->
                <span v-if="scope.row.tags === '1'">学术科技类</span>
                <span v-if="scope.row.tags === '3'">创新创业类</span>
                <span v-if="scope.row.tags === '0'">思想政治类</span>
                <span v-if="scope.row.tags === '2'">文化体育类</span>
                <span v-if="scope.row.tags === '4'">志愿公益类</span>
                <span v-if="scope.row.tags === '5'">自律互助类</span>
              </template>
            </el-table-column>
            <el-table-column prop="collage" label="学院" width="120" />
            <el-table-column prop="pname" label="负责人" width="120" />
            <el-table-column prop="department" label="负责人学院" width="120" />
            <el-table-column prop="status" label="审核状态" width="120" >
            <template #default="scope">
              <span v-if="scope.row.status === '3'">未审核</span>
              <span v-if="scope.row.status === '4'">审核通过</span>
              <span v-if="scope.row.status === '6'">审核不通过</span>
            </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="handleClick(scope.row)"  v-if="scope.row.status==='3'">
                  同意
                </el-button>
                <el-button link type="primary" size="small" @click="rejectClick(scope.row)" v-if="scope.row.status==='3'">拒绝</el-button>
                <el-button link type="primary" size="small" v-if="scope.row.status==='4'">已同意</el-button>
                <el-button link type="primary" size="small" v-if="scope.row.status==='6'">已拒绝</el-button>
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
import {computed, onMounted, reactive, toRefs, watch} from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import axios from "axios";

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const formInline = reactive({
  id:'',
  collage: '',
  status:'',
  tags:'',
  name:''
})

const onSubmit = () => {
  console.log('submit!')
}

// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return tableData.filter(item => {
    // 如果输入社团名称，也进行名称筛选
    if (formInline.name && !item.name.includes(formInline.name)) {
      return false;
    }
    // 如果选择了学院，只显示该学院的社团
    if (formInline.collage && item.collage !== formInline.collage) {
      return false;
    }
    // 如果选择了类别，只显示该类别的社团
    if (formInline.tags && item.tags !== formInline.tags) {
      return false;
    }
    //审核状态
    if (formInline.status && item.status !== formInline.status) {
      return false;
    }
    return item.status === '3' || item.status === '4'||item.status === '6';
    return true;
  });
});

//审核同意
async function handleClick(row) {
  // 通过 row 来获取当前行数据
  if (row.status === '3') {
    try {
      // 将当前行的审核状态改为 '已审核'
      row.status = '4'; // 更新状态
      console.log('状态更新为已审核：', row);

      // 发送 PUT 请求到服务器更新数据
      const response = await axios({
        url: `http://localhost:8080/club/audit/${encodeURIComponent(row.id)}?status=4`,
        method: 'put',
      });
      // 请求成功，处理响应
      console.log('状态更新成功：', response.data);
    } catch (error) {
      // 请求失败，处理错误
      console.error('状态更新失败：', error.response.data);
    }
    const response = await axios({
      url: `http://localhost:8080/club/delete/${encodeURIComponent(row.id)}`,
      method: 'delete',
    });
    console.log('状态更新成功：', response.data);
  }
}
//审核拒绝
async function rejectClick(row) {
  //通过 row 来获取当前行数据
  if (row.status === '3') {
    try {
      // 将当前行的审核状态改为 '已审核'
      row.status = '6'; // 更新状态
      console.log('状态更新为已审核：', row);

      // 发送 PUT 请求到服务器更新数据
      const response = await axios({
        url: `http://localhost:8080/club/audit/${encodeURIComponent(row.id)}?status=6`,
        method: 'put',
      });
      // 请求成功，处理响应
      console.log('状态更新成功：', response.data);
    } catch (error) {
      // 请求失败，处理错误
      console.error('状态更新失败：', error.response.data);
    }
  }
}

//初始数据
const tableData = reactive([])
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
async function getList() {
  try {
    // 第一次调用：获取社团列表数据
    const clubRes = await axios.get('http://localhost:8080/club/list');
    const clubRecords = clubRes.data.data.records;
    // 将社团列表数据存储到 tableData
    tableData.splice(0, tableData.length, ...clubRecords);
    console.log('第一次获取的社团数据:', tableData);

    // 异步函数数组，用于存储第二次调用的 Promise
    const userPromises = clubRecords.map(record => {
      // 为每个社团的 CreateUserId 调用第二个接口
      return axios.get(`http://localhost:8080/user/getInfo/${record.createUserId}`);
    });

    // 等待所有第二次调用完成
    const userResponses = await Promise.all(userPromises);

    // 根据 CreateUserId 将用户信息与社团数据合并
    userResponses.forEach((response, index) => {
      const userInfo = response.data; // 获取用户信息
      const club = tableData.find(item => item.createUserId === clubRecords[index].createUserId);
      if (club) {
        // 假设用户信息存储在一个新的字段中，例如 creatorInfo
        club.pname = userInfo.data.name;
        club.department=userInfo.data.department;
        club.status+='';
      }
    });

    console.log('更新后的 tableData:', tableData);
  } catch (error) {
    console.error("Error fetching data: ", error);
  }
}
onMounted(getList);
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