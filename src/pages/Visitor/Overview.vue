<template>
  <div class="common-layout">
    <el-container>
      <el-container>
        <el-aside>
         <!-- 侧边栏 -->
         <div class="sidebar">
            <ul class="nav2">
                <li>
                    <a href="#" data-target="whole">
                        全部分类
                    </a>
                </li>
                <li>
                    <a href="#" data-target="mind">
                        思想政治类
                    </a>
                </li>
                <li>
                    <a href="#" data-target="tech">
                        学术科技类
                    </a>
                </li>
                <li>
                    <a href="#" data-target="sport">
                        文化体育类
                    </a>
                </li>
                <li>
                    <a href="#" data-target="innovation">
                        创新创业类
                    </a>
                </li>
                <li>
                    <a href="#" data-target="volunteer">
                        志愿公益类
                    </a>
                </li>
                <li>
                    <a href="#" data-target="help">
                        自律互助类
                    </a>
                </li>
            </ul>
        </div>
        </el-aside>

        <el-main>
          
           <!-- 内容区域 -->
        <div id="content">
            <div class="container-fluid">
                <div id="whole" class="page">
                  <div class = "box">
                    <div class = "image"></div>
                      <div>
                        <h3>我是街舞社</h3>
                        <p>机务段回款及哦孩子小·</p>
                        <p>何须问i哦健康码</p>
                      </div>
                  </div>
                  <div class = "box">这是一个社团</div>
                  <div class = "box">这是一个社团</div>
                  <div class = "box">这是一个社团</div>
                
                </div>
                <div id="mind" class="page" style="display:none">
                  <div v-for = "club in lists" class = "box">
                    <div class = "image"></div>
                        <h3>{{club.name}}</h3>
                        <p>{{club.class}}</p>
                        <p>指导单位{{club.unit}}</p>
                        <p>指导老师：{{club.teacher}}</p>
                      </div>
                </div>
                <div id="tech" class="page" style="display:none">
                    <h1>概览内容</h1>
                    <p>这是概览的内容。</p>
                </div>
                <div id="sport" class="page" style="display:none">
                    <h1>事件内容</h1>
                    <p>这是事件的内容。</p>
                </div>
                <div id="innovation" class="page" style="display:none">
                    <h1>关于内容</h1>
                    <p>这是关于的内容。</p>
                </div>
                <div id="volunteer" class="page" style="display:none">
                    
  <div class = "box" v-for="(club, index) in lists" :key="index">
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
                <div id="help" class="page" style="display:none">
                    <h1>联系我们内容</h1>
                    <p>这是联系我们的内容。</p>
                </div>
            </div>

        <!-- 添加分页 -->

        </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup name = "Overview">
  import { onMounted, ref } from 'vue';  
  import { Search } from '@element-plus/icons-vue';

  const input2 = ref('')
  const dialogVisible = ref({}); 

  function setDialogVisible(index, isVisible) {  
  dialogVisible.value[index] = isVisible;  
}  


  const lists = [
    {
      name:"AOE舞蹈社",
      class:"文化体育类",
      unit:"武汉大学计算机学院",
      teacher:"周晓晓"
    },
    {
      name:"超级爱发电社",
      class:"自律互助类",
      unit:"武汉大学计算机学院",
      teacher:"程嘉佳"
    },
    {
      name:"作业好多写不完社",
      class:"自律互助类",
      unit:"北京大学计算机学院",
      teacher:"three"
    },
    {
      name:"AOE舞蹈社",
      class:"文化体育类",
      unit:"武汉大学计算机学院",
      teacher:"周晓晓"
    },
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

.sidebar .nav2{

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
  background-color:#1684FC;
  width:500px;
  height:300px;
  margin-top:35px;
  margin-bottom:30px;
}

.image{
  background-color:#d1d9e6;
  width:300px;
  height:300px;
}
</style>