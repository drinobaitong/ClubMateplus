// createRouter用来新建路由实例，createWebHashHistory用来配置我们内容使用hash的模式（也就是路径上会通过#来区分）
import { createRouter, createWebHashHistory } from 'vue-router'
import Collection from '../pages/MeHome/collection.vue'
import Participation from '../pages/MeHome/Participation.vue'
import Administration from '../pages/MeHome/Administration.vue'
import SetUpClub from '../pages/MeHome/SetUpClub.vue'
import JoinClub from '../pages/ClubDetail/JoinClub.vue'
import Post from '../pages/ClubDetail/Post.vue'

import ClubReview from '../pages/SystemController/ClubReview.vue'
import ClubCancellation from '../pages/SystemController/ClubCancellation.vue'
import ChangeMessage from '../pages/SystemController/ChangeMessage.vue'
import NumberReview from '../pages/SystemController/NumberReview.vue'
import PostReview from '../pages/SystemController/PostReview.vue'
import ClubControl from '../pages/SystemController/ClubControl.vue'
import ContentControl from '../pages/SystemController/ContentControl.vue'
import TypeControl from '../pages/SystemController/TypeControl.vue'
import ClNumberReview from '../pages/ClubController/NumberReview.vue'//社团成员审核
import ClubMessage from '../pages/ClubController/ClubMessage.vue'//社团信息管理
import ClContentControl from '../pages/ClubController/ContentControl.vue'//社团评论管理
import ExitControl from '../pages/ClubController/ExitControl.vue'//退出审核
import ClNumberControl from '../pages/ClubController/NumberControl.vue'//社团成员管理
import PostControl from '../pages/ClubController/PostControl.vue'//发帖管理

import HomePage from '../pages/Visitor/HomePage.vue'//主页
import Login from '../pages/Visitor/Login.vue'//登录注册
import NotLogged from '../pages/Visitor/NotLogged.vue'//社团详情
import Overview from '../pages/Visitor/Overview.vue'//社团概览



const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: '/overview',
        name: 'Overview',
        component: Overview
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/ClubReview',
        name: 'ClubReview',
        component: ClubReview
    },
    {
        path: '/ClubMessage',
        name: 'ClubMessage',
        component: ClubMessage
    },
    {
        path: '/PostControl',
        name: 'PostControl',
        component: PostControl
    },
    {
        path: '/Collection',
        name: 'Collection',
        component: Collection
    },
    {
        path: '/Participation',
        name: 'Participation',
        component: Participation
    },
    {
        path: '/Administration',
        name: 'Administration',
        component: Administration
    },
    {
        path: '/SetUpClub',
        name: 'SetUpClub',
        component: SetUpClub
    },
    {
        path: '/JoinClub',
        name: 'JoinClub',
        component: JoinClub
    },
    {
        path: '/Post',
        name: 'Post',
        component: Post
    },

    {
        path: '/ClContentControl',
        name: 'ClContentControl',
        component: ClContentControl
    },
    {
        path: '/ExitControl',
        name: 'ExitControl',
        component: ExitControl
    },
    {
        path: '/ClNumberControl',
        name: 'ClNumberControl',
        component: ClNumberControl
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
        path: '/ClNumberReview',
        name: 'ClNumberReview',
        component: ClNumberReview
    },
]



const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router