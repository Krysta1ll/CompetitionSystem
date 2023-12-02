package csu.krystal.recommsys.common.compkey.tools;

import csu.krystal.recommsys.common.compkey.entity.KeyWord;
import csu.krystal.recommsys.entity.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KwToML {
    // 将KeyWord转化为 List<Model>类同时,加上创建时间
    public static List<Model> getModelsFromKw(KeyWord keyWord){
        List<Model> modelList = new ArrayList<>();
        StringBuilder astringBuilder = new StringBuilder();
        for(int i = 0; i < keyWord.midKeywords.size(); i++){
            astringBuilder.append(keyWord.midKeywords.get(i)).append(" ");
        }
        String astring = astringBuilder.toString();

        for(int i = 0; i < keyWord.compKeywords.size(); i++){
            Model model = new Model();

            // 种子关键字
            model.setS(keyWord.seedKeyword);
            // 联询中介关键字
            model.setA(astring);
            //竞争关键字
            model.setK(keyWord.compKeywords.get(i));
            //竞争度
            String compString = String.format("%.6f", keyWord.compPower[i]);
            model.setComp(compString);
            model.setCreateTime(new Date());
            modelList.add(model);
        }

        return modelList;
    }
}
