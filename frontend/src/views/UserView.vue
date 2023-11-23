<template>
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
            keywords: ['test','test2'], // 存储关键词
            ratings: new Array(10).fill(0) // 初始化十个关键词的评分
        };
    },
    methods: {


        // fetchCloudImages(keywords) {
        //   // 获取或生成云图的逻辑
        // }
        // fetchKeywords() {
            // 根据searchQuery获取关键词
            // 假设fetchKeywordsLogic是获取关键词的逻辑
        //     this.keywords = fetchKeywordsLogic(this.searchQuery);
        //
        //     // 这里可以调用获取云图的函数
        // },
        fetchKeywordsLogic() {
            // 使用axios或其他方式获取关键词
            // 示例: axios.get('/api/keywords', { params: { query: this.searchQuery } })

            // 用获取到的数据更新keywords数组
            // this.keywords = response.data.keywords;

            // 获取云图的逻辑
            // this.fetchCloudImages(this.keywords);
        },

        // fetchCloudImages(keywords) {
        //   // 获取或生成云图的逻辑
        // }
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
</style>
