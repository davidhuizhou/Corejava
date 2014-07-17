package com.dzhou.corejava.crackingthecode;


/**
 * Created by huizhou on 7/15/14.
 */
public class LinkedListTest<E> {

    /**
     *
     * @param node 2.3 deleteNode
     *
     */
    private static boolean deleteNode(LinkedListNode node){
        if(node == null)
            return false;

        if(node.next == null){
            node = null;
        } else {
            node.e = node.next.e;
            node.next = node.next.next;
        }
        return true;

    }

    /**
     *
     * 2.5 add number list
     */
    private static LinkedListNode<Integer> addList(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2){
        int carry = 0;
        LinkedListNode<Integer> sentinel = new LinkedListNode<Integer>(0);

        LinkedListNode<Integer> n1 = l1;
        LinkedListNode<Integer> n2 = l2;
        LinkedListNode n3 = sentinel;

        while(n1 != null || n2 != null){
            if(n1 != null) {
                carry += n1.e;
                n1 = n1.next;
            }

            if(n2 != null){
                carry += n2.e;
                n2 = n2.next;
            }

            n3.next = new LinkedListNode<Integer>(carry % 10);
            n3 = n3.next;
            carry = carry / 10;

        }
        if(carry == 1)
            n3.next = new LinkedListNode<Integer>(1);

        return sentinel.next;
    }


    public static LinkedListNode<Integer> addList1(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2){
        l1 = l1.reverse();
        l2 = l2.reverse();
        LinkedListNode<Integer> l3 = addList(l1, l2);
        return l3.reverse();

    }

    /**
     *  2.1 Remove duplicates from unsorted linked list
     */
    private static void testRemoveDuplicates() {
        System.out.println("testRemoveDuplicates");
        LinkedListNode<Integer> list = new LinkedListNode<Integer>(1);
        System.out.println(list.toString());
        list.removeDuplicates();

        list = list.addFirst(1);
        list.removeDuplicates();
        System.out.println(list.toString());

        list = list.addFirst(2);
        list = list.addFirst(3);
        list.removeDuplicates();
        System.out.println(list.toString());

        list = list.addFirst(1);
        list = list.addFirst(3);
        list = list.addFirst(4);
        list = list.addFirst(5);
        list = list.addFirst(6);
        list = list.addFirst(7);
        list = list.addFirst(3);
        list = list.addFirst(3);
        System.out.println(list.toString());

        list.removeDuplicates();
        System.out.println(list.toString());

        list = new LinkedListNode<Integer>(1);
        System.out.println(list.toString());
        list.removeDuplicates();

        list = list.addFirst(1);
        list.removeDuplicates1();
        System.out.println(list.toString());

        list = list.addFirst(2);
        list = list.addFirst(3);
        list.removeDuplicates1();
        System.out.println(list.toString());

        list = list.addFirst(1);
        list = list.addFirst(3);
        list = list.addFirst(4);
        list = list.addFirst(5);
        list = list.addFirst(6);
        list = list.addFirst(7);
        list = list.addFirst(3);
        list = list.addFirst(3);
        System.out.println(list.toString());

        list.removeDuplicates1();
        System.out.println(list.toString());
    }

    private static void testKthToLast() {
        System.out.println("testKthToLast");
        LinkedListNode<Integer> list = new LinkedListNode<Integer>(1);
        list = list.addFirst(1);
        list = list.addFirst(2);
        list = list.addFirst(3);
        list = list.addFirst(1);
        list = list.addFirst(3);
        list = list.addFirst(4);
        list = list.addFirst(5);
        list = list.addFirst(6);
        list = list.addFirst(7);
        list = list.addFirst(3);
        list = list.addFirst(3);
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
    }

    private static void testDeleteNode(){
        System.out.println("testDeleteNode");
        LinkedListNode<Integer> list = new LinkedListNode<Integer>(1);
        list = list.addFirst(1);
        list = list.addFirst(2);
        list = list.addFirst(3);
        list = list.addFirst(1);
        list = list.addFirst(3);
        list = list.addFirst(4);
        list = list.addFirst(5);
        list = list.addFirst(6);
        list = list.addFirst(7);
        list = list.addFirst(3);
        list = list.addFirst(3);
        System.out.println(list.toString());

        System.out.println(list);

        deleteNode(list.kthToLast(1));
        System.out.println(list.toString());

        deleteNode(list.kthToLast(2));
        System.out.println(list.toString());

        deleteNode(list.kthToLast(3));
        System.out.println(list.toString());

        deleteNode(list.kthToLast(3));
        System.out.println(list.toString());

        deleteNode(list.kthToLast(4));
        System.out.println(list.toString());

        deleteNode(list.kthToLast(2));
        System.out.println(list.toString());

        deleteNode(list.kthToLast(2));
        System.out.println(list.toString());

    }

    public static void testAddList() {
        System.out.println("testAddList");
        LinkedListNode<Integer> l1 = new LinkedListNode<Integer>(6);
        l1 = l1.addFirst(1);
        l1 = l1.addFirst(7);
        System.out.println(l1);

        LinkedListNode<Integer> l2 = new LinkedListNode<Integer>(2);
        l2 = l2.addFirst(9);
        l2 = l2.addFirst(5);
        System.out.println(l2);

        l1 = addList(l1, l2);
        System.out.println(l1);

        l1 = l1.reverse();
        System.out.println(l1);

        l2 = l2.reverse();
        System.out.println(l2);

        l2 = l2.addFirst(9);
        System.out.println(l2);


        System.out.println("addList1");
        System.out.println(l1);
        System.out.println(l2);
        l2 = addList1(l1, l2);
        System.out.println(l2);


    }


//
//    public void reverse(){
//        Node<E> rev = null;
//        Node<E> n = null;
//
//        while(first != null) {
//            n = first;
//            first = first.next;
//            n.next = rev;
//            rev = n;
//        }
//        first = rev;
//
//    }

    public static void test(){
        testRemoveDuplicates();
        testKthToLast();
        testDeleteNode();
        testAddList();
    }


}
