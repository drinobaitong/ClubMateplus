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
          <span class="text-large font-600 mr-3"><h2>成员管理 </h2></span>
        </el-header>
        <!---主界面--->
        <el-main>
          <!---搜索框--->
          <el-form :inline="true" :model="data.formInline" class="demo-form-inline">
            <el-form-item label="学院">
              <el-select
                  v-model="data.formInline.college"
                  placeholder="计算机学院"
                  clearable
              >
                <el-option
                    v-for="item in data.department"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="职位">
              <el-select
                  v-model="data.formInline.rank"
                  placeholder="成员"
                  clearable
              >
                <el-option
                    v-for="item in positionList"
                    :label="item.position"
                    :value="item.rank"
                />
              </el-select>
            </el-form-item>
            <el-form-item >
              <el-input v-model="data.formInline.name" placeholder="成员名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="warning" @click="addTypeDialogVisible = true" >增加</el-button>

              <el-dialog
                  v-model="addTypeDialogVisible"
                  title="增加社团成员"
                  width="30%"
              >
                <el-form :model="newType" label-width="100px">
                  <el-form-item label="姓名">
                    <el-input v-model="newType.name" />
                  </el-form-item>
                  <el-form-item label="学号">
                    <el-input v-model="newType.Sno" />
                  </el-form-item>
                  <el-form-item label="学院">
                    <el-select
                        v-model="newType.college"
                        placeholder="计算机学院"
                        clearable
                    >
                      <el-option
                          v-for="item in data.department"
                          :key="item.name"
                          :label="item.name"
                          :value="item.name"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="职位">
                    <el-select
                        v-model="newType.rank"
                        placeholder="成员"
                        clearable
                    >
                      <el-option
                          v-for="item in positionList"
                          :label="item.position"
                          :value="item.rank"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="电话号码">
                    <el-input v-model="newType.phone" />
                  </el-form-item>
                  <el-form-item label="政治面貌">
                    <el-input v-model="newType.politicalAffiliation" />
                  </el-form-item>
                  <el-form-item label="年级">
                    <el-input v-model="newType.grade" />
                  </el-form-item>
<!--                  <el-form-item label="加入时间">
                    <el-input v-model="newType.date" />
                  </el-form-item>-->
                </el-form>
                <template #footer>
                  <el-button @click="addTypeDialogVisible = false">取消</el-button>
                  <el-button  type="primary" @click="addType">确认</el-button>
                </template>
              </el-dialog>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">
            <el-table-column prop="rank" label="职位" width="120" >
              <template v-slot="scope">{{positionList[scope.row.rank].position}}</template></el-table-column>
            <el-table-column prop="user.sno" label="学号" width="220" />
            <el-table-column prop="user.name" label="姓名" width="120" />
            <el-table-column prop="user.department" label="学院" width="120" />
            <el-table-column prop="user.phone" label="联系电话" width="120" />
            <el-table-column prop="user.politicalAffiliation" label="政治面貌" width="120" />
            <el-table-column prop="user.grade" label="年级" width="120"></el-table-column>
            <el-table-column prop="joinTime" label="申请时间" width="120" />
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-popconfirm
                    confirm-button-text="Yes"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认要移除此成员吗?"
                    prop="userId"
                    @confirm="cancel(scope.row,scope.row.userId)"
                >
                  <template #reference>
                    <el-button  plain link type=danger size="small" >
                      移除
                    </el-button>
                  </template>
                </el-popconfirm >
                <el-button link type="success" @click="openEditDialog(scope.row)" size="small" prop="userId">更改职位</el-button>
                <!-- 修改社团类型的对话框 -->
                <el-dialog
                    v-model="editDialogVisible"
                    title="修改社团类型"
                    width="30%"
                >
                  <el-form :model="editCategoryForm" label-width="100px">
                    <el-form-item label="成员职位">
                      <el-select
                          v-model="editCategoryForm.rank"
                          placeholder="成员"
                          clearable
                      >
                        <el-option
                            v-for="item in positionList"
                            :label="item.position"
                            :value="item.rank"
                        />
                      </el-select>
                    </el-form-item>
                  </el-form>
                  <template #footer >
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="confirmEdit(scope.row.userId)">确认修改</el-button>
                  </template>
                </el-dialog>
              </template>
            </el-table-column>
          </el-table>
          <!---分页--->
          <div class="changePage">
            <el-pagination
                @current-change="handleCurrentChange"
                :current-page.sync="data.currentPage"
                :page-size="data.pageSize"
                layout="prev, pager, next, jumper"
                :total="data.total"
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
import request from '@/request/request';
import {useWebStore} from '@/stores/web'

const webStore = useWebStore()

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const data= reactive({
  formInline:{
    name:'',
    rank:'',
    college: '',
  },
  currentPage: 1, // 当前页码
  pageSize: 10, // 每页显示的条目数
  total: 100,
  members:[],
  department:[]
})

const onSubmit = () => {
  console.log('submit!')
}

const load=()=>{
  request.get(`/club/users/${webStore.web.clubId}`,{
    params:{
      pageNo:data.currentPage,
      pageSize:data.pageSize
    }
  }).then(res=>{
    console.log(res)
    data.members=res.data.data.records
    console.log(data.members)
  })
  request.get('/department/getAll').then(res=>{
    console.log(res)
    data.department=res.data.data
  })
}
load()
// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return data.members.filter(item => {
    // 如果输入成员名称，也进行名称筛选
    if (data.formInline.name && !item.name.includes(data.formInline.name)) {
      return false;
    }
    // 如果选择了学院，只显示该学院的社团
    if (data.formInline.college && item.college !== data.formInline.college) {
      return false;
    }
    //职位
    if (data.formInline.rank && item.rank !== data.formInline.rank) {
      return false;
    }
    return true;
  });
});

const positionList=[
    {
  rank:0,
  position:'成员'
  },
  {
    rank:1,
    position: '管理员'
  },
  ]

// 监听页码变化
watch(() => data.currentPage, (newPage) => {
  // 这里可以请求数据或使用计算属性更新数据
  // 例如: fetchData(newPage);
});

// 分页变化事件处理
const handleCurrentChange = (newPage) => {
  data.currentPage = newPage;
  load();
};

// 假设的请求数据方法，需要根据实际情况实现
// const fetchData = (page) => {
//   // 根据 page 请求数据
// };

// 假设初始加载第一页数据
// fetchData(state.currentPage);
// 新社团成员的表单数据
const newType = reactive({
  Sno:'',
  name:'',
  rank:'',
  college: '',
  phone:'',
  politicalAffiliation:'',
  grade:'',
  //date: '',
  userId:''
});
const addTypeDialogVisible = ref(false);
addTypeDialogVisible.value = false;
// 处理点击确认后增加社团类型的逻辑
const addType = () => {
  if (!newType.Sno.trim()) {
    // 输入为空，可以提示用户或不做任何操作
    return;
  }
  // 确保不添加重复的社团类型
  const exists = data.members.some(item => item.Sno === newType.Sno);
  if (exists) {
    // 可以提示用户社团类型已存在
    return;
  }
  request.get("/user/getBySno",{
    params:{
      sno:newType.Sno
    }
  }).then(res=>{
    newType.userId=res.data.data.id
    request.post("/join",newType,{
      params:{
        clubId:webStore.web.clubId,
        userId:res.data.data.id
      }
    }).then(re=>{
      if(re.data.code==='200'){
        load()
        ElMessage.success('添加成功')
      }else {
        ElMessage.error('添加失败')
      }
    })
  })

  addTypeDialogVisible.value = false;
}

// 修改对话框状态
const editDialogVisible = ref(false);
// 注销类型的方法
const cancel = (row, userId) => {
  // 找到要删除的成员的索引
  const rowIndex = data.members.findIndex(item => item.Sno === row.Sno);
  if (rowIndex !== -1&&row.position!=='负责人') {
    // 从 tableData 中删除对应的社团
    //data.members.splice(rowIndex, 1);
    request.delete('/join/quit',{
      params:{
        clubId:webStore.web.clubId,
        userId:userId
      }
    }).then(res=>{
      if(res.data.code=='200'){
        load()
        ElMessage.success("成功删除")
      }else ElMessage.error("删除失败")
    });
    // 更新分页总数
    data.total = data.members.length;
    // 如果需要，可以在这里处理分页状态的更新
    if (data.total < data.pageSize * data.currentPage) {
      data.currentPage = Math.max(1, data.currentPage - 1);
    }
  } else {
    console.error('未找到对应的类型');
  }
};
// 存储要编辑的社团类型
const editCategoryForm = reactive({ rank: '' });
// 用于存储当前正在编辑的社团的索引
const currentEditingIndex = ref(-1);
const index = ref(-1);
// 打开修改对话框并设置要编辑的社团类型
const openEditDialog = (row) => {
  editCategoryForm.rank = row.rank; // 设置要编辑的社团类型
  editDialogVisible.value = true; // 打开对话框
  index.value = data.members.findIndex(item => item.position === row.position);
};
import { InfoFilled } from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
// 确认修改社团类型的逻辑
const confirmEdit = (userId) => {
  // 找到要修改的社团类型在 tableData 中的索引

  let member={

  }
  console.log(member);
  request.post('/join/rank',member,{
    params:{
      userId:userId,
      clubId:webStore.web.clubId,
      rank:editCategoryForm.rank
    }
      }
  ).then(res=>{
    if(res.data.code=='200') {
      load();
      ElMessage.success('修改成功')
    }
    else ElMessage.error('修改失败')
  })
  editDialogVisible.value = false; // 关闭对话框
  currentEditingIndex.value = -1;
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