### 主要实现业务功能
+ 首页轮播图列表展示
  + localhost:8080/api/v1/pub/video/list_banner
+ 首页视频订单列表展示（不分页）
  + localhost:8080/api/v1/pub/video/list_video
+ 查看视频详情
  + localhost:8080/api/v1/pub/video/find_detail_by_id?video_id=44
+ 模糊查询视频课程
  + localhost:8080/api/v1/pub/video/query_video?point=9.10&title=Dubbo
+ 新增视频课程
  + localhost:8080/api/v1/pub/video/add
+ 批量新增课程视频
  + localhost:8080/api/v1/pub/video/addBatch
+ 更新视频
  + localhost:8080/api/v1/pub/video/updateVideo
+ 根据条件动态更新视频
  + localhost:8080/api/v1/pub/video/updateVideoSelective
+ 根据条件删除视频
  + localhost:8080/api/v1/pub/video/deleteByCreateTimeAndPrice
+ 用户注册
  + localhost:8080/api/v1/pri/user/register
+ 用户登录
  + localhost:8080/api/v1/pri/user/login
+ 用户下单
  + localhost:8080/api/v1/pri/video_order/save
+ 订单列表查询（不分页）
    + localhost:8080/api/v1/pri/video_order/list
+ 分页查询全部订单列表
    + localhost:8080/api/v1/pri/video_order/page_all

### mybatis技术栈主要功能点
+ 原生各种配置
+ 与spring boot整合后的配置方式
    + application.properties中配置
        + 打印sql
        + 驼峰转换
        + 扫描Mapper.xml文件
        + 配置xml的对象别名
 + Mapper.xml中的各种标签用法
    + 参考官网进行编写
    + 增删改查的标准写法
    + 单表分页与多表关联查询分页的写法（整合pagehelper）
    + 其它辅助标签的用法
 + Mapper.java中的各种注解的用法
    + 参考官网进行编写
+ 一级/二级缓存实现、运用以及原理
+ 自定义插件的开发与spring boot的集成使用。（未完成）


> 建议还是使用Mapper.xml方便一些，纯注解的方式开发工作量大，且没有自动提示。
    
原生Mybatis框架没有提供分页插件的支持，需要自己基于自己使用的数据库语法去实现。
比如MySQL数据库需要使用LIMIT语法来实现：
select * from user where user_type = 'A' LIMIT 0,10;

另外就是可以使用一些开源的分页插件来实现，比如pagehelper，使用pagehelper插件非常简单，只需要在正常的查询代码中增加如下一行代码即可：
PageHelper.startPage(page, size);
