<template>
  <div class="common-layout">
    <!---侧边栏--->
    <el-container>
      <el-aside class="aside" >
        <!---切换--->
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="7"
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
          <el-page-header :icon="ArrowLeft" >
            <template #content>
              <span class="text-large font-600 mr-3"><h3>系统后台管理 </h3></span>
            </template>
          </el-page-header>
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
            <el-form-item >
              <el-input v-model="formInline.clubName" placeholder="社团名称" clearable />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">Query</el-button>
            </el-form-item>
          </el-form>
          <!---审核数据--->
          <el-table :data="filteredTableData" style="width: 100%">
            <el-table-column fixed prop="clubName"  label="社团名称"  width="150" />
            <el-table-column label="社团头像" width="150">
              <template #default="{ row }">
                <!-- 使用作用域插槽的 row 参数来访问 proPost 属性 -->
                <el-image
                    style="width: 100px; height: 100px"
                    :src="row.proPost"
                    fit="cover"
                ></el-image>
              </template>
            </el-table-column>
            <el-table-column prop="Title"  label="帖子标题" width="120" />
            <el-table-column prop="college" label="学院" width="120" />
            <el-table-column prop="President" label="负责人" width="120" />
            <el-table-column prop="date" label="活动时间" width="120" />
            <el-table-column prop="right" label="帖子详情" min-width="120">
                <el-button >
                  查看详情
                </el-button>
            </el-table-column>
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
import { ArrowLeft } from '@element-plus/icons-vue'
import { nextTick } from 'vue';

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
})

const onSubmit = () => {
  console.log('submit!')
}
import { InfoFilled } from '@element-plus/icons-vue'
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
    return true;
  });
});


//初始数据
const tableData = [
  {
    clubName: '舞蹈队',
    Title: '打扫卫生',
    college: '计算机学院',
    President: '🦌',
    date: '2023-4-5',
    state: '未审核',
    flag:'',//拒绝1，同意0
    contextURL:'https://modao.cc/proto/SSYTHFmxsfz4xzr6pnZYqu/sharing?view_mode=device&screen=rbpUHM74DnUJPa8cT&canvasId=sskp59muUHMA6u8NVCoixt',
    proPost:'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
  },
  {
    clubName: '排球队',
    Title: '读书会',
    college: '哲学学院',
    President: '张三',
    date: '2022-6-7',
    state: '已审核',
    flag:'',
    contextURL:'',
    proPost:'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
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
import { ref } from 'vue'
const dialogVisible = ref(false)
const form = reactive({
  ClubName: '',
  Type:'',
  College:'',
  President: '',
  PresidentCollege:'',
  proPost:'',
  Profile:'',
})

const url =
    'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'

// 用于存储当前正在编辑的社团的索引
const currentEditingIndex = ref(-1);
const index = ref(-1);

// 注销社团的方法
const cancel = (row) => {
  // 找到要删除的社团的索引
  const rowIndex = tableData.findIndex(item => item.clubName === row.clubName);

  if (rowIndex !== -1) {
    // 从 tableData 中删除对应的社团
    tableData.splice(rowIndex, 1);
    console.log('Deleted item from tableData:', tableData);

    // 等待 Vue 响应性更新完成
    nextTick(() => {
      console.log('filteredTableData after update:', filteredTableData.value);
    });

    // 更新分页总数
    pages.total = tableData.length;

    // 如果需要，可以在这里处理分页状态的更新
    if (pages.total < pages.pageSize * pages.currentPage) {
      pages.currentPage = Math.max(1, pages.currentPage - 1);
    }
  } else {
    console.error('未找到对应的社团');
  }
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