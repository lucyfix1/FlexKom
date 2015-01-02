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
public class OddzialyOstatniaModyfikacja {
    private int oddzialID;
    private Date ostatniaModyfikacja;

    public void setOddzialID(int oddzialID) {
        this.oddzialID = oddzialID;
    }

    public void setOstatniaModyfikacja(Date ostatniaModyfikacja) {
        this.ostatniaModyfikacja = ostatniaModyfikacja;
    }

    public int getOddzialID() {
        return oddzialID;
    }

    public Date getOstatniaModyfikacja() {
        return ostatniaModyfikacja;
    }
}
