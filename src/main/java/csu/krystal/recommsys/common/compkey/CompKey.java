package csu.krystal.recommsys.common.compkey;


import csu.krystal.recommsys.common.compkey.entity.KeyWord;
import csu.krystal.recommsys.common.compkey.tools.DataHandler;
import csu.krystal.recommsys.common.compkey.tools.KwToML;
import csu.krystal.recommsys.entity.Model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompKey {

    public static List<Model> compKey(String seedKeyword, int midNum) {
        System.out.println("-------------Compkey算法------------");
        DataHandler dataHandler = new DataHandler();
        //从经过预处理的搜索内容中，选取出与种子关键字相关的搜索信息,并保存
        System.out.println("开始搜索与种子关键字相关的搜索信息...");
        String inputFileName = Path.QUERIES_DATA;
        String outputFileName = Path.RELATED_QUERIES_DATA;

        //这一步比较无奈,  刚开始设计的时候是输入一串种子关键字, 查找到的相关搜索内容存在同一个文件夹里
        String[] seedKeywords = {seedKeyword};
        //当更新了种子关键词时需要运行 下面这行 获取与 种子关键词相关的搜索内容并分词
        dataHandler.getRelatedQueriesAndSplit(seedKeywords, inputFileName, outputFileName);

        // 首先清空 竞争关键词储存结果文件
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Path.COMPKEY_RESULT, false)); // 使用false参数打开文件，不追加内容
            writer.write(""); // 将文件内容设置为空
            writer.close();
            System.out.println("File content cleared.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        KeyWord keyWord = new KeyWord(seedKeyword, midNum);

        keyWord = dataHandler.getMidWords(keyWord, midNum, Path.RELATED_KEYWORDS_DATA_BY_JIEBA);
        System.out.println("种子关键词:\n" + keyWord.seedKeyword + ":" + keyWord.s);
        System.out.println("----------中介关键词----------");
        System.out.println("中介关键词：");
        for (int j = 0; j < midNum; j++) {
            double weightA = (double) keyWord.sa[j] / keyWord.s;
            System.out.println(keyWord.midKeywords.get(j) + ":" + keyWord.sa[j] + "\t权重:\t" + weightA);
        }

        System.out.println("----------竞争关键词----------");
        System.out.println("-----候选竞争关键词-----");
        //a
        keyWord = dataHandler.getCompWords(keyWord);

        System.out.println("-----计算竞争度-----");
        keyWord = calculateComp(keyWord);
        for (int j = 0; j < 5; j++) {
            System.out.println(keyWord.compKeywords.get(j) + ":\t" + keyWord.compPower[j]);
        }
        System.out.println("--------------------");

        return KwToML.getModelsFromKw(keyWord);
    }

    public static KeyWord calculateComp(KeyWord keyWord){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(Path.TEMP_MIDWORD_DATA)), StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null){
                String[] midWordAndSplit = line.split(":");
                if(midWordAndSplit.length > 1){
                    // ka
                    for(int i = 0; i < keyWord.midKeywords.size(); i++){
                        if(midWordAndSplit[0].equals(keyWord.midKeywords.get(i)))
                            for(int j = 0; j < 5; j++){
                                if (midWordAndSplit[1].contains(keyWord.compKeywords.get(j))){
                                    keyWord.ka[i][j] += 1;
                                }
                            }
                    }
                }
            }
            for(int i = 0; i < 5; i++){
                keyWord.compPower[i] = 0;
                for(int j = 0; j < keyWord.midKeywords.size(); j++){
                    keyWord.compPower[i] += (double) keyWord.sa[j] / keyWord.s * keyWord.ka[j][i] / (keyWord.a[j] - keyWord.sa[j]);
                }
            }

            //冒泡排序
            for(int i = 0; i < 5 - 1; i++){
                for(int j = 0; j < 4 - i ; j++){
                    if (keyWord.compPower[j] < keyWord.compPower[j + 1]){
                        double tempPower = keyWord.compPower[j];
                        keyWord.compPower[j] = keyWord.compPower[j + 1];
                        keyWord.compPower[j + 1] = tempPower;

                        String tempCompKey = keyWord.compKeywords.get(j);
                        keyWord.compKeywords.set(j, keyWord.compKeywords.get(j + 1));
                        keyWord.compKeywords.set(j + 1, tempCompKey);
                    }
                }
            }
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(Path.COMPKEY_RESULT)), StandardCharsets.UTF_8));
            //追加模式
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Path.COMPKEY_RESULT, true), StandardCharsets.UTF_8));

            for(int i = 0; i < 5; i++){
                writer.write("种子关键词:" + keyWord.seedKeyword + ",竞争性关键词:" + keyWord.compKeywords.get(i) + ",竞争度:" + keyWord.compPower[i]);
                writer.newLine();
            }
            writer.close();


        }catch (IOException e){
            e.printStackTrace();
        }
        return keyWord;
    }
}
