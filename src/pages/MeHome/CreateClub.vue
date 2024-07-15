<template>
<el-button plain @click="dialogFormVisible = true">
    创建社团
  </el-button>


    <el-dialog v-model="dialogFormVisible"  width="750" style="margin-top: 20px;">
      <p class = "title">创建社团</p>
   
    <!-- 社团信息表单 -->
    <el-form ref="form" :model="formData" label-width="120px">

      <div class = "form-item">
        <span class = "form-label">社团名称</span>
        <el-input v-model="formData.clubName" placeholder="请填入社团名称" clearable style="width: 500px;" />
      </div>

      <div class = "form-item">
        <span class = "form-label">所属学院</span>
        <el-select v-model="formData.clubCollege" placeholder="请选择所属学院" clearable style="width: 500px;"> 
          <el-option v-for="item in collegeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </div>

      <div class = "form-item">
        <span class = "form-label">社团类型</span>
        <el-select v-model="formData.type" placeholder="请选择类型" clearable style="width: 500px;">
          <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </div>

      <!-- <div class = "form-item">
        <span class = "form-label">创建日期</span>
        <el-input v-model="formData.setupDate" placeholder="请填入社团成立时间" clearable style="width: 500px;" />
      </div> -->

      <div class = "form-item">
        <span class = "form-label">社团宗旨</span>
        <el-input v-model="formData.textarea" :maxlength="'100'" placeholder="请填入社团宗旨，100字以内" clearable style="width: 500px;"/>
      </div>

        <!-- 负责人信息表单 -->

        <div class = "form-item">
        <span class = "form-label">负责人信息</span>
        <el-input v-model="formData.studentId" placeholder="请填入学号" clearable style="width: 500px; margin-left: -18px;"/>
        <el-input v-model="formData.studentName" placeholder="请填入姓名" clearable style="width: 500px; margin-top: 20px; margin-left: 152px;"/>
        <el-select v-model="formData.studentCollege" placeholder="请选择学院" clearable style="width: 500px; margin-top: 20px; margin-left: 152px;">
          <el-option v-for="item in studentCollegeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
        <el-input v-model="formData.studentPhone" placeholder="请填入电话" clearable style="width: 500px; margin-top: 20px; margin-left: 152px;" />
        <el-input v-model="formData.politics" placeholder="请填入政治面貌" clearable style="width: 500px; margin-top: 20px; margin-left: 152px;" />
        <el-input v-model="formData.grade" placeholder="请填入年级" clearable style="width: 500px; margin-top: 20px; margin-left: 152px;" />
      </div>

      

      <el-form-item label="附件信息说明">
        <!-- 上传、删除附件 -->
        <div>
          <div>规范的社团章程，包括社团名称、LOGO、社团类别、宗旨、成员资格、权利和义务、组织管理制度、财务制度、负责人产生程序、社团终止和其他应由章程规定的相关事项。附件（打包上传）。
            <button @click="handleUploadClick" type="button">上传附件</button>
          </div>
          <input type="file" id="upload-file" style="display: none" @change="handleFileChange" />
        </div>
        <div v-if="fileInfo">
          <p v-html="fileInfo"></p>
          <button @click="deleteAttachment">删除附件</button>
        </div>
      </el-form-item>

      <el-form-item label="意见表述说明">
        <div>本人已阅读《xxx学生社团管理条例》及各项管理制度，了解学生社团管理及学生管理的各项规定，申请发起成立社团。</div>
      </el-form-item>

      <!-- 操作按钮 -->
      <el-form-item>
        <el-checkbox v-model="formData.checked1" label="我已阅读并同意以上条款" style="margin-left: -40px; font-weight: 700;"/>
        <el-button type="primary" @click="saveForm" style="margin-left: 50px;">保存</el-button>
        <el-button type="success" @click="submitForm">提交</el-button>
        <el-button type="primary" plain @click="$router.go(-1)">返回</el-button>
      </el-form-item>
    </el-form>
      </el-dialog>
</template>

<script setup>
import { ref,reactive } from 'vue';
const dialogFormVisible = ref(false)

const formData = ref({
  clubName: '',
  clubCollege: '',
  type: '',
  setupDate: '',
  textarea: '',
  fileInfo: '',
  checked1: false,
  studentId: '',
  studentName: '',
  studentCollege: '',
  studentPhone: '',
  politics: '',
  grade: '',
});


//社团“所属学院”下拉框
const collegeOptions = [
  { value: 'Option1', label: 'Option1' },
  { value: 'Option2', label: 'Option2' },
  { value: 'Option3', label: 'Option3' },
  { value: 'Option4', label: 'Option4' },
  { value: 'Option5', label: 'Option5' },
];

//社团“类型”下拉框
const typeOptions = [
  { value: 'Option1', label: 'Option1' },
  { value: 'Option2', label: 'Option2' },
  { value: 'Option3', label: 'Option3' },
  { value: 'Option4', label: 'Option4' },
  { value: 'Option5', label: 'Option5' },
];

//上传、删除附件
let fileInfo = ref('');
let fileInput = null; // Reference to file input element

function handleUploadClick() {
  document.getElementById('upload-file').click();
}

function handleFileChange(event) {
  const file = event.target.files[0];
  fileInfo.value = `
    文件名: ${file.name}<br>
    文件大小: ${file.size} 字节
  `;
}

function deleteAttachment() {
  fileInfo.value = ''; // Clear fileInfo to remove displayed file information
  if (fileInput) {
    fileInput.value = ''; // Reset file input value
  }
}

// Function to store reference to file input element
function setFileInput(inputElement) {
  fileInput = inputElement;
}

//负责人“学院”下拉框
const studentCollegeOptions = [
  { value: 'Option1', label: 'Option1' },
  { value: 'Option2', label: 'Option2' },
  { value: 'Option3', label: 'Option3' },
  { value: 'Option4', label: 'Option4' },
  { value: 'Option5', label: 'Option5' },
];

//保存
function saveForm() {
  // Logic to save the form data (e.g., store in localStorage)
  console.log('Form data saved:', formData.value);
}
//提交
function submitForm() {
  // Logic to submit the form data (e.g., send to server)
  console.log('Form data submitted:', formData.value);
}

</script>


<style scoped>

.title{
  font-size: 25px;
  font-weight: 700;
  margin-top: -15px;
  margin-left: 5px;
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

.comments{
  display: flex;
}

</style>