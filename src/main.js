import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

// 创建 Pinia 实例  
const pinia = createPinia()
// 持久化
pinia.use(piniaPluginPersistedstate)

// 创建 Vue 应用实例  
const app = createApp(App)

// 使用 Pinia  
app.use(pinia)

// 使用 Vue Router  
app.use(router)

// 使用 Element Plus  
app.use(ElementPlus)

app.mount('#app')