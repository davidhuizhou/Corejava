package com.dzhou.corejava;

/**
 * Created by davidzhou on 5/7/14.
 */

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


    public static void main(String[] args){
        InputOutputWrapper wrapper = new InputOutputWrapper(new Scanner(System.in), System.out);
        StringUtils util = new StringUtils();
        String s = util.getInput(wrapper);

        util.printInput(s, wrapper);
    }

}
