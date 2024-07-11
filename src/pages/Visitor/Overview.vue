<template>
  <div class="common-layout">
    <el-container>
      <el-container>
        <el-aside>
         <!-- 侧边栏 -->
         <div class="sidebar">
          <ul class = "nav2">
            <li v-for="category in categories" :key="category.target">  
               <a href="#" :data-target="category.target" :class="{ 'active': activeTarget === category.target }">  
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
                <div id="whole" class="page">
                  <div class = "box" v-for="(club, index) in lists" :key="index">
                    <NotLogged :club = club :setDialogVisible = setDialogVisible :wid=300 :hei=300 />
                    <div style="margin-left:40px ;">
                   <h4 style="font-size: 24px;">{{club.name}}</h4>
                   <p>{{club.tags}}</p>
                   <p>{{club.unit}}</p>
                </div>
           </div>
                </div>

                <!-- 思想政治类 -->
                <div id="mind" class="page" style="display:none">
                </div>

                <!-- 学术科技类 -->
                <div id="tech" class="page" style="display:none">
                </div>

                <!-- 文化体育类 -->
                <div id="sport" class="page" style="display:none">
                </div>

                <!-- 创新创业类 -->
                <div id="innovation" class="page" style="display:none">
                </div>

                <!-- 志愿公益类 -->
                <div id="volunteer" class="page" style="display:none">
                </div>

                <!-- 自律互助类 -->
                <div id="help" class="page" style="display:none">
                </div>
            </div>

        <!-- 添加分页 -->
         <div class = "pagination">
        <el-pagination background layout="prev, pager, next"    class="mt-4"  :total="50"/>
      </div>
        </div>

        <div class = "bgi"></div>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup name = "Overview">
  import { ref } from 'vue';  
  import NotLogged from './NotLogged.vue';

  const dialogVisible = ref({}); 

  function setDialogVisible(index, isVisible) {  
  dialogVisible.value[index] = isVisible;  
}  

// 存储当前选中的链接的data-target值  
const activeTarget = ref('');  
  
const categories = [  
  { name: '全部分类', target: 'whole' },  
  { name: '思想政治类', target: 'mind' },  
  { name: '学术科技类', target: 'tech' },  
  { name: '文化体育类', target: 'sport' },  
  { name: '创新创业类', target: 'innovation' },  
  { name: '志愿公益类', target: 'volunteer' },  
  { name: '自律互助类', target: 'help' }  
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
    }
  ]
  
  document.addEventListener('DOMContentLoaded', function () {  
    // 给左侧菜单栏添加点击事件处理程序  
    const navLinks = document.querySelectorAll(".sidebar .nav2 a");  
    navLinks.forEach(link => {  
        link.addEventListener('click', function (event) {  
            event.preventDefault(); // 阻止链接的默认行为（如果它们是真正的链接）  
  
            const target = this.getAttribute('data-target');  
  
            // 隐藏所有页面内容  
            const pages = document.querySelectorAll('.page');  
            pages.forEach(page => {  
                page.style.display = 'none';  
            });  
  
            // 显示目标页面内容  
            const targetPage = document.querySelector('#' + target);  
            if (targetPage) {  
                targetPage.style.display = 'flex';  
            }  
        });  
    });  
  
    // 显示默认的页面 全部分类 
    const whole = document.querySelector('#whole');  
    if (whole) {  
        whole.style.display = 'flex';  
    }  
});
</script>

<style scoped>
  li{
    list-style-type:none;
  }

  a{
    text-decoration:none;
    padding:auto auto;
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

  .pagination{
    margin-top: 20px;
    margin-left: 650px;
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