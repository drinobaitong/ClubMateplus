<script setup>
//头像图片
import { reactive, toRefs } from 'vue'
import axios from 'axios'
import { useWebStore } from '@/stores/web';
import NotLogged from '../Visitor/NotLogged.vue';

const webStore = useWebStore()

const state = reactive({
  circleUrl:
      'https://clubmate.oss-cn-beijing.aliyuncs.com/074071e2-f794-447d-bc46-e27887404728.jpg',
})

const { circleUrl } = toRefs(state)


//第三部分代码
import { computed, ref } from 'vue'
import { fixedDataType } from 'element-plus/es/components/table-v2/src/common';
const search = ref('')
import MyMessage from './MyMessage.vue'
import CreateClub from './CreateClub.vue';

//lookDetail 和 deleteClub 的方法还没有写

const tableData = ref([])


async function getClubData() {
  const pageNo = 1 // Query参数
  const pageSize = 10 // Query参数
  try {
    console.log('用户id',webStore.web.uid)
    const response = await axios.get(
        'http://localhost:8080/join/club/' + webStore.web.uid,
        {
          params: {
            pageNo: pageNo,
            pageSize: pageSize,
          },
        }
    );

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
        name: clubDetailResponse.data.data.name,
        tags: clubDetailResponse.data.data.tags,
        avatarUrl: clubDetailResponse.data.data.avatarUrl,
        registerTime: clubDetailResponse.data.data.registerTime,
        studentName: studentDetailResponse.data.data.name,
        id:clubDetailResponse.data.data.id,
        createUserId:studentDetailResponse.data.data.id
      };

      // 将组装好的数据对象推入tableData数组
      tableData.value.push(rowData);
    }

    // 输出用于调试
    console.log('tableData', tableData);

    // 现在可以将tableData数组用于页面显示或其他操作
  }catch (error) {
    // console.log('apifox',response)
    console.error('获取社团数据失败', error)
  }
}

// 页面加载时发送请求
getClubData()

const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.clubName.toLowerCase().includes(search.value.toLowerCase())
    )
)

console.log('过滤后的数据',filterTableData.value)


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
         
        </el-main>

      </el-container>


      <!--第二部分-->
      <el-main>
        <el-row class="el-row">
          <el-col :span="3">
            <router-link to="/Collection">
              <el-button type="primary" round>收藏的社团</el-button>
            </router-link>
          </el-col>
          <el-col :span="3">
            <router-link to="/Participation">
              <el-button type="primary" round>加入的社团</el-button>
            </router-link>
          </el-col>
          <el-col :span="3">
            <router-link to="/Administration">
              <el-button type="primary" round>管理的社团</el-button>
            </router-link>
          </el-col>
        </el-row>
      </el-main>


      <!--第三部分-->
      <el-footer>
        <el-table :data="filterTableData" style="width: 85%">
          <!--<el-table-column label="Date" prop="date" />-->
          <!--<el-table-column label="Name" prop="name" />-->
          <el-table-column label="社团名称" prop="name" />
          <el-table-column label="社团分类" prop="tags" />
          <el-table-column label="图片" prop="avatarUrl">
            <template #default="scope">
              <img :src="scope.row.avatarUrl" style="max-width: 100px; max-height: 100px;" />
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="registerTime" />
          <el-table-column label="社长姓名" prop="studentName" />
          <el-table-column align="right">
            <!-- <template #header>
              <el-input v-model="search" size="small" placeholder="搜索社团名称" />
            </template>
            <template #default="scope">
              <el-button size="small" @click="lookDetail(scope.$index, scope.row)">查看</el-button>
              <el-button size="small" type="danger" @click="deleteClub(scope.$index, scope.row)">删除</el-button>
            </template> -->
          </el-table-column>
        </el-table>
        <el-input v-model="search" size="small" placeholder="搜索社团名称" class="toSearch" />
        <ul class = "dataButton">
          <li v-for = "temp in filterTableData">
            <NotLogged :club = temp :setDialogVisible = setDialogVisible :wid=50 :hei=25 :where = 1 />
          </li>
        </ul>
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

.dataButton{
  list-style: none;
  float: right;
  margin-top: -670px;
  margin-right: 150px;
}

.dataButton li{
  margin-bottom: 75px;
}

.toSearch{
  left:1660px;
  top:-780px;
  width: 250px;

}

</style>