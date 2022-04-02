import axios from 'axios'
import router from '@/router';
import notification from 'ant-design-vue/es/notification'

const settings = require('../../settings')
const instance = axios.create({timeout: 12 * 1000 * 1000});

instance.interceptors.request.use(
    config => {
        if (/get/i.test(config.method)) {
            config.params = config.params || {}
            config.params.temp = Date.parse(new Date()) / 1000
        }
        return config;
    },
    error => Promise.error(error)
);

instance.interceptors.response.use(
    response => {
        console.warn('响应拦截->success', response);
        let data = response.data
        if (response.data.status === 401) {
            router.push('/login')
        }
        return response.status === 200 ? Promise.resolve(data) : Promise.reject(data)
    },
    error => {
        console.warn('响应拦截->error', error);
        notification.error({message: '服务器异常'});
        return Promise.reject(error.response)
    }
);

export default instance
