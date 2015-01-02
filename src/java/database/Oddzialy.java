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
public class Oddzialy {
    private int oddzialID;
    private String adres;
    private String kodPocztowy;
    private String miasto;
    private String email;
    private String nrTelefonu;

    public void setOddzialID(int oddzialID) {
        this.oddzialID = oddzialID;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public int getOddzialID() {
        return oddzialID;
    }

    public String getAdres() {
        return adres;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getEmail() {
        return email;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }
    
}
