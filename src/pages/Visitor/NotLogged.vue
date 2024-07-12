  <template>
     <el-button plain @click="setDialogVisible(index, true)" class = "image" :style="{ width: wid + 'px', height: hei + 'px' }"></el-button>
       <el-dialog
         v-model="dialogVisible[index]"
         fullscreen
         destroy-on-close
         center
        align-center>
<div class = "detail">
  <!-- 显示图片 -->
  <div class = "picture"></div>
  <!-- 文字内容 -->
  <div class = "content">
    <div style = "display: flex; align-items: center;">
    <p class = "detail-t">{{ club.name }}</p>
    <el-button class = "btn" @click = "collect" v-if = "webStore.web.status">收藏</el-button>
    <el-button class = "btn" @click = "join" v-if = "webStore.web.status" :disabled="isIn">加入</el-button>
    <el-button class = "btn" @click = "goBack(index, false)">返回</el-button>
  </div>
    <div class = "mid">
      <span class = "detail-o">{{ club.tags }}</span>
      <span class = "detail-o">创建于{{club.registerTime}}年</span>
      <span class = "detail-o">id:{{ club.id }}</span>
      <span class = "detail-o">社长：{{club.createUserId}}</span>
    </div>
    <!-- 介绍区 -->
    <div class="introduction">
      {{ club.introduce }}
    </div>
  </div>

</div>

<div class = "article">
  <!-- 帖子 -->
<div class = "a-content">
  <div class = "a-header">
    <el-avatar shape="square" :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
    <span class = "publisher">排球队队长</span> <span class = "p-time">20小时前</span>
  </div>
 <div class = "a-image" @click = "openArticle"></div>
 <div class = "a-text"> <p>{{ club.introduce }}</p></div>
</div>

<div class = "a-content">
  <div class = "a-header">
    <el-avatar shape="square" :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
  </div>
 <div class = "a-image" @click = "openArticle"></div>
</div>

<div class = "a-content">
  <div class = "a-header">
    <el-avatar shape="square" :size="50" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
  </div>
 <div class = "a-image" @click = "openArticle"></div>
</div>

</div>
<el-input
      v-model="input1"
      style="width: 240px"
      size="large"
      placeholder="输入搜索关键词"
      :suffix-icon="Search"
      @keydown.enter="handleSearch"  
    />
</el-dialog>
</template>

<script setup>
  import { ref } from 'vue';  
  import { Search } from '@element-plus/icons-vue';
  import { ElNotification } from 'element-plus'
  import { useWebStore } from '@/stores/web.js';
  import { useRouter } from 'vue-router';

  const rt = useRouter()

  //声明接收的props
  defineProps(['club','setDialogVisible','wid','hei'])

  //是否加入社团
  let isIn = ref(false)
  const input1 = ref('')

  const webStore = useWebStore();

  function handleSearch(){//搜索响应函数
    console.log("我搜索了")
  }
  const dialogVisible = ref({}); 

  function setDialogVisible(index, isVisible) {  
    dialogVisible.value[index] = isVisible;  
  }  

  function goBack(index, isVisible){
    dialogVisible.value[index] = isVisible;  
  }

  function join(){//加入社团
    //已在社团内按钮需禁用
    let status = true; 
    isIn.value = true;
    if(status){
      ElNotification({title: 'Success',message: '已发出入团申请！',type: 'success',
    })}
    else{
      ElNotification({title: 'Error',message: '入团申请发送失败',type: 'error',
    })}
  }

  function collect(){//收藏社团
    let status = true; 
    //未收藏
    if(status){
      ElNotification({title: 'Success',message: '已收藏该社团！',type: 'success',
    })}
    //已收藏
    else{
      ElNotification({title: 'Error',message: '已取消收藏该社团',type: 'error',
    })}
  }

  //打开对应帖子页
  function openArticle(){
    //是否需要进行登录验证？
    rt.push('/MePost')
  }

</script>

<style scoped>

.image{
  width:200px;
  height:200px;
  background-image: url(@/picture/02.jpg);
}


.detail{
  margin: 20px;
  margin-left: 75px;
  justify-content: center;
  align-items: center;
  display: flex;
  height: 40vh;
  width: 1700px;
  background-color: #f7f7f7;
  font-size:18px;
  line-height:30px;
}

.picture{
  width: 30%;
  height: 80%;
  background-image: url(@/picture/02.jpg);
}

.article{
  height: 40vh;
  width: 100%;
  display: flex;
  overflow: hidden;
  flex-wrap: wrap;
}

.content{
  margin-top: -100px;
  margin-left: 60px;
}

  .detail-h{
    text-align:center;
    font-size:25px;
    margin-top:-5px;
    margin-bottom:30px;
  }
  /* 社团名 */
  .detail-t{
    width: 700px;
    margin:40px ;
    margin-left: 0 ;
    font-size: 30px;
    font-weight: 700;
  }

  .btn{
    font-size: 20px;
    margin-top: 10px;
    margin-right: 10px;
  }

  .mid{
    font-weight: 700;
    display: flex;
    width: 800px;
    font-size:20px;
    margin-right:15px;
    justify-content: space-between;
  }

  .introduction{
    margin-top: 40px;
    max-width: 1000px;
  }

  /* 贴子区域 */
  .article{
    justify-content: center;
    margin-top: 40px;
    overflow: hidden;
  }

  .a-header{
    margin-top: 10px;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }

  .publisher{
    font-size: 20px;
    font-weight: 700;
  }

  .a-content{
    background-color: #f7f7f7;
    width: 540px;
    height: 360px;
    margin-right: 40px;
  }

  .a-image{
    background-image: url(@/picture/03.jpg);
    width: 445px;
    height:200px;
    margin-left:55px;
    margin-top: 15px;
  }

  .a-image:hover{
    cursor:pointer;
  }

  .a-text{
    margin-left:55px;
    width: 445px;
    font-size: 20px;
    font-weight: 700;
  }

  .el-input{
    float:right;
    margin-top: 20px;
    font-size: 20px;
    font-weight: 700;
    margin-right: 115px;
  }
</style>