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
public class Produkty {
    private int produktID;
    private int producentID;
    private int kategoriaID;
    private String nazwa;
    private float cena;

    public void setProduktID(int produktID) {
        this.produktID = produktID;
    }

    public void setProducentID(int producentID) {
        this.producentID = producentID;
    }

    public void setKategoriaID(int kategoriaID) {
        this.kategoriaID = kategoriaID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getProduktID() {
        return produktID;
    }

    public int getProducentID() {
        return producentID;
    }

    public int getKategoriaID() {
        return kategoriaID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public float getCena() {
        return cena;
    }
}
