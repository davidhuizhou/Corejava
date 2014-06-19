package com.dzhou.corejava.dsaj.ch2;

/**
 * Created by davidzhou on 6/19/14.
 */
public class Listing {
    private String name;    //key field
    private String address;
    private String number;

    public Listing(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public Listing deepCopy() {
        Listing clone = new Listing(name, address, number);
        return clone;
    }

    public int compareTo(String targetKey){
        return name.compareTo(targetKey);
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
