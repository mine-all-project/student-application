import axios from 'axios'

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
        let data = response.data
        return response.status === 200 ? Promise.resolve(data) : Promise.reject(data)
    },
    error => {
        return Promise.reject(error.response)
    }
);

export default instance
