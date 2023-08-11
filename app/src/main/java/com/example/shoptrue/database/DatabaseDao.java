package com.example.shoptrue.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

/*@Dao - bestimmt, dass es sich um einen Data Access Object handelt */


// interface  DatabaseDao - ist eine Schnittstelle zur Ansammlung von Methoden
// @Insert - Eingabefunkton
 // void Insert (LoginTable loginTable) - bedeutet, wenn ein User sich annmeldet, wird er in dem Objekt LoginTable gespeichert
// @Querry - Abfragefunktion
    // Wähle alle Attribute aus der LoginTable, wo der Username gleich ist
    // LoginTable getLoginDetails (String user) - erwartet den Usernamen als String und wird als Objekt der Klasse LoginTable übergeben
@Dao
public interface  DatabaseDao {

    @Insert
    public void Insert (LoginTable loginTable);

    @Query("SELECT * FROM LoginTable where username=:user")
   LoginTable getLoginDetails (String user);

    @Insert
    public void insertCartModel (CartTable model);

    @Query("SELECT * FROM CartTable")
    List<CartTable> getCardDetails ();
}