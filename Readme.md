> ### 名词解释(带有对应前缀或后缀的文件就是对应该项功能相关的代码)
* `guide` 指导教师
* `review` 评阅教师
* `subject` 课题
* `status` 状态(也可以理解是流程)
> ### 整体结构
* `common`目录

系统配置、框架配置、常量定义。里面是一些公共的配置代码

---
* `custom`目录

里面是具体的业务逻辑代码，有关整个系统的各项针对流程操作的代码在这里

---
* `system`目录

里面是系统逻辑的代码，如登录，修改密码，添加用户之类的代码在这里

---
>### 子目录
* `controller` 定义了和前端进行数据交互的接口
* `service` 定义了每项功能的抽象方法
* `service.impl` 实现了`service`中定义的抽象方法，就是实现每个功能具体逻辑的代码
* `form` 定义了前端发送数据到后端时用于接收数据的表单
* `entity` 定义了和数据库交互的数据模型，jpa会根据这里面类的属性自动创建和更新数据表，需要进行查询时自动生成对应的sql语句
* `dao` 定义了和数据库交互时的一些方法
* `dao.jpa` jpa的抽象接口，继承了jpa的基础查询方法，使得和数据库交互不用编写大量的sql预计，增加了开发效率
* `dto` 仅在`system`目录下有，定义了和前段进行数据交互的数据模型格式
---
>### 文件
* controller
  * `GuideTeacherController` 填写指导教师时的数据接口
  * `ReviewTeacherController` 填写评阅教师时的数据接口
  * `SubjectController` 填写指导教师时的数据接口
  * `FileInfoController` 文件接口，用于上传头像
  * `PageController` 页面接口，用于将前端页面返回给浏览器打开
  * `SystemController` 系统核心接口，用于获取用户信息，登录等
  * `SystemUserController` 系统用户接口，用于管理用户
  * `TestController` 测试接口，可以直接删掉这个文件
* service
    * `GuideService` 填写指导教师时的具体逻辑
    * `ReviewService` 填写评阅教师时的具体逻辑
    * `SubjectService` 课题的具体逻辑，如课题的添加，删除及课题状态的流转等
    * `FileInfoService` 针对文件的具体逻辑
    * `SystemService` 系统核心功能的具体逻辑
    * `SystemUserService` 系统用户相关的具体逻辑
---
>### 数据库
* 通用字段(即每个数据表都有的字段)
  * `id` 主键
  * `create_time` 数据创建时间
  * `update_time` 数据更新时间
  * `del_flag` 删除标记
--- 
* `guide_teacher` 指导教师内容表
  * `content` 指导教师的评语
  * `teacher_id` 指导教师的用户id
---
* `revire_teacher` 评阅教师内容表
  * `content` 评阅教师的评语
  * `teacher_id` 评阅教师的用户id
---
* `system_user` 用户表
  * `age` 年龄
  * `name` 姓名
  * `password` 密码
  * `role` 用户权限
  * `status` 用户的状态
  * `username` 用户名
  * `head_img_id` 头像文件的id
---
* `status` 状态表
  * `code` 状态码
  * `content` 状态的内容(备注或其他信息等)
---
* `subject_status` 课题状态表(用于管理课题及其对应状态的中间表)
  * `subject_id` 课题id
  * `status_id` 状态id
---
* `status_files` 状态文件关联表(用于关联每种状态下产生的文件)
  * `status_id` 状态id
  * `files_id` 文件id
---
* `subject` 课题表
  * `create_by_id` 课题创建人id
  * `description` 课题简介
  * `name` 课题名称
  * `status_id` 状态id
  * `is_select` 可以是否可以被学生选择
  * `selecter_id` 选择的学生的id
  * `address` 答辩地点
  * `score` 分数
  * `time` 答辩时间
  * `guide_teacher_id` 指导教师评价id
  * `review_teacher_id` 评阅教师评价id
  * `reviewer` 评阅教师id
  * `paper_pdf_url` 答辩现场用表文件的url地址
  * `note` 最终评语
  * `scord_pdf_url` 最终成绩表文件的url地址
  * `is_good` 是否为优秀毕业论文
  * `two_time` 二次答辩时间
---
* `subject_groups` 课题组员表
  * `subject_id` 课题的id
  * `groups_id` 组员的id
---
* `file_info` 文件表
  * `content_type` 文件类型
  * `file_size` 文件大小
  * `old_name` 文件上传时的文件名
  * `upload_path` 文件上传后的存储位置
  * `virtual_path` 文件在前端的访问路径
---

