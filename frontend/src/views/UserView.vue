<template>
    <div class="user-keywords">
        <!-- 居中放置框体 -->

        <div class="center-container">
            <header>
                <h1 style="align-items: center">竞争性关键词评分系统</h1>
            </header>
            <el-button class="logout-button" @click="logout">退出登录</el-button>
            <!-- 移动搜索栏到框体外部 -->
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

            <!-- 添加框体，并调整内部样式 -->
            <div class="glass-box">
                <p>搜索结果</p>
                <div v-if="keywords.length > 0" class="keywords-list">
                    <div
                        v-for="(keyword, index) in keywords"
                        :key="index"
                        class="keyword-item"
                        @mouseenter="showCompetitiveness(keyword.comp)"
                        @mouseleave="hideCompetitiveness()"
                    >
                        <span>{{ keyword.k }}</span>
                        <el-rate v-model="ratings[index]" show-text @change="rateKeyword(index)"></el-rate>
                    </div>
                </div>
                <!-- 显示竞争度 -->
                <div v-if="showCompetitive" class="competitive-info">
                    竞争度: {{ currentCompetitiveness }}
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import axios from "axios";
import index from "@/views/Index.vue";
import VueWordCloud from "vue-wordcloud";
export default {
    components: {
        VueWordCloud,
    },
    computed: {
        index() {
            return index
        }
    },
    data() {
        return {
            searchQuery: "",
            keywords: [], // 存储关键词及其评分
            ratings: [], // 存储评分
            user: "", // 用户信息
            username: "",
            email: "",
            showCompetitive: false, // 是否显示竞争度
            currentCompetitiveness: "", // 当前关键词的竞争度
            wordCloudData: [
                { text: "词汇1", value: 10 },
                { text: "词汇2", value: 8 },
                { text: "词汇3", value: 6 },
                { text: "词汇4", value: 4 },
                { text: "词汇5", value: 2 },
            ],
        };
    },
    created() {
        if (!localStorage.getItem("userToken_user")) this.$router.push("/");
        const token = localStorage.getItem('userToken_user');
        console.log(token);
        axios.defaults.headers.common['token'] = `${token}`;
    },
    methods: {
        showCompetitiveness(competitiveness) {
            this.currentCompetitiveness = competitiveness;
            this.showCompetitive = true;
        },

        // 鼠标移出时隐藏竞争度
        hideCompetitiveness() {
            this.showCompetitive = false;
        },

        logout() {
            // 移除JWT Token
            localStorage.removeItem('userToken_user');
            // 清空用户信息（如果有的话）
            this.user = '';
            this.username = '';
            this.email = '';
            // 重定向到登录页面
            this.$router.push("/");
        },
        fetchKeywords() {
            axios.get(`/models/${this.searchQuery}`)
                .then(response => {
                    // 清空原有数据
                    this.keywords = [];
                    this.ratings = [];
                    // 添加搜索结果
                    this.keywords = response.data.data;
                    // 初始化评分数组
                    this.ratings = new Array(this.keywords.length).fill(0);
                    console.log(response.data.data);
                })
                .catch(error => {
                    console.error('搜索失败', error);
                    this.$message.error('搜索过程中发生错误');
                });
        },
        rateKeyword(index) {
            // 发送评分到后端或进行其他操作
            const keyword = this.keywords[index];
            const rating = this.ratings[index];
            // 可以在这里将评分信息发送到后端保存，示例代码如下：
            // axios.post('/ratings', { keywordId: keyword.id, rating: rating })
            //     .then(response => {
            //         this.$message.success('评分成功');
            //     })
            //     .catch(error => {
            //         console.error('评分失败', error);
            //         this.$message.error('评分过程中发生错误');
            //     });
        },
    }
}
</script>
<style>

.center-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh; /* 让内容垂直居中 */
}

.search-input {
    width: 80%; /* 调整搜索栏宽度 */
    max-width: 400px;
    margin-bottom: 20px; /* 调整搜索栏与框体的距离 */
}

.glass-box {
    backdrop-filter: blur(10px);
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 400px;
    height: 300px;
    max-width: 400px;
    color: #555; /* 修改字体颜色 */
    display: flex;
    flex-direction: column;
    align-items: center;
    background: rgba(255, 255, 255, 0.7);
    background: linear-gradient(to right, #a1c4fd 0%, #c2e9fb 100%);
}

.keyword-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    font-size: 18px; /* 调整字体大小 */
    color: #333; /* 修改字体颜色 */
}

.logout-button {
    background-color: #ff4d4f; /* 赤红色背景 */
    color: white; /* 白色文字 */
    border: none;
    border-radius: 4px; /* 轻微的圆角 */
    padding: 10px 15px; /* 内边距 */
    margin-bottom: 20px; /* 与框体的距离 */
}

.logout-button:hover {
    background-color: #ff7875; /* 深一点的红色 */
}
.competitive-info {
    margin-top: 5px;
    font-size: 14px;
    color: #555;
}
.wordcloud-container {
     margin-top: 20px;
 }
</style>
