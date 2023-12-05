<template>
    <div class="record-list">
        <hr>
        <el-input v-model="searchUserId" placeholder="请输入用户 ID"></el-input>
        <el-button type="primary" @click="fetchScores">刷新列表</el-button>
        <el-button type="success" @click="showCurrentUserScores">显示当前用户评分</el-button>
        <hr>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-sizes="[5, 10, 20, 30, 40]"
                :page-size="pageSize"
                :current-page="currentPage"
                :total="totalScores"
                layout="total, sizes, prev, pager, next, jumper"
        ></el-pagination>
        <el-table :data="filteredScores" style="width: 100%">
            <el-table-column prop="id" label="编号"></el-table-column>
            <el-table-column prop="score" label="评分星级"></el-table-column>
            <el-table-column prop="modelId" label="关键字编号"></el-table-column>
            <el-table-column prop="userId" label="用户id"></el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            scores: [],
            searchUserId: '', // 用于搜索的用户 ID
            currentUser: '12345', // 假设当前用户的 ID 是 '12345'
            currentPage: 1,
            pageSize: 10,
            totalScores: [],
        };
    },
    mounted() {
        this.fetchScores();
    },
    computed: {
        filteredScores() {
            if (!this.searchUserId) {
                return this.scores;
            } else {
                return this.scores.filter(score =>
                    score.userId.toString().includes(this.searchUserId)
                );
            }
        },
    },
  methods: {
    fetchScores() {
      const params = {
        current: this.currentPage, // 使用当前页数
        size: this.pageSize, // 使用单页最大条数
      };
      axios.get("/scores", { params })
          .then((response) => {
              console.log("test")
              console.log(response.data.data.records)
            this.scores = response.data.data.records;
            this.totalScores = response.data.data.records.length;
          })
          .catch((error) => {
            console.error(error);
          });
    },
    handleSizeChange(val) {
      // 处理单页最大条数变化
      this.pageSize = val;
      this.currentPage = 1; // 重置当前页数为1
      this.fetchScores(); // 重新获取数据
    },
    handleCurrentChange(val) {
      // 处理当前页数变化
      this.currentPage = val;
      this.fetchScores(); // 重新获取数据
    },
  },
    showCurrentUserScores() {
        this.searchUserId = this.currentUser; // 设置搜索 ID 为当前用户的 ID
        this.fetchScores(); // 获取并显示当前用户的评分数据
    },
};
</script>

<style>
/* 在这里添加样式 */
</style>
