<template>
	<el-container style=" border: 1px solid #eee">
		<el-header width="100%" style="text-align: right; font-size: 12px">
			<!--      <router-link to="/demo">Demo</router-link>-->
			<h1>管理平台</h1>
			<!--      <el-dropdown>-->
			<!--        <i class="el-icon-setting" style="margin-right: 15px"></i>-->
			<!--        <el-dropdown-menu slot="dropdown">-->
			<!--          <el-dropdown-item>查看</el-dropdown-item>-->
			<!--          <el-dropdown-item>新增</el-dropdown-item>-->
			<!--          <el-dropdown-item>删除</el-dropdown-item>-->
			<!--        </el-dropdown-menu>-->
			<!--      </el-dropdown>-->
			<!--      <span>王小虎</span>-->
		</el-header>
		<el-container>
			<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
				<el-menu :default-openeds="['0']">
					<template v-for="(item,index) in menus.menu">
						<template v-if="item.children">
							<el-submenu :index="`${index}`">
								<template slot="title">
									<i :class="item.icon"></i>{{item.name}}
								</template>
								<el-menu-item :index="item.id" v-for="item in item.children" @click="clickMenu(item.url)" :key="item.id">
									<i :class="item.icon"></i>{{item.name}}
								</el-menu-item>
							</el-submenu>
						</template>
						<template v-else>
							<el-menu-item :index="item.id" @click="clickMenu(item.url)">
								<i :class="item.icon"></i>
								<span slot="title">{{item.name}}</span>
							</el-menu-item>
						</template>
					</template>
				</el-menu>
			</el-aside>
			<el-main>
				<!--				<p v-if="welcome">欢迎登录</p>-->
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
    module.exports = {
        data() {
            return {
                menus: {
                    menu: [
                        {
                            id: '1',
                            name: '系统设置',
                            icon: 'el-icon-menu',
                            url: '/demo'
                        },
                        {
                            id: '2',
                            name: '用户管理',
                            icon: 'el-icon-menu',
                            url: '/user-list'
                        },
                    ],
                    manageMenu: [
                        {
                            id: '1',
                            name: '系统设置',
                            icon: 'el-icon-menu',
                            url: '/demo'
                        },
                        {
                            id: '2',
                            name: '用户管理',
                            icon: 'el-icon-menu',
                            url: '/user-list'
                        },
                    ],
                    buyMenu: [
                        {
                            id: '3',
                            name: '进货信息',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '3-1',
                                    name: '进货信息登记',
                                    icon: 'el-icon-menu',
                                    url: '/goods-add'
                                },
                                {
                                    id: '3-2',
                                    name: '进货信息查询',
                                    icon: 'el-icon-menu',
                                    url: '/goods-query'
                                },
                            ]
                        },
                        {
                            id: '5',
                            name: '供应商管理',
                            icon: 'el-icon-menu',
                        },
                        {
                            id: '6',
                            name: '进货统计',
                            icon: 'el-icon-menu',
                        },
                    ],
                    inventoryMenu: [
                        {
                            id: '1',
                            name: '公交管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '1-1',
                                    name: '公交线路管理',
                                    icon: '',
                                    url: '/demo'
                                },
                                {
                                    id: '1-2',
                                    name: '公交站点管理',
                                    icon: 'el-icon-menu',
                                    url: '/manage/stands'
                                },
                            ]
                        },
                        {
                            id: '2',
                            name: '信息管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '2-1',
                                    name: '公交动态管理',
                                    icon: '',
                                    url: '/manage/dynamic'
                                },
                                {
                                    id: '2-2',
                                    name: '公告管理',
                                    icon: 'el-icon-menu',
                                    url: '/manage/notice'
                                },
                            ]
                        },
                        {
                            id: '3',
                            name: '用户管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '3-1',
                                    name: '用户列表',
                                    icon: 'el-icon-menu',
                                    url: '/manage/user-list'
                                }
                            ]
                        },
                        // {
                        //     id: 'demo',
                        //     name: '测试菜单',
                        //     icon: 'el-icon-menu',
                        //     children: [
                        //         {
                        //             id: 'demo-1',
                        //             name: '表格列表(富文本)',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo1'
                        //         },
                        //         {
                        //             id: 'demo-2',
                        //             name: '表格列表(文本域)',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo2'
                        //         },
                        //         {
                        //             id: 'demo-3',
                        //             name: '照片墙',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo3'
                        //         },
                        //         {
                        //             id: 'demo-4',
                        //             name: '富文本页面',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo4'
                        //         },
                        //         {
                        //             id: 'demo-5',
                        //             name: '表格列表(带图片)',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo5'
                        //         }
                        //     ]
                        // },
                    ],
                    saleMenu: [
                        {
                            id: '1',
                            name: '公交管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '1-1',
                                    name: '公交线路管理',
                                    icon: '',
                                    url: '/demo'
                                },
                                {
                                    id: '1-2',
                                    name: '公交站点管理',
                                    icon: 'el-icon-menu',
                                    url: '/manage/stands'
                                },
                            ]
                        },
                        {
                            id: '2',
                            name: '信息管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '2-1',
                                    name: '公交动态管理',
                                    icon: '',
                                    url: '/manage/dynamic'
                                },
                                {
                                    id: '2-2',
                                    name: '公告管理',
                                    icon: 'el-icon-menu',
                                    url: '/manage/notice'
                                },
                            ]
                        },
                        {
                            id: '3',
                            name: '用户管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '3-1',
                                    name: '用户列表',
                                    icon: 'el-icon-menu',
                                    url: '/manage/user-list'
                                }
                            ]
                        },
                    ],
                    menuBak: [
                        {
                            id: '1',
                            name: '公交管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '1-1',
                                    name: '公交线路管理',
                                    icon: '',
                                    url: '/demo'
                                },
                                {
                                    id: '1-2',
                                    name: '公交站点管理',
                                    icon: 'el-icon-menu',
                                    url: '/manage/stands'
                                },
                            ]
                        },
                        {
                            id: '2',
                            name: '信息管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '2-1',
                                    name: '公交动态管理',
                                    icon: '',
                                    url: '/manage/dynamic'
                                },
                                {
                                    id: '2-2',
                                    name: '公告管理',
                                    icon: 'el-icon-menu',
                                    url: '/manage/notice'
                                },
                            ]
                        },
                        {
                            id: '3',
                            name: '用户管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '3-1',
                                    name: '用户列表',
                                    icon: 'el-icon-menu',
                                    url: '/manage/user-list'
                                }
                            ]
                        },
                        // {
                        //     id: 'demo',
                        //     name: '测试菜单',
                        //     icon: 'el-icon-menu',
                        //     children: [
                        //         {
                        //             id: 'demo-1',
                        //             name: '表格列表(富文本)',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo1'
                        //         },
                        //         {
                        //             id: 'demo-2',
                        //             name: '表格列表(文本域)',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo2'
                        //         },
                        //         {
                        //             id: 'demo-3',
                        //             name: '照片墙',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo3'
                        //         },
                        //         {
                        //             id: 'demo-4',
                        //             name: '富文本页面',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo4'
                        //         },
                        //         {
                        //             id: 'demo-5',
                        //             name: '表格列表(带图片)',
                        //             icon: 'el-icon-menu',
                        //             url: '/demo/demo5'
                        //         }
                        //     ]
                        // },
                    ],
                },

            };
        },
        mounted() {
            router.push('demo')
            this.menus.menu = this.menus.buyMenu
            // this.getTableDataList()
        },
        methods: {
            clickMenu(url) {
                this.welcome = false
                router.push(url)
                console.log(url)
            },

            remove(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认删除？').then(e => {
                    _this.drawer.loading = true;
                    axios.delete(`/api/removePaperById/${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getTableDataList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            changeStatus(scope) {
                const _this = this;
                const id = scope.row.id;
                _this.$confirm('确认操作？').then(e => {
                    _this.drawer.loading = true;
                    axios.put(`/manage/changeStatus/${id}`).then(response => {
                        _this.getTableDataList();
                        const result = response.data;
                        console.log('通过api获取到的数据:', result);
                        if (result.status !== 200) {
                            _this.$message.error('数据加载失败');
                            return
                        }
                        _this.$message.success('操作成功')
                    }).catch(function (error) {
                        _this.getTableDataList();
                        console.log('请求出现错误:', error);
                    });
                });
            },
            getTableDataList() {
                const _this = this;
                axios.get('/manage/getUserList').then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return;
                    }
                    _this.tableData = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            edit(scope) {
                this.$nextTick(() => {
                    this.findDataById(scope ? scope.row.id : ' ');
                    this.drawer.show = true;
                })
            },
            findDataById(id) {
                const _this = this;
                axios.get(`/manage/getUsersById?id=${id}`).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.form = result.data;
                }).catch(function (error) {
                    console.log('请求出现错误:', error);
                });
            },
            formClose() {
                this.drawer.loading = false;
                this.drawer.show = false;
                this.getTableDataList();
            },
            saveForm() {
                const _this = this;
                _this.drawer.loading = true;
                axios.post(`/manage/saveUserInfo`, _this.form).then(response => {
                    const result = response.data;
                    console.log('通过api获取到的数据:', result);
                    if (result.status !== 200) {
                        this.$message.error('数据加载失败');
                        return
                    }
                    _this.$message.success('操作成功');
                    _this.drawer.loading = false;
                    _this.drawer.show = false;
                    _this.getTableDataList()
                }).catch(function (error) {
                    window.location.reload();
                    console.log('请求出现错误:', error);
                });
            },
        }
    }
</script>

<style scoped>
	.el-header {
		background-color: #B3C0D1;
		color: #333;
		line-height: 60px;
	}

	.el-aside {
		color: #333;
	}
</style>
