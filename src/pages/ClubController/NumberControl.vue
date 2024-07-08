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
            <el-form-item label="职位">
              <el-select
                  v-model="formInline.position"
                  placeholder="成员"
                  clearable
              >
                <el-option label="负责人" value="负责人" />
                <el-option label="管理员" value="管理员" />
                <el-option label="成员" value="成员" />
              </el-select>
            </el-form-item>
            <el-form-item >
              <el-input v-model="formInline.name" placeholder="成员名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="warning" @click="addTypeDialogVisible = true" >增加</el-button>

              <el-dialog
                  v-model="addTypeDialogVisible"
                  title="增加社团类型"
                  width="30%"
              >
                <el-form :model="newType" label-width="100px">
                  <el-form-item label="社团名">
                    <el-input v-model="newType.clubName" />
                  </el-form-item>
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
                      <el-option label="计算机学院" value="计算机学院" />
                      <el-option label="哲学学院" value="哲学学院" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="职位">
                    <el-select
                        v-model="newType.position"
                        placeholder="成员"
                        clearable
                    >
                      <el-option label="管理员" value="管理员" />
                      <el-option label="成员" value="成员" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="电话号码">
                    <el-input v-model="newType.phoneNumber" />
                  </el-form-item>
                  <el-form-item label="政治面貌">
                    <el-input v-model="newType.polOutlook" />
                  </el-form-item>
                  <el-form-item label="年级">
                    <el-input v-model="newType.grade" />
                  </el-form-item>
                  <el-form-item label="加入时间">
                    <el-input v-model="newType.date" />
                  </el-form-item>
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
            <el-table-column prop="position" label="职位" width="120" />
            <el-table-column prop="Sno" label="学号" width="220" />
            <el-table-column prop="name" label="姓名" width="120" />
            <el-table-column prop="college" label="学院" width="120" />
            <el-table-column prop="phoneNumber" label="联系电话" width="120" />
            <el-table-column prop="polOutlook" label="政治面貌" width="120" />
            <el-table-column prop="grade" label="年级" width="120" />
            <el-table-column prop="date" label="申请时间" width="120" />
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-popconfirm
                    confirm-button-text="Yes"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认要移除此成员吗?"
                    @confirm="cancel(scope.row)"
                >
                  <template #reference>
                    <el-button  plain link type=danger size="small" >
                      移除
                    </el-button>
                  </template>
                </el-popconfirm>
                <el-button link type="success" @click="openEditDialog(scope.row)" size="small">更改职位</el-button>
                <!-- 修改社团类型的对话框 -->
                <el-dialog
                    v-model="editDialogVisible"
                    title="修改社团类型"
                    width="30%"
                >
                  <el-form :model="editCategoryForm" label-width="100px">
                    <el-form-item label="成员职位">
                      <el-select
                          v-model="editCategoryForm.position"
                          placeholder="成员"
                          clearable
                      >
                        <el-option label="管理员" value="管理员" />
                        <el-option label="成员" value="成员" />
                      </el-select>
                    </el-form-item>
                  </el-form>
                  <template #footer>
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="confirmEdit">确认修改</el-button>
                  </template>
                </el-dialog>
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

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const formInline = reactive({
  clubName: '',
  Sno:'',
  name:'',
  position:'',
  college: '',
  phoneNumber:'',
  polOutlook:'',
  grade:'',
  date: '',
})

const onSubmit = () => {
  console.log('submit!')
}

// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return tableData.filter(item => {
    // 如果输入成员名称，也进行名称筛选
    if (formInline.name && !item.name.includes(formInline.name)) {
      return false;
    }
    // 如果选择了学院，只显示该学院的社团
    if (formInline.college && item.college !== formInline.college) {
      return false;
    }
    //职位
    if (formInline.position && item.position !== formInline.position) {
      return false;
    }
    return true;
  });
});

//初始数据
const tableData = [
  {
    clubName: '舞蹈队',
    Sno:'2022333333333',
    name:'张三',
    position:'负责人',
    college: '计算机学院',
    phoneNumber:'1234444444',
    polOutlook:'共产党员',
    grade:'2022级',
    date: '2022-3-4',
    flag:'',//拒绝1，同意0
  },
  {
    clubName: '舞蹈队',
    Sno:'2023333333333',
    name:'里斯本',
    position:'管理员',
    college: '哲学学院',
    phoneNumber:'1404444444',
    polOutlook:'群众',
    grade:'2023级',
    date: '2023-6-4',
  },
  {
    clubName: '舞蹈队',
    Sno:'2023333333333',
    name:'菲亚特',
    position:'成员',
    college: '哲学学院',
    phoneNumber:'1404443444',
    polOutlook:'群众',
    grade:'2024级',
    date: '2024-6-4',
  },
]
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
// 新社团成员的表单数据
const newType = reactive({
  clubName: '',
  Sno:'',
  name:'',
  position:'',
  college: '',
  phoneNumber:'',
  polOutlook:'',
  grade:'',
  date: '',
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
  const exists = tableData.some(item => item.Sno === newType.Sno);
  if (exists) {
    // 可以提示用户社团类型已存在
    return;
  }
  // 添加新社团类型
  tableData.push({
    clubName: newType.clubName,
    Sno: newType.Sno,
    college: newType.college,
    date: newType.date,
    grade: newType.grade,
    name: newType.name,
    phoneNumber: newType.phoneNumber,
    polOutlook: newType.polOutlook,
    position: newType.position
  });
  // 关闭对话框
  console.warn(tableData);
  addTypeDialogVisible.value = false;
}

// 修改对话框状态
const editDialogVisible = ref(false);
// 注销类型的方法
const cancel = (row) => {
  // 找到要删除的成员的索引
  const rowIndex = tableData.findIndex(item => item.Sno === row.Sno);
  if (rowIndex !== -1&&row.position!=='负责人') {
    // 从 tableData 中删除对应的社团
    tableData.splice(rowIndex, 1);
    // 更新分页总数
    pages.total = tableData.length;
    // 如果需要，可以在这里处理分页状态的更新
    if (pages.total < pages.pageSize * pages.currentPage) {
      pages.currentPage = Math.max(1, pages.currentPage - 1);
    }
  } else {
    console.error('未找到对应的类型');
  }
};
// 存储要编辑的社团类型
const editCategoryForm = reactive({ position: '' });
// 用于存储当前正在编辑的社团的索引
const currentEditingIndex = ref(-1);
const index = ref(-1);
// 打开修改对话框并设置要编辑的社团类型
const openEditDialog = (row) => {
  editCategoryForm.position = row.position; // 设置要编辑的社团类型
  editDialogVisible.value = true; // 打开对话框
  index.value = tableData.findIndex(item => item.position === row.position);
};
import { InfoFilled } from '@element-plus/icons-vue'
// 确认修改社团类型的逻辑
const confirmEdit = () => {
  // 找到要修改的社团类型在 tableData 中的索引
  console.warn(index.value);
  let club = tableData[index.value];
  if (index.value !== -1) {
    // 更新社团类型
    if(club.position=='负责人') {editDialogVisible.value = false; return;}
    else club.position = editCategoryForm.position;
  }
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