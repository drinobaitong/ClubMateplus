import { defineStore } from "pinia";
import { reactive } from "vue";

// 实现持久化pinia
export const useWebStore = defineStore('web', () => {
  const web = reactive({
    status: false,//登录状态
    identity: ''//身份
  })

  return {
    web
  }
}, {
  persist: true,
})
