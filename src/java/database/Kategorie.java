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
public class Kategorie {
    private int kategoriaID;
    private String nazwa;

    public void setKategoriaID(int kategoriaID) {
        this.kategoriaID = kategoriaID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getKategoriaID() {
        return kategoriaID;
    }

    public String getNazwa() {
        return nazwa;
    }
    
}
