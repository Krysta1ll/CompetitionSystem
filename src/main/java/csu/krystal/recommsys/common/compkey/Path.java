package csu.krystal.recommsys.common.compkey;

import java.io.File;

public class Path {
    public static String USER_TAG_QUERY_TRAIN = "data/user_tag_query.10W.TRAIN";

    public static String QUERIES_DATA = "outcome/queries_data.txt";

    public static String RELATED_QUERIES_DATA = "outcome/related_words_JieBa.txt";

    public static String TRAIN_ORIGIN_DATA = "outcome/origin_data.txt";

    public static String KEYWORDS_DATA_BY_JIEBA = "outcome/keywords_JieBa.txt";

    public static String KEYWORDS_DATA_BY_ANSJ = "outcome/keywords_ANSJ.txt";

    public static String RELATED_KEYWORDS_DATA_BY_JIEBA = "outcome/related_words_JieBa.txt";

    public static String TEMP_MIDWORD_DATA = "temp/midWord_data.txt";

    public static String TEMP_CANDIDATE_DATA = "temp/candidate_compKeyword.txt";

    public static String STOP_WORDS_LIST = "data/stopwords_list";

    public static String RELATED_KEYWORDS_DATA_BY_ANSJ = "outcome/related_words_ANSJ.txt";

    public static String COMPKEY_RESULT =  "outcome/result.txt";

    public static String getFilePath(String relativePath) {
        // 获取当前工作目录
        String currentDir = System.getProperty("user.dir");
        // 构建文件路径
        return currentDir + File.separator + relativePath;
    }
}
