#项目简介
学习分布式配置中心组件spring cloud config 
config-server

它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，
一是config server，二是config client

###访问规则
Spring Cloud Config 有它的一套访问规则，我们通过这套规则在浏览器上直接访问就可以
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
* {application} 就是应用名称，对应到配置文件上来，就是配置文件的名称部分，例如我上面创建的配置文件。
* {profile} 就是配置文件的版本，我们的项目有开发版本、测试环境版本、生产环境版本，
对应到配置文件上来就是以 application-{profile}.yml 加以区分，例如application-dev.yml、application-sit.yml、
application-prod.yml。
* {label} 表示 git 分支，默认是 master 分支，如果项目是以分支做区分也是可以的，那就可以通过不同的 label 来控制访问不同的配置文件了。

#测试
启动程序：访问http://localhost:8888/foo/dev

显示 {"name":"foo","profiles":["dev"],"label":"master",
"version":"792ffc77c03f4b138d28e89b576900ac5e01a44b","state":null,"propertySources":[]}

证明配置服务中心可以从远程程序获取配置信息。

#高可用分布式配置中心
eureka-server作为注册中心

改造config-server

修改application.properties



