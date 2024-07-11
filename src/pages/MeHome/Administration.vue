<script setup>
//头像图片
import { reactive, toRefs } from 'vue'
import {CirclePlus} from "@element-plus/icons-vue";

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
})

const { circleUrl } = toRefs(state)

//第三部分（表格）的copy代码
/*
import { computed, ref } from 'vue'

interface User {
  date: string
  name: string
  address: string
}

const search = ref('')
const filterTableData = computed(() =>
    tableData.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)
const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'John',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
*/
//第三部分代码
import { computed, ref } from 'vue'
const search = ref('')
const filterTableData = computed(() =>
    tableData.filter(
        (data) =>
            !search.value ||
            data.clubName.toLowerCase().includes(search.value.toLowerCase())
    )
)

//lookDetail 和 deleteClub 的方法还没有写

const tableData = [
  {
    clubName: '排球队',
    type: '体育艺术类',
    clubPic: 'src/picture/排球.jpg',
    date: '20040507',
    studentName: '张三',
  },
  {
    clubName: '舞蹈队',
    type: '体育艺术类',
    clubPic: 'src/picture/舞蹈.jpg',
    date: '20040722',
    studentName: '李四',
  },

]
</script>




<template>
  <div class="common-layout">
    <el-container>


      <!--第一部分-->
      <el-container>
        <!--<el-icon><CirclePlus /></el-icon>-->

        <!-- 单个100px大小的圆形头像 -->
        <el-aside width="160px" class="aside-center">
          <div class="block">
            <el-avatar :size="150" :src="circleUrl" />
          </div>
        </el-aside>

        <!--姓名，班级，学号。右上角两个按钮部分-->
        <el-main>

          <el-row class="el-row1">
            <el-col :span="7">
              <!--张乐遥-->
              <span class="name">张乐遥</span>
            </el-col>
            <el-col :span="9" :offset="8" class="button-container">
              <router-link to="/SetUpClub">
                <el-button type="primary" plain>申请创建社团</el-button>
              </router-link>
              <el-button type="primary" plain>我的消息</el-button>
            </el-col>
          </el-row>
          <el-row class="el-row2">
            <el-col :span="5" class="lightgray-box">计算机学院5班</el-col>
            <el-col :span="5" class="gray-box">2022302111371</el-col>
          </el-row>

        </el-main>

      </el-container>


      <!--第二部分-->
      <el-main>
        <el-row class="el-row">
          <el-col :span="3">
            <!--原本是像下一行这样，:to="{ name: 'Collection' }" :class是为了调按钮按下去的样式，后面两个也是如此-->
            <!--<router-link to="/Collection">-->
            <router-link :to="{ name: 'Collection' }" :class="{ 'active-button': $route.meta.activeButton === 'collection' }">
            <el-button type="primary" round>收藏的社团</el-button>
            </router-link>
          </el-col>
          <el-col :span="3">
            <!--<router-link to="/Participation">-->
            <router-link :to="{ name: 'Participation' }" :class="{ 'active-button': $route.meta.activeButton === 'participation' }">
              <el-button type="primary" round>加入的社团</el-button>
            </router-link>
          </el-col>
          <el-col :span="3">
            <!--<router-link to="/Administration">-->
            <router-link :to="{ name: 'Administration' }" :class="{ 'active-button': $route.meta.activeButton === 'administration' }">
              <el-button type="primary" round>管理的社团</el-button>
            </router-link>
          </el-col>
          <el-col :span="1" :offset="14">
            <el-button type="primary" round>+</el-button>
          </el-col>
        </el-row>
      </el-main>


      <!--第三部分-->
      <el-footer>
        <el-table :data="filterTableData" style="width: 100%">
          <!--<el-table-column label="Date" prop="date" />-->
          <!--<el-table-column label="Name" prop="name" />-->
          <el-table-column label="社团名称" prop="clubName" />
          <el-table-column label="社团分类" prop="type" />
          <el-table-column label="图片" prop="clubPic">
            <template #default="scope">
              <img :src="scope.row.clubPic" style="max-width: 100px; max-height: 100px;" />
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="date" />
          <el-table-column label="社长姓名" prop="studentName" />
          <el-table-column align="right">
            <template #header>
              <el-input v-model="search" size="small" placeholder="搜索社团名称" />
            </template>
            <template #default="scope">
              <el-button size="small" @click="lookDetail(scope.$index, scope.row)">管理</el-button>
              <el-button size="small" type="danger" @click="deleteClub(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-footer>
    </el-container>
  </div>
</template>





<style scoped>

.lightgray-box {
  background-color: lightgray;
  height: 24px;
}

.gray-box {
  background-color: gray;
  height: 24px;
}

.el-row1 {
  margin-bottom: 35px;
  display: flex;
  flex-wrap: wrap;
  position: relative; /* 使得内部元素可以基于此定位 */
  line-height: 1.2; /* 调整行高 */
}

.el-row2 {
  display: flex;
  flex-wrap: wrap
}

.aside-center {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.name {
  font-size: 3.3em; /* 放大字体 */
}

.button-container {
  position: absolute;
  top: 0;
  right: 0;
}

.active-button {
  background-color: #4caf50; /* 示例：修改按钮背景色 */
  color: #ffffff; /* 示例：修改按钮文字颜色 */
  /* 可以根据需要自定义其他样式 */
}

</style>