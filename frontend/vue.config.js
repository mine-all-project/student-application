module.exports = {
    /** 区分打包环境与开发环境
     * process.env.NODE_ENV==='production'  (打包环境)
     * process.env.NODE_ENV==='development' (开发环境)
     * baseUrl: process.env.NODE_ENV==='production'?"https://cdn.didabisai.com/front/":'front/',
     */
    devServer: {
        open: process.platform === "darwin",
        disableHostCheck: false,
        host: "0.0.0.0",
        port: 8080,
        https: false,
        hotOnly: false, // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#configuring-proxy
        proxy: {
            '/api': {
                // target: 'http://0.0.0.0:9093/', // 接口的域名
                target: 'http://linux.crabapples.cn', // 接口的域名
                // target: 'http://dev.crabapples.cn', // 接口的域名
                // secure: false,  // 如果是https接口，需要配置这个参数
                changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
                pathRewrite: {
                    '^/api': '/api'       // 把 /api 开头的路径替换为 ''
                }
            },
        },
        // string | Object
        // before: app => {}

    }, // 第三方插件配置

    pluginOptions: {
        // ...
    },
};
