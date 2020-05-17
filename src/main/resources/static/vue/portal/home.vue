<template>
	<div>
		<div id="page">
			<page-header></page-header>
			<foods-list></foods-list>
			<page-footer></page-footer>
		</div>
	</div>
</template>
<script>
    module.exports = {
        components: {
            'page-header': httpVueLoader('/vue/portal/page-header.vue'),
            'foods-list': httpVueLoader('/vue/portal/foods-list.vue'),
            'page-footer': httpVueLoader('/vue/portal/page-footer.vue'),
        },
        data() {
            return {
                housesList: [],
            };
        },
        mounted() {
            this.getHousesList();
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
            getHousesList() {
                axios.get('/api/getHousesList').then(({data: res}) => {
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
<style>
	.img-box .item {
		position: relative;
		margin-bottom: 15px;
		min-height: 100px;
	}

	.img-box .item .layui-upload-file {
		display: none !important;
		opacity: .01;
		filter: Alpha(opacity=1);
	}

	.img-box .item .btn-upload {
		height: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background: lightgrey;
		border-radius: 8px;
	}

	.img-box .item .btn-upload:hover {
		font-weight: bold;
		font-size: larger;
	}

	.img-box .item img {
		width: 100%;
		border-radius: 8px;
	}

	.img-box .item i {
		position: absolute;
		top: 0;
		right: 15px;
		width: 18px;
		height: 18px;
		border-radius: 100px;
		border: 1px solid black;
		background: white;
		text-align: center;
		cursor: pointer;
	}

	.img-box .item i:hover {
		color: white;
		background: #004dda;
	}

</style>
