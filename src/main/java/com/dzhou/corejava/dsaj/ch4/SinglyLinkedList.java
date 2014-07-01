package com.dzhou.corejava.dsaj.ch4;

/**
 * Created by huizhou on 6/30/14.
 */
public class SinglyLinkedList<T> {

    private Node h;
    public Iterator[] i;

    //http://code.stephenmorley.org/articles/java-generics-type-erasure/
    private Class<T> tClass;

    public SinglyLinkedList() {
        h = new Node();
        i = new SinglyLinkedList.Iterator [1];
        i[0] = new Iterator();
        h.l = null;
        h.next = null;

    }

    public SinglyLinkedList(int numberOfIterators){
        h = new Node();
        i = new SinglyLinkedList.Iterator [numberOfIterators];
        for(int count = 0; count < numberOfIterators; count++){
            i[count] = new Iterator();
        }
        h.l = null;
        h.next = null;
    }

    public boolean insert(T newNode){
        Node n = new Node();
        if(n == null)
            return false;
        else{
            KeyMode k = ((KeyMode)newNode).deepCopy();
            n.next = h.next;
            h.next = n;
            n.l = (T)k;
            return true;

        }
    }


    public T fetch(Object targetKey) {
        Node n = h.next;
        while (n != null && ((KeyMode) n.l).compareTo(targetKey) != 0) {
            n = n.next;

        }

        if (n != null) {
            KeyMode k = ((KeyMode) n.l).deepCopy();
            return (T) k;

        } else {
            return null;

        }

    }

    public boolean delete(Object targetKey) {
        Node p = h;
        Node n = h.next;

        while (n != null && ((KeyMode) n.l).compareTo(targetKey) != 0) {
            p = n;
            n = n.next;

        }

        if (n != null) {
            p.next = n.next;
            return true;
        } else {
            return false;
        }


    }

    public boolean update(Object targetKey, T newNode) {
        if (!delete(targetKey))
            return false;
        if (!insert(newNode))
            return false;

        return true;
    }

    public T pop() {
        Node<T> n = h.next;

        if (n == null)
            return null;

        else {
            h.next = n.next;
            return n.l;

        }


    }

    public void showAll(){

        Node n = h.next;
        while(n != null){
            System.out.println(n.l.toString());
            n = n.next;
        }

    }

    public com.dzhou.corejava.dsaj.ch4.Iterator<T> iterator (){
        return new com.dzhou.corejava.dsaj.ch4.Iterator<T>(h);
    }


//    public class Node {
//        private T l;
//        private Node next;
//
//        public Node() {
//
//        }
//    }

    public class Iterator {
        private Node ip;

        public Iterator() {
            ip = h;
        }

        public void reset() {
            ip = h;
        }

        public boolean hasNext() {
            if (ip.next != null)
                return true;
            else
                return false;
        }

        public T next() {
            if (hasNext()) {
                ip = ip.next;
                KeyMode k = (KeyMode) ip.l;
                return (T) k.deepCopy();


            } else {
                return null;
            }

        }

        public void set(T newNode) {
            KeyMode k = (KeyMode) newNode;
            T n = (T) k.deepCopy();
            ip.l = n;


        }

    }

    public static void main(String[] args){
        SinglyLinkedList<PhoneListing> boston = new SinglyLinkedList();
        PhoneListing l1 = new PhoneListing("X", "1st Avenue", "123 4567" );
        PhoneListing l2 = new PhoneListing("X", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("X", "3rd Avenue", "333 3333");
        // three ìpushî operations
        boston.insert(l1);
        boston.insert(l2);
        boston.insert(l3);
        //Three ìpopî operations
        l3 = boston.fetch("X");   // first ìpopî
        boston.delete("X");
        System.out.println(l3); // (Java automatically invokes the toString method)
        l3 = boston.fetch("X");   // second ìpopî
        boston.delete("X");
        System.out.println(l3); // (Java automatically invokes the toString method)
        l3 = boston.fetch("X");   // third ìpopî
        boston.delete("X");
        System.out.println(l3); //


        SinglyLinkedList<PhoneListing> chicago = new SinglyLinkedList(2);
        String number;
        PhoneListing c1 = new PhoneListing("Bill", "1st Avenue", "123 4567");
        PhoneListing c2 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing c3 = new PhoneListing("Mike", "3rd Avenue", "333 3333");
        chicago.insert(c1);  // test insert
        chicago.insert(c2);
        chicago.insert(c3);
        // output all the listings using the iterator, i

        while (chicago.i[1].hasNext()) {
            System.out.println(chicago.i[1].next()); // Java automatically invokes toString
        }
        // add an area code to all the listings using the iterator, i
        chicago.i[1].reset();
        while (chicago.i[1].hasNext()) {
            c1 = chicago.i[1].next();
            number = c1.getNumber();
            number = "631 " + number;
            c1.setNumber(number);
            chicago.i[1].set(c1);
        }
        // output the updated listings using the iterator, i
        chicago.i[1].reset();
        while (chicago.i[1].hasNext()) {
            System.out.println(chicago.i[1].next()); // Java automatically invokes toString
        }


        SinglyLinkedList<PhoneListing> newyork = new SinglyLinkedList<PhoneListing>();

        PhoneListing l11 = new PhoneListing("Bill", "1st Avenue", "123 4567" );
        PhoneListing l22 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing l33 = new PhoneListing("Mike", "3rd Avenue", "333 3333");

        boston.insert(l1);  // test insert
        boston.insert(l2);
        boston.insert(l3);
        com.dzhou.corejava.dsaj.ch4.Iterator i1,i2,i3;
        i1 = boston.iterator();
        i2 = boston.iterator();
        i3 = boston.iterator();
        // output all the listings using iterator 1
        while(i1.hasNext())
        {
            System.out.println(i1.next()); // Java automatically uses the toString method;
        }
        // add an area code to all the listings using iterator 2

        while(i2.hasNext())
        {
            l11 = (PhoneListing)i2.next();
            number = l1.getNumber();
            number = "631 " + number;
            l11.setNumber(number);
            i2.set(l11);
        }
        // output all the updated listings using iterator 3

        while(i3.hasNext())
        {  System.out.println(i3.next());
        }

        System.exit(0);

    }
}
