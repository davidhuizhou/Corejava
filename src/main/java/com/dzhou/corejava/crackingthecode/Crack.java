package com.dzhou.corejava.crackingthecode;

/**
 * Created by huizhou on 7/15/14.
 */
public class Crack {


    /**
     *  2.1 Remove duplicates from unsorted linked list
     */
    public static void removeDuplicates(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        System.out.println(list.toString());
        list.removeDuplicates();

        list.addFirst(1);
        list.removeDuplicates();
        System.out.println(list.toString());

        list.addFirst(2);
        list.addFirst(3);
        list.removeDuplicates();
        System.out.println(list.toString());

        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(3);
        list.addFirst(3);
        list.removeDuplicates();
        System.out.println(list.toString());

        list = new LinkedList<Integer>();
        System.out.println(list.toString());
        list.removeDuplicates();

        list.addFirst(1);
        list.removeDuplicates1();
        System.out.println(list.toString());

        list.addFirst(2);
        list.addFirst(3);
        list.removeDuplicates1();
        System.out.println(list.toString());

        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(3);
        list.addFirst(3);
        list.removeDuplicates1();
        System.out.println(list.toString());

        System.out.println(list.kthToLast(0));
        System.out.println(list.kthToLast(1));
        System.out.println(list.kthToLast(2));
        System.out.println(list.kthToLast(3));
        System.out.println(list.kthToLast(4));
        System.out.println(list.kthToLast(5));
        System.out.println(list.kthToLast(6));
        System.out.println(list.kthToLast(7));
        System.out.println(list.kthToLast(8));

        list.deleteNode(list.kthToLast(1));
        System.out.println(list.toString());

        list.deleteNode(list.kthToLast(3));
        System.out.println(list.toString());

        list.deleteNode(list.kthToLast(7));
        System.out.println(list.toString());

        list.deleteNode(list.kthToLast(6));
        System.out.println(list.toString());


    }

    public static void addList(){
        LinkedList<Integer> l1 = new LinkedList<Integer>();
        l1.addFirst(6);
        l1.addFirst(1);
        l1.addFirst(7);
        System.out.println(l1);

        LinkedList<Integer> l2 = new LinkedList<Integer>();
        l2.addFirst(2);
        l2.addFirst(9);
        l2.addFirst(5);
        System.out.println(l2);

        l1 = l1.addList(l1, l2);
        System.out.println(l1);
        l1.reverse();
        System.out.println(l1);

        l1 = new LinkedList<Integer>();
        l1.addFirst(7);
        l1.addFirst(1);
        l1.addFirst(6);
        System.out.println(l1);

        l2 = new LinkedList<Integer>();
        l2.addFirst(5);
        l2.addFirst(9);
        l2.addFirst(2);
        l2.addFirst(7);
        System.out.println(l2);

        l1 = l1.addList1(l1, l2);
        System.out.println(l1);


    }

    public static void main(String[] args){
        removeDuplicates();
        addList();

    }
}
