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

### 乐观锁测试请求
+ localhost:8080/test/version/video/51
+ localhost:8080/test/version/xmlsql/video/51

## mybatis plus主要常用功能点
+ BaseMapper操作单表的各种api使用
+ 多种Wapper包装器查询功能
  + QueryWapper
  + UpdateWapper
  + LambdaQueryWrapper
  + LambdaQueryWrapper
+ 查询性能优化-指定select字段查询
+ 逻辑删除支持 （根据实际情况选择使用）
+ 常用插件
  + 多租户插件 （不建议使用）
  + 分页插件（常用）
  + 乐观锁插件（常用）测试一下自己编写的xml中的sql语句是否也自动支持乐观锁机制吗？