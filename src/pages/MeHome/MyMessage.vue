<template>
   <el-button plain @click="dialogTableVisible = true" 
   style="width: 132px;height: 40px; font-size: 18px;font-weight: 700; color: rgba(0,0,0,0.4);">
    我的消息
  </el-button>
  <el-dialog v-model="dialogTableVisible" width="800">
    <p class = "title">消息中心</p>
    <div class="demo-collapse collapse">
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item  @click="readMessage(id)" v-for="m in messageList">
        <template #title>
          <p class="m-title"><span class="tag">{{ true?'New':'' }}</span>{{ truncate(m.content) }}</p>
        </template>
        <div class="item">
         {{ m.content }}
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

  const messageList = ref([])

  //获取消息列表
  const getMessageList = async() =>{
    try {  
        const response = await axios.get(`http://localhost:8080/message/${webStore.web.uid}`, webStore.web.uid); 
        messageList.value = response.data.data.records 
  
        console.log('拉取消息列表',response.data.data.records)
      } catch (error) {    
        console.error('拉取消息失败', error);  
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

  function truncate(str){//标题字符串显示样式
    if (str.length > 10) {  
    return str.substring(0, 10) + '...';  
  }  
  return str;  
  }

</script>

<style scoped>
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
  /* max-height: 480px; */
  height: 300px;
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