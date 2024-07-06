// createRouter用来新建路由实例，createWebHashHistory用来配置我们内容使用hash的模式（也就是路径上会通过#来区分）
import { createRouter, createWebHashHistory } from 'vue-router'
import ClubReview from '../pages/SystemController/ClubReview.vue'
import ClubCancellation from '../pages/SystemController/ClubCancellation.vue'
import ChangeMessage from '../pages/SystemController/ChangeMessage.vue'
import NumberReview from '../pages/SystemController/NumberReview.vue'
import PostReview from '../pages/SystemController/PostReview.vue'
import ClubControl from '../pages/SystemController/ClubControl.vue'
import ContentControl from '../pages/SystemController/ContentControl.vue'
import TypeControl from '../pages/SystemController/TypeControl.vue'
import home from '../pages/ClubController/home.vue'

const routes = [
    {
        path: '/',
        name: 'ClubReview',
        component: ClubReview
    },
    {
        path: '/Cancel',
        name: 'ClubCancellation',
        component: ClubCancellation
    },
    {
        path: '/ChangeN',
        name: 'ChangeMessage',
        component: ChangeMessage
    },
    {
        path: '/NumberR',
        name: 'NumberReview',
        component: NumberReview
    },
    {
        path: '/PostR',
        name: 'PostReview',
        component: PostReview
    },
    {
        path: '/ClubControl',
        name: 'ClubControl',
        component: ClubControl
    },
    {
        path: '/ContentControl',
        name: 'ContentControl',
        component: ContentControl
    },
    {
        path: '/TypeControl',
        name: 'TypeControl',
        component: TypeControl
    },
    {
        path: '/home',
        name: 'home',
        component: home
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router