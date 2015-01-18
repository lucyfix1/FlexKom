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
public class Produkt {
    private String nazwaProducenta;
    private String nazwaProduktu;
    private float cena;

    public void setNazwaProducenta(String nazwaProducenta) {
        this.nazwaProducenta = nazwaProducenta;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getNazwaProducenta() {
        return nazwaProducenta;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public float getCena() {
        return cena;
    }
}
