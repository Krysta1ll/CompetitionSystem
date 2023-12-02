package csu.krystal.recommsys.common.compkey;


import csu.krystal.recommsys.common.compkey.tools.DataHandler;

import java.io.IOException;

public class HandleData {

    public static void main(String[] args) throws IOException {
        DataHandler dataHandler = new DataHandler();
        String inputFileName;
        String outputFileName;

        System.out.println("-------竞争关键词 ");
        //获取原数据，并保存
        inputFileName = Path.USER_TAG_QUERY_TRAIN; // 原文件
        outputFileName = Path.TRAIN_ORIGIN_DATA; //原数据
        dataHandler.getOriginData(inputFileName, outputFileName);

        //将原数据第一步处理：去除无关数据，保留搜索内容
        //去除无效搜索内容
        inputFileName = Path.TRAIN_ORIGIN_DATA;
        outputFileName = Path.QUERIES_DATA;
        dataHandler.getQueries(inputFileName, outputFileName);

        //进一步处理，从搜索内容中提取关键词
        inputFileName = Path.QUERIES_DATA;
        outputFileName = Path.KEYWORDS_DATA_BY_JIEBA;

        //将输入文件中的 搜索内容分词保存 并显示 频率前十的关键词
        dataHandler.splitWord(inputFileName, outputFileName);
        dataHandler.getMostFrequentWords(10, Path.KEYWORDS_DATA_BY_JIEBA);

    }
}
