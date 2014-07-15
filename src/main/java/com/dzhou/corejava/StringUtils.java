package com.dzhou.corejava;

/**
 * Created by davidzhou on 5/7/14.
 */

import com.dzhou.util.Stack;

import java.util.*;

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
//        d.add("a");
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

    static List<String> getStartingGoodWords(String s) {
        List<String> firstWords = new ArrayList<String>();

        for (int i = 1; i <= s.length(); i++) {
            String w = s.substring(0, i);
            if (isWord(w))
                firstWords.add(w);

        }

        log("getStartingGoodWords with " + s + " returns:");
        log(getListString(firstWords));
        return firstWords;

    }

    static List<String> getFirstWords(String s){
        List<String> firstWords = getStartingGoodWords(s);

        if(firstWords.size() == 0) {
            for(int i = 1; i <= s.length() - 1; i++){
                List<String> l = getStartingGoodWords(s.substring(i));
                if(l.size() > 0){
                    firstWords.add(s.substring(0, i));
                }


            }
        }
        log("get firstWords returns " + getListString(firstWords));
        return firstWords;
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

    static int addSpaces(List<String> words, String s) {
        int retVal = 0;

        List<String> addList = new ArrayList<String>();
        addList.add(s);

        if (isWord(s))
            retVal = 1;

        List<String> firstWords = getFirstWords(s);

        if (firstWords.size() > 0) {

            for (String w : firstWords) {
                int goodWordCount = 0;
                if (isWord(w))
                    goodWordCount++;

                List<String> l = new ArrayList<String>();
                l.add(w);
                int r = addSpaces(l, s.substring(w.length()));
                goodWordCount += r;

                if (goodWordCount > retVal) {
                    addList = l;
                    retVal = goodWordCount;
                }
                log("********* w=" + w + ",goodWordCount=" + goodWordCount + ",retVal=" + retVal);

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
        int r = addSpaces(words, s);

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
            log("Enter loop with j=" + j + ", length=" + length);
            if(aArray[j] == ' ' || aArray[j] == '\t'){
                log("aArray[" + j + "] is space and IN = " + IN);
                if(IN == false){
                    IN = true;
                    //Shift to right 2 sport starting from j + 1 to replace the space char with %20
                    log("Before shift right by 2, length=" + length + "j=" + j);
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
            log("leave loop with j=" + j + ",length=" + length);
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

    /* compress aabcccaaa to a2b1c3a3 */
    public static String compress(String s){
        if(s.length() == 0)
            return s;

        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int len = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == c){
                len++;
            }else{
                sb.append(c).append(len);
                c = s.charAt(i);
                len = 1;
            }
        }
        sb.append(c).append(len);
        return sb.toString();
    }


    /* zero N * N matrix */
    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this should be a field rather than a method variable so
        // that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    private static int[][] generateMatrix(int n){
        int[][] N = new int[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                N[i][j] = randInt(0, n - 1);

        return N;


    }

    public static void printN(int[][] N){
        int n = N[0].length;

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(N[i][j] + " ");

            System.out.println("");
        }

    }

    public static void setZero(int n){
        int[][] N = generateMatrix(n);
        System.out.println("The original matrix is - ");
        printN(N);

        System.out.println("zero rows and cols -");
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (N[i][j] == 0) {
                    row[i] = true;
                    break;
                }
            }
        }

        for(int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (N[i][j] == 0) {
                    col[j] = true;
                    break;
                }
            }
        }

        for(int i = 0; i < n; i++){
            if(row[i]){
                for(int j = 0; j < n; j++)
                    N[i][j] = 0;
            }
        }

        for(int j = 0; j < n; j++){
            if(col[j]){
                for(int i = 0; i < n; i++)
                    N[i][j] = 0;
            }

        }

        System.out.println("The after matrix is - ");
        printN(N);

    }


    public static void rotateArray(int[][] A){
        int N = A.length;

        for(int layer = 0; layer < N/2; layer++){
            int r1 = layer;
            int c1 = layer;
            int r2 = N - 1 - layer;
            int c2 = N - 1 - layer;

            for(int i = 0; i < c2 - c1; i++){
                int temp = A[r1][c2 - i];
                A[r1][c2 - i] = A[r1 + i][c1];
                A[r1 + i][c1] = A[r2][c1 + i];
                A[r2][c1 + i] = A[r2 -i][c2];
                A[r2 - i][c2] = temp;

            }
        }
    }

    /* Check for Parentheses balance */
    private static boolean isPair(char c1, char c2){
        return (c1 == '{' && c2 == '}')
            || (c1 == '[' && c2 == ']')
            || (c1 == '(' && c2 == ')');
    }

    private static boolean isLeftParenthese(char c){
        return c == '{' || c == '[' || c == '(';
    }

    private static boolean isRightParenthese(char c) {
        return c == '}' || c== ']' || c== ')';
    }

    public static boolean isParenthesesBalanced(String s){
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(isLeftParenthese(c))
                stack.push(c);
            else if (isRightParenthese(c)){
                try {
                    char c1 = stack.pop();
                    if (!isPair(c1, c))
                        return false;
                }catch(NoSuchElementException exc){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /* equivalent infix expression with the parentheses
    *
    * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
    * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
     */
    private static boolean isDigit(char c){
        return '0' <= c && c <= '9';
    }

    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c== '*' || c == '/' || c == '%';
    }

    public static boolean isOperator(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%") || s.equals("sqrt");
    }

    private static String getString(Stack<Character> stack){
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }


    public static String insertLeftParentheses(String s){
        Stack<Character> stack1 = new Stack<Character>();
        Stack<Character> stack2 = new Stack<Character>();
        Stack<Character> stack3 = new Stack<Character>();
        Stack<Character> stack4 = new Stack<Character>();

        for(int i = 0; i < s.length(); i++){
            stack1.push(s.charAt(i));
            if(s.charAt(i) == ')')
                stack2.push('(');
        }

        if(stack2.isEmpty()) {
            return s;
        } else {
            while(!stack1.isEmpty()) {
                char c = stack1.pop();

                if (c == ')') {
                    stack4.push(c);
                } else if (isDigit(c)) {
                    if (stack4.isEmpty())
                        stack4.push('D');
                    else {
                        if (stack4.peek() != 'D')
                            stack4.push('D');
                    }
                } else if (isOperator(c)) {
                    if (stack4.size() >= 4) {
                        do {
                            char c1 = stack4.pop();
                            char c2 = stack4.pop();
                            char c3 = stack4.pop();
                            char c4 = stack4.pop();

                            if(c1 == 'D' && c2 == 'O' && c3 == 'D' && c4 == ')'){
                                stack3.push(stack2.pop());
                                stack4.push('D');

                            } else {
                                stack4.push(c4);
                                stack4.push(c3);
                                stack4.push(c2);
                                stack4.push(c1);

                                break;
                            }

                        } while(stack4.size() >= 4);

                        stack4.push('O');

                    } else {
                        stack4.push('O');
                    }
                }
                stack3.push(c);

            }

            while(!stack2.isEmpty())
                stack3.push(stack2.pop());

        }



        return getString(stack3);

    }

    //This one is not working, it should restart from the first index of the repeating character
    //abcadbc  when it reach abca it restart from bcad and so on
    public static String longestNoneRepeatingSubString(String s){
        int len = s.length();
        if(len <= 1)
            return s;

        String longest = "";
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < len; i++){
            String c = "" + s.charAt(i);
            int index = sb.toString().indexOf(c);

            if(index < 0){
                sb.append(c);
            } else {
                if(sb.toString().length() > longest.length())
                    longest = sb.toString();
                sb = new StringBuilder(sb.toString().substring(index + 1)).append(c);

            }
        }
        if(sb.toString().length() > longest.length())
            longest = sb.toString();
        return longest;
    }

    public static int lengthOfLongestSubstring(String s) {

        char[] arr = s.toCharArray();
        int pre = 0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            } else {
                pre = pre > map.size() ? pre : map.size();
                i = map.get(arr[i]);
                map.clear();
            }
        }

        return Math.max(pre, map.size());
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

        s = "";
        System.out.println("compress(\"" + s + "\")=" + compress(s));

        s = "a";
        System.out.println("compress(\"" + s + "\")=" + compress(s));

        s = "aa";
        System.out.println("compress(\"" + s + "\")=" + compress(s));

        s = "ab";
        System.out.println("compress(\"" + s + "\")=" + compress(s));

        s = "abc";
        System.out.println("compress(\"" + s + "\")=" + compress(s));

        s = "aabcccccaaa";
        System.out.println("compress(\"" + s + "\")=" + compress(s));

        setZero(10);


        s = "";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "{";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = ")";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "[()]{}{[()()]()}";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "[()]{}{[())]()}";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "[()]{}{[))]()}";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "[(])";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "[}{]";
        System.out.println("isParenthesesBalanced(\"" + s + "\") = "  + isParenthesesBalanced(s));

        s = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        System.out.println(insertLeftParentheses(s));

        s = "5 - 6 )";
        System.out.println(insertLeftParentheses(s));

        System.out.println("Before rotate");
        int[][] A = generateMatrix(10);
        printN(A);

        System.out.println("After rotate");
        rotateArray(A);
        printN(A);


        s = "";
        System.out.println(longestNoneRepeatingSubString(s) + lengthOfLongestSubstring(s));

        s = "a";
        System.out.println(longestNoneRepeatingSubString(s) + lengthOfLongestSubstring(s));

        s = "abcabcbb";
        System.out.println(longestNoneRepeatingSubString(s) + lengthOfLongestSubstring(s));

        s = "abcadefghijk";
        System.out.println(longestNoneRepeatingSubString(s) + lengthOfLongestSubstring(s));

        s = "abcadebb";
        System.out.println(longestNoneRepeatingSubString(s) + lengthOfLongestSubstring(s));

        s = "bbbbbbb";
        System.out.println(longestNoneRepeatingSubString(s) + lengthOfLongestSubstring(s));



    }

}
