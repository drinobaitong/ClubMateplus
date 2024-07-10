import { createApp } from 'vue'
import App from './App.vue'
//import './style.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia'

const app = createApp(App)

// 创建Pinia实例  
const pinia = createPinia()

// 将Pinia实例添加到Vue应用中  
app.use(pinia)

app.use(ElementPlus)
app.mount('#app')
import router from './router/index'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(router).mount('#app')
createApp(App)
    .use(router)
    .use(ElementPlus)
    .mount('#app')
