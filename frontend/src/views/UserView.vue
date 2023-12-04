<template>
    <header class="header">
        <h1>竞争性关键词评分系统</h1>

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
    </header>
    <div class="user-keywords">
        <!-- 居中放置框体 -->

        <div class="center-container">


            <div class="glass-box">
                <p>搜索结果</p>
                <div v-if="keywords.length > 0" class="keywords-list">
                    <div
                        v-for="(keyword, index) in keywords"
                        :key="index"
                        class="keyword-item"
                    >
                        <span>{{ keyword.k }}</span>
                        <span class="competitiveness">竞争度: {{ keyword.comp }}</span>
                        <el-tooltip class="item" effect="dark" placement="top" :content="'评分: ' + ratings[index]">
                            <el-rate v-model="ratings[index]"  @change="rateKeyword(index)"></el-rate>
                        </el-tooltip>
                    </div>
                </div>
            </div>

            <div class="glass-box">
                <el-button @click="generateWordCloud">生成云图</el-button>
                <p>云图</p>
                <div ref="wordCloud" class="echarts-word-cloud"></div>
                <!-- 在这里放置您的云图组件 -->

            </div>
        </div>

    </div>


</template>

<script>
import axios from "axios";
import * as echarts from 'echarts/core';

import { CanvasRenderer } from 'echarts/renderers';

// 注册词云图和渲染器
echarts.use([CanvasRenderer]);

export default {
    components: {
        // 您的组件
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
        };
    },

    computed: {

    },

    created() {
        if (!localStorage.getItem("userToken_user")) this.$router.push("/");
        const token = localStorage.getItem('userToken_user');
        axios.defaults.headers.common['token'] = `${token}`;
    },



    methods: {
        logout() {
            localStorage.removeItem('userToken_user');
            this.user = '';
            this.username = '';
            this.email = '';
            this.$router.push("/");
        },
        generateWordCloud() {
            // 使用 ref 获取 wordCloud 元素
            const chart = echarts.init(this.$refs.wordCloud);

            // 从 this.keywords 中获取数据，转换为云图数据格式
            const wordCloudData = this.keywords.map(keyword => ({
                name: keyword.k,
                value: keyword.comp
            }));

            // 设置云图的配置项
            const option = {
                series: [{
                    type: 'wordCloud',
                    gridSize: 2,
                    sizeRange: [12, 50],
                    rotationRange: [-90, 90],
                    shape: 'pentagon',
                    width: '100%',
                    height: '100%',
                    textStyle: {
                        normal: {
                            color: () => {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 255),
                                    Math.round(Math.random() * 255),
                                    Math.round(Math.random() * 255)
                                ].join(',') + ')';
                            }
                        }
                    },
                    data: wordCloudData
                }]
            };

            // 使用 setOption 方法设置云图配置
            chart.setOption(option);
        },
        fetchKeywords() {
            axios.get(`/models/${this.searchQuery}`)
                .then(response => {
                    this.keywords = response.data.data;
                    this.ratings = new Array(this.keywords.length).fill(0);
                })
                .catch(error => {
                    console.error('搜索失败', error);
                    this.$message.error('搜索过程中发生错误');
                });
        },
        rateKeyword(index) {
            const keyword = this.keywords[index];
            const rating = this.ratings[index];
            // 发送评分到后端的逻辑
        },
    }
}
</script>

<style>

.center-container {
    display: flex;
    flex-direction: row; /* 水平排列子元素 */
    align-items: flex-start;
    justify-content: space-around;
    flex-wrap: wrap;
    height: auto; /* 自动高度 */
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
    height: 300px;
    width: 45%;
    max-width: 400px;
    color: #555; /* 修改字体颜色 */
    display: flex;
    flex-direction: column;
    align-items: center;
    background: rgba(255, 255, 255, 0.7);
    background: linear-gradient(to right, #a1c4fd 0%, #c2e9fb 100%);
    flex: 1;
    margin: 10px;

}
.keyword-item {
    display: flex;
    align-items: center;
    justify-content: space-between; /* 调整为平均分布 */
    margin-bottom: 10px;
    font-size: 18px;
    color: #333;
}

.competitiveness {
    margin-left: 10px;
    color: #666;
    font-size: 15px;
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
.header {
    flex-direction: column;
    align-items: center;
    justify-content: center;
    display: flex;
}

.echarts-word-cloud {
    width: 100%;
    height: 400px;
}
</style>
