<template>
  <div class = "body">
    <div class = "shell">
      <div class = "container a-container" id = "a-container">
        <form action = "" method = "" class = "form" id = "a-form" @submit.prevent = "handleCreate">
          <h2 class = "form_title title">创建账号</h2>
          <input type="text" class="form_input" placeholder="用户名" v-model="registerData.username" />  
          <span v-if="errors.username" class="error-message">{{ errors.username }}</span>  
          <input type="password" class="form_input" placeholder="密码" v-model="registerData.password" />  
          <span v-if="errors.password" class="error-message">{{ errors.password }}</span>  
          <input type="password" class="form_input" placeholder="再次输入密码" v-model="registerData.rePassword" />  
          <span v-if="errors.rePassword" class="error-message">{{ errors.rePassword }}</span>  
          <button class="form_button button submit" @click="validateForm">SIGN UP</button>  
        </form>
      </div>

      <div class = "container b-container" id = "b-container">
        <form action = "" method = "" class = "form" id = "b-form">
          <h2 class = "form_title title">登入账号</h2>
          <input type = "text" class = "form_input" placeholder="用户名">
          <input type = "text" class = "form_input" placeholder="密码">
          <a href = "#" class = "form_link" @click = "handleForget">忘记密码？</a>
          <button class = "form_button button submit" @click = "handleLogin">SIGN IN</button>
        </form>
      </div>

      <div class = "switch" id = "switch-cnt">
        <div class = "switch_circle"></div>
        <div class = "switch_circle switch_circle-t"></div>

        <div class = "switch_container" id = "switch-c1">
          <h2 class = "switch_title title" style = "letter-spacing: 0;">Welcome Back !</h2>
          <p class = "switch_description description">已经有账号了嘛,去登入账号吧！！！</p>
          <button class = "switch_button button switch-btn">SIGN IN</button>
        </div>

        <div class = "switch_container is-hidden" id = "switch-c2">
          <h2 class = "switch_title title" style="letter-spacing: 0;">Hello Friend !</h2>
          <p class = "switch_description description">去注册一个账号吧~</p>
          <button class = "switch_button button switch-btn">SIGN UP</button>
        </div>

      </div>

    </div>
  </div>
  </template>

<script setup name = "Login">
  import { onMounted} from 'vue';  
  import { ref, reactive, computed} from 'vue'
  import axios from 'axios'

  import { useWebStore } from '@/stores/web.js';

  const webStore = useWebStore();

  // 定义数据类型
  const registerData = reactive({
    username:'',
    password:'',
    rePassword:''
  })

  const errors = reactive({  
      username: '',  
      password: '',  
      rePassword: ''  
    });  

  const validateForm = () => {  
      errors.username = registerData.username.trim() === '' ? '用户名不能为空' : '';  
      errors.password = registerData.password.trim() === '' ? '密码不能为空' : '';  
      errors.rePassword = registerData.rePassword.trim() === '' ? '请再次输入密码' :  
                          registerData.rePassword !== registerData.password ? '两次输入密码不一致' : '';  
  
      if (!errors.username && !errors.password && !errors.rePassword) {  
        handleCreate();  
      } 
      else{
        Object.assign(registerData,{
          username:'',
          password:'',
          rePassword:''
        })
      } 
    };  

    const handleCreate = async () => {  
      // 处理表单提交的逻辑  
      try {  
        // 发送POST请求到后端API  
        const response = await axios.post('http://127.0.0.1:4523/m1/4751967-4405137-default/user/register', registerData);  
        // 处理响应  
        console.log('注册成功', response);  
        // 页面跳转（更新导航栏)
      } catch (error) {  
        // 处理错误  
        console.error('注册失败', error);  
        // 你可以在这里显示错误消息给用户  
      }  
      console.log('表单提交成功', registerData);  
    };  

  function handleForget(){//忘记密码
    console.log("我点了")
  }

  function handleLogin(){//登录
    webStore.web.status = true;
  }

  onMounted(() => {  
    let switchCtn = document.querySelector("#switch-cnt");
    let switchC1 = document.querySelector("#switch-c1");
    let switchC2 = document.querySelector("#switch-c2");
    let switchCircle = document.querySelectorAll(".switch_circle");
    let switchBtn = document.querySelectorAll(".switch-btn");
    let aContainer = document.querySelector("#a-container");
    let bContainer = document.querySelector("#b-container");
    let allButtons = document.querySelectorAll(".submit");

    let getButtons = (e) => e.preventDefault()
    let changeForm = (e) => {
      //修改类名
      switchCtn.classList.add("is-gx");
      setTimeout(function() {
        switchCtn.classList.remove("is-gx");
      }, 1500)
      switchCtn.classList.toggle("is-txr");
      switchCircle[0].classList.toggle("is-txr");
      switchCircle[1].classList.toggle("is-txr");

      switchC1.classList.toggle("is-hidden");
      switchC2.classList.toggle("is-hidden");
      aContainer.classList.toggle("is-txl");
      bContainer.classList.toggle("is-txl");
      bContainer.classList.toggle("is-z");
    }
    //点击切换
    let shell = (e) =>{
      for(let i = 0;i< allButtons.length;i++)
        allButtons[i].addEventListener("click",getButtons);
      for(let i = 0;i< switchBtn.length;i++)
        switchBtn[i].addEventListener("click",changeForm)
    }
    window.addEventListener("load",shell);
  }
)


</script>

<style scoped>

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
  /* 字体无法选中 */
  user-select:none;
}

.body{
  width:100%;
  height:90vh;
  display:flex;
  justify-content:center;
  align-items:center;
  font-size:12px;
  background-color:#ecf0f3;
  color:#a0a5a8;
}

.shell{
  position:relative;
  width:1000px;
  min-width:1000px;
  min-height:600px;
  height:600px;
  padding:25px;
  background-color:#ecf0f3;
  box-shadow:10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius:12px;
  overflow:hidden;
}

/* 设置响应式 */
@media (max-width:1200px){
    .shell{
      transform:scale(0.7);
    }
}

@media (max-width:1000px){
    .shell{
      transform:scale(0.6);
    }
}

@media (max-width:800px){
    .shell{
      transform:scale(0.5);
    }
}

@media (max-width:600px){
    .shell{
      transform:scale(0.4);
    }
}

.container{
  display:flex;
  justify-content:center;
  align-items:center;
  position:absolute;
  top:0;
  width:600px;
  height:100%;
  padding:25px;
  background-color:#ecf0f3;
  transition:1.25s;
}

.form{
  display:flex;
  justify-content:center;
  align-items:center;
  flex-direction:column;
  width:100%;
  height:100%;
}

.form_input{
  width:350px;
  height:40px;
  margin:4px 0;
  padding-left:25px;
  font-size:13px;
  letter-spacing:0.15px;
  border:none;
  outline:none;
  background-color:#ecf0f3;
  transition:0.25s ease;
  border-radius:8px;
  box-shadow:inset 2px 2px 4px #d1d9e6,inset -2px -2px 4px #f9f9f9;
}

.form_input:focus{
  box-shadow:inset 4px 4px 4px #d1d9e6,inset -4px -4px 4px #f9f9f9;
}

.form_span{
  margin-top:30px;
  margin-bottom:12px;
}

.form_link{
  color:#181818;
  font-size:15px;
  margin-top:25px;
  border-bottom:1px solid #a0a5a8;
  line-height:2;
  text-decoration:none;
}

.title{
  font-size:34px;
  font-weight:700;
  line-height:3;
  color:#181818;
  letter-spacing:10px;
}

.description{
  font-size:14px;
  letter-spacing:0.25px;
  text-align:center;
  line-height:1.6;
}

.button{
  width:180px;
  height:50px;
  border-radius:25px;
  margin-top:50px;
  font-weight:700;
  font-size:14px;
  letter-spacing:1.15px;
  background-color:#4870e2;
  color:#f9f9f9;
  box-shadow:8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border:none;
  outline:none;
}

.a-container{
  z-index:100;
  left:calc(100% - 600px);
}

.b-container{
  left:calc(100% - 600px);
  z-index:0;
}

.switch{
  display:flex;
  justify-content:center;
  align-items:center;
  position:absolute;
  top:0;
  left:0;
  height:100%;
  width:400px;
  padding:50px;
  z-index:200;
  transition:1.25s;
  background-color:#ecf0f3;
  overflow:hidden;
  box-shadow:4px 4px 10px #d1d9e6,-4px -4px 10px #d1d9e6;
}

.switch_circle{
  position:absolute;
  width:500px;
  height:500px;
  border-radius:50%;
  background-color:#ecf0f3;
  box-shadow:inset 8px 8px 12px #b8bec7 ,inset -8px -8px 12px #fff;
  bottom: -60%;
  left: -60%;
  transition:1.25s;
}

.switch_circle-t{
  top:-30%;
  left:60%;
  width:300px;
  height:300px;
}

.switch_container{
  display:flex;
  justify-content:center;
  align-items:center;
  flex-direction:column;
  position:absolute;
  width:400px;
  padding:50px 55px;
  transition:1.25s;
}

.switch_button{
  cursor:pointer;
}

.switch_button:hover,
.submit:hover{
  box-shadow:6px 6px 10px #d1d9e6,-6px -6px 10px #f9f9f9;
  transform:scale(0.985);
  transition:0.25s;
}

.switch_button:active,
.switch_button:focus{
  box-shadow:2px 2px 6px #d1d9e6,-2px -2px 6px #f9f9f9;
  transform:scale(0.97);
  transition:0.25s;
}

.is-txr{
  left:calc(100% - 400px);
  transition:1.25s;
  transform-origin:left;
}

.is-txl{
  left:0;
  transition:1.25s;
  transform-origin:right;
}

.is-z{
  z-index:200;
  transition:1.25s;
}

.is-hidden{
  visibility: hidden;
  opacity:0;
  position:absolute;
  transition:1.25s;
}

.is-gx{
  animation:is-gx 1.25s;
}
@keyframes is-gx{
  0%,
  10%,
  100%{
    width:400px;
  }

  30%,
  50%{
    width:500px;
  }
}

.error-message{
  font-size:16px;
  font-weight:700;
}
</style>