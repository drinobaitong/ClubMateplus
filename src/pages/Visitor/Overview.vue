<template>
  <div class="common-layout">
    <el-container>
      <el-container>
        <el-aside>
         <!-- 侧边栏 -->
         <div class="sidebar">
          <ul class = "nav2">
            <li v-for="category in categories" :key="category.name">  
               <a @click="filterByTag(category.name)" href="#" :data-target="category.name">  
                  {{ category.name }}  
               </a>  
          </li>  
       </ul>
        </div>
        </el-aside>

        <el-main>

          <!-- 搜索区域 -->
          <el-select
      v-model="value"
      placeholder="计算机学院"
      size="large"
      style="width: 240px; float:right ;height: 60px; font-size: 25px;font-weight: 700;"
    ><el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>

           <!-- 内容区域 -->
        <div id="content" style="overflow: hidden;">
            <div class="container-fluid">
              <Pagination :lists="filteredLists" />
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

  // 当前选中的标签 
  const currentTag = ref(''); 
  
  const categories = [  
    { name: '全部分类'},  
    { name: '思想政治类'},  
    { name: '学术科技类'},  
    { name: '文化体育类'},  
    { name: '创新创业类'},  
    { name: '志愿公益类'},  
    { name: '自律互助类'}  
  ];  
  
  const value = ref('')

  const options = [
    {
      value: 'Option1',
      label: '计算机学院',
    },
   {
      value: 'Option2',
      label: '数学与统计学院',
    },
    {
      value: 'Option3',
      label: '法学院',
    },
    {
      value: 'Option4',
      label: '哲学学院',
    },
    {
      value: 'Option5',
      label: '信息管理学院',
    },
  ]
 
  const lists = [
    {
      id: 90,
      createUserId: 75,
      name:'珞珈晨跑队',
      tags:'文化体育类',
      unit:'弘毅学堂',
      registerTime: "1974-05-20 16:53:33",
      totalNumber:56,
      avatarUrl: "http://dummyimage.com/100x100",
      introduce:'这里是一个汇聚晨光与活力的温暖集体。不论你是跑步的初学者，还是经验丰富的马拉松爱好者，都能找到属于自己的节奏与伙伴。社团定期组织晨跑活动，享受运动带来的快乐与释放。我们鼓励成员间相互激励，分享跑步心得，共同成长。加入晨跑社团，不仅能让你的身体更加强健，更能让你的心灵在晨曦中得到净化与升华，开启一天满满的正能量。让我们一起，用奔跑的姿态，迎接每一个充满希望的新开始！'
    },
    {
      id: 90,
      createUserId: 75,
      registerTime: "1974-05-20 16:53:33",
      totalNumber:56,
      avatarUrl: "http://dummyimage.com/100x100",
      name:'AOE舞蹈队',
      tags:'文化体育类',
      unit:'计算机学院',
      introduce:'舞动青春，韵动梦想！我们是一支充满热情与创意的舞蹈队，以舞为媒，融合多元风格，用每一个跃动的节拍诠释对生活的热爱与追求。'
    },
    {
      id: 90,
      createUserId: 75,
      registerTime: "1974-05-20 16:53:33",
      totalNumber:56,
      avatarUrl: "http://dummyimage.com/100x100",
      name:'安全工作协会',
      tags:'自律互助类',
      unit:'保卫部',
      introduce:'安全护航，责任为先。安全工作协会，致力于构建安全文化，提升安全意识，通过专业培训与交流，共筑安全防线，守护每一份安心与和谐。'
    },
    {
      id: 90,
      createUserId: 75,
      registerTime: "1974-05-20 16:53:33",
      totalNumber:56,
      avatarUrl: "http://dummyimage.com/100x100",
      name:'安全工作协会',
      tags:'自律互助类',
      unit:'保卫部',
      introduce:'安全护航，责任为先。安全工作协会，致力于构建安全文化，提升安全意识，通过专业培训与交流，共筑安全防线，守护每一份安心与和谐。'
    },
    {
      id: 90,
      createUserId: 75,
      registerTime: "1974-05-20 16:53:33",
      totalNumber:56,
      avatarUrl: "http://dummyimage.com/100x100",
      name:'安全工作协会',
      tags:'自律互助类',
      unit:'保卫部',
      introduce:'安全护航，责任为先。安全工作协会，致力于构建安全文化，提升安全意识，通过专业培训与交流，共筑安全防线，守护每一份安心与和谐。'
    }
  ]
  
    
  // 根据当前标签过滤列表  
  const filteredLists = computed(() => {  
    if (currentTag.value === '全部分类' || currentTag.value === '') {  
      return lists;
    }  
    console.log('点击了此标签',lists.filter(item => item.tags === currentTag.value))
    return lists.filter(item => item.tags === currentTag.value);  
  });  

  // 根据点击的标签来过滤列表  
  function filterByTag(tag) {  
    currentTag.value = tag;  
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
}

.sidebar .nav2 a:hover{
    background: none;
    color: #1684FC;
}

.nav2 a .active {  
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