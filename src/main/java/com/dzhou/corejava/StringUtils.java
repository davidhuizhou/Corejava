package com.dzhou.corejava;

/**
 * Created by davidzhou on 5/7/14.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringUtils {



    public String getInput(InputOutputWrapper wrapper){

        System.out.println("Please type and type . and hit return to end the input:");
        StringBuilder sb = new StringBuilder();

        String s = "";
        while(!(s = wrapper.nextLine()).equals(".")) {
            sb.append(s).append("\n");
            System.out.println(s);
        }

        return sb.toString();

    }

    public void printInput(String input, InputOutputWrapper wrapper) {
        for (int i = 0; i < input.length(); i++)
            wrapper.printChar(input.charAt(i));
    }

    //Check if a String has all unique characters
    //Assuming ASCII characters only
    public boolean isUniqueChars(String s){
        if(s.length() > 128)
            return false;

        boolean[] char_checker = new boolean[128];

        for(int i = 0; i < s.length(); i++){
            int c = s.charAt(i);
            if(char_checker[c])
                return false;
            else
                char_checker[c] = true;
        }
        return true;

    }

    /* Functions for adding spaces to words starts*/
    private static boolean logToConsole = false;

    static void log(String msg){

        if(logToConsole)
            System.out.println(msg);
    }

    static List<String> getDictionary() {
        List<String> d = new ArrayList<String>();
        d.add("Mail");
        d.add("man");
        d.add("He");
        d.add("is");
        d.add("such");
        d.add("a");
        d.add("good");
        return d;
    }

    static boolean isWord(String s) {
        boolean retVal = false;
        List<String> dictionary = getDictionary();
        for (String w : dictionary)
            if (w.equalsIgnoreCase(s)) {
                retVal = true;
                break;
            }
        log("is Word - " + s + " is " + retVal);
        return retVal;
    }

    static List<String> getStartingWords(String s) {
        List<String> firstWords = new ArrayList<String>();

        for (int i = 1; i <= s.length(); i++) {
            String w = s.substring(0, i);
            if (isWord(w))
                firstWords.add(w);

        }

        log("getFirstWords with " + s + " returns:");
        log(getListString(firstWords));
        return firstWords;

    }

    static List<String> getFirstWords(String s){
        List<String> list = getStartingWords(s);
        if(list.size() == 0) {
            for(int i = 1; i <= s.length(); i++){
                List<String> l = getStartingWords(s.substring(i));
                if(l.size() > 0){
                    list.add(s.substring(0, i));
                }


            }
        }
        return list;
    }

    static private String getListString(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String s : list)
            sb.append(s).append(" ");
        return sb.toString();
    }

    static void concatList(List<String> a, List<String> b) {
        for (String w : b)
            a.add(w);



    }

    static boolean addSpaces(List<String> words, String s) {
        boolean retVal = false;
        List<String> addList = new ArrayList<String>();
        addList.add(s);

        if (isWord(s))
            retVal = true;

        List<String> firstWords = getFirstWords(s);


        if (firstWords.size() > 1 || (firstWords.size() == 1  && firstWords.get(0).length() < s.length()) ) {
            for (String w : firstWords) {
                List<String> l = new ArrayList<String>();
                l.add(w);
                boolean r = addSpaces(l, s.substring(w.length()));

                if (r == true) {
                    if (retVal == true) {
                        if (l.size() > addList.size())
                            addList = l;
                    } else {
                        retVal = true;
                        addList = l;
                    }
                } else {
                    log("r=false, and l is - " + getListString(l) + "addList.size=" + addList.size() + " l.size=" + l.size() );
                    if (retVal == false && l.size() > addList.size()) {
                        addList = l;
                        log("In r== false and addList is now - " + getListString(addList));
                    }
                }

            }


        }

        log("words=" + getListString(words));
        log("addList=" + getListString(addList));
        concatList(words, addList);
        log("retVal=" + retVal + ",After concatList words=" + getListString(words));
        return retVal;
    }

    static String addSpaces(String s){
        if(s.length() == 0)
            return s;

        StringBuilder sb = new StringBuilder();
        List<String> words = new ArrayList<String>();
        boolean r = addSpaces(words, s);

        log("r=" + r);

        for(String w : words){
            sb.append(w).append(" ");
        }
        return sb.toString();
    }

    /* Functions for adding spaces to words ends*/

    public static boolean isPermutation(String s1, String s2){
        if(s1.length() != s2.length())
            return false;

        for(int i = 0; i < s1.length(); i++){
            char c = s1.charAt(i);
            log("i=" + i + ",c=" + c);

            int j = 0;
            log("s2=" + s2);
            while(s2.charAt(j) != c && j < s2.length() - 1) {
                j++;
                log("j=" + j);
            }

            if(s2.charAt(j)  == c) {
                if (j == s2.length() - 1)
                    s2 = s2.substring(0, j);
                else
                    s2 = s2.substring(0, j) + s2.substring(j + 1);
            } else {
                return false;
            }

        }
        return true;
    }

    public static String replaceWithSpaces(String s){
        char[] cArray = s.toCharArray();
        int length = cArray.length;

        System.out.println("length=" + length);

        char[] aArray = new char[cArray.length + 100];
        for(int i = 0; i < cArray.length; i++)
            aArray[i] = cArray[i];

        int j = 0;
        boolean IN = false;

        while(j < length){
            System.out.println("Enter loop with j=" + j + ", length=" + length);
            if(aArray[j] == ' ' || aArray[j] == '\t'){
                System.out.println("aArray[" + j + "] is space and IN = " + IN);
                if(IN == false){
                    IN = true;
                    //Shift to right 2 sport starting from j + 1 to replace the space char with %20
                    System.out.println("Before shift right by 2, length=" + length +"j=" + j);
                    for(int l = length -1; l >= j + 1; l--)
                        aArray[l + 2] = aArray[l];
                    aArray[j] = '%';
                    aArray[j+1] = '2';
                    aArray[j+2] = '0';
                    length += 2;
                    j = j + 3;
                } else {
                    //Shift left one spot to remove the space char
                    for(int l = j; l <= length - 1; l++)
                        aArray[l] = aArray[l + 1];
                    length--;


                }
            } else {
                IN = false;
                j++;
            }
            System.out.println("leave loop with j=" + j + ",length=" + length);
        }

        //Remove if %20 is the last three characters
        if(IN == true){
            aArray[length - 3] = aArray[length];
            aArray[length - 2] = aArray[length + 1];
            aArray[length - 1] = aArray[length + 2];
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
            sb.append(aArray[i]);

        return sb.toString();
    }


    public static void main(String[] args){
//        InputOutputWrapper wrapper = new InputOutputWrapper(new Scanner(System.in), System.out);
//        StringUtils util = new StringUtils();
//        String s = util.getInput(wrapper);
//
//        util.printInput(s, wrapper);

        String s = addSpaces("");
        System.out.println("1 - " + s);

        s = addSpaces("He");
        System.out.println("2 - " + s);

        s = addSpaces("Mad");
        System.out.println("3 - " + s);

        s = addSpaces("HeMad");
        System.out.println("4 - " + s);

        s = addSpaces("Heis");
        System.out.println("4 - " + s);

        s = addSpaces("Heissuchagoodmailman");
        System.out.println("5 - " + s);

        s = addSpaces("Heissuchagoodsmartmailman");
        System.out.println("5 - " + s);

        s = addSpaces("Heissuchagoodperson");
        System.out.println("6 - " + s);

        String s1 = "abcdefghijk";
        String s2 = "bdaeghjikfc";
        String s3 = "bdaeghjikf";
        String s4 = "bdaeghlikfc";
        String s5 = "adaeghjikfc";
        String s6 = "abcdefghijb";

        System.out.println("isPermutation(s1, s2)=" + isPermutation(s1, s2));
        System.out.println("isPermutation(s1, s3)=" + isPermutation(s1, s3));
        System.out.println("isPermutation(s1, s4)=" + isPermutation(s1, s4));
        System.out.println("isPermutation(s1, s5)=" + isPermutation(s1, s5));
        System.out.println("isPermutation(s1, s6)=" + isPermutation(s1, s6));

        String r1 = "Mr John   Smith         ";
        System.out.println(replaceWithSpaces(r1));


    }

}
