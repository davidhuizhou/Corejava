package com.dzhou.corejava.crackingthecode;


import com.dzhou.util.Stack;

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


    public static LinkedListNode<Integer> findFirstNode(LinkedListNode<Integer> list){
        if(list == null)
            return null;

        LinkedListNode<Integer> slow =list;
        LinkedListNode<Integer> fast = list;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                break;
        }
        if(fast == null || slow == null)
            return null;

        slow = list;

        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return fast;


    }

    public static boolean isPalindrome(LinkedListNode<Integer> list){
        LinkedListNode<Integer> slow = list;
        LinkedListNode<Integer> fast = list;
        Stack<Integer> stack = new Stack();

        while(fast != null && fast.next != null){
            stack.push(slow.e);
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null)
            slow = slow.next;

        while(slow != null){
            if(!stack.pop().equals(slow.e))
                return false;
            slow = slow.next;
        }
        return true;
    }

    private static Result isPalindrome(LinkedListNode<Integer> list, int length){
        if(list == null || length == 0)
            return new Result(true, null);

        if(length == 1)
            return new Result(true, list.next);

        if(length == 2)
            return new Result(list.e.equals(list.next.e), list.next.next);

        Result res = isPalindrome(list.next, length - 2);

        if(res.result == false || res.node == null)
            return res;

        else{
            return new Result(list.e.equals(res.node.e), res.node.next);
        }

    }

    public static boolean isPalindromeRes(LinkedListNode<Integer> list){
        return isPalindrome(list, list.length()).result;
    }

    private static class Result{
        boolean result;
        LinkedListNode<Integer> node;

        public Result(boolean b, LinkedListNode<Integer> n){
            this.result = b;
            this.node = n;
        }
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

    public static void testIsPalindrome(){
        System.out.println("testIsPalindrome");
        LinkedListNode<Integer> l1 = new LinkedListNode<Integer>(6);
        System.out.println(isPalindrome(l1));
        System.out.println(isPalindromeRes(l1));

        l1 = l1.addFirst(1);
        System.out.println(isPalindrome(l1));
        System.out.println(isPalindromeRes(l1));

        l1 = l1.addFirst(3);
        l1 = l1.addFirst(5);
        l1 = l1.addFirst(3);
        l1 = l1.addFirst(1);
        l1 = l1.addFirst(6);
        System.out.println(l1);
        System.out.println(isPalindrome(l1));
        System.out.println(isPalindromeRes(l1));

    }


    public static void test(){
        testRemoveDuplicates();
        testKthToLast();
        testDeleteNode();
        testAddList();
        testIsPalindrome();
    }


}
