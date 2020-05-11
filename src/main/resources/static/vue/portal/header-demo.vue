<template>
	<div>
		<p>这个是一段测试</p>
	</div>
</template>
<script>
    module.exports = {
        name: 'header-demo',
        data() {
            return {
                editPwd: false,
                keywords: '',
                form: {
                    username: '',
                    name: '',
                    phone: '',
                    mail: ''
                },
                pwd: {
                    password: '',
                    newPassword: '',
                    confirm: ''
                },
                userInfo: {}
            };
        },
        mounted() {
            // this.getUserInfo();
        },
        methods: {
            publish() {
                if (!this.userInfo.id) {
                    layer.alert('请先登录再操作', {icon: 0}, function (index) {
                        location = '/login';
                        layer.close(index);
                    });
                } else {
                    let house = {
                        address: '',
                        contracts: '',
                        img_src: '',
                        note: '',
                        price: '',
                        title: '',
                        user_id: this.userInfo.id
                    };
                    this.editHouses(house);
                }
            },
            editInfo() {
                this.pwd = {
                    password: '',
                    newPassword: '',
                    confirm: ''
                };
            },
            checkForm(form) {
                for (let value of Object.values(form)) {
                    if (value === '') {
                        alert('请完整填写信息');
                        return false;
                    }
                }
                return true;
            },
            submit() {
                if (this.checkForm(this.form)) {
                    let data = {
                        ...this.form
                    };
                    if (this.editPwd && this.checkForm(this.pwd)) {
                        if (this.pwd.confirm !== this.pwd.newPassword) {
                            alert('两次密码不一致');
                            return;
                        }
                        Object.assign(data, this.pwd);
                    }
                    axios.post('/saveUserInfo', data).then(({data: res}) => {
                        if (res.success) {
                            layer.msg(res.message, {icon: 6});
                            setTimeout(() => {
                                this.editPwd = false
                            }, 1000);
                        } else {
                            layer.msg(res.message, {icon: 5});
                        }
                    });
                }
            },
            getUserInfo() {
                axios.get('/getUserInfo').then(({data: res}) => {
                    if (res.success && res.data) {
                        this.userInfo = res.data;
                        for (let k of Object.keys(this.form)) {
                            this.form[k] = res.data[k];
                        }
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
