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
				<keep-alive>
					<router-view></router-view>
				</keep-alive>

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
                            id: '1',
                            name: '采购管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '1-1',
                                    name: '采购计划登记',
                                    icon: 'el-icon-menu',
                                    url: '/purchases-add'
                                },
                                {
                                    id: '1-2',
                                    name: '采购记录查询',
                                    icon: 'el-icon-menu',
                                    url: '/purchases-list'
                                },
                            ]
                        },
                        {
                            id: '2',
                            name: '消息列表',
                            icon: 'el-icon-menu',
                            url: '/message-list'
                        },
                        {
                            id: '3',
                            name: '进货统计',
                            icon: 'el-icon-menu',
                            url: '/statistical'
                        },
                    ],
                    storagesMenu: [
                        {
                            id: '12',
                            name: '采购管理',
                            icon: 'el-icon-menu',
                            children: [
                                {
                                    id: '1-1',
                                    name: '采购计划登记',
                                    icon: 'el-icon-menu',
                                    url: '/purchases-add'
                                },
                                {
                                    id: '1-2',
                                    name: '采购记录查询',
                                    icon: 'el-icon-menu',
                                    url: '/purchases-list'
                                },
                            ]
                        },
                        {
                            id: '23',
                            name: '消息列表',
                            icon: 'el-icon-menu',
                            url: '/message-list'
                        },
                        {
                            id: '34',
                            name: '进货统计',
                            icon: 'el-icon-menu',
                            url: '/statistical'
                        },
                        {
                            id: '1',
                            name: '入库验收',
                            icon: 'el-icon-menu',
                            url: '/storages-input'
                        },
                        {
                            id: '2',
                            name: '出库信息',
                            icon: 'el-icon-menu',
                            url: '/storages-output'
                        },
                        {
                            id: '3',
                            name: '库存盘点',
                            icon: 'el-icon-menu',
                            url: '/storages-manage'
                        },
                        {
                            id: '4',
                            name: '库存信息',
                            icon: 'el-icon-menu',
                            url: '/storages-list'
                        },

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
            this.menus.menu = this.menus.manageMenu
            this.menus.menu = this.menus.buyMenu
            this.menus.menu = this.menus.storagesMenu
            // this.menus.menu = this.menus.saleMenu
            // this.getTableDataList()
        },
        methods: {
            clickMenu(url) {
                router.push(url)
                console.log(url)
            },
        }
    }
</script>

<style>
	.el-header {
		background-color: #B3C0D1;
		color: #333;
		line-height: 60px;
	}

	.el-aside {
		color: #333;
	}

	.parent-body {
		padding: 16px;
	}
</style>
