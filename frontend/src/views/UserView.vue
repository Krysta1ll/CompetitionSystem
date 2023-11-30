<template>
    <div class="header">
        <div class="user-info-box">
            <div class="user-info">
                <div class="name">您好，{{username}}</div>
                <div class="email">{{email}}</div>
            </div>
        </div>
        <div class="title-box">
            <div class="title">竞争性关键词评分系统</div>
        </div>
        <el-button class="logout-button" @click="logout">退出登录</el-button>
    </div>
<br>
    <div class="user-keywords">
        <div class="search-container">
            <el-input
                    placeholder="请输入关键词"
                    v-model="searchQuery"
                    class="search-input"
                    @keyup.enter="fetchKeywords"
            >
                <template #append>
                    <el-button icon="el-icon-search" @click="fetchKeywords">搜索</el-button>
                </template>
            </el-input>
        </div>
        <div v-if="keywords.length > 0" class="keywords-list">
            <div v-for="(keyword, index) in keywords" :key="index" class="keyword-item">
                <span>{{ keyword }}</span>
                <el-rate v-model="ratings[index]" show-text></el-rate> <!-- 打分组件 -->
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            searchQuery: '',
            keywords: ['test', 'test2'], // 存储关键词
            ratings: new Array(10).fill(0), // 初始化十个关键词的评分
            user: '',
            username:'',
            email:'',
        };
    },
    created() {
        if (!localStorage.getItem("user-data")) this.$router.push("/")
        this.user = JSON.parse(localStorage.getItem('user-data'));
        this.username = this.user.username;
        this.email = this.user.email;
    },
    methods: {
        logout() {
            // 清除本地存储的用户信息
            localStorage.removeItem('user-data');
            // 重定向到登录页面或其他页面
            this.$router.push('/');
        }

    }
}
</script>

<style>
.user-keywords .search-input {
    margin-bottom: 20px;
}

.keywords-list .keyword-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}

.user-keywords .search-container {
    display: flex;
    justify-content: center; /* 水平居中 */
    margin-bottom: 30px; /* 与下方内容的间距 */
}

.user-keywords .search-input {
    width: 50%; /* 搜索栏宽度 */
    max-width: 400px; /* 最大宽度 */
}

/* 关键词列表样式 */
.keywords-list .keyword-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}


.user-info-box {

    backdrop-filter: blur(10px);
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    margin: 20px;
    max-width: 400px;
    color: #333;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    position: relative; /* 添加定位属性 */
    background: rgba(255, 255, 255, 0.7);
    background: linear-gradient(to right, #a1c4fd 0%, #c2e9fb 100%);

}

/* 修改渐变效果，使其仅出现在用户信息框内 */
.user-info-box::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: -1;
    border-radius: 10px; /* 使渐变背景遵循边框圆角 */
    display: flex;
    align-items: center;

}
.user-keywords .search-container .el-button {
    background-color: #a1c4fd; /* 蓝色背景 */
    color: white; /* 白色文字 */
    border: none;
}

.user-keywords .search-container .el-button:hover {
    background-color: #92baf3; /* 深一点的蓝色 */
}

.title {
    font-size: 1.5em;
    font-weight: bold;
    color: #333;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

}

.user-info .name {
    font-size: 1.2em;
    font-weight: bold;

}

.user-info .email {
    font-size: 1em;
    color: #555;
}
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 30px;
    background: #f5f5f5;
}

.title-box {
    background: rgba(255, 255, 255, 0.7); /* 半透明白色背景 */
    backdrop-filter: blur(10px); /* 毛玻璃效果 */
    border-radius: 10px; /* 圆角 */
    padding: 20px; /* 内边距 */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 阴影效果 */
    margin: 0 20px;
}

.title {
    font-size: 1.5em; /* 增大字体大小 */
    font-weight: bold; /* 字体加粗 */
    color: #333; /* 字体颜色 */
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* 更改字体 */
    text-shadow: none;
}

.logout-button {
    background-color: #ff4d4f; /* 赤红色背景 */
    color: white; /* 白色文字 */
    border: none;
    border-radius: 4px; /* 轻微的圆角 */
    padding: 10px 15px; /* 内边距 */
}

.logout-button:hover {
    background-color: #ff7875; /* 深一点的红色 */
}
</style>
