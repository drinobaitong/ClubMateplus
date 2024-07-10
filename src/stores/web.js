import { defineStore } from "pinia";
import { reactive } from "vue";

export const useWebStore = defineStore('web', () => {
  const web = reactive({
    status: false,
  })

  return {
    web
  }
})
