<script setup>
//头像图片
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router';
import {CirclePlus} from "@element-plus/icons-vue";
import MyMessage from './MyMessage.vue'
import CreateClub from './CreateClub.vue';

const router = useRouter()

const state = reactive({
  circleUrl:
      'https://clubmate.oss-cn-beijing.aliyuncs.com/074071e2-f794-447d-bc46-e27887404728.jpg',
})

const { circleUrl } = toRefs(state)
//第三部分代码
import { computed, ref } from 'vue'
import { useWebStore } from '@/stores/web';
import axios from 'axios';

const webStore = useWebStore()

const search = ref('')
//lookDetail 和 deleteClub 的方法还没有写
function lookDetail(index,raw){
  //raw数据不包含id
  webStore.web.clubId = raw.cludId2;//将正在管理的社团id用全局变量保存
  router.push('/ClubMessage')
}

const tableData = ref([])


async function getManageClubData() {
  try {
    const response = await axios.get('http://localhost:8080/join/club/control/' + webStore.web.uid);
    console.log('返回值',response.data)
      // 遍历每个记录，获取社团详细信息并组装数据
      for (let record of response.data.data.records) {
      const clubId = record.clubId; // 获取社团ID

      // 获取社团详细信息
      const clubDetailResponse = await axios.get(
          `http://localhost:8080/club/get/${clubId}`
      );
      // 获取用户详细信息
      const studentDetailResponse = await axios.get(
          `http://localhost:8080/user/getInfo/${clubDetailResponse.data.data.createUserId}`
      );

      // 提取需要的属性并按顺序组装成新的对象
      let rowData = {
        cludId2:clubId,
        clubName: clubDetailResponse.data.data.name,
        type: clubDetailResponse.data.data.tags,
        clubPic: clubDetailResponse.data.data.avatarUrl,
        date: clubDetailResponse.data.data.registerTime,
        studentName: studentDetailResponse.data.data.name,
      };

      // 将组装好的数据对象推入tableData数组
      tableData.value.push(rowData);
    }
  }catch (error) {
    console.error('获取管理社团数据失败', error)
  }
}

// 页面加载时发送请求
getManageClubData()
const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.clubName.toLowerCase().includes(search.value.toLowerCase())
    )
)

</script>




<template>
  <div class="common-layout">
    <el-container>


      <!--第一部分-->
      <el-container  style="height: 150px;">
        <!--<el-icon><CirclePlus /></el-icon>-->

        <!-- 单个100px大小的圆形头像 -->
        <el-aside width="200px" class="aside-center">
          <div class="block" style="margin-left: -20px;">
            <el-avatar :size="120" :src="circleUrl" />
          </div>
        </el-aside>

        <!--姓名，班级，学号。右上角两个按钮部分-->
        <el-main>

          <div class = "tableTop"> 
              <span class="name">程嘉佳</span>
              <span class="lightgray-box">计算机学院593班</span>
              <span class="gray-box">2022302111159313</span>
              <div style="display: flex;margin-left: 1520px;margin-top: 0px;">
                <CreateClub />
              <MyMessage />
              </div>
            </div>
              
          <!-- <el-row class="el-row1">
            <el-col :span="50"> -->
              <!--张乐遥-->
              <!-- <span class="name">程嘉佳</span>
              <span class="lightgray-box">计算机学院x班</span>
              <span class="gray-box">20223021111xx</span> -->

            <!-- </el-col>
            <el-col :span="9" :offset="8" class="button-container"> -->
              <!-- <router-link to="/SetUpClub">
                <el-button type="primary" plain>创建社团</el-button>
              </router-link> -->
              <!-- <CreateClub /> -->
              <!-- <el-button type="primary" plain>我的消息</el-button> -->
               <!-- <MyMessage /> -->
            <!-- </el-col>
          </el-row> -->

        </el-main>

      </el-container>


      <!--第二部分-->
      <el-main>
        <el-row class="el-row" style="margin-top: 0px;">
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
        </el-row>
      </el-main>


      <!--第三部分-->
      <el-footer style = "margin-top: 0px;">
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
          <el-table-column >
            <template #header>
              <el-input v-model="search" size="small" placeholder="搜索社团名称" />
            </template>
            <template #default="scope">
              <el-button size="small" @click="lookDetail(scope.$index, scope.row)">管理</el-button>

            </template>
          </el-table-column>
        </el-table>
      </el-footer>
    </el-container>
  </div>
</template>





<style scoped>

.lightgray-box {
  margin-left: 80px;
  margin-top: 32px;
  font-size: 25px;
  font-weight: 700;
  /* background-color: lightgray; */
  height: 40px;
  color:#575854 ;
}

.gray-box {
  /* background-color: gray; */
  margin-left: 80px;
  margin-top: 32px;
  height: 40px;
  font-size: 25px;
  font-weight: 700;
  color:#575854 ;
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
  margin-left: 20px;
  font-size: 3.3em; /* 放大字体 */
  font-weight: 700;
  color: rgba(0,0,0,0.4);
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