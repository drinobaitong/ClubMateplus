//所有请求在这里，拦截器要在这里使用，先导入拦截器文件
import request from './request.js';
//export导出需求，const按需导出，在使用的页面按需导入
export const GetHomeData = () => request.get('index/index')