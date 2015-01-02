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
public class ProduktOddzialy {
    private String adres;
    private String miasto;
    private int ilosc;

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getAdres() {
        return adres;
    }

    public String getMiasto() {
        return miasto;
    }

    public int getIlosc() {
        return ilosc;
    }
}
