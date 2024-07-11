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
        <span class = "nav-exit nav" v-if = "webStore.web.status" @click="dropLogin">退出登录</span>
      </el-header> 
      <!-- 确认退出 -->
      <el-dialog
    v-model="dialogVisible"
    width="500"
  >
    <span style="font-size: 20px;">请确认是否退出登录？</span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="reConfirm">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>

  <div class = "main-content">
    <RouterView></RouterView>
  </div>
  </template>


<script setup>
  import {RouterView,RouterLink, useRouter} from 'vue-router'
  import { Search } from '@element-plus/icons-vue';
  import { useWebStore } from './stores/web.js';
  import { watch ,ref} from 'vue';

  const dialogVisible = ref(false)

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


function dropLogin () {//退出登录
  dialogVisible.value = true
}

function reConfirm(){
  dialogVisible.value = false
  webStore.web.identity = ''
  webStore.web.status = ''
  router.push('/')
  ElNotification({
          title: 'Success',
          message: '退出登录成功',
          type: 'success',
  })
}


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

  .nav-exit{
    font-size: 20px;
    text-decoration: underline;
  }

  .nav-exit:hover{
    color:#4095e5;
  }

  .active{
    color:#4095e5;
    font-size:22px;
  }
</style>