<template>
	<div>
		<el-col :span="8">
			<h3>美食推荐11</h3>
		</el-col>
		<el-col :span="16">
			<div class="cp-list min-width">
				<ul>
					<li>
						<div class="cp-img">
							<img src="/portal/img/pu1.jpg"/>
						</div>
						<h4>玫瑰鼓油鸡</h4>
						<h3>享誉国际的美食</h3>
						<p>玫瑰鼓油鸡色泽金黄，味甘香，豉油味浓，肉滑。在广式菜系中，玫瑰豉油鸡具有补血益气、滋阴养颜的功效。这道凉菜鲜香、味美、肉嫩、皮脆。对女性起到暖宫的作用。</p>
						<strong>了解更多</strong>
					</li>
					<li>
						<div class="cp-img"><img src="/portal/img/pu2.jpg"/></div>
						<h4>白灼虾</h4>
						<h3>享誉国际的美食</h3>
						<p>白灼虾以鲜虾通过白灼之法烹饪而成，虾鲜嫩，美味可口，而且烹饪简单。 食用功效有健脾养脾、养胃健胃、补血养血、补气益气、调理肠胃、滋阴补阴、开胃消食、清热去火、抗衰老、增强抵抗力。</p>
						<strong>了解更多</strong>
					</li>
					<li>
						<div class="cp-img"><img src="/portal/img/pu3.jpg"/></div>
						<h4>上汤焗龙虾</h4>
						<h3>享誉国际的美食</h3>
						<p>上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。特别适合滋补食用。</p>
						<strong>了解更多</strong>
					</li>
					<li>
						<div class="cp-img"><img src="/portal/img/pu7.jpg"/></div>
						<h4>玫瑰鼓油鸡</h4>
						<h3>享誉国际的美食</h3>
						<p>玫瑰鼓油鸡色泽金黄，味甘香，豉油味浓，肉滑。在广式菜系中，玫瑰豉油鸡具有补血益气、滋阴养颜的功效。这道凉菜鲜香、味美、肉嫩、皮脆。对女性起到暖宫的作用。</p>
						<strong>了解更多</strong>
					</li>
					<li>
						<div class="cp-img"><img src="/portal/img/pu8.jpg"/></div>
						<h4>白灼虾</h4>
						<h3>享誉国际的美食</h3>
						<p>白灼虾以鲜虾通过白灼之法烹饪而成，虾鲜嫩，美味可口，而且烹饪简单。 食用功效有健脾养脾、养胃健胃、补血养血、补气益气、调理肠胃、滋阴补阴、开胃消食、清热去火、抗衰老、增强抵抗力。</p>
						<strong>了解更多</strong>
					</li>
					<li>
						<div class="cp-img"><img src="/portal/img/pu9.jpg"/></div>
						<h4>上汤焗龙虾</h4>
						<h3>享誉国际的美食</h3>
						<p>上汤焗龙虾是一道色香味俱全的传统名菜，属于粤菜系。此菜以龙虾为主料，配以高汤制成的一道海鲜美食。本品肉质洁白细嫩，味道鲜美，蛋白质含量高，脂肪含量低，营养丰富。特别适合滋补食用。</p>
						<strong>了解更多</strong>
					</li>
				</ul>
			</div>
		</el-col>

	</div>
</template>
<script>
    module.exports = {
        components: {
            'page-header': httpVueLoader('/vue/portal/page-header.vue'),
            'page-footer': httpVueLoader('/vue/portal/page-footer.vue'),
        },
        data() {
            return {
                housesList: []
            };
        },
        mounted() {
            // this.getMyHousesList();
        },
        methods: {
            search(keywords) {
                if (keywords) {
                    axios.get('/api/getHousesListByTitle?title=' + keywords).then(({data: res}) => {
                        if (res.success) {
                            this.housesList = res.data;
                        } else {
                            this.housesList = [];
                        }
                    });
                } else {
                    this.getHousesList()
                }
            },
            removeHouses(id) {
                layer.confirm('确定删除该房源吗？', {icon: 3, title: '删除确认'}, index => {
                    layer.close(index);
                    axios.delete(`/api/removeHousesById/${id}`).then(({data: res}) => {
                        if (res.success) {
                            layer.msg(res.message, {icon: 6});
                            this.getMyHousesList();
                        } else {
                            layer.msg(res.message, {icon: 5});
                        }
                    })
                });
            },

            getMyHousesList() {
                this.showMyHouse = true;
                axios.get('/api/getHousesByUser').then(({data: res}) => {
                    if (res.success) {
                        this.housesList = res.data;
                    } else {
                        this.housesList = [];
                    }
                });
            },
        }
    };
</script>
<style scoped>
	nav {
		height: 200px;
	}

	nav h4 {
		font-size: 20px;
		margin-top: 20px;
	}

	.cp-list {

	}

	.cp-list ul {
		width: 100%;
		height: 1000px;
	}

	.cp-list ul li {
		width: 360px;
		height: 450px;
		float: left;
		margin: 15px;
	}

	.cp-list ul .cp-img {
		overflow: hidden;
	}

	.cp-list ul .cp-img img {
		width: 360px;
		transition: .5s ease;
	}

	.cp-list ul .cp-img img:hover {
		transform: scale(1.1);
	}

	.cp-list ul li h3 {
		color: rgb(255, 87, 34);
	}

	.cp-list ul li p {
		text-align: justify;
	}

	.cp-list ul li strong {
		display: block;
		width: 140px;
		height: 36px;
		margin-top: 10px;
		line-height: 36px;
		color: white;
		text-align: center;
		background-color: rgb(172, 145, 90);
	}

	.cp-info {
		height: 500px;
	}

	.cp-info .cp-info-left {
		display: inline-block;
		float: left;
		width: 65%;
		height: 100%;
	}

	.cp-info .cp-info-left p {
		line-height: 34px;
	}

	.cp-info-right {
		display: inline-block;
		margin-left: 40px;
		width: 30%;
		height: 100%;
	}


	/*小屏幕*/

	@media screen and (max-width: 768px) {
		.cp-list ul {
			width: 768px;
			height: 100%;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}

		.cp-info {
			width: 768px;
			height: 100%;
			margin-left: 100px;
		}
	}

	/*平板*/

	@media screen and (min-width: 768px) and (max-width: 992px) {


	}

	/*中等屏幕*/

	@media screen and (min-width: 992px) and (max-width: 1200px) {

	}

	/*大屏幕，或者宽屏*/

	@media screen and (min-width: 1200px) {

	}

	/*小屏幕*/

	@media screen and (max-width: 768px) {
		.uu-nav {
			width: 768px;

		}

		.nav-lists {
			display: none;
		}

		.nav ul .ico {
			width: 160px;
			text-align: right;
			font-size: 26px;

		}
	}

	/*平板*/

	@media screen and (min-width: 768px) and (max-width: 992px) {
		.uu-nav {
			display: flex;
			justify-content: space-between;
			align-items: center;
		}

		.nav ul .ico {
			display: none;
		}
	}

	/*中等屏幕*/

	@media screen and (min-width: 992px) and (max-width: 1200px) {
		.nav ul .ico {
			display: none;
		}
	}

	/*大屏幕，或者宽屏*/

	@media screen and (min-width: 1200px) {
		.nav ul .ico {
			display: none;
		}
	}
</style>
