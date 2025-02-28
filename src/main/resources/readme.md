# SpringBoot项目配置文件加载顺序
1. bootstrap.yml
2. bootstrap-${spring.profile.active}.yml
3. application.yml 

# 加载原则
1. 后加载的配置会覆盖掉原先加载的配置