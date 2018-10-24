## 万能资源管理系统

* 抽象resource概念，可以表示任意资源

* mysql表结构
1. 资源表t_cms_resource
id,create_time,update_time,deleted, author_id, name,type, description, content, cover, link, publish_time, status, title, extra
type可以不断扩张，可以是课程，视频，文章，课程包，等等

2. 资源属性表t_cms_resource_attribute
id,resource_id,name,value

3. 资源分类表t_cms_resource_category
id, resource_id, category_id, platform_id
分类是一组资源的聚合，可以灵活修改，扩张

4. 资源关系表：t_cms_resource_relation
id, resource_id, parent_parent_id, order

这个方案的问题是对于resource的attribute不能很灵活的查询，因为resource表和attribute表join后造成resource的不同属性变成n行无法进行and操作。

* mongodb
如果用mongodb可以很好的解决属性查询问题，因为mongodb支持json形式存储，可以将attribute作为resource的一个字段，并且attribute字段支持查询
