  <template>
     <el-button plain @click="setDialogVisible(index, true)" 
     :style="{ width: wid + 'px', height: hei + 'px',
     backgroundImage: `url(${props.club.avatarUrl})`, 
     backgroundSize:'cover' }">
      {{ props.where ===  1? '查看': ''}}
     </el-button>
       <el-dialog
         v-model="dialogVisible[index]"
         fullscreen
         destroy-on-close
         center
        align-center>
<div class = "detail">
  <!-- 显示图片 -->
  <div class = "picture" :style = "pictureShow"></div>
  <!-- 文字内容 -->
  <div class = "content"> 
    <div style = "display: flex; align-items: center;">
    <p class = "detail-t">{{ club.name }}</p>

    <!-- 还要判断是否已加入 -->
  <WriteArticle v-if="webStore.web.status && isIn" />
    <el-button class = "btn" @click = "collect" v-if = "webStore.web.status">{{ collectOrNot }}</el-button>
    <el-button class = "btn" @click = "join" v-if = "webStore.web.status" :disabled="isIn">加入</el-button>
    <el-button class = "btn" @click = "goBack(index, false)">返回</el-button>
  </div>
    <div class = "mid">
      <span class = "detail-o">{{ club.tags }}</span>
      <span class = "detail-o">创建于 : {{club.registerTime}}</span>
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
<div class = "a-content" v-for="article in informationList">
  <div class = "a-header">
    <div class = "a-text"> <p>{{ article.title }}</p></div>
    <span class = "publisher">{{ article.personName }}</span>
  </div>
  <!-- <Post :articleId ="article.id" /> -->
 <div class = "a-image" @click = "openArticle(article.id)" :style="{
  backgroundImage: `url(${article.avatarUrl})`,
  backgroundSize:'cover'
 }"></div>
 <div style="margin-top: 15px;margin-left: 60px;">发布日期：{{ article.registerTime }}</div>
 <div :class="{ 'showEssence': article.essence === 1} "></div>
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
  import { ref ,computed} from 'vue';  
  import { Search } from '@element-plus/icons-vue';
  import { ElNotification } from 'element-plus'
  import { useWebStore } from '@/stores/web.js';
  import { useRouter } from 'vue-router';
  import WriteArticle from '@/pages/MeHome/WriteArticle.vue'
  import axios from 'axios';

  const rt = useRouter()

  const collectOrNot = ref('收藏')

  //声明接收的props
  const props =   defineProps(['club','wid','hei','where'])
  

  // 计算属性来返回包含样式的对象  
const pictureShow = computed(() => ({  
  width: '30%',  
  height: '80%',  
  backgroundImage: `url(${props.club.avatarUrl})`,  
  backgroundSize: 'cover',  
  backgroundPosition: 'center',  
}));  



  //是否加入社团
  let isIn = ref(false)
  const input1 = ref('')

  const webStore = useWebStore();

  const articleList = ref([])

  const informationList = ref([])

  function handleSearch(){//搜索响应函数
  }
  const dialogVisible = ref({}); 

  async function judgeCollection(){
    //判断是否收藏
    try{
      const response2 = await axios.get('http://localhost:8080/collection/exit',{
        params:{
          userId:webStore.web.uid,
          clubId:webStore.web.clubId
        }
      })
      if(response2.data.data === true){
        collectOrNot.value = '取消收藏'
      }
      else{
        collectOrNot.value = '收藏'
      }
    }catch(error){
      console.log('判断是否收藏的error',error)
    }
  }


  async function judgeAdd(){
    //判断是否加入
    try{
      const response2 = await axios.get('http://localhost:8080/join/exit',{
        params:{
          userId:webStore.web.uid,
          clubId:webStore.web.clubId
        }
      })
      if(response2.data.data === true){
        isIn.value = true
      }
      else{
        isIn.value = false
      }
    }catch(error){
      console.log('判断是否加入的error',error)
    }
  }

  async function setDialogVisible(index, isVisible) {  
    webStore.web.isShow = false
    dialogVisible.value[index] = isVisible;  
    webStore.web.clubId = props.club.id
    judgeCollection()
    judgeAdd()
    getArticleLists()//获取社团内的所有帖子
  }  

  function goBack(index, isVisible){
    webStore.web.isShow = true
    dialogVisible.value[index] = isVisible;  
  }

  async function join(){//加入社团
    //已在社团内按钮需禁用
    if(isIn.value === false){
        await axios.post('http://localhost:8080/join',{
        userId:webStore.web.uid,
        clubId:webStore.web.clubId
      })
      ElNotification({title: 'Success',message: '已发出入团申请！',type: 'success',
    })}
    else{
      ElNotification({title: 'Error',message: '入团申请发送失败',type: 'error',
    })}
  }

    const collect = async() =>{//收藏社团
      const cObject = {
      userId:webStore.web.uid,
      clubId:webStore.web.clubId
    }
    //未收藏
    if(webStore.web.status){
      if(collectOrNot.value === '收藏'){
      const response = await axios.post('http://localhost:8080/collection/add',cObject)
      judgeCollection()
      ElNotification({title: 'Success',message: '已收藏该社团！',type: 'success',})
    }
    //已收藏
    else{
      const response = await axios.post('http://localhost:8080/collection/delete',cObject)
      judgeCollection()
      ElNotification({title: 'Success',message: '已取消收藏该社团',type: 'success',
    })}
    }
  }

  async function getArticleLists(){//获取社团内的所有帖子
    const response = await axios.get(`http://localhost:8080/articles/club/${webStore.web.clubId}`)
    console.log('获取社团内的所有帖子',response.data.data.records)
    articleList.value = response.data.data.records

    for(let art of articleList.value){
      const response = await axios.get(`http://localhost:8080/user/getInfo/${art.createUserId}`)
      let info = {
        ...art,
        personName:response.data.data.name
      }
      informationList.value.push(info)
    }
  }

  //打开对应帖子页
  function openArticle(articleId){
    //是否需要进行登录验证？
    rt.push({ name: 'PostArticle', params: { articleId: articleId } });  
  }

</script>

<style scoped>

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

.article{
  width: 100%;
  display: flex;
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
    margin-left: 75px;
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
    margin-left: -50px;
    font-size: 20px;
    font-weight: 700;
    margin-top: -15px;
  }

  .a-content{
    background-color: #f7f7f7;
    width: 540px;
    height: 360px;
    margin-right: 40px;
    margin-bottom: 40px;
  }

  .a-image{
    background-image: url(@/picture/03.jpg);
    width: 445px;
    height:200px;
    margin-left:55px;
    /* margin-top: 15px; */
  }

  .a-image:hover{
    cursor:pointer;
  }

  .a-text{
    margin-top: -15px;
    margin-left:5px;
    width: 300px;
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

  .writeArticle{
    margin-left: -150px;
    margin-right: 50px;
  }

  .writeArticle:hover{
    cursor: pointer;
  }

  .write{
    margin-top: 10px;
  }

  .write-label{
    margin-left: 20px;
    font-size: 20px;
    font-weight: 700;
    float: right;
    margin-top: 18px;
  }

  .showEssence{
    width: 40px;
    height: 40px;
    background-size: cover;
    float: right;
    background-image: url(@/picture/essence.png);
    margin-right: 40px;
    margin-top: -20px;
  }
</style>