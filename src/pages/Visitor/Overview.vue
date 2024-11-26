<template>
  <div class="common-layout">
    <el-container>
      <el-container>
        <el-aside>
         <!-- 侧边栏 -->
         <div class="sidebar">
          <ul class = "nav2">
            <li v-for="category in categories" :key="category.name">  
               <a @click="filterByTag(category.name)" href="#" :data-target="category.name" :class="{ 'active': activeTarget === category.name } ">  
                  {{ category.name }}  
               </a>  
          </li>  
       </ul>

       <Robot />
        </div>
        </el-aside>


        <el-main>

          <!-- 搜索区域 -->
          <el-select
            v-model="currentC"
            placeholder="计算机学院"
            size="large"
            class ="search2"
    ><el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>

           <!-- 内容区域 -->
        <div id="content" style="overflow: hidden; margin-left: 24px;">
            <div class="container-fluid">
              <Pagination :lists="finalList" />
            </div>
        </div>

        <div class = "bgi"></div>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup name = "Overview">
  import { ref,computed } from 'vue';  
  import Pagination from './Pagination.vue';
  import axios from 'axios';
  import Robot from './Robot.vue'

  // 当前选中的标签 
  const currentTag = ref(''); 

  // 当前选择的学院
  const currentC = ref('')

  // 存储当前选中的链接的data-target值  
  const activeTarget = ref('');

  
  const categories = [  
    { name: '全部分类'},  
    { name: '思想政治类'},  
    { name: '学术科技类'},  
    { name: '文化体育类'},  
    { name: '创新创业类'},  
    { name: '志愿公益类'},  
    { name: '自律互助类'}  
  ];  

  const options = [
    {
      value: '计算机学院',
      label: '计算机学院',
    },
   {
      value: '数学与统计学院',
      label: '数学与统计学院',
    },
    {
      value: '法学院',
      label: '法学院',
    },
    {
      value: '哲学学院',
      label: '哲学学院',
    },
    {
      value: '信息管理学院',
      label: '信息管理学院',
    },
    {
      value: '',
      label: '所有学院',
    },
  ]
 
  const lists = ref([])//社团列表
  
  //获取社团列表
  const getClubList = async () =>{
    try {  
        const response = await axios.get('http://localhost:8080/club/list');  
        console.log('获取社团数据',response.data.data.records)
        lists.value = response.data.data.records

      } catch (error) {  
        // 处理错误  
        console.error('注册失败', error);  
   
      }  
  }
  getClubList()
    
  // 根据当前标签过滤列表  
  const filteredLists = computed(() => {  
    if (currentTag.value === '全部分类' || currentTag.value === '') {  
      return lists.value;
    }  
    return lists.value.filter(item => item.tags === currentTag.value);  
  });  


  //根据选择的学院过滤列表
  const finalList = computed(() =>{
    if(currentC.value === ''){
      return filteredLists.value;
    }
    console.log("选择的学院",currentC.value)
    console.log("最终传入列表",filteredLists.value.filter(item => item.collage === currentC.value))
    return filteredLists.value.filter(item => item.collage === currentC.value);
  })

  // 根据点击的标签来过滤列表  
  function filterByTag(tag) {  
    currentTag.value = tag;  
    activeTarget.value = tag;  
  }

 
</script>

<style scoped>
  li{
    list-style-type:none;
  }

  a{
    text-decoration:none;
    /* padding:auto auto; */
  }

  .header{
    display:flex;
    align-items:center;
  }

  .icon{
    font-size:35px;
    font-weight:700;
    color:#9A9A9A;
    margin-left:3rem;
    margin-right:8rem
  }

  .nav{
    margin-left:10rem;
    color:#9A9A9A;
    font-weight:700;
  }

  .search{
    margin-left:10rem;
  }

  /* 学院搜索栏 */
  .search2{
    width: 240px;
    float: right;
    height: 60px;
    font-size:25px;
    font-weight:700;
  }

/* 侧边栏样式 */
.sidebar{
    z-index: 100;
    left:0;
    width: 300px;
    height:52.25rem;
    overflow-y: auto;
}

.sidebar .nav2 a{
    font-size:1.5rem;
    display:block;
    background: none;
    color:#6C6C6C;
    margin-top:3.5rem;
    text-align:center;
    margin-left: -80px;
}

.sidebar .nav2 a:hover{
    background: none;
    color: #1684FC;
}

.nav2 a.active {  
  color: #1684FC;
} 

.el-main{
  background-color:#EFEFEF;
}

.page{
  display:flex;
  justify-content:space-evenly;
  flex-wrap:wrap;
}

.box{
  margin-left:50px;
  display:flex;
  width:500px;
  height:300px;
  margin-top:35px;
  margin-bottom:30px;
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

  .bgi{
    z-index: 0;
    position: absolute; 
    left: 1530px;
    top:600px;
    width: 360px;
    height: 250px;
    background-size: cover;
    background-image: url(@/picture/bgimage.png);
  }
</style>