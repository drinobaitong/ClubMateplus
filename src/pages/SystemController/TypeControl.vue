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
              <span class="text-large font-600 mr-3"><h2>社团类型管理</h2></span>
        </el-header>
        <!---主界面--->
        <el-main >
          <!---搜索框--->
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item >
              <el-input v-model="formInline.category"  placeholder="搜索类型" clearable />
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
                  <el-form-item label="社团类型">
                    <el-input v-model="newType.category" />
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
          <el-table :data="filteredTableData"  style="width: 100%">
            <el-table-column prop="category"  label="社团类型" width="500" />
            <el-table-column fixed="right" label="操作" min-width="120">
              <template #default="scope">
                <el-button type="success" @click="openEditDialog(scope.row)" size="small">修改</el-button>
                <!-- 修改社团类型的对话框 -->
                <el-dialog
                    v-model="editDialogVisible"
                    title="修改社团类型"
                    width="30%"
                >
                  <el-form :model="editCategoryForm" label-width="100px">
                    <el-form-item label="社团类型">
                      <el-input v-model="editCategoryForm.category" />
                    </el-form-item>
                  </el-form>
                  <template #footer>
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="confirmEdit">确认修改</el-button>
                  </template>
                </el-dialog>
                <el-popconfirm
                    confirm-button-text="Yes"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认要删除吗?"
                    @confirm="cancel(scope.row)"
                >
                  <template #reference>
                    <el-button  plain type=danger size="small" >
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
import { ArrowLeft } from '@element-plus/icons-vue'
import { nextTick } from 'vue';

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  sizeList: ['small', '', 'large'] as const,
})

const { circleUrl} = toRefs(state)
const formInline = reactive({
  category: '',
})

const onSubmit = () => {
  console.log('submit!')
}

// 使用计算属性根据筛选条件过滤数据
const filteredTableData = computed(() => {
  return tableData.filter(item => {
    // 如果输入类型名称
    if (formInline.category && !item.category.includes(formInline.category)) {
      return false;
    }
    return true;
  });
});


//初始数据
const tableData = [
  {
    category:'创新创业类',
  },
  {
    category:'文学创作类',
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
// 修改对话框状态
const editDialogVisible = ref(false);

// 存储要编辑的社团类型
const editCategoryForm = reactive({ category: '' });

// 打开修改对话框并设置要编辑的社团类型
const openEditDialog = (row) => {
  editCategoryForm.category = row.category; // 设置要编辑的社团类型
  editDialogVisible.value = true; // 打开对话框
  index.value = tableData.findIndex(item => item.category === row.category);
};
import { InfoFilled } from '@element-plus/icons-vue'
// 确认修改社团类型的逻辑
const confirmEdit = () => {
  // 找到要修改的社团类型在 tableData 中的索引
  console.warn(index.value);
  let club = tableData[index.value];
  if (index.value !== -1) {
    // 更新社团类型
    club.category = editCategoryForm.category;
  }
  editDialogVisible.value = false; // 关闭对话框
  currentEditingIndex.value = -1;
};
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
import { ref } from 'vue'

const url =
    'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'

// 用于存储当前正在编辑的社团的索引
const currentEditingIndex = ref(-1);
const index = ref(-1);

// 注销类型的方法
const cancel = (row) => {
  // 找到要删除的社团的索引
  const rowIndex = tableData.findIndex(item => item.category === row.category);
  if (rowIndex !== -1) {
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
// 控制增加社团类型对话框的显示
const addTypeDialogVisible = ref(false);

// 新社团类型的表单数据
const newType = reactive({
  category: '',
});

// 处理点击确认后增加社团类型的逻辑
const addType = () => {
  if (!newType.category.trim()) {
    // 输入为空，可以提示用户或不做任何操作
    return;
  }
  // 确保不添加重复的社团类型
  const exists = tableData.some(item => item.category === newType.category);
  if (exists) {
    // 可以提示用户社团类型已存在
    return;
  }
  // 添加新社团类型
  tableData.push({ category: newType.category });
  // 清空输入
  newType.category = '';
  // 关闭对话框
  console.warn(tableData);
  addTypeDialogVisible.value = false;
  // 使用 nextTick 等待 DOM 更新
  nextTick(() => {
    console.log('Table data updated:', tableData);
  });
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