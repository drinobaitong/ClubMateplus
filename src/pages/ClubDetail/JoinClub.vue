<script setup>
import { ref } from 'vue';

const formData = ref({
  studentId: '',
  studentName: '',
  studentCollege: '',
  studentPhone: '',
  politics: '',
  grade: '',
});


//“学院”下拉框
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

<template>
  <div>
    <p>申请加入社团</p>

    <!-- 个人信息表单 -->
    <el-form ref="form" :model="formData" label-width="120px">
      <el-form-item label="学号">
        <el-input v-model="formData.studentId" placeholder="请填入学号" clearable />
      </el-form-item>

        <el-form-item label="姓名">
          <el-input v-model="formData.studentName" placeholder="请填入姓名" clearable />
        </el-form-item>

      <el-form-item label="学院">
        <el-select v-model="formData.studentCollege" placeholder="请选择学院" clearable>
          <el-option v-for="item in studentCollegeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>

      <el-form-item label="电话">
        <el-input v-model="formData.studentPhone" placeholder="请填入电话" clearable />
      </el-form-item>

      <el-form-item label="政治面貌">
        <el-input v-model="formData.politics" placeholder="请填入政治面貌" clearable />
      </el-form-item>

      <el-form-item label="年级">
        <el-input v-model="formData.grade" placeholder="请填入年级" clearable />
      </el-form-item>

      <!-- 操作按钮 -->
      <el-form-item>
        <el-button type="primary" @click="saveForm">保存</el-button>
        <el-button type="success" @click="submitForm">提交</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<style scoped>

</style>