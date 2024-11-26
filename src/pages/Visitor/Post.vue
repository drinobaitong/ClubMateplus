<template>
      <el-container style="background-color: #f2f2f2;">
        <!--发帖人信息-->
        <el-aside width="400px" style="background-color: #E0EAEA;"><!-- aside部分淡蓝色 -->
          <!--justify="center" type="flex" align="middle"使一行中的每列中的内容水平垂直居中-->
          <el-row class="el-row1" justify="center" type="flex" align="middle">
            <el-col :span="8" align="center">
              <div class="userPic">
                <el-avatar :size="60" :src="state.responseTwo.avatarUrl" />
              </div>
            </el-col>
            <el-col :span="12"><!--水平居中可加入 type="flex" align="center"-->
              <div class="postSender" style="font-size: 25px; display: flex; justify-content: center; align-items: center;">
                {{ state.responseTwo.name }}
              </div>
            </el-col>
          </el-row>
          <el-row class="el-row2" justify="center" type="flex" align="middle">
            <el-col :span="24" type="flex" align="center">
              <div class="postTime" style="font-size: 15px;">
                {{state.response.registerTime}}
              </div>
            </el-col>
          </el-row>
        </el-aside>

        <el-container>

          <!--帖子标题-->
          <el-header style="font-size: 35px;
                display: flex; justify-content: space-between; align-items: center;
                border-bottom: 2px solid gray; position: relative;">

            <div style="display: flex; justify-content: center; align-items: center; flex: 1;">
              {{ state.response.title }}
            </div>

            <el-button plain @click="$router.go(-1)" style="position: absolute; top: 0; right: 0;">返回</el-button>

          </el-header>


          <!--帖子内容-->
          <el-main style="text-align: center; font-size: 24px">
            <!--以下div和p都是调文本左对齐且首行缩进为2的，文字多的时候才能看出效果-->
            <div style="text-align: left;">
              <p style="text-indent: 2em;">
            {{ state.response.content }}
              </p>
            <br>
            </div>
            <img :src="state.response.avatarUrl" alt="Avatar" class="articlePic" >
          </el-main>

          <!--评论-->
          <el-main style="border:dotted; ">

            <div  v-for="comment in data">
              <el-row class="message-row">
                <!-- 头像列 -->
                <el-col :span="1" class="avatar-column">
                  <div class="commentPic">
                    <el-avatar :size="40" :src="comment.avatarUrl"/>
                  </div>
                </el-col>

                <!-- 用户名列 -->
                <el-col :span="3" class="username-column">
                  <div class="commentName">
                    {{ comment.name }}
                  </div>
                </el-col>

                <!-- 时间列 -->
                <el-col :span="4" class="time-column">
                  <div class="commentTime">
                    {{ comment.time }} {{ comment.layer }}楼
                  </div>
                </el-col>
              </el-row>

              <!--评论内容行-->
              <el-row>
                <el-col :span="22" :offset="1">
                  <div class="comment">
                    {{ comment.content }}
                  </div>
                </el-col>
              </el-row>
            </div>


            <!--写留言按钮-->
            <!--弹窗版写留言-->
            <div style="display: flex; justify-content: flex-end;">
              <el-button plain @click="open">写留言</el-button>
            </div>

          </el-main>

        </el-container>


      </el-container>

</template>

<script setup>
import {reactive, ref, toRefs} from 'vue'
import axios from 'axios'

const data = ref([])

const props =  defineProps({articleId:{
  type:Number,
}})

const state = reactive({
  response: {
    registerTime: '',
    content: '',
    title: '',
    avatarUrl: 'https://cn.bing.com/images/search?view=detailV2&ccid=HUEiQLWO&id=3117CB3C99C944F144535A0B9C881C6DEE54D6E1&thid=OIP.HUEiQLWOHrUrOzyK5LoAZAHaHa&mediaurl=https%3a%2f%2fpic.616pic.com%2fys_bnew_img%2f00%2f16%2f95%2fOjCm8gnt48.jpg&exph=801&expw=800&q=test%e5%9b%be%e7%89%87&simid=607995107365320190&FORM=IRPRST&ck=4C859440F93CDFAB2799F9291A954F27&selectedIndex=0&itb=0&ajaxhist=0&ajaxserp=0',
    createUserId: '',
    id: ''
  },
  responseTwo: {
    name: '',
    avatarUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
  },
  circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  comment: {
    avatarUrl: '',
    name: '',
    time:'',
    layer:'',
    content:''
  }
})

const { circleUrl } = toRefs(state)

//根据帖子id查询对应帖子
axios.get(`http://localhost:8080/articles/${props.articleId}`)
    .then((response) => {
      state.response.registerTime = response.data.data.registerTime;
      state.response.content = response.data.data.content;
      state.response.title = response.data.data.title;
      state.response.avatarUrl = response.data.data.avatarUrl;
      state.response.createUserId = response.data.data.createUserId;
      state.response.id = response.data.data.id;//文章id
      console.log('帖子成功',response)
      const userId=state.response.createUserId;

      const articleId2 = state.response.id;

      
      axios.get(`http://localhost:8080/user/getInfo/${userId}`)
          .then((responseTwo) => {
            state.responseTwo.name = responseTwo.data.data.name;
            state.responseTwo.avatarUrl = responseTwo.data.data.avatarUrl;
            console.log('发帖人成功',responseTwo)

          })
          .catch((error)=>{
            // console.log('apifox',response)
            console.error('获取发帖人数据失败', error)
          })

      //查询评论合集
          fetchCommentsAndUserInfo(articleId2)
    })
    .catch((error)=>{
  // console.log('apifox',response)
  console.error('获取帖子数据失败', error)
})

async function fetchCommentsAndUserInfo(articleId2) {  
  try {  
    // 查询评论合集  
    const response = await axios.get(`http://localhost:8080/comments/article/${articleId2}`);  
    const comments = response.data.data;  
  
    const promises = [];  
    for (const comment of comments) {  
      const userId = comment.createUserId;  
      // 对每个评论的用户信息请求创建一个 promise  
      promises.push(axios.get(`http://localhost:8080/user/getInfo/${userId}`).then(userResponse => {  
        const { avatarUrl, name } = userResponse.data.data;  
        return {  
          avatarUrl,  
          name,  
          time: comment.registerTime,  
          layer: comment.layer,  
          content: comment.content,  
        };  
      }));  
    }  
  
    // 等待所有 promise 完成，然后按照原始顺序处理结果  
    const commentDataArray = await Promise.all(promises);  
  
    // 将处理后的评论数据推入 data.value 数组  
    data.value.push(...commentDataArray);  
  
    console.log('所有评论者信息获取成功');  
  } catch (error) {  
    console.error('获取评论或评论者信息失败', error);  
  }  
}  
  
// 调用函数  
// fetchCommentsAndUserInfo(articleId2);

//写留言
import { ElMessage, ElMessageBox } from 'element-plus';
import { useWebStore } from '@/stores/web';

const webStore = useWebStore()
const messageInput = ref('');

const open = () => {
  ElMessageBox.prompt('请输入你的留言', '写留言', {
    confirmButtonText: '发送',
    cancelButtonText: '返回',
    inputType: 'textarea',
    inputPlaceholder: '请输入你的留言',
  })

      .then(async({ value }) => {
        postComment(value)
        ElMessage({
          type: 'success',
          message: `已成功发送你的留言: ${value}`,
        });
        // 在这里添加发送留言的逻辑，比如调用后端 API 或者其他操作
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '已取消写留言',
        });
      });
};


async function postComment(value){
    const commentPiece = {
      content:value,
      createUserId:webStore.web.uid,
      articleId:state.response.id
  }
    try {  
      const response = await axios.post('http://localhost:8080/comments/add', commentPiece);  
      console.log('发送评论成功', response.data);  
      // 这里可以添加更多的成功处理逻辑   
  } catch (error) {  
      console.log('发给后端的数据',commentPiece)
      console.error('发送评论时发生错误', error);  
    // 处理请求错误，比如网络问题  
  }  
}

</script>

<style scoped>
.el-row1 {
  margin-top: 20px;
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



.image {
  text-align: center; /* 让内部内容居中对齐 */
}

.image .block {
  display: inline-block; /* 改为行内块级元素 */
  text-align: center; /* 内容居中对齐 */
  margin-bottom: 20px; /* 图片间的垂直间距 */
}

.text-content {
  text-align: left; /* 文字内容左对齐 */
}

.text-content > div {
  margin-bottom: 20px; /* 段落之间的垂直间距 */
}

.avatar-column {
  display: flex;
  align-items: center; /* 垂直居中头像 */
}

.username-column,
.time-column {
  display: flex;
  align-items:center; /* 文字底部对齐 */
  height: 40px; /* 确保列高度与头像列一致，以便对齐 */
}

.message-row {
  margin-top: 15px; /* 调整 el-row 和按钮之间的底部间距 */
}

.articlePic{
  display: block ;
  margin:0 auto ;
  width: 1000px;
  height:  666px;
  background-size: contain;
}

</style>