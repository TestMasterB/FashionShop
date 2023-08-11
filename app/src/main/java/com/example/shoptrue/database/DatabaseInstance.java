package com.example.shoptrue.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database - gibt an, dass es sich um eine Room Datenbank handelt
//enthält 2 Parameter - LoginTable (Klasse) und version
//version - notwendig für Updates und Wartung; ist standardmäßig gefordert, bei mehreren Versionen erhöht sich die Zahl

// Also noch kein DatanbaseInstance Objekt vorliegt
// Das ist wichtig,um zu überprüfen, dass auch nur eine Instanz vorliegt, wegen Speicherverschwendung etc..

// Threads - sind kleinste Ausführungseinheiten im Computer
// Das Ziel ist ja eine Singleton - Instanz zu erstellen
// Mehrere Threads könnten bei der Überprüfung feststellen, dass es die Singleton nicht gibt und mehrere Singletons erstellen


// databaseBuilder - erstellt Datenbank
// allowMainThreadQueries - erlaubt Abfragen
// .fallbackToDestructiveMigration() - löscht die Daten und erstellt eine neue Datenbank (wird nicht empfohlen)
// build - erstellt die DatabaseInstance und zurückgegeben um die Singleton zu speichern


@Database(entities = {LoginTable.class, CartTable.class}, version = 2 )

// Pre - Defined
public abstract class DatabaseInstance extends RoomDatabase {

    // public abstract DatabaseDao databaseDao - wird benutzt um DatabaseDao zurückzugeben
    // DatabaseDao ist eine Schnittsstelle, die die Datenbankoperationen definiert
    // databaseDao - Objekt* = ermöglicht den Zugang in die Database

    public abstract DatabaseDao databaseDao ();

    // public static DatabaseInstance instance - speichert Singleton - Instanz der DatabaseInstance Klasse
    // Singleton - Instanz - stellt sicher, dass während der Laufzeit nur EINE EINZIGE Verbindung zur Datenbank besteht
    public static DatabaseInstance instance;

    // public static DatabaseInstance getInstance(final Context context) - ruft Singleton - Instanz der DatabaseInstance - Klasse abzurufen
    // if (instance == null) - überprüft, ob die instance - Variable, die die Singleton - Instanz speichert noch nicht initialisiert wurde
    // Nimmt den context als parameter auf und stellt sich, dass es nur eine Instanz von DatabaseInstance gibt
    // getInstance - Methode, zur Synchronisierung, die sicherstellt, dass die DBVerindung nur einmal erstellt wird
    // Ansonsten bei mehrfacher DBVerbindung esteht die Gefahr der Speicherverschwendung & Dateninkonsistenzen, wenn mehrere Daten gleichzeitig verändert werden

    // Pre - Defined
   public static DatabaseInstance getInstance(final Context context) {

       // synchronized (LoginTable.class) - folgender Codeblock wird atomar erstellt
       // atomar - bedeutet: es kann von anderen Codeblöcken nicht unterbrochen werden
       // verhindert von gleichzeitigen von Threads

       // 2. if (instance == null) - erneute Überprüfung als Sicherheitsmaßnahme, ob es kein Threads bereits einen Singleton erstellt hat
       // Wenn instance = Null, soll Room instanziert werden

        if (instance == null) {
            synchronized (LoginTable.class){
                if (instance == null){
                 instance =   Room
                            // Bedingung und sonstiges bereits vorgegeben, muss aber aus Room kommen
                            .databaseBuilder (context.getApplicationContext(), DatabaseInstance.class, "EcoLoginData")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }
        }
        return instance;
    }
}
