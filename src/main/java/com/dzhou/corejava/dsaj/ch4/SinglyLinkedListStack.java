package com.dzhou.corejava.dsaj.ch4;

/**
 * Created by huizhou on 6/30/14.
 */
public class SinglyLinkedListStack<T> {

    private SinglyLinkedList<T> list;

    public SinglyLinkedListStack() {
        list = new SinglyLinkedList();
    }

    public boolean push(T newNode) {
        return list.insert(newNode);

    }

    public T pop() {
        return list.pop();

    }

    public void showAll(){
        list.showAll();
    }

    public static void main(String[] args){
        SinglyLinkedListStack<PhoneListing> boston = new SinglyLinkedListStack();
        PhoneListing l1 = new PhoneListing("X", "1st Avenue", "123 4567" );
        PhoneListing l2 = new PhoneListing("X", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("X", "3rd Avenue", "333 3333");
        // three ìpushî operations
        boston.push(l1);
        boston.push(l2);
        boston.push(l3);
        boston.showAll();
        //Three ìpopî operations
        l3 = boston.pop();   // first ìpopî
        System.out.println(l3); // (Java automatically invokes the toString method)

        l3 = boston.pop();   // second ìpopî
        System.out.println(l3); // (Java automatically invokes the toString method)

        l3 = boston.pop();   // second ìpopî
        System.out.println(l3);

        boston.showAll();

        System.exit(0);
    }

}
