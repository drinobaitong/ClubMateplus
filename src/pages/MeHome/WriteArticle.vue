<template>

<div @click="toWrite" class = "writeArticle" v-if = "webStore.web.status">
    <el-icon size = "40px" class="write"><EditPen /></el-icon><span class="write-label" >发布帖子</span>
  </div>
  <!-- 写帖子页内容 -->
   <el-dialog v-model="dialogTableVisible" style="background-color:#EFEFEF; margin-top: 60px">
    <p class = "title">发布帖子</p>

    <el-form  :model="formInline" class="demo-form-inline">
      <div class = "form-item">
        <span class = "form-label">文章标题</span>
        <el-input v-model="formInline.title" placeholder="请输入标题" clearable style="width: 500px;" />
      </div>
  
      <div class = "form-item">
        <span class = "form-label">发布日期</span>
        <el-date-picker
        v-model="formInline.date"
        type="date"
        placeholder="请选择日期"
        clearable
      />
      </div>

      <div class = "form-item form-add-image">
        <span class = "form-label">添加图片</span>
      <el-upload action="#" list-type="picture-card" :auto-upload="false">
    <el-icon><Plus /></el-icon>

    <template #file="{ file }">
      <div>
        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
        <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
          >
            <el-icon><zoom-in /></el-icon>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleDownload(file)"
          >
            <el-icon><Download /></el-icon>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
          >
            <el-icon><Delete /></el-icon>
          </span>
        </span>
      </div>
    </template>
  </el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
  </div>

          
    <!-- 富文本编辑器 -->
    <div class = "form-item form-editor">
      <span class = "form-label">正文内容</span>
      <div id="editor" class="editor">
      </div>
      </div>

      <div class="form-button">
        <el-button type="primary">发布</el-button>
        <el-button @click = "saveArticle">保存</el-button>
        <el-button @click = "dialogTableVisible = false">返回</el-button>
      </div>
  </el-form>
  
   </el-dialog>
  </template>

<script setup>
  import { useWebStore ,articleStore } from '@/stores/web';
  import {ref , onMounted} from 'vue';
  import E from 'wangeditor';
  import { reactive } from 'vue'
  import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'

  const webStore = useWebStore()
  const article = articleStore()

  const dialogImageUrl = ref('')
  const dialogVisible = ref(false)
  const disabled = ref(false)

const handleRemove = (file) => {
  console.log(file)
}

const handlePictureCardPreview = (file) => {
  if(file && file.url){
    dialogImageUrl.value = file.url
    dialogVisible.value = true
  }
}

const handleDownload = (file) => {
  console.log(file)
}

const formInline = reactive({
  title: article.article.title,
  date: article.article.date,
  content: article.article.content,
})


  const dialogTableVisible = ref(false)

  let editor
  function initWangEditor(content){ setTimeout(() => {
    if(!editor){
      editor = new E('#editor')
      editor.config.placeholder = '请输入内容'
      editor.config.uploadFileName = 'file'
      editor.config.uploadImgServer = 'http://localhost:8080/files/wang/upload'
      editor.create()
    }
    editor.txt.html(content)

    editor.config.onchange = (html) => {
        formInline.content = html// 将草稿保存
      };
  },0)
}

function toWrite(){
  dialogTableVisible.value = true
  initWangEditor(article.article.content)
}

function saveArticle(){
  article.article.title = formInline.title
  article.article.date = formInline.date
  article.article.content = formInline.content
  dialogTableVisible.value = false
}

</script>


<style scoped>

.title{
  font-size: 28px;
  font-weight: 700;
  margin-top: -15px;
  margin-left: 5px;
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

.editor{
  width: 700px;
}

.form-item{
  margin-bottom: 20px;
}


.form-label{
  margin-left: 50px;
  margin-right: 30px;
  font-size: 18px;
  font-weight: 700;
}

.form-add-image{
  display: flex;
  align-items: center;
}

.form-editor{
  display: flex;
  align-items: center;
}

.form-button{
  margin-left: 600px;
  /* font-size: 18px !important; */
}

</style>