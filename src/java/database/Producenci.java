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
public class Producenci {
    private int producentID;
    private String nazwa;

    public void setProducentID(int producentID) {
        this.producentID = producentID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getProducentID() {
        return producentID;
    }

    public String getNazwa() {
        return nazwa;
    }
}
