package yabao.novel.system.txtsystem.business.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortPossible {


    static List<String> resultList = new ArrayList<>();
    static Set<String> hashSet = new HashSet<>();

    public  static void sortPoss(char[] array,char[] result,int height,int dataNumber) {
        if(height == dataNumber) {
            resultList.add(String.valueOf(result));
            hashSet.add(String.valueOf(result));
            return;
        }
        for (char c : array) {
            char[] charsReduce = new char[array.length-1];
            int index = 0;
            for (char c1 : array) {
                if(c1!=c) {
                    charsReduce[index] = c1;
                    index ++;
                }
            }
            result[height] = c;
            sortPoss(charsReduce,result,height + 1,dataNumber);
        }
    }

    public static void main(String[] args) {
        char[] data = "abcefghijk".toCharArray();
        int dataLength = data.length;
        sortPoss(data,new char[dataLength],0, dataLength);
//        resultList.forEach(System.out::println);
        System.out.println( resultList.size());
        System.out.println(hashSet.size());
    }
}
