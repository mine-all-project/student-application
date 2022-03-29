Vue.use(httpVueLoader);
const routes = [
	{path: '/paper/type/list',	component: httpVueLoader('pages/paper/typeList.vue')},
	{path: '/paper/list/:id',	component: httpVueLoader('pages/paper/list.vue')},
	{path: '/paper/info/:id',	component: httpVueLoader('pages/paper/info.vue')},
	{path: '/paper/add',	component: httpVueLoader('pages/paper/add.vue')},
	{path: '/goods/list',	component: httpVueLoader('pages/goods/list.vue')},
	{path: '/goods/info/:id',	component: httpVueLoader('pages/goods/info.vue')},
	{path: '/goods/storeCar',	component: httpVueLoader('pages/goods/storeCar.vue')},
	{path: '/person/index',	component: httpVueLoader('pages/person/index.vue')},
	{path: '/person/order',	component: httpVueLoader('pages/person/order.vue')},
	{path: '/person/order/info/:id',	component: httpVueLoader('pages/person/orderInfo.vue')},
	{path: '/person/goods',	component: httpVueLoader('pages/person/goods.vue')},
	{path: '/person/paper',	component: httpVueLoader('pages/person/paper.vue')},
	{path: '/person/password',	component: httpVueLoader('pages/person/password.vue')},
	{path: '/person/login',	component: httpVueLoader('pages/person/login.vue')},
	{path: '/person/registry',	component: httpVueLoader('pages/person/registry.vue')},
	]
const router = new VueRouter({
	routes
})
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
