<template>
	<div>
		<div class="nav">
			<ul class="uu-nav">
				<li class="logo">美食汇</li>
				<li class="nav-lists"><a href="index.html">首页</a></li>
				<li class="nav-lists"><a href="health.html">饮食健康</a></li>
				<li class="nav-lists"><a href="master.html">美食达人</a></li>
				<li class="nav-lists"><a href="caipu.html">菜谱大全</a></li>
				<!--				<li class="nav-lists"><a href="login.html">登录</a></li>-->
				<li class="nav-lists">
					<router-link to="/login">登录1</router-link>
				</li>
				<li class="ico">三</li>
			</ul>
			<ul class="uu-lists">
				<li><a href="index.html">首页</a></li>
				<li><a href="health.html">饮食健康</a></li>
				<li><a href="master.html">美食达人</a></li>
				<li><a href="caipu.html">菜谱大全</a></li>
				<li><a href="login.html">登录</a></li>
			</ul>
		</div>
		<header>
			<div id="benner">
				<div class="img">
					<img src="/portal/img/11.jpg" class="imga active">
					<img src="/portal/img/22.jpg" class="imga">
					<img src="/portal/img/33.jpg" class="imga">
					<img src="/portal/img/44.jpg" class="imga">
					<img src="/portal/img/55.jpg" class="imga">
				</div>
				<ul class="title">
					<li class="nr active"></li>
					<li class="nr"></li>
					<li class="nr"></li>
					<li class="nr"></li>
					<li class="nr"></li>
				</ul>
			</div>
		</header>
	</div>
</template>
<script>
    module.exports = {
        name: 'page-header',
        props: {
            search: {
                type: Function
            },
            show: {
                type: Boolean,
                default: true
            },
        },
        data() {
            return {
                keywords: '',
                userInfo: {},
                style: {
                    display: 'none',
                    show: false
                }
            };
        },
        mounted() {
            benner('#benner', '.nr', '.imga')

            function benner(parent, controller, mod) {

                var num = 0;
                var controller = $(controller);
                var mod = $(mod);
                var benner = $(parent);
                var autoPlayTime = 3000;
                var imgNum = mod.length - 1;
                var auto;

                autoPlay();

                //根据传进来的名字获取这个名字所对应的对象
                function $(name) {
                    if (name[0] == '#') {
                        return document.getElementById(name.substr(1));
                    } else if (name[0] == '.') {
                        return document.getElementsByClassName(name.substr(1));
                    } else {
                        return document.getElementsByTagName(name);
                    }
                }

                //实现鼠标划过圆点,变化图片的效果
                //function change(controller, mod){
                //遍历控制器,绑定鼠标划过事件
                for (var i = 0; i < controller.length; i++) {
                    //给控制器加个索引,并把i值赋值给索引
                    controller[i].index = i;
                    //给所有的控制器都加划过事件
                    controller[i].onmouseover = function () {
                        for (var j = 0; j < controller.length; j++) {
                            //将所有控制器改为默认样式
                            controller[j].className = 'nr';
                        }
                        //改变所选的控制器样式
                        this.className = 'nr active';
                        //将模型改为默认样式
                        for (var x = 0; x < mod.length; x++) {
                            mod[x].className = 'imga';
                        }
                        //改变所选控制器对应的模型的样式
                        mod[this.index].className = 'imga active';
                    }
                }
                //}
                //绑定鼠标移入,暂停播放图片
                benner.onmouseover = function () {
                    clearInterval(auto);
                }
                //绑定鼠标离开,继续播放
                benner.onmouseleave = function () {
                    autoPlay();
                }

                //指定播放那个图片
                function play(num) {
                    for (var j = 0; j < controller.length; j++) {
                        controller[j].className = 'nr';
                    }
                    controller[num].className = 'nr active';
                    for (var x = 0; x < mod.length; x++) {
                        mod[x].className = 'imga';
                    }
                    mod[num].className = 'imga active';
                }

                //自动播放
                function autoPlay() {
                    auto = setInterval(function () {
                        if (num > imgNum) {
                            num = 0;
                        }
                        play(num);
                        num++;
                    }, autoPlayTime)
                }
            }

            //	导航栏start
//获取元素
            var ico = document.getElementsByClassName("ico")[0];
            var lists = document.getElementsByClassName("uu-lists")[0];
//点击事件
            ico.onclick = function () {
//	判断是否透明
                if (lists.style.opacity == 1) {
                    //	透明择显示
                    lists.style.opacity = "0"
                } else {
                    //	显示择透明
                    lists.style.opacity = "1"
                }


            }
//	导航栏end


        },
        methods: {
            clickUser() {
                this.style.show = !this.style.show
                this.style.display = this.style.show ? 'block' : 'none'
            },
            getUserInfo() {
                axios.get('/getUserInfo').then(({data: res}) => {
                    if (res.success && res.data) {
                        this.userInfo = res.data;
                    } else {
                        this.userInfo = {};
                    }
                });
            },
        }
    };
</script>
<style>

</style>

