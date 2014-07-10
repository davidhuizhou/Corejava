package com.dzhou;

import com.dzhou.corejava.StringUtils;
import com.dzhou.lib.algorithms.StdIn;
import com.dzhou.lib.algorithms.StdOut;
import com.dzhou.util.Stack;

/**
 * Created by huizhou on 7/9/14.
 */
public class Evaluate {
    public static void main(String[] args){
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();

            if(s.equals("("))
                ;
            else if (StringUtils.isOperator(s)){
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();

                if(op.equals("+"))
                    v = vals.pop() + v;
                else if(ops.equals("-"))
                    v = vals.pop() - v;
                else if (ops.equals("*"))
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
}
