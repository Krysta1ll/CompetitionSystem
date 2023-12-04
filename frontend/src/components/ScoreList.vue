<template>
  <div class="record-list">
    <hr>
    <el-button type="primary" @click="fetchScores">刷新列表</el-button>
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
    <el-table :data="filteredRecords" style="width: 100%">
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
      currentPage: 1, // 当前页数
      pageSize: 10, // 单页最大条数
      totalScores: 0, // 总记录数
    };
  }, mounted() {
    this.fetchRecords();
  },
  computed: {
    filteredRecords() {
      if (!this.searchUser) {
        return this.records;
      } else {
        return this.records.filter((record) =>
            record.user_id.includes(this.searchUser)
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
            this.scores = response.data.data.scores;
            this.totalScores = response.data.data.total; // 设置总记录数
          })
          .catch((error) => {
            console.error(error);
          });
    },
    searchRecords() {

    },
    handleSizeChange(val) {
      // 处理单页最大条数变化
      this.pageSize = val;
      this.currentPage = 1; // 重置当前页数为1
      this.fetchRecords(); // 重新获取数据
    },
    handleCurrentChange(val) {
      // 处理当前页数变化
      this.currentPage = val;
      this.fetchRecords(); // 重新获取数据
    },
  },
};
</script>

<style>
/* 在这里添加样式 */
</style>
