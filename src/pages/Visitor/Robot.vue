<template>
  <el-button plain @click="centerDialogVisible = true">
    <div class = "robot-img">
    </div>
   
  </el-button>

  <el-dialog
    v-model="centerDialogVisible"
    title="欢迎咨询Clubot！"
    width="500"
    align-center
  >
    <!-- span>Open the dialog from the center from the screen</span -->

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="clearInput">清空输入框</el-button>
        <el-button @click="sendMessage" >发送</el-button>
        
      </div>
    </template>

  <div class="right">
    <el-scrollbar height="550px">
      <div class="chat">
        <div class="chat-messages">
          <div
                  v-for="(item, index) in messages"
                  :key="item.id"
                  class="message"
          >
          <div class="mid">
            <div v-if="item.show" class="user"></div>
            <div v-else class="robot"></div>
            <div class="content">{{ item.text }}</div>
          </div>
            <div class="date">{{ formatTimestamp(item.timestamp) }}</div>
            <div class="delate" @click="delateMsg(index)">
                <div class="text">删除</div>
            </div>
            <div class="copy" @click="copyMsg(index)">
                <div class="text">复制</div>
            </div>
          </div>
        </div>
      </div>
    </el-scrollbar>
          
   </div>
        
   <div>
      <el-input
        v-model="input"
        @keydown.enter.prevent="sendMessage"
        isUser="true"
        placeholder="输入想咨询的问题！"
        class="input"
        type="textarea"
        :rows="3"
        autosize
        :style="{ maxHeight: '50px', overflowY: 'auto' }"
      >
      </el-input>

      
    </div>
  </el-dialog>
</template>
<script lang="ts" setup>
import {ElLoading} from 'element-plus'
import {Position} from "@element-plus/icons-vue";
import {
  ElButton,
  ElScrollbar,
  ElInput,
  ElMessageBox,
  ElMessage
} from 'element-plus'
import { ref, nextTick } from 'vue'
import axios from 'axios';
const centerDialogVisible = ref(false)
const messages = ref([])
const input = ref('')
const text = ref('')



const sendMessage = async () => {
  

  if (input.value.trim() !== '') {
    const loading=ElLoading.service({
      target:'.el-dialog',
      text:'Clubot思考中...',
      lock:false,
      background:'rgba(246,246,246,0.7)'
    })
    //loading.visible.value=true;
    
    messages.value.push({
      id: Date.now(),
      text: input.value,
      timestamp: new Date(),
      show:true
    })
    const res=await axios.post('http://localhost:8080/ai/chat', {content: input.value})
    messages.value.push({
          id: Date.now(),
          text: res.data.data,
          timestamp: new Date(),
          show:false
        }
    )
    input.value = ''

    await nextTick() // 等待DOM更新完成

    loading.visible.value=false;
    const messageElements = document.getElementsByClassName('message')
    const lastMessageElement1 = messageElements[messageElements.length - 2]
    lastMessageElement1.scrollIntoView({ behavior: 'smooth', block: 'end' })
    const lastMessageElement2 = messageElements[messageElements.length - 1]
    lastMessageElement2.scrollIntoView({ behavior: 'smooth', block: 'end' })

    
  }
}

const formatTimestamp = timestamp => {
  const options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric'
  }
  return new Intl.DateTimeFormat('default', options).format(timestamp)
}

const copyMsg = index => {
  const text = messages.value[index].text
  const textarea = document.createElement('textarea')
  textarea.value = text
  document.body.appendChild(textarea)
  textarea.select()
  document.execCommand('copy')
  document.body.removeChild(textarea)
  ElMessage({
    type: 'success',
    message: '复制成功'
  })
}

const delateMsg = index => {
  ElMessageBox.confirm(`是否删除此数据吗?`, '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
      .then(() => {
        messages.value.splice(index, 1)
        ElMessage({
          type: 'success',
          message: '删除成功'
        })
      })
      .catch(() => {})
}

const clearInput = () => {
  input.value = ''
}

const userAsk=()=>{
  
}
</script>

<style scoped>

.mid{
  display: flex;
}

.robot-img{
  width: 240px;
  height: 240px;
  background-image: url(@/picture/robot.png);
}

.right{
  margin-bottom: 5px;
  flex: 1;
      padding: 0px 30px;
      border: 2px solid #ccc;
      border-radius: 7px;
      position: relative;
  .chat{
    height :550px;
    padding: 0px;
    display: flex;
    flex-direction: column;
    
    .chat-messages{
      flex: 1;
      margin-left: 0px;
      margin-right: 10px;

      .message{
        display: flex;
        flex-direction: column;
        margin-top: 25px;
        margin-left: 0px;
        position: relative;

        .user{
          margin-left: 0px;
          align-self: baseline;
          width: 25px;
          height: 25px;
          border:1px solid #ccc;
          background-image: url(@/picture/user.jpg);
          background-size: contain;
        }

        .robot{
          margin-left: 0px;
          align-self: baseline;
          width: 25px;
          height: 25px;
          border:1px solid #ccc;
          background-image: url(@/picture/robot.png);
          background-size: contain;
        }

        .content{
          background: #edf1f5;
          display: flex;
          margin-left: 10px;
          padding: 3px 0 0 4px;
          border:1px solid #ccc;
          word-wrap: break-word;
          font-size: 15px;
          
        }

        .copy{
          margin-left:330px;
          color:#555454
        }

        .date {
              position: absolute;
              align-self: flex-start;
              top: -19px;
              margin-left: 35px;
              color: #a09e9e;
        }

        .delate {
              position: absolute;
              align-self: flex-end;
              bottom: 0px;
              right: 0px;
              display: flex;
              align-items: center;
              color:#555454
              
        }
        
        .delate:hover {
              cursor: pointer;
        }
        .delate:hover .text {
              opacity: 1;
        }
      }
    }
  }
}

</style>