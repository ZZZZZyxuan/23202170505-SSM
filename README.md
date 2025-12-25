# 淘票票影评社区

基于 SSM 框架的电影评分与评论社区系统

## 项目简介

这是一个基于 Spring + SpringMVC + MyBatis (SSM) 框架的电影评论社区平台，支持用户注册登录、浏览电影、发表评分和评论等功能。

## 技术栈

- **后端框架**: Spring 5.3.20 + SpringMVC + MyBatis 3.5.10
- **数据库**: MySQL 8.0
- **连接池**: Druid 1.2.11
- **前端技术**: JSP + JSTL + CSS3 + JavaScript
- **项目构建**: Maven
- **服务器**: Tomcat 9.x

## 功能模块

### 1. 用户模块
- 用户注册（用户名、密码、昵称）
- 用户登录/登出
- 个人中心

### 2. 电影模块
- 电影列表展示
- 按类型筛选（剧情、科幻、动画、爱情等）
- 按地区筛选（中国、美国、日本等）
- 关键词搜索（支持电影名、导演、演员搜索）
- 电影详情页

### 3. 评论模块
- 发表评分（1-5星）
- 发表评论
- 查看我的评价
- 修改/删除评论

## 项目结构

```
23202170505-SSM/
├── pom.xml                          # Maven配置
├── database/
│   └── movie_community.sql          # 数据库脚本
├── src/
│   └── main/
│       ├── java/org/zhengyuxuan/
│       │   ├── controller/          # 控制器层
│       │   ├── entity/              # 实体类
│       │   ├── interceptor/         # 拦截器
│       │   ├── mapper/              # MyBatis Mapper接口
│       │   ├── service/             # 服务层接口
│       │   │   └── impl/            # 服务层实现
│       │   ├── util/                # 工具类
│       │   └── vo/                  # 视图对象
│       └── resources/
│           ├── db.properties        # 数据库配置
│           ├── mybatis-config.xml   # MyBatis配置
│           ├── spring-context.xml   # Spring配置
│           ├── spring-mvc.xml       # SpringMVC配置
│           └── mapper/              # MyBatis映射文件
└── web/
    ├── index.jsp
    ├── static/                      # 静态资源
    │   ├── css/
    │   └── images/
    └── WEB-INF/
        ├── web.xml
        └── views/                   # JSP视图
            ├── common/
            ├── movie/
            └── user/
```

## 运行步骤

### 1. 环境准备
- JDK 8+
- Maven 3.6+
- MySQL 8.0+
- Tomcat 9.x

### 2. 数据库配置
1. 创建数据库并执行SQL脚本：
   ```sql
   source database/movie_community.sql
   ```

2. 修改数据库连接配置 `src/main/resources/db.properties`：
   ```properties
   jdbc.username=你的数据库用户名
   jdbc.password=你的数据库密码
   ```

### 3. 在IDEA中运行
1. 使用 IntelliJ IDEA 打开项目
2. 等待 Maven 下载依赖
3. 配置 Tomcat 服务器：
   - Run > Edit Configurations
   - 添加 Tomcat Server > Local
   - 选择 Tomcat 9.x 安装目录
   - Deployment 中添加 Artifact: `23202170505-SSM:war exploded`
   - Application context 设置为 `/`
4. 运行 Tomcat 服务器
5. 访问 http://localhost:8080/

### 4. 测试账号
- 用户名: `admin` / 密码: `123456`
- 用户名: `zhangsan` / 密码: `123456`
- 用户名: `lisi` / 密码: `123456`

## API接口

### 用户相关
- `POST /api/user/register` - 用户注册
- `POST /api/user/login` - 用户登录
- `GET /api/user/logout` - 用户登出
- `GET /api/user/current` - 获取当前用户
- `GET /api/user/check?username=xxx` - 检查用户名是否可用

### 电影相关
- `GET /api/movie/list` - 获取电影列表（支持筛选）
- `GET /api/movie/{id}` - 获取电影详情
- `GET /api/movie/genres` - 获取所有类型
- `GET /api/movie/regions` - 获取所有地区

### 评论相关
- `POST /api/review/add` - 发表评论
- `GET /api/review/my` - 获取我的评论
- `GET /api/review/movie/{movieId}` - 获取电影评论
- `DELETE /api/review/{id}` - 删除评论

## 注意事项

1. 确保 MySQL 服务已启动
2. 数据库字符集建议使用 `utf8mb4`
3. 首次运行需要先执行数据库脚本
4. 如遇到乱码问题，请检查 Tomcat 和 IDEA 的编码设置

## 作者

郑宇轩 - 学号: 23202170505

