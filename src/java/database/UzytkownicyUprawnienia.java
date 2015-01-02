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
public class UzytkownicyUprawnienia {
    private int uzytkownikID;
    private int uprawnienieID;

    public void setUzytkownikID(int uzytkownikID) {
        this.uzytkownikID = uzytkownikID;
    }

    public void setUprawnienieID(int uprawnienieID) {
        this.uprawnienieID = uprawnienieID;
    }

    public int getUzytkownikID() {
        return uzytkownikID;
    }

    public int getUprawnienieID() {
        return uprawnienieID;
    }
}
