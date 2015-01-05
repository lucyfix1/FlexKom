/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;

/**
 *
 * @author Dariusz
 */
public class Zamowienie {
    private int zamowienieID;
    private int produktID;
    private int klientOD;
    private int oddzialID;
    private String status;
    private String dataZlozeniaZamowienia;

    public void setZamowienieID(int zamowienieID) {
        this.zamowienieID = zamowienieID;
    }

    public void setProduktID(int produktID) {
        this.produktID = produktID;
    }

    public void setKlientOD(int klientOD) {
        this.klientOD = klientOD;
    }

    public void setOddzialID(int oddzialID) {
        this.oddzialID = oddzialID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataZlozeniaZamowienia(String dataZlozeniaZamowienia) {
        this.dataZlozeniaZamowienia = dataZlozeniaZamowienia;
    }

    public int getZamowienieID() {
        return zamowienieID;
    }

    public int getProduktID() {
        return produktID;
    }

    public int getKlientOD() {
        return klientOD;
    }

    public int getOddzialID() {
        return oddzialID;
    }

    public String getStatus() {
        return status;
    }

    public String getDataZlozeniaZamowienia() {
        return dataZlozeniaZamowienia;
    }
    
}
