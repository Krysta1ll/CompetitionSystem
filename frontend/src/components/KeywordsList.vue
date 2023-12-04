<template>
    <div class="model-list">
        <hr>
        <el-button type="primary" @click="fetchModels">刷新列表</el-button>
        <el-table :data="models" style="width: 100%">
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="s" label="种子关键字"></el-table-column>
            <el-table-column prop="a" label="中介关键字"></el-table-column>
            <el-table-column prop="k" label="竞争关键字"></el-table-column>
            <el-table-column prop="comp" label="竞争度"></el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            models: [],
        };
    },
    mounted() {
        this.fetchModels();
    },
    methods: {
        fetchModels() {
            axios
                .get("/models") // 替换成您的后端接口地址
                .then((response) => {
                    this.models = response.data.data;
                    console.log(response)
                })
                .catch((error) => {
                    console.error(error);
                });
        },
    },
};
</script>

<style>
/* 在这里添加样式 */
</style>
