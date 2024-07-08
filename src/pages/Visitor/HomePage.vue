<template>
  <div class = "whole">
<div class = "body">
<div class = "main-content" id = "homepage">
  <div class = "shell">
    <div class = "content">
      <div class = "item"></div>
      <div class = "item"></div>
      <div class = "item"></div>
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
    <el-button plain @click="setDialogVisible(index, true)" class = "image"></el-button>
    
  <el-dialog
    v-model="dialogVisible[index]"
    width="600"
    destroy-on-close
    center
    align-center
  >
  <div class = "detail">
    <h3 class = "detail-h">社团介绍</h3>
  <p><strong class = "detail-s">社团名称:</strong>{{club.name}}</p>
  <p><strong class = "detail-s">社团分类:</strong>{{club.tags}}</p>
  <p><strong class = "detail-s">社团介绍:</strong>{{club.introduce}}</p>
  </div>
  </el-dialog>
    <div>
      <h4>{{club.name}}</h4>
      <p>指导单位：{{club.unit}}</p>
    </div>
  </div>

</div>
</div>
</div>
  </template>

<script setup name = "HomePage">
  import { Search } from '@element-plus/icons-vue';
  import {useRouter} from 'vue-router';
  import {ref} from 'vue';

  const dialogVisible = ref({}); // 使用对象来跟踪每个对话框的可见性 

  function setDialogVisible(index, isVisible) {  
  dialogVisible.value[index] = isVisible;  
}  

  const lists = [
    {
      name:'珞珈晨跑队',
      tags:'文化体育类',
      unit:'弘毅学堂',
      introduce:'这里是一个汇聚晨光与活力的温暖集体。不论你是跑步的初学者，还是经验丰富的马拉松爱好者，都能找到属于自己的节奏与伙伴。社团定期组织晨跑活动，享受运动带来的快乐与释放。我们鼓励成员间相互激励，分享跑步心得，共同成长。加入晨跑社团，不仅能让你的身体更加强健，更能让你的心灵在晨曦中得到净化与升华，开启一天满满的正能量。让我们一起，用奔跑的姿态，迎接每一个充满希望的新开始！'
    },
    {
      name:'AOE舞蹈队',
      tags:'文化体育类',
      unit:'计算机学院',
      introduce:'舞动青春，韵动梦想！我们是一支充满热情与创意的舞蹈队，以舞为媒，融合多元风格，用每一个跃动的节拍诠释对生活的热爱与追求。'
    },
    {
      name:'武汉大学大学生安全工作协会',
      tags:'自律互助类',
      unit:'保卫部',
      introduce:'安全护航，责任为先。安全工作协会，致力于构建安全文化，提升安全意识，通过专业培训与交流，共筑安全防线，守护每一份安心与和谐。'
    }
  ]
  const router = useRouter()

</script>

  <style scoped>
  a{
    text-decoration:none;
    padding:auto auto;
  }

  .whole{
    height:90vh;
    background-image:linear-gradient(to top,#ffffff 0%,#377f7f 100%);
  }

  .body{
    display:flex;
    align-items:center;

    flex-direction:column;
    height:45vh;
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

  .item:nth-child(1){
    background-image:url(../picture/01.jpg);
    transform:rotateY(0) translateZ(35vw);
  }

  .item:nth-child(2){
    background-image:url(../picture/02.jpg);
    transform:rotateY(120deg) translateZ(35vw);
  }

  .item:nth-child(3){
    background-image:url(../picture/03.jpg);
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
  }

  .divider{
    margin-top:7rem;
    margin-left:10rem;
    color:rgba(0,0,0,0.5);
  }

  .introduction{
    margin-top:1rem;
    display:flex;
    justify-content:space-evenly;
  }

  .introduction .left{
    width:400px;
    height:200px;
    text-align:center;
    display:flex;
  }

  .introduction .image{
    width:200px;
    height:200px;
  }

  .introduction .image:nth-child(1){
    background-image:url(../picture/02.jpg);
  }
  
  .detail{
    font-size:18px;
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