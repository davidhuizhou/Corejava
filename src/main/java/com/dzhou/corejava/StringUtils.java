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


    public static void main(String[] args){
        InputOutputWrapper wrapper = new InputOutputWrapper(new Scanner(System.in), System.out);
        StringUtils util = new StringUtils();
        String s = util.getInput(wrapper);

        util.printInput(s, wrapper);
    }

}
