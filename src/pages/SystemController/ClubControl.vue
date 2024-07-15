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
                <router-link to="/">
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
              <span class="text-large font-600 mr-3"><h2>社团信息管理</h2></span>
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
            <el-form-item >
              <el-input v-model="formInline.name" placeholder="社团名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">
            <el-table-column fixed prop="name"  label="社团名称"  width="150" />
            <el-table-column label="社团头像" width="150">
              <template #default="{ row }">
                <!-- 使用作用域插槽的 row 参数来访问 proPost 属性 -->
                <el-image
                    style="width: 100px; height: 100px"
                    :src="row.avatarUrl"
                fit="cover"
                ></el-image>
              </template>
            </el-table-column>
            <el-table-column prop="tags"  label="类别" width="120" >
              <template #default="scope">
                <!-- 根据 tags 的值显示不同的类别名称 -->
                <span v-if="scope.row.tags === '1'">学术科技类</span>
                <span v-if="scope.row.tags === '3'">创新创业类</span>
                <span v-if="scope.row.tags === '0'">思想政治类</span>
                <span v-if="scope.row.tags === '2'">文化体育类</span>
                <span v-if="scope.row.tags === '4'">志愿公益类</span>
                <span v-if="scope.row.tags === '5'">自律互助类</span>
                <!-- 如果需要，可以添加更多的条件分支 -->
              </template>
            </el-table-column>
            <el-table-column prop="collage" label="学院" width="120" />
            <el-table-column prop="pname" label="负责人" width="120" />
            <el-table-column prop="department" label="负责人学院" width="120" />
            <el-table-column fixed="right" label="社团变更信息" min-width="120">
              <template #default="scope">
                <el-button plain @click="openEditDialog(scope.row)">
                  修改社团信息
                </el-button>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="社团注销" min-width="120">
              <template #default="scope">
                <el-popconfirm
                    confirm-button-text="Yes"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title=" 确认要注销社团吗？"
                    @confirm="cancel(scope.row)"
                >
                  <template #reference>
                    <el-button plain >
                      注销社团
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
          <!---查看详情--->
          <el-dialog v-model="dialogVisible" title="社团信息情况" width="500" draggable>
            <el-form :model="form" label-width="auto" style="max-width: 600px">
              <el-form-item label="社团名称">
                <el-input v-model="form.name" placeholder=""/>
              </el-form-item>
              <el-form-item label="社团头像">
                <div class="demo-image__preview">
                  <el-image
                      style="width: 100px; height: 100px"
                      :src="form.avatarUrl"
                      :zoom-rate="1.2"
                      :max-scale="7"
                      :min-scale="0.2"
                      :initial-index="4"
                      fit="cover"
                  />
                </div>
              </el-form-item>
              <el-form-item label="学院">
                <el-select v-model="form.college">
                <el-option label="计算机学院" value="计算机学院"/>
                <el-option label="哲学学院" value="哲学学院" />
                </el-select>
              </el-form-item>
              <el-form-item label="负责人">
                <el-input v-model="form.pname"/>
              </el-form-item>
              <el-form-item label="负责人学院">
                <el-select v-model="form.college">
                <el-option label="计算机学院" value="计算机学院"/>
                <el-option label="哲学学院" value="哲学学院" />
                </el-select>
              </el-form-item>
              <el-form-item  label="社团简介">
                <el-input v-model="form.introduce"  />
              </el-form-item>
            </el-form>
            <template #footer>
              <div class="dialog-footer">
                <el-popconfirm
                    confirm-button-text="Yes"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认要修改吗？"
                    @confirm="confirmUpdate"
                >
                  <template #reference>
                    <el-button type="primary" >
                      修改
                    </el-button>
                  </template>
                </el-popconfirm>
              </div>
            </template>
          </el-dialog>
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
import { ArrowLeft } from '@element-plus/icons-vue'
import { nextTick } from 'vue';

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
    return item.status !== '2';
    return true;
  });
});


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
  getList()
};

// 假设的请求数据方法，需要根据实际情况实现
// const fetchData = (page) => {
//   // 根据 page 请求数据
// };

// 假设初始加载第一页数据
// fetchData(state.currentPage);
import { ref } from 'vue'
const dialogVisible = ref(false)
const form = reactive({
  name: '',
  avatarUrl:'',
  college:'',
  pname: '',
  department:'',
  introduce:'',
  tags:'',
  id:''
})



// 用于存储当前正在编辑的社团的索引
const currentEditingIndex = ref(-1);
const index = ref(-1);

// 打开编辑对话框并设置表单数据的方法
const openEditDialog = (row) => {
  // 将当前行的数据同步到 form 中
  form.id=row.id
  form.name = row.name;
  form.pname = row.pname;
  form.college = row.collage;
  form.department = row.department;
  form.introduce = row.introduce;
  form.tags = row.tags; // 确保这里的值与el-select绑定的v-model匹配
  // 如果需要显示头像，也更新头像的 URL
  form.avatarUrl = row.avatarUrl;
  // 记录当前编辑的社团索引
  currentEditingIndex.value = row.name;
  index.value = tableData.findIndex(item => item.name === row.name);
  if (index.value !== -1) {
    // 记录当前编辑的社团索引
    currentEditingIndex.value = index.value;
  } else {
    console.error('未找到对应的社团');
    return;
  }
  // 打开对话框
  dialogVisible.value = true;
};
//确认修改
watch(() => pages.currentPage, (newPage) => {
  // 分页逻辑
});

import { InfoFilled } from '@element-plus/icons-vue'
import axios from "axios";
import {onMounted} from "vue";
import request from '@/request/request'
import {ElMessage} from "element-plus";


// 确认修改社团信息的方法
const confirmUpdate = () => {
  request.post('/club/update/direct',form).then(res=>{
    if(res.data.code=='200'){
      getList()
      ElMessage.success('修改成功')
    }else {
      ElMessage.error(res.data.msg)
    }
  })
  // 确保 currentEditingIndex 已经在 openEditDialog 中设置
  // 获取当前编辑的社团对象
 /* let club = tableData[index.value];
  // 只更新 form 中存在的字段到 club
   club.id=tableData[index.value].id;
   club.clubName = form.ClubName;
   club.college = form.College;
   club.President = form.President;
   club.preCollege = form.PresidentCollege;
   club.profile = form.Profile;
   club.category = form.Type;
   club.proPost = form.proPost;*/

  var data = {
    id: tableData[index.value].id,
    name: tableData[index.value].name,
    introduce: tableData[index.value].introduce,
    createUserId: tableData[index.value].createUserId,
    avatarUrl: tableData[index.value].avatarUrl,
    tags: tableData[index.value].tags,
    collage: tableData[index.value].collage
  };

  // 关闭对话框
  dialogVisible.value = false;
  // 重置当前编辑的索引（如果需要）
  currentEditingIndex.value = -1;
};
// 注销社团的方法
async function cancel (row) {
  const response = await axios({
    url: `http://localhost:8080/club/delete/${encodeURIComponent(row.id)}`,
    method: 'delete',
  });
  await getList();
  console.log('状态更新成功：', response.data);
};

async function getList() {
  try {
    // 第一次调用：获取社团列表数据
    const clubRes = await axios.get('http://localhost:8080/club/list',{
      params:{
        pageNo:pages.currentPage,
        pageSize:pages.pageSize
      }
    });
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
        club.avatarUrl=userInfo.data.avatarUrl;
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
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}
</style>