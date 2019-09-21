import axios from 'axios'
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // 请求超时时间
})

// 拦截失败请求
service.interceptors.response.use((response) => {
  const data = response.data
  if (data.status !== 0) {
    Message({
      message: data.msg,
      type: 'error'
    })
  }
  response.data = data.data
  return response
})

export default service
