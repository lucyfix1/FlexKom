/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Dariusz
 */
public class Uprawnienia {
    private int uprawnienieID;
    private String nazwa;

    public void setUprawnienieID(int uprawnienieID) {
        this.uprawnienieID = uprawnienieID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getUprawnienieID() {
        return uprawnienieID;
    }

    public String getNazwa() {
        return nazwa;
    }
}
