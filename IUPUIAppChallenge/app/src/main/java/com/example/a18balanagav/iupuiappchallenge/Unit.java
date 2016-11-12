package com.example.a18balanagav.iupuiappchallenge;

/**
 * Created by 18balanagav on 11/11/2016.
 */

public class Unit {

    private int row;
    private int col;
    private boolean state;

    public Unit() {

    }

    public Unit(int r, int c) {
        row = r;
        col = c;
    }

    public Unit(int r, int c, boolean s) {
        row = r;
        col = c;
        state = s;
    }

    //=========ACCESSORS AND MODIFIERS=======================================
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public void setState(boolean s) {
        state = s;
    }

    public boolean getState() {
        return state;
    }
}
