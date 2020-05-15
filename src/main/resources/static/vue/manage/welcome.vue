<template>
	<el-row>
		<p>欢迎登录</p>
	</el-row>
</template>

<script>
    module.exports = {
        data() {
            return {};
        },
        mounted() {
            // this.getUserInfo()
        },
        methods: {
            getUserInfo() {
                axios.get(`/getUserInfo`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    // this.userInfo = result.data ? result.data : null;
                    console.log(result.data === {})
                    if (result.data === {}) {
                        window.location.href = '/login'
                    }
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            }
        }
    }
</script>

<style scoped>
</style>
