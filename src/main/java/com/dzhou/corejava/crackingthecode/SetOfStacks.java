package com.dzhou.corejava.crackingthecode;

/**
 * Created by huizhou on 7/23/14.
 */
public class SetOfStacks {
    int index;
    Stack[] stacks;

    SetOfStacks(int numberOfStacks){
        index = 0;
        stacks = new Stack[numberOfStacks];
        stacks[0] = new Stack();
    }

    public void push(Comparable e) throws StackisFullException{
        try{
            stacks[index].push(e);
        } catch(StackisFullException exc) {
            index++;
            stacks[index] = new Stack();
            stacks[index].push(e);

        }
    }

    public Comparable pop(){
        Comparable e = stacks[index].pop();

        if(e == null && index > 0){
            stacks[index] = null;
            index--;
            e = stacks[index].pop();
        }
        return e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i <= index; i++){
            sb.append("(").append(stacks[i].toString()).append(")");
        }
        return sb.toString();
    }
}
