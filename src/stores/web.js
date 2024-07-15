import { defineStore } from "pinia";
import { reactive } from "vue";

// 实现持久化pinia
export const useWebStore = defineStore('web', () => {
  const web = reactive({
    status: false,//登录状态
    identity: '',//身份
    uid: 0, //登录用户的id
    clubId: 0//当前正在管理的社团的id
  })

  return {
    web
  }
}, {
  persist: true,
})


export const useUserStore1 = defineStore('user1', () => {
  const user1 = reactive({
    name: "张乐瑶",
    password: '123456',
    rank: 0,
  })

  return {
    user1
  }
})


export const useUserStore2 = defineStore('user2', () => {
  const user2 = reactive({
    name: "白彤",
    password: '123456',
    rank: 1,
  })
  return {
    user2
  }
})

export const articleStore = defineStore('article', () => {
  const article = reactive({
    title: '',
    date: '',
    content: '',
  })
  return {
    article
  }
})
