package com.dzhou.corejava;

import com.dzhou.util.StringUtils;
import com.dzhou.lib.algorithms.StdIn;
import com.dzhou.lib.algorithms.StdOut;
import com.dzhou.util.Stack;

/**
 * Created by huizhou on 7/9/14.
 */
public class Evaluate {

    private static void evaluateInfix(){
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("go"))
                break;

            if(s.equals("("))
                ;
            else if (StringUtils.isOperator(s)){
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();

                if(op.equals("+"))
                    v = vals.pop() + v;
                else if(op.equals("-"))
                    v = vals.pop() - v;
                else if (op.equals("*"))
                    v = vals.pop() * v;
                else if (op.equals("/"))
                    v = vals.pop() / v;
                else if (op.equals("sqrt"))
                    v = vals.pop() / v;
                vals.push(v);

            } else {
                vals.push(Double.parseDouble(s));
            }

        }
        StdOut.println(vals.pop());
    }

    private static String InfixToPostfix(){
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        StringBuilder sb = new StringBuilder();

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("go")) {
                sb.append("go");
                break;
            }

            if(s.equals("("))
                ;
            else if (StringUtils.isOperator(s)){
                ops.push(s);
            } else if (s.equals(")")) {
                if(!vals.isEmpty()) {
                    double v2 = vals.pop();
                    double v1 = vals.pop();
                    sb.append(v1).append(" ").append(v2).append(" ");
                }
                if(!ops.isEmpty()) {
                    String op = ops.pop();
                    sb.append(op).append(" ");
                }

            } else {
                vals.push(Double.parseDouble(s));
            }

        }
        return sb.toString();

    }

    private static void evulatePostfix(){
        Stack<Double> vals = new Stack<Double>();
        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("go"))
                break;

            if (StringUtils.isOperator(s)){
                double v = vals.pop();

                if(s.equals("+"))
                    v = vals.pop() + v;
                else if(s.equals("-"))
                    v = vals.pop() - v;
                else if (s.equals("*"))
                    v = vals.pop() * v;
                else if (s.equals("/"))
                    v = vals.pop() / v;
                else if (s.equals("sqrt"))
                    v = vals.pop() / v;
                vals.push(v);

            } else {
                vals.push(Double.parseDouble(s));
            }

        }
        StdOut.println(vals.pop());
    }

    public static void main(String[] args){
        //evaluateInfix();
        //System.out.println(InfixToPostfix());
        evulatePostfix();

    }
}


