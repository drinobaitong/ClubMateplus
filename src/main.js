import { createApp } from 'vue'
import App from './App.vue'
//import './style.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
const app = createApp(App)
app.use(ElementPlus)
app.mount('#app')
import router from './router/index'
import axios from "axios";
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(router).mount('#app')
app.config.globalProperties.$http=axios
createApp(App)
    .use(router)
    .use(ElementPlus)
    .mount('#app')
