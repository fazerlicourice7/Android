package com.example.a18balanagav.equationbalancer;

/**
 * Created by 18balanagav on 9/27/2016.
 */

public class Element {

    private String name;
    private String compound;
    private int coefficient;
    private int subscript;
    private int num;

    public Element(String n, String comp, int coeff, int sub, int nu) {
        name = n;
        compound = comp;
        coefficient = coeff;
        subscript = sub;
        num = nu;
    }

    public Element(String n, String comp, int nu) {
        name = n;
        compound = comp;
        num = nu;
    }

    //=========ACCESSORS AND MODIFIERS==================
    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public String getCompound(){
        return compound;
    }

    public void setCompound(String c){
        compound = c;
    }

    /* DON'T USE THESE TWO FUNCTIONS: USE COEFFICIENT AND SUBSCRIPT INSTEAD
    public int getNum(){
        return num;
    }

    public void setNum(int n){
        num = n;
    } */

    public int getCoefficient(){
        return coefficient;
    }

    public void setCoefficient(int c){
        coefficient = c;
        num = coefficient * subscript;
    }

    public void setSubscript(int s){
        subscript = s;
        num = coefficient * subscript;
    }

    public int getSubscript(){
        return subscript;
    }





    //====== update num = subscript * coefficient; at the end of each method======================
}
