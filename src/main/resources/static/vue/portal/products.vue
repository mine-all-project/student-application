<template>
    <el-row>
        <el-col>
            <div class="pg-main clearfix">
                <div class="pg-con productspg clearfix">

                    <ul class="clearfix">
                        <li class="products-list" v-for="item in dataList">
                            <div>
                                <router-link :to="{path:'/products-detail',query:{id:`${item.id}`}}">
                                    <img :src="item.audioFiles[0].url" class="products-list-img"
                                         v-if="item.audioFiles.length>0"/>
                                    <h3 class="products-list-name">
                                        <span style="display: inline-block;width: 40%">{{item.name}}</span>
                                        <span style="display: inline-block;width: 40%">￥{{item.price}}</span>
                                    </h3>
                                    <p class="products-list-text">{{item.content}}</p>
                                </router-link>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="space"></div>
        </el-col>
    </el-row>

</template>
<script>
    module.exports = {
        data() {
            return {
                dataList: [],
            };
        },
        mounted() {
            this.getAnimalList()
        },
        methods: {
            getAnimalList() {
                const _this = this;
                axios.get(`/api/getAnimalList`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.dataList = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
        }
    }
</script>
<style scoped>
    .products-list {
        width: 20%;
        float: left;
        margin: 10px;
    }

    .products-list-img {
        width: 100%;
    }

    .products-list-name {
        width: 100%;
    }

    .products-list-text {
        width: 100%;
        line-height: 20px;
    }

</style>