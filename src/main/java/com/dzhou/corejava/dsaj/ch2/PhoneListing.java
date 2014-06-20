package com.dzhou.corejava.dsaj.ch2;

/**
 * Created by davidzhou on 6/20/14.
 */
public class PhoneListing implements KeyMode {
    private String name;
    private String address;
    private String number;

    public PhoneListing(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public String toString()
    {  return("Name is " + name +
            "\nAddress is " + address +
            "\nNumber is " + number + "\n");
    }

    public KeyMode deepCopy(){
        PhoneListing clone = new PhoneListing(name, address, number);
        return clone;
    }

    public int compareTo(Object targetKey){
        String tKey = (String) targetKey;
        return (name.compareTo(tKey));
    }
}
