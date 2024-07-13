<template>
   <el-button plain @click="dialogTableVisible = true">
    我的消息
  </el-button>
  <el-dialog v-model="dialogTableVisible" width="800">
    <p class = "title">消息中心</p>
    <div class="demo-collapse collapse">
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item name="1" @click="readMessage(id)">
        <template #title>
          <p class="m-title"><span class="tag">{{ true?'New':'' }}</span>排球社长发布了新公告！快去看看吧！</p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="2">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="3">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="4">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="5">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="6">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="7">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="8">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
      <el-collapse-item name="9">
        <template #title>
          <p class="m-title">排球社长发布了新公告！快去看看吧！<span class="tag">{{ true?'New':'' }}</span></p>
        </template>
        <div class="item">
          Consistent with real life: in line with the process and logic of real
          life, and comply with languages and habits that the users are used to;
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
  </el-dialog>
</template>

<script setup>
  import { ref } from 'vue'
  const dialogTableVisible = ref(false)
  const activeName = ref('0')
  import axios from 'axios';
  import { useWebStore } from '@/stores/web';

  const webStore = useWebStore()

  //获取消息列表
  const getMessageList = async() =>{
    try {  
        const response = await axios.get('http://127.0.0.1:4523/m1/4751967-4405137-default/message/1', webStore.web.uid);  
        console.log('拉取列表',response)
      } catch (error) {    
        console.error('拉取失败', error);  
      } 
  }
  getMessageList()

  //点击则消息设为已读
  async function readMessage(id){
    try {  
        const response = await axios.post('http://127.0.0.1:4523/m1/4751967-4405137-default/message/read', id);  
        //已读则修改显示样式

        
        console.log('消息已读',response)
      } catch (error) {    
        console.error('en', error);  
      } 
  }

</script>

<style>
.title{
  font-size: 30px;
  font-weight: 700;
  margin-top: -10px;
  margin-left: 30px;
  color:#4095e5;
}

.collapse{
  margin-left: 40px;
  margin-right: 40px;
  max-height: 480px;
  overflow-y: auto;
}

.m-title{
  font-size: 18px;
  color: rgba(0,0,0,0.7);
  font-weight: 700;
  max-width: 400px;
  overflow: hidden;
}

.m-title-checked{
  font-size: 18px;
  color: rgba(0,0,0,0.3);
  font-weight: 700;
}

.tag{
  float: right;
  color:#4095e5;
  margin-top: -5px;
  margin-left: 10px;
}

.item{
  font-size: 18px;
}

</style>