package csu.krystal.recommsys.common.compkey.tools;


import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import csu.krystal.recommsys.common.compkey.Path;
import csu.krystal.recommsys.common.compkey.entity.KeyWord;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataHandler {
    /**
     * 读取原数据，保存为txt文档
     */
    public void getOriginData(String inputFileName, String outputFileName){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "GBK"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // 读取数据并写入到输出文件
                // 使用制表符
                String[] queries = line.split("\t");
                // 读取关键词并写到输出文件
                for (String query : queries) {
                    writer.write(query);
                    writer.newLine(); // 换行
                }
            }
            System.out.println("原数据已成功写入到 " + outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 预处理数据，初步去除原数据中的无关内容，保留有效搜索内容
     */
    public void getQueries(String inputFileName, String outputFileName){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))) {

            String line;
            // 使用正则表达式匹配网址
            String urlRegex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            //不包含中文
            String noChinesePattern = "^[^\\u4e00-\\u9fa5]+$";
//            //正则表达式，包含一个或多个中文字符
//            String regex = "[\\u4e00-\\u9fa5]+";
            // 编译正则表达式
//            Pattern pattern = Pattern.compile(regex);
            // 读取数据并写入到输出文件
            while ((line = reader.readLine()) != null) {
                //
                String trimLine = line.trim();
                // 检查是否匹配
                //筛选 剔除网站和不包含中文的搜索记录
                if (!trimLine.matches(urlRegex) && trimLine.length() > 1 && !trimLine.matches(noChinesePattern)) {
                    //字符串与正则表达式匹配
                    writer.write(line);
                    writer.newLine(); // 换行
                }
            }
            System.out.println("搜索内容已成功写入到 " + outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**分词，去除文本中的停词（无关词），获取有效词
     *
     * @param stopwords  停用词列表
     * @param text     需要去除停用词的文本， 在本项目中 为条搜索记录
     * @throws IOException
     */
//    public List<String> splitWordsByJieBa(List<String> stopwords, String text) throws IOException{
//        List<String> meaningfulWords = new ArrayList<>();
//
//        // 使用 jieba-java 进行分词
//        JiebaSegmenter segmenter = new JiebaSegmenter();
//        List<SegToken> segTokens = segmenter.process(text, JiebaSegmenter.SegMode.SEARCH);
//        // 去除停用词
//        for (SegToken token : segTokens) {
//            String word = token.word;
//            if (!stopwords.contains(word)) {
//                meaningfulWords.add(word);
//            }
//        }
//        return meaningfulWords;
//    }

    public List<String> splitWordsByAnsj(List<String> stopwords, String text) throws IOException{
        List<String> meaningfulWords = new ArrayList<>();

        // 使用Ansj Seg进行分词
        Result result = ToAnalysis.parse(text);
        // 去除停用词
        List<Term> terms = result.getTerms();
        for (Term term : terms) {
            String word = term.getName();
            if (!stopwords.contains(word)) {
                meaningfulWords.add(word);
            }
        }
        return meaningfulWords;
    }

    /** 初步清理原数据，得到搜索内容
     *
     * @param inputFileName  原始数据文件路径
     * @param outputFileName 预处理后文件路径
     */
    public void clean(String inputFileName, String outputFileName){
        String tempFileName = Path.TRAIN_ORIGIN_DATA;
        //获取原数据，并保存
        getOriginData(inputFileName, tempFileName);

        getQueries(tempFileName, outputFileName);
    }


    public void getRelatedQueriesAndSplit(String[] seedKeywords, String inputFileName, String outputFileName){
        try {
            // 创建一个写入器对象
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputFileName)), StandardCharsets.UTF_8));

            //创建停用词列表
            String stopWordsFileName = Path.STOP_WORDS_LIST;
            List<String> stopwords = new ArrayList<>();
            BufferedReader stopwordsReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(stopWordsFileName)), StandardCharsets.UTF_8));
            String str;
            // 读取数据并写入到输出文件
            while ((str = stopwordsReader.readLine()) != null) {
                stopwords.add(str);
            }

            // 输出格式  关键词：分词 分词 分词
            // 比如：
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(inputFileName)), StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null){
                for(String seedKeyword: seedKeywords) {
                    if (line.contains(seedKeyword)) {
                        writer.write(seedKeyword+":");
                        //搜索内容中包含 种子关键词，接下来对这个搜索内容进行分词，去除停词
                        List<String> wordList = splitWordsByAnsj(stopwords, line);
                        for(String word: wordList){
                                writer.write(word + " ");
                        }
                        writer.newLine();
                    }
                }
            }
            System.out.println("与种子关键词相关搜索内容已成功写入到 " + outputFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void splitWord(String inputFileName, String outputFileName) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(inputFileName)), StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputFileName)), StandardCharsets.UTF_8));
        //创建停用词列表
        String stopWordsFileName = Path.STOP_WORDS_LIST;
        List<String> stopwords = new ArrayList<>();
        BufferedReader stopwordsReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(stopWordsFileName)), StandardCharsets.UTF_8));
        String str;
        // 读取数据并写入到输出文件
        while ((str = stopwordsReader.readLine()) != null) {
            stopwords.add(str);
        }

        String line;
        while ((line = reader.readLine()) != null) {
            List<String> wordList = splitWordsByAnsj(stopwords, line);
            if(wordList.isEmpty()) continue;
            StringBuilder outline = new StringBuilder();
            for(String word: wordList){
                    outline.append(word).append(" ");
            }
            if(!outline.isEmpty()){
                writer.write(outline.toString());
                writer.newLine();
            }

        }
        System.out.println("分词结果已成功写入到 " + outputFileName);
    }
    /** 获取文件中关键词出现频率前 n 个
     *
     * @param num 获取n个关键词
     * @param inputFileName 数据来源
     * @throws IOException
     */
    public void getMostFrequentWords(int num, String inputFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(inputFileName)), StandardCharsets.UTF_8));

        // 统计词语出现频率
        Map<String, Integer> wordFrequency = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null){
            String[] wordList = line.split("\\s+");
            for(String word: wordList){
                /*去掉一个字的关键字*/
//                if(word.length() > 1){
//                    wordFrequency.put(word,wordFrequency.getOrDefault(word, 0) + 1);
//                }
                wordFrequency.put(word,wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        //输出频率前十的关键词
        // 将HashMap中的数据转换为List
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());
        // 使用比较器对List进行排序，按值降序排序
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        // 获取排名前十的数据
        List<Map.Entry<String, Integer>> topTenList = list.subList(0, Math.min(num, list.size()));
        // 打印前n个数据
        System.out.println("排名前" + num + "的关键词");
        System.out.println("关键词:频次");
        for (Map.Entry<String, Integer> entry : topTenList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public KeyWord getMidWords(KeyWord keyWord, int midNum, String relatedFileName){
        try{
            //找到与种子关键字相关的中介关键字,统计频率，计算权重
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(relatedFileName)), StandardCharsets.UTF_8));

            //统计
            int s = 0; //种子关键词有关的搜索量

            // 统计与种子关键词一起出现的候选词语频率， 选取前 midNum个为中介关键词
            Map<String, Integer> wordFrequency = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null){
                String[] keyAndWord = line.split(":");
                if(keyAndWord[0].equals(keyWord.seedKeyword)) {
                    //该行数据是关于 种子关键词的
                    s += 1;
                    if(keyAndWord.length > 1){
                        //去掉种子关键词
                        String rmSeedLine = keyAndWord[1].replaceAll(keyWord.seedKeyword, "");
                        rmSeedLine = rmSeedLine.trim();
                        String[] wordList = rmSeedLine.split("\\s+");
                        for (String word : wordList) {
                            /* 排除种子关键词 和 单字 */
                            if(!word.isEmpty() & word.length() > 1){
                                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                            }

                        }
                    }
                }
            }
            //种子关键词的频数
            keyWord.s = s;
            // 将HashMap中的数据转换为List
            List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());
            // 使用比较器对List进行排序，按值降序排序
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            // 获取排名 midNum 的数据
            List<Map.Entry<String, Integer>> topTenList = list.subList(0, Math.min(midNum, list.size()));
            // 打印前n个数据
//            System.out.println("排名前" + midNum + "的中介关键词：");
            int j = 0;
            for (Map.Entry<String, Integer> entry : topTenList) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
                keyWord.midKeywords.add(entry.getKey());
                //种子关键词和 中介关键词同时出现的频数
                keyWord.sa[j] = entry.getValue();
                j++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return keyWord;
    }

    // 在分好词的搜索日志 找查找含有关键词的部分，并统计各中介关键词在所有的搜索日志中出现的频数， 同时统计与中介关键词相关的内容中关键词的频数
    // 挑选频数最高的 5个作为种子关键字的 竞争关键字
    public KeyWord getCompWords(KeyWord keyWord) {
        ArrayList<String> midWordList = keyWord.midKeywords;
        String[] midWords = midWordList.toArray(new String[0]);
        String inputFileName = Path.KEYWORDS_DATA_BY_JIEBA;
        String tempFileName = Path.TEMP_MIDWORD_DATA;
        String outputFileName = Path.TEMP_CANDIDATE_DATA;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(inputFileName)), StandardCharsets.UTF_8));
            BufferedWriter tempWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(tempFileName)), StandardCharsets.UTF_8));

            String line;
            Map<String, Integer> wordFrequency = new HashMap<>();
            while ((line = reader.readLine()) != null){
                for(int i = 0; i < midWords.length; i++){
                    //包含中介关键字
                    if(line.contains(midWords[i])){
                        keyWord.a[i] += 1;
                        //保证 竞争性关键字不同时与种子关键字出现
                        if(!line.contains(keyWord.seedKeyword)){
                            // 去掉中介关键词
                            for(int j = 0; j < keyWord.midKeywords.size(); j++){
                                line = line.replace(midWords[j], "");
                            }
                            line = line.trim();
                            //存储 去掉中介关键并标记中介关键字的搜索记录, 后续有用
                            tempWriter.write(midWords[i] + ":" +line);
                            tempWriter.newLine();

                            if(line.length() >= 1){
                                String[] words = line.split("\\s+");
                                for(String word: words){
                                    if(word.length() > 1){
                                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 将HashMap中的数据转换为List
            List<Map.Entry<String, Integer>> list = new ArrayList<>(wordFrequency.entrySet());
            // 使用比较器对List进行排序，按值降序排序
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            // 获取排名 前 5 的数据
            List<Map.Entry<String, Integer>> topTenList = list.subList(0, Math.min(5, list.size()));

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputFileName)), StandardCharsets.UTF_8));
            for (Map.Entry<String, Integer> entry : topTenList) {
                keyWord.compKeywords.add(entry.getKey());
                System.out.println(entry.getKey() + ":" + entry.getValue());
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return keyWord;
    }
}
