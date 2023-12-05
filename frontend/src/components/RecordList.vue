<template>
    <div class="record-list">
        <hr>
        <el-button type="primary" @click="fetchRecords">刷新列表</el-button>
<!--        <el-input-->
<!--            v-model="searchUser"-->
<!--            placeholder="搜索用户"-->
<!--            @input="searchRecords"-->
<!--        ></el-input>-->
        <hr>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        :current-page="currentPage"
        :total="totalRecords"
        layout="total, sizes, prev, pager, next, jumper"
        ></el-pagination>
        <el-table :data="filteredRecords" style="width: 100%">
            <el-table-column prop="id" label="记录ID"></el-table-column>
            <el-table-column prop="userId" label="用户ID"></el-table-column>
            <el-table-column prop="content" label="内容"></el-table-column>
            <el-table-column prop="runTime" label="运行时间"></el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
        </el-table>
    </div>
</template>
<script>
import axios from "axios";

export default {
    data() {
        return {
            records: [],
            searchUser: "",
            currentPage: 1, // 当前页数
            pageSize: 10, // 单页最大条数
            totalRecords: 0, // 总记录数
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
        fetchRecords() {
            const params = {
                current: this.currentPage, // 使用当前页数
                size: this.pageSize, // 使用单页最大条数
            };
            axios
                .get("/records", { params })
                .then((response) => {
                    this.records = response.data.data.records;
                    this.totalRecords = response.data.data.records.length; // 这里应该是从后端获取的总记录数
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
