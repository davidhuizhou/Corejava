package com.dzhou.corejava.dsaj.ch2;

/**
 * Created by davidzhou on 6/19/14.
 */
public class MainUnsortedOptimizedArray {

    public static void main(String[] args) {
        UnsortedOptimizedArray boston = new UnsortedOptimizedArray();
        Listing temp;
        // Test of the constructor
        Listing bill = new Listing("Bill", "First Avenue", "345 7474");
        Listing mary = new Listing("Mary", "Second Avenue", "123 4567");
        Listing tom = new Listing("Tom", "Third Avenue", "999 9999");

        // Test of the insert and fetch methods. Outputs true, true, null, Bill and Mary
        System.out.println("*** Test of the insert and fetch methods ***");
        System.out.println(boston.insert(bill));
        System.out.println(boston.insert(mary));
        System.out.println(boston.fetch("Tom"));
        temp = boston.fetch("Bill");
        System.out.println(temp.toString());
        temp = boston.fetch("Mary");
        System.out.println(temp.toString());

        // Test of encapsulation. Output Maryís new listing, then the encapsulated listing
        System.out.println("*** Test of the data encapsulation ***");
        mary.setAddress("Ninth Avenue");  // will not change the data structure listing
        System.out.println(mary.toString());
        temp = boston.fetch("Mary");
        System.out.println(temp.toString());

        // Test of the Delete method. Next three outputs should be false, true and null
        System.out.println("*** Test of the delete method ***");
        System.out.println(boston.delete("Tom"));
        System.out.println(boston.delete("Bill"));
        System.out.println(boston.fetch("Bill"));

        // Test of the Update method. Output should be false, true, null, Tom
        System.out.println("\n*** Test of the update method ***");
        System.out.println(boston.update("tom", mary));
        boston.insert(bill);
        System.out.println(boston.update("Bill", tom));
        System.out.println(boston.fetch("Bill"));
        temp = boston.fetch("Tom");
        System.out.println(temp.toString());
        System.exit(0);
    }
}
