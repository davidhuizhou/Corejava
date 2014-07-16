package com.dzhou.corejava.crackingthecode;


/**
 * Created by davidzhou on 7/16/14.
 */
public class LinkedListNode <E> {
    E e;
    LinkedListNode<E> next;

    public LinkedListNode(E e){
        this.e = e;
        next = null;
    }

    public int length(){
        int length = 1;
        LinkedListNode<E> n = next;
        while(n != null){
            length++;
            n = n.next;
        }
        return length;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        LinkedListNode<E> n = this;
        while(n != null){
            sb.append(n.e).append(" ");
            n = n.next;
        }
        return sb.toString();
    }

    public LinkedListNode<E> addFirst(E e){
        LinkedListNode<E> n = new LinkedListNode<E>(e);
        n.next = this;
        return n;
    }

    public void addLast(E e){
        LinkedListNode<E> n = this;
        while(n.next != null)
            n = n.next;
        n.next = new LinkedListNode<E>(e);

    }

    public void removeDuplicates()
    {
        LinkedListNode<E> n = this;

        while(n != null){

            LinkedListNode<E> p = n;
            LinkedListNode<E> q = p.next;

            while(q != null){
                if(q.e.equals(n.e)){
                    p.next = q.next;
                    q = q.next;
                } else {
                    p = p.next;
                    q = q.next;
                }
            }
            n = n.next;
        }


    }

    public LinkedListNode<E> kthToLast(int k){
        if(k <= 0)
            return null;

        LinkedListNode<E> p1 = this;
        LinkedListNode<E> p2 = this;

        for(int i = 0; i < k - 1; i++){
            if(p2 == null)
                return null;
            p2 = p2.next;
        }

        if(p2 == null)
            return null;

        while(p2 != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;

    }



    public static void main(String[] args){
        LinkedListNode<Integer> l = new LinkedListNode<Integer>(1);
        System.out.println(l.length() + " - " + l);

        l = l.addFirst(2);
        System.out.println(l.length() + " - " + l);

        l = l.addFirst(3);
        System.out.println(l.length() + " - " + l);

        l.addLast(4);
        System.out.println(l.length() + " - " + l);

        l.addLast(5);
        System.out.println(l.length() + " - " + l);


        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        System.out.println(l.length() + " - " + l);

        l.removeDuplicates();
        System.out.println(l.length() + " - " + l);

    }
}
