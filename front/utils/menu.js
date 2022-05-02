const menu = {
    list() {
        return [{
            "backMenu": [
                {
                    "child": [{
                        "appFrontIcon": "cuIcon-present",
                        "buttons": ["新增", "查看", "修改", "删除"],
                        "menu": "会员",
                        "menuJump": "列表",
                        "tableName": "huiyuan"
                    }],
                    "menu": "会员管理"
                },
                {
                    "child": [{
                        "appFrontIcon": "cuIcon-similar",
                        "buttons": ["新增", "查看", "修改", "删除", "查看评论"],
                        "menu": "公司信息",
                        "menuJump": "列表",
                        "tableName": "gongsixinxi"
                    }],
                    "menu": "公司信息管理"
                },
                {
                    "child": [{
                        "appFrontIcon": "cuIcon-link",
                        "buttons": ["新增", "查看", "修改", "删除", "查看评论", "申请"],
                        "menu": "活动信息",
                        "menuJump": "列表",
                        "tableName": "huodongxinxi"
                    }],
                    "menu": "活动信息管理"
                },
                {
                    "child": [{
                        "appFrontIcon": "cuIcon-pay",
                        "buttons": ["查看", "删除", "审核"],
                        "menu": "活动申请",
                        "menuJump": "列表",
                        "tableName": "huodongshenqing"
                    }],
                    "menu": "活动申请管理"
                },
                {
                    "child": [{
                        "appFrontIcon": "cuIcon-qrcode",
                        "buttons": ["查看", "删除"],
                        "menu": "论坛管理",
                        "tableName": "forum"
                    }],
                    "menu": "论坛管理"
                },
                {
                    "child": [
                        {
                            "appFrontIcon": "cuIcon-goodsnew",
                            "buttons": ["新增", "查看", "修改", "删除"],
                            "menu": "新闻资讯",
                            "tableName": "news"
                        },
                        {
                            "appFrontIcon": "cuIcon-service",
                            "buttons": ["新增", "查看", "修改", "删除"],
                            "menu": "在线客服",
                            "tableName": "chat"
                        },
                        {
                            "appFrontIcon": "cuIcon-newshot",
                            "buttons": ["查看", "修改"],
                            "menu": "轮播图管理",
                            "tableName": "config"
                        }],
                    "menu": "系统管理"
                }],
            "frontMenu": [
                {
                    "child": [{
                        "appFrontIcon": "cuIcon-form",
                        "buttons": ["申请"],
                        "menu": "活动信息列表",
                        "menuJump": "列表",
                        "tableName": "huodongxinxi"
                    }],
                    "menu": "活动信息模块"
                }
            ],
            "hasBackLogin": "是",
            "hasBackRegister": "否",
            "hasFrontLogin": "否",
            "hasFrontRegister": "否",
            "roleName": "管理员",
            "tableName": "users"
        },
            {
                "backMenu": [
                    {
                        "child": [{
                            "appFrontIcon": "cuIcon-pay",
                            "buttons": ["查看", "修改", "删除"],
                            "menu": "活动申请",
                            "menuJump": "列表",
                            "tableName": "huodongshenqing"
                        }],
                        "menu": "活动申请管理"
                    },
                    {
                        "child": [{
                            "appFrontIcon": "cuIcon-favor",
                            "buttons": ["查看", "删除"],
                            "menu": "我的收藏管理",
                            "tableName": "storeup"
                        }],
                        "menu": "我的收藏管理"
                    }],
                "frontMenu": [{
                    "child": [
                        {
                            "appFrontIcon": "cuIcon-form",
                            "buttons": ["申请"],
                            "menu": "活动信息列表",
                            "menuJump": "列表",
                            "tableName": "huodongxinxi"
                        }
                    ],
                    "menu": "活动信息模块"
                }],
                "hasBackLogin": "否",
                "hasBackRegister": "否",
                "hasFrontLogin": "是",
                "hasFrontRegister": "是",
                "roleName": "会员",
                "tableName": "huiyuan"
            }]
    }
}
export default menu;
