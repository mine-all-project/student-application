const base = {
    get() {
        return {
            url : "http://localhost:8080/springbooth5wle/",
            name: "springbooth5wle",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbooth5wle/front/h5/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于微信小程序的汽车俱乐部"
        } 
    }
}
export default base
