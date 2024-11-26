<template>
  <div class = "whole">
<div class = "body">
<div class = "main-content" id = "homepage">
  <div class = "shell">
    <div class = "content">
      <div class = "item" @click = "openPost(6)"></div>
      <div class = "item" @click = "openPost(7)"></div>
      <div class = "item" @click = "openPost(2)"></div>
    </div>
  </div>
</div>
</div>

<div class = "b-content">
<div class = "divider">
  <h2>社团推荐</h2>
</div>
<div class = "introduction">

  <div class = "left" v-for="(club, index) in lists" :key="index">
    <!-- <el-button plain @click="setDialogVisible(index, true)" class = "image"></el-button> -->
    
  <NotLogged :club = club  :wid=200 :hei=200 :where = 0 />

    <div class="clubBrief">
      <h4 class="clubName">{{club.name}}</h4>
      <p style="font-size: 18px;">{{club.collage}}</p>
    </div>
  </div>

</div>
</div>
</div>
  </template>

<script setup name = "HomePage">
  import NotLogged from './NotLogged.vue';
  import {ref} from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';

  
  const rt = useRouter()

  // const dialogVisible = ref({}); // 使用对象来跟踪每个对话框的可见性 

//   function setDialogVisible(index, isVisible) {  
//   dialogVisible.value[index] = isVisible;  
// }  


function openPost(articleId){
  rt.push({ name: 'PostArticle', params: { articleId: articleId } });  
}

const lists = ref([])
  //获取社团列表
  const getClubList = async () =>{
    try {  
        const response = await axios.get('http://localhost:8080/club/list');  
        console.log('获取社团数据',response.data.data.records)
        lists.value = response.data.data.records
        lists.value= lists.value.slice(0,3)

      } catch (error) {  
        // 处理错误  
        console.error('注册失败', error);  
   
      }  
  }
  getClubList()
</script>

  <style scoped>
  a{
    text-decoration:none;
    padding:auto auto;
  }

  .whole{
    height:80vh;
    background-image:linear-gradient(to top,#ffffff 0%,#c9c6c6 100%);
  }

  .body{
    display:flex;
    align-items:center;

    flex-direction:column;
    height:40vh;
  }
  
  .header{
    display:flex;
    width:100%;
    align-items:center;
    background-color:white;
    padding-bottom:1.5rem;
  }

  .icon{
    font-size:35px;
    font-weight:700;
    color:#9A9A9A;
    margin-left:1rem;
    margin-right:20rem
  }

  .nav{
    margin-left:10rem;
    color:#9A9A9A;
    font-weight:700;
  }
  
  .search{
    font-size:20px;
    margin-left:10rem;
  }

  .main-content{
    margin-top:7rem;
  }

  .shell{
    position:relative;
    width:60vh;
    height:40vh;
    max-width:380px;
    max-height:250px;
    margin:0;
    color:white;
    perspective:1000px;
    transform-origin:center;
  }

  .content{
    margin-top: 30px;
    display:flex;
    justify-content:center;
    align-items:center;
    position:absolute;
    width:100%;
    height:100%;
    transform-origin:center;
    transform-style:preserve-3d;
    transform: translateZ(-30vw) rotateY(0);
    animation:carousel 9s infinite cubic-bezier(0.77,0,0.175,1) forwards;
  }

  .item{
    position:absolute;
    width:60vw;
    height:40vw;
    max-width:750px;
    max-height:375px;
    box-shadow:0 5px 20px rgba(0,0,0,0.3);
    border-radius:6px;
    background-size:cover;
    -webkit-box-reflect:below 25px -webkit-linear-gradient(transparent 50%,rgba(255,255,255,0.3));
  }

  .item:hover{
    cursor: pointer;
  }

  /* 6 */
  .item:nth-child(1){
    background-image:url(https://clubmate.oss-cn-beijing.aliyuncs.com/05a6368a-1287-4882-9b6e-c6732f990a09.jpg
    );
    transform:rotateY(0) translateZ(35vw);
  }

  /* 7 */
  .item:nth-child(2){
    background-image:url(https://clubmate.oss-cn-beijing.aliyuncs.com/73a07a28-a11b-488e-bd8b-dea9fd78764c.jpg
    );
    transform:rotateY(120deg) translateZ(35vw);
  }

  /* 2 */
  .item:nth-child(3){
    background-image:url(https://clubmate.oss-cn-beijing.aliyuncs.com/017a91ea-0181-469a-bd98-957b1019a117.jpg
    );
    transform:rotateY(240deg) translateZ(35vw);
  }

  @keyframes carousel{
    0%,
    17.5%{
      transform:translateZ(-35vw) rotateY(0);
    }

    27.5%,
    45%{
      transform:translateZ(-35vw) rotateY(-120deg);
    }
    
    55%,
    72.5%{
      transform:translateZ(-35vw) rotateY(-240deg);
    }
    
    82.5%,
    100%{
      transform:translateZ(-35vw) rotateY(-360deg);
    }
  }
  
  .b-content{
    padding-left:5rem;
    margin-top: 40px;
  }

  .divider{
    margin-top:7rem;
    margin-left:10rem;
    color:rgba(0,0,0,0.5);
  }

  .introduction{
    max-width: 100%;
    max-height: 300px;
    margin-top:60px;
    display:flex;
    justify-content:space-evenly;
    flex-wrap: wrap;
    overflow: hidden;
  }

  .introduction .left{
    width:400px;
    height:250px;
    text-align:center;
    display:flex;
  }

  .introduction .image{
    width:200px;
    height:200px;
  }

  .introduction .image:nth-child(1){
    background-image:url(@/picture/02.jpg);
  }

  .clubBrief{
    width: 240px;
  }

  .clubName{
    margin-top: 40px;
    margin-left: 20px;
    font-size: 18px;
  }
  
  .detail{
    font-size:20px;
    line-height:30px;
  }

  .detail-h{
    text-align:center;
    font-size:25px;
    margin-top:-5px;
    margin-bottom:30px;
  }

  .detail-s{
    font-size:20px;
    margin-right:15px;
  }

  </style>