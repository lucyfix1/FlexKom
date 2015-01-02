/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Date;


/**
 *
 * @author Dariusz
 */
public class Pracownicy {
    private int pracownikID;
    private int uzytkownikID;
    private int oddzialID;
    private String stanowisko;
    private float pensja;
    private Date dataZatrudnienia;

    public void setPracownikID(int pracownikID) {
        this.pracownikID = pracownikID;
    }

    public void setUzytkownikID(int uzytkownikID) {
        this.uzytkownikID = uzytkownikID;
    }

    public void setOddzialID(int oddzialID) {
        this.oddzialID = oddzialID;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setPensja(float pensja) {
        this.pensja = pensja;
    }

    public void setDataZatrudnienia(Date dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public int getPracownikID() {
        return pracownikID;
    }

    public int getUzytkownikID() {
        return uzytkownikID;
    }

    public int getOddzialID() {
        return oddzialID;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public float getPensja() {
        return pensja;
    }

    public Date getDataZatrudnienia() {
        return dataZatrudnienia;
    }
}
