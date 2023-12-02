package csu.krystal.recommsys.common.compkey.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class KeyWord {
    public String seedKeyword;
    public ArrayList<String> midKeywords;
    public ArrayList<String> compKeywords;
    public double[] compPower;
    public int s;
    public int[] sa;
    public int[][] ka;
    public int[] a;

    public KeyWord(String wordKey, int midNum){
        this.seedKeyword = wordKey;
        this.midKeywords = new ArrayList<>();
        this.compKeywords = new ArrayList<>();
        this.sa = new int[midNum];
        this.ka = new int[midNum][5];
        this.a = new int[5];
        this.compPower = new double[5];

        Arrays.fill(this.sa, 0);
        Arrays.fill(this.a, 0);
        Arrays.fill(this.compPower, 0);
        for(int i = 0; i < midNum; i++){
            Arrays.fill(this.ka[i], 0);
        }
    }
}
