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
public class Klienci {
    private int klientID;
    private int uzytkownikID;
    private float stanKonta;
    private Date dataZalozenia;

    public void setKlientID(int klientID) {
        this.klientID = klientID;
    }

    public void setUzytkownikID(int uzytkownikID) {
        this.uzytkownikID = uzytkownikID;
    }

    public void setStanKonta(float stanKonta) {
        this.stanKonta = stanKonta;
    }

    public void setDataZalozenia(Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public int getKlientID() {
        return klientID;
    }

    public int getUzytkownikID() {
        return uzytkownikID;
    }

    public float getStanKonta() {
        return stanKonta;
    }

    public Date getDataZalozenia() {
        return dataZalozenia;
    }
    
}
