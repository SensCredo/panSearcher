# panSearcher

## 网盘资源搜索（采用Spring+SpringMVC+MyBatis框架）<br>
可实现对现有公开分享的网盘资源的搜索与展示（数据爬取来源为百度贴吧，暂时支持百度网盘与微盘资源的搜索），搜索界面如下：<br>
![主界面](http://wx3.sinaimg.cn/large/a8c6e1abgy1fvcn05de7aj211y0hemxr.jpg)
<br>结果展示界面如下：<br>
![结果界面](http://wx4.sinaimg.cn/large/a8c6e1abgy1fvx4luz9j1j211y0hedgs.jpg)

### 使用注意

#### 1、项目导入<br>
本项目采用Maven进行依赖管理，项目由父工程panSearcher-parent与panSearcher-beans、panSearcher-dao、panSearcher-service及panSearcher-web四个子模块构成，导入前请将本项目所有文件置入panSearcher-parent文件夹，并以Maven工程导入。<br>

#### 2、数据库配置<br>
本项目使用MySQL数据库进行数据的存贮与管理，项目运行前请配置好MySQL数据库，并根据panSearcher-web/src/main/webapp/db/中的panresult.sql文件创建相应的数据库表。同时请根据自身数据库设置修改panSearcher-dao/src/main/resources/下的jdbc.properties配置文件。<br>
![配置文件](http://wx1.sinaimg.cn/large/a8c6e1abgy1fvcnzfora6j208s02kq2r.jpg)

### 联系方式<br>
本项目由个人单独完成，仍存在诸多缺陷及不足，如果有任何改进建议及功能需求，请通过以下方式与我进行联系：<br>
邮箱： yzasdf@outlook.com<br>
微信号： YZ_2269
