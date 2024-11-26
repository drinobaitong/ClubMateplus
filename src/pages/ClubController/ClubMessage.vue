<template>
  <div class="common-layout">
    <!---侧边栏--->
    <el-container>
      <el-aside class="aside" >
        <!---切换--->
        <el-menu
            background-color="#f6f1ea"
            class="el-menu-vertical-demo"
            text-color="#090A0B"
        >
          <!---头像--->
          <el-row class="demo-avatar demo-basic">
            <el-col :span="20">
              <div class="demo-basic--circle">
                <div class="block">
                  <el-avatar :size="50"  />
                </div>
                <div class="sub-title">admin</div>
              </div>
            </el-col>
          </el-row>
          <div class="menu-list">
            <el-menu-item index="1">
              <el-icon><icon-menu /></el-icon>
              <span >首页</span>
            </el-menu-item>
            <router-link to="/CLubMessage">
              <el-menu-item index="2" >
                <el-icon><document /></el-icon>
                <span>社团信息管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ClNumberReview">
              <el-menu-item index="3">
                <el-icon><setting /></el-icon>
                <span>成员审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ClNumberControl">
              <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>成员管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/PostControl">
              <el-menu-item index="5">
                <el-icon><setting /></el-icon>
                <span>发帖管理</span>
              </el-menu-item>
            </router-link>
            <router-link to="/ExitControl">
              <el-menu-item index="6">
                <el-icon><setting /></el-icon>
                <span>退出审核</span>
              </el-menu-item>
            </router-link>
            <router-link to="/clContentControl">
              <el-menu-item index="7">
                <el-icon><setting /></el-icon>
                <span>评论管理</span>
              </el-menu-item>
            </router-link>
          </div>
        </el-menu>
      </el-aside>
      <!---上边栏--->
      <el-container >
        <el-header class="header">
          <span class="text-large font-600 mr-3"><h2>社团信息管理 </h2></span>
        </el-header>
        <!---主界面--->
        <el-main>
          <el-header>
            <el-row :gutter="20">
              <el-col :span="6">
                社团名称
                <el-input
                    v-model="data.tableData.name"
                    style="width: 240px"
                    clearable
                /><div class="grid-content ep-bg-purple" /></el-col>
              <el-col :span="6" :offset="6">
                <div class="grid-content ep-bg-purple" />
                社团分类
                <el-select
                    v-model="data.tableData.type"
                    placeholder="Select"
                    style="width: 240px"
                >
                  <el-option
                      v-for="item in data.type"
                      :key="item.type"
                      :label="item.type"
                      :value="item.type"
                  />
                </el-select>
              </el-col>
            </el-row>
          </el-header>

          <!---中间--->
          <el-main>
            社团头像
                <el-upload
                    v-model:file-list="fileList"
                    action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                >
                  <el-icon><Plus /></el-icon>
                </el-upload>
                <el-dialog v-model="dialogVisible">
                  <img w-full :src="dialogImageUrl" alt="Preview Image" />
                </el-dialog>
            <el-row :gutter="20">
              <el-col :span="6">
                创建日期
                <el-input
                    v-model="data.tableData.registerTime"
                    style="width: 240px"
                    disabled
                />
                <div class="grid-content ep-bg-purple" /></el-col>
              <el-col :span="6" :offset="6">
                <div class="grid-content ep-bg-purple" />
                学院
                <el-select
                    v-model="data.tableData.collage"
                    placeholder="Select"
                    style="width: 240px"
                >
                  <el-option
                      v-for="item in data.department"
                      :key="item.name"
                      :label="item.name"
                      :value="item.name"
                  />
                </el-select>
              </el-col>
            </el-row>
          </el-main>

          <!---下边--->
          <el-footer>
            <el-row :gutter="20">
              <el-col :span="6">
                <div >社团简介</div>
                <el-input
                    v-model="data.tableData.introduce"
                    style="width: 340px"
                    :rows="7"
                    type="textarea"
                    placeholder="Please input"
                />
                <div class="grid-content ep-bg-purple" /></el-col>
              <el-col :span="6" :offset="6">
                <div class="grid-content ep-bg-purple" />
                负责人姓名
                <el-input
                    v-model="data.tableData.createUserName"
                    style="width: 240px"
                    :placeholder="placeholderText2" 
                    clearable
                />
                <br><br><br>
                <el-row :gutter="20">
                  <el-col :span="6">
                    <el-button type="danger" plain @click="open">注销</el-button>
                    <div class="grid-content ep-bg-purple" /></el-col>
                  <el-col :span="6" :offset="6">
                    <div class="grid-content ep-bg-purple" />
                    <el-button type="success" plain @click="open2">提交</el-button>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-footer>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import {computed, reactive, toRefs,watch} from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import type { UploadProps, UploadUserFile } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
import request from "@/request/request";
import {useWebStore} from '@/stores/web'

const webStore = useWebStore()
let club
const placeholderText = ref('')
const placeholderText2 = ref('')

const fileList = ref<UploadUserFile[]>([]); // 初始化为空数组  

//初始数据
const data = reactive(
  {
    tableData:{
      name: '',
      type:'',
      collage: '',
      registerTime: '',
      avatarUrl:'',
      introduce:'',
      createUserId:'',
      createUserName:'',
      status:''
    },
    department:[],
    type:[],
  });

//获取管理社团的基本信息
async function getManageClubData() {
  try {
      // 获取社团详细信息
      const clubDetailResponse = await axios.get(
          `http://localhost:8080/club/get/${webStore.web.clubId}`
      );
      club = clubDetailResponse.data.data
      console.log('管理社团返回club',club)
      placeholderText.value = club.tags
      data.tableData.avatarUrl = club.avatarUrl

      fileList.value = [  
      {  
        name: 'food.jpeg',  
        url: data.tableData.avatarUrl, // 假设 data.tableData 已经在其他地方被定义和更新  
      },  
    ];  
      // 获取用户详细信息
      const studentDetailResponse = await axios.get(
          `http://localhost:8080/user/getInfo/${clubDetailResponse.data.data.createUserId}`
      );
      placeholderText2.value =  studentDetailResponse.data.data.name
  
  }catch (error) {
    console.error('获取社团基本信息失败', error)
  }
}

getManageClubData()


const load=()=>{
  request.get(`/club/get/${webStore.web.clubId}`
  ).then(res=>{
    data.tableData=res.data.data
  })
  request.get('/department/getAll').then(res=>{
    console.log(res)
    data.department=res.data.data
  })
  request.get('/clubtype/allType').then(res=>{
    data.type=res.data.data
  })
}
load()


// const fileList = ref<UploadUserFile[]>([
//   {
//     name: 'food.jpeg',
//     url: data.tableData.avatarUrl,
//   },
// ])


const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}

const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}

const open = () => {
  ElMessageBox.alert('确定要注销吗', '注销', {
    autofocus: false,
    confirmButtonText: 'OK',
    callback: (action: Action) => {
      data.tableData.status='5'
      request.put('/club/update',data.tableData).then(res=>{
        console.log(res)
        if(res.data.code=='200'){
          load()
          ElMessage({
            type: 'info',
            message: '注销成功',
          })
        }else {
          ElMessage.error('注销失败')
        }

      })

    },
  })
}

const open2 = () => {
  ElMessageBox.alert('确定要提交吗', '修改', {
    autofocus: false,
    confirmButtonText: 'OK',
    callback: (action: Action) => {
      data.tableData.status='3'
      request.put('/club/update',data.tableData).then(res=>{
        console.log(res)
        if(res.data.code=='200'){
          load()
          ElMessage({
            type: 'info',
            message: '提交成功',
          })
        }else {
          ElMessage.error('提交失败')
        }
      })
    },
  })
}
</script>

<style scoped>
.demo-basic {
  text-align: center;
}
.demo-basic .sub-title {
  margin-bottom: 10px;
  font-size: 18px;
  color: var(--el-text-color-secondary);
}
.demo-basic .demo-basic--circle{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 50px;
  margin-bottom: 20px;
}
.demo-basic .block {
  flex: 1;
}
.aside{
  width:200px;
  height:750px;
  background-color: #f6f1ea;
}
.menu-list{
  margin-left: 15px;
}
.header{
  background: rgba(90, 122, 154, 0.15);
  height:80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.demo-form-inline .el-input {
  --el-input-width:120px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
.common-layout{
  background: rgba(153, 217, 202, 0.08);
}
</style>