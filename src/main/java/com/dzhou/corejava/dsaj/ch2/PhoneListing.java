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
    {
        return "(" + name + "," + address + "," + number + ")";
    }

    public KeyMode deepCopy(){
        PhoneListing clone = new PhoneListing(name, address, number);
        return clone;
    }

    public int compareTo(Object targetKey){
        if (targetKey instanceof PhoneListing) {
            return (name.compareTo(((PhoneListing) targetKey).name));
        } else {
            String tKey = (String) targetKey;
            return (name.compareTo(tKey));
        }
    }

    public boolean equals(Object o){
        if(o == null)
            return false;

        return compareTo(o) == 0;
    }

}
