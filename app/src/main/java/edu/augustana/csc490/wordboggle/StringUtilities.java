package edu.augustana.csc490.wordboggle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emilyleazer11 on 4/1/2015.
 */
public class StringUtilities {


    //sends a word in from either animal or food array list and returns the word scrambled
    public static String wordScramble (String word){
        char[] wordCharArray = word.toCharArray();
        List<Character> wordCharArrayList = new ArrayList<Character>();
        for(char c : wordCharArray){
            wordCharArrayList.add(c);
        }

        Collections.shuffle(wordCharArrayList);
        String scrambledWord = "";
        for(int i=0; i<wordCharArrayList.size(); i++){
            scrambledWord = scrambledWord + wordCharArrayList.get(i);
        }

        return scrambledWord;
    }



}
