import instance from "@/utils/AxiosUtils";

const commonApi = {
    login(data) {
        return instance({
            url: '/api/login',
            method: 'post',
            data: data
        })
    },
    getUserInfo() {
        return instance({
            url: '/api/user/getUserInfo',
            method: 'get',
        })
    },
    logout() {
        return instance({
            url: '/api/logout',
            method: 'get',
        })
    }
}
export default commonApi