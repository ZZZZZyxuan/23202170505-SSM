# 淘票票影评社区 - SSM框架实战项目

## 项目简介

基于SSM（Spring + SpringMVC + MyBatis）框架开发的电影评分与评论社区，实现了用户注册登录、电影信息展示、评分评论、个人中心等核心功能。

## 技术栈

- **后端框架**：Spring 5.3.20 + SpringMVC + MyBatis 3.5.10
- **数据库**：MySQL 8.0 + Druid连接池
- **前端技术**：JSP + JSTL + CSS3 + JavaScript (Fetch API)
- **项目构建**：Maven
- **日志框架**：SLF4J + Logback

## 功能特点

### 1. 用户模块
- 用户注册（用户名唯一性校验、密码MD5加密）
- 用户登录/登出
- 登录状态保持（Session）

### 2. 电影模块
- 电影列表展示
- 按类型、地区筛选（动态SQL）
- 关键词搜索（标题/导演/演员）
- 电影详情查看

### 3. 评论模块
- 发表评分（1-5星）和评论
- 每个用户对每部电影只能评价一次（可更新）
- 自动计算并更新电影平均评分

### 4. 个人中心
- 查看"我评价过的电影"
- 查看"我的评论"
- 删除自己的评论

## 项目结构

```
23202170505-SSM/
├── pom.xml                          # Maven配置文件
├── database/
│   └── movie_community.sql          # 数据库初始化脚本
├── src/main/
│   ├── java/org/zhengyuxuan/
│   │   ├── controller/              # 控制层（RESTful API）
│   │   │   ├── UserController.java
│   │   │   ├── MovieController.java
│   │   │   ├── ReviewController.java
│   │   │   └── PageController.java
│   │   ├── service/                 # 业务逻辑层
│   │   │   ├── UserService.java
│   │   │   ├── MovieService.java
│   │   │   ├── ReviewService.java
│   │   │   └── impl/                # 实现类
│   │   ├── mapper/                  # 持久层（MyBatis Mapper）
│   │   ├── entity/                  # 实体类
│   │   ├── vo/                      # 视图对象
│   │   ├── util/                    # 工具类
│   │   └── interceptor/             # 拦截器
│   └── resources/
│       ├── spring-context.xml       # Spring IoC配置
│       ├── spring-mvc.xml           # SpringMVC配置
│       ├── mybatis-config.xml       # MyBatis配置
│       ├── db.properties            # 数据库连接配置
│       ├── logback.xml              # 日志配置
│       └── mapper/                  # MyBatis XML映射
└── web/
    ├── WEB-INF/
    │   ├── web.xml                  # Web应用配置
    │   └── views/                   # JSP视图
    │       ├── common/              # 公共组件
    │       ├── movie/               # 电影相关页面
    │       └── user/                # 用户相关页面
    └── static/
        └── css/style.css            # 样式文件
```

## 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Tomcat 9.0+

## 快速开始

### 1. 数据库配置

1. 创建MySQL数据库并执行初始化脚本：
```sql
source database/movie_community.sql
```

2. 修改数据库连接配置 `src/main/resources/db.properties`：
```properties
jdbc.url=jdbc:mysql://localhost:3306/movie_community?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=你的密码
```

### 2. 构建项目

```bash
mvn clean package
```

### 3. 部署运行

将生成的 `target/23202170505-SSM-1.0-SNAPSHOT.war` 部署到Tomcat，或直接在IDEA中配置Tomcat运行。

### 4. 访问应用

浏览器访问：`http://localhost:8080/23202170505-SSM/`

### 5. 测试账号

| 用户名 | 密码 | 说明 |
|--------|------|------|
| admin | 123456 | 管理员 |
| zhangsan | 123456 | 测试用户 |
| lisi | 123456 | 测试用户 |

## API接口

### 用户接口
| 接口 | 方法 | 说明 |
|------|------|------|
| /api/user/register | POST | 用户注册 |
| /api/user/login | POST | 用户登录 |
| /api/user/logout | GET | 用户登出 |
| /api/user/current | GET | 获取当前用户 |
| /api/user/check | GET | 检查用户名可用性 |

### 电影接口
| 接口 | 方法 | 说明 |
|------|------|------|
| /api/movie/list | GET | 获取电影列表（支持筛选） |
| /api/movie/{id} | GET | 获取电影详情 |
| /api/movie/genres | GET | 获取所有类型 |
| /api/movie/regions | GET | 获取所有地区 |
| /api/movie/filters | GET | 获取筛选选项 |

### 评论接口
| 接口 | 方法 | 说明 |
|------|------|------|
| /api/review/add | POST | 发表评论 |
| /api/review/my | GET | 获取我的评论 |
| /api/review/movie/{id} | GET | 获取电影评论 |
| /api/review/{id} | DELETE | 删除评论 |

## 技术亮点

1. **三层架构**：Controller → Service → Mapper，职责清晰
2. **RESTful API**：规范的接口设计
3. **动态SQL**：MyBatis实现灵活的条件查询
4. **事务管理**：Spring声明式事务
5. **登录拦截**：自定义拦截器保护需要登录的接口
6. **统一响应**：ResultVO封装API返回结果
7. **密码加密**：MD5加密存储密码
8. **响应式布局**：CSS Grid/Flexbox实现自适应

## 作者

学号：23202170505

---

*本项目为SSM框架学习实践项目*

