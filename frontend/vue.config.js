// let url = global.setting && global.setting.baseUrl;
let url = 'http://localhost:80/';
let env = process.env.NODE_ENV;
console.log(env);
module.exports = {
    pages: {
        index: {
            entry: 'src/main.js',
            template: 'public/index.html',
            filename: 'index.html',
            title: 'index',
            chunks: ['chunk-vendors', 'chunk-common', 'index']
        }
    },
    devServer: {
        open: process.platform === "darwin",
        disableHostCheck: false,
        host: "0.0.0.0",
        port: 8080,
        https: false,
        hotOnly: false, // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#configuring-proxy
        proxy: {
            '/api': {
                target: url,
                changeOrigin: true,
            },
            '/file': {
                target: url,
                changeOrigin: true,
            }
        },
    },

    pluginOptions: {
        // ...
    },
};
