//根域名配置和请求拦截器的js文件
import axios from 'axios'
const request = axios.create({
    baseURL:'http://localhost:8080',   //请求根路径
    timeout: 5000 , //请求超时时间
})
// 请求拦截器
request.interceptors.request.use(config=>{
    return config
},err=>{
    return Promise.reject(err)  //返回错误
})

// 响应拦截器
request.interceptors.response.use(res=>{
    return res
},err=>{
    return Promise.reject(err)  //返回错误
})

//导出这个请求文件
export default request;
