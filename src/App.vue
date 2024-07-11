<template>
  <!-- 导航区 -->
  <el-header class = "header">
        <div class = "icon" >CLUBMATE</div>
        <div>
          <RouterLink to = "/" class = "nav" active-class = "active">首页</RouterLink>
          <RouterLink to = "/overview" class = "nav" active-class = "active">社团概览</RouterLink>
          <RouterLink to = "/login" class = "nav" active-class = "active" v-if = "!webStore.web.status">登录/注册</RouterLink>
          <RouterLink to = "/Administration" class = "nav" active-class = "active" v-if = "webStore.web.status && webStore.web.identity !== 'admin'">个人信息</RouterLink>
          <RouterLink to = "/ClubReview" class = "nav" active-class = "active" v-if = "webStore.web.status && webStore.web.identity === 'admin'">后台管理</RouterLink>
        </div>
        <el-input v-model="input" style="width: 240px" placeholder="Please Input" :suffix-icon="Search" class = "search"/>
      </el-header> 

  <div class = "main-content">
    <RouterView></RouterView>
  </div>
  </template>


<script setup>
  import {RouterView,RouterLink, useRouter} from 'vue-router'
  import { Search } from '@element-plus/icons-vue';
  import { useWebStore } from './stores/web.js';
  import { watch ,ref} from 'vue';

  const input = ref('');

  const router = useRouter();

  const webStore = useWebStore();

  watch(() => webStore.web.status, (newStatus) => {  
  if (newStatus && router.currentRoute.value.path === '/login' && webStore.web.identity === 'admin') {  
    router.push('/ClubReview');  
  }
  if(newStatus && router.currentRoute.value.path === '/login' && webStore.web.identity !== 'admin')  {
    router.push('/Administration');  
  }
});  
  

</script>

<style scoped>

  a{
    text-decoration:none;
    padding:auto auto;
  }

 
  .header{
    margin-top:20px;
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

  .active{
    color:#4095e5;
    font-size:22px;
  }
</style>