<script setup>
import { reactive, toRefs } from 'vue'

const state = reactive({
  circleUrl:
      'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
})

const { circleUrl } = toRefs(state)
</script>

<template>
  <el-container>

    
    <!--第一部分-->
    <el-container>

        <el-aside width="300px" height="400px" margin-bottom="20px">
          <!--justify="center" type="flex" align="middle"使一行中的每列中的内容水平垂直居中-->
          <el-row class="el-row1" justify="center" type="flex" align="middle">
            <el-col :span="12" align="center">
              <div class="userPic">
                <el-avatar :size="60" :src="circleUrl" />
              </div>
            </el-col>
            <el-col :span="12"><!--水平居中可加入 type="flex" align="center"-->
              <div class="postSender" style="font-size: 25px; display: flex; justify-content: center; align-items: center;">
              排球队队长
              </div>
            </el-col>
          </el-row>
          <el-row class="el-row2" justify="center" type="flex" align="middle">
            <el-col :span="24" type="flex" align="center">
              <div class="postTime" style="font-size: 15px;">
              计算机学院排球队 2024-07-02 11:15
              </div>
            </el-col>
          </el-row>
        </el-aside>

      <el-main style="font-size: 35px; display: flex; justify-content: center; align-items: center;">
        排球队春季招新啦！！！！！！零基础友好，快来报名吧！！！！！
      </el-main>

    </el-container>


        <!--第二部分-->
  <el-main>Main</el-main>


  <!--第三部分-->
  <el-footer>Footer</el-footer>
  </el-container>




</template>

<style scoped>
.el-row1 {
  margin-bottom: 15px;
  display: flex;
  flex-wrap: wrap;
  position: relative; /* 使得内部元素可以基于此定位 */
  line-height: 1.2; /* 调整行高 */
}

.el-row2 {
  margin-bottom: 15px;
  display: flex;
  flex-wrap: wrap;
  line-height: 1.2; /* 调整行高 */
}
</style>