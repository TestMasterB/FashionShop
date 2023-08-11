package com.example.shoptrue.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*@Entity - Jedes Objekt repräsentiert eine Zeile in der Tabelle */
/*@ColumnInfo - Attribute, die in der Spalte gespeichert werden */
/*@PrimaryKey - jedem Benutzer wird eine individuelle "id" gesetzt, dient der Eindeutigkeit */

// Pre - Defined
@Entity
public class LoginTable implements Parcelable {
    // Id wird Auto - Generated bei dem System
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    // id -Attribute
    public Integer id;

    @ColumnInfo(name ="username")
    // username - Attribute
    public String username;

    @ColumnInfo(name ="password")
    //password -Attribute
    public String password;

    // Alle Funktionalitäten, die mit PARCEL verbunden sind, sind Pre Defined!
    // protected LoginTable(Parcel in) - spezieller Konstruktor, um die Instanz der LoginTable aus einem Parcel Bundle wiederherzustellen
    // Parcels werden verwendet um einen Datenpaket zwischen verschiedenen Komponenten zu übergeben (Bsp: Aktivität und Fragments)
    // Parcel dient also dazu Datenpakete zu versenden, damit es funktioniert muss es vorher serialisieren & deserialisieren

    // id = null - bedeutet, ob die id nicht vorhanden ist
    // 0 = numerische Zahl/ Null = Attribut keinen Wert zuzuordnen
    // if (in.readByte() == 0) - überprüft, ob id - Wert = Null ist, also nicht im Parcel gespeichert wurde
    // In diesem Fall wird die id in der LoginTable - Instanz auf Null gesetzt und gespeichert
    // Else: id = in.readInt(); - Andernfalls ist die id vorhanden und in LoginTable gespeichert
    // Password & username - änhlich, wie id
    // Dieser Abschnitt stellt sicher, dass nur eine id, password und username vorhanden ist

    // Pre Defined
    protected LoginTable(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }

        if (in.readByte() == 0){
            username = null;
        } else {
            username = in.readString();
        }

        if (in.readByte() == 0) {
            password = null;
        } else {
            password = in.readString();
        }
    }

    // Abschnitt: Serialisierung = Schreiben
    // In diesem Abschnitt werden die Daten in ein Parcel der Instanz LoginTable zu schreiben
    // Parcel dest - ist das Parcel Objekt in welches die Daten eingeschrieben werden
    // int flags - Parameter der meist im Default ist und für Komprressionen und Verschlüsselungen dient
    // if (id == null) - wenn id = null, dann soll der id der Wert 0 zugeordnet werden
    // Wenn NICHT Null, dann wird der id der Wert 1 zugeordnet und der tatsächliche Wert in das Parcel geschrieben

    // Pre -Defined (est.writeString(username); - est.writeString( password);

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(username);
        dest.writeString( password);
    }

    // describeContents - ist ein Teil des Parcel Interfaces beschreibt, ob es spezielle Objekte gibt
    // Wert 0 sagt nein

    // Pre - Defined
    @Override
    public int describeContents() {
        return 0;
    }

    // Abschnitt: Deserialisierung = LESEN
    // Creator Objekt - statitisches Feld der Parcel Interface
    // dient dazu aus einem Parcel zu lesen, was zuvor geschrieben wurde

    // LoginTable createFromParcel(Parcel in) - erstellt ein neues Objekt, dass zuvor aus dem Pacel serialisiert (geschrieben) wurde
    // Parcel in - ist das Objekt aus dem die Daten gelesen werden um die LoginTable - Objekt zu erstellen
    // newArray (int size) - erstellt ein neues leeres Array von LoginTable, damit die Anzahl der deserialisierten Objekte aufgenommen werden können


// Pre  -Defined
    public static final Creator<LoginTable> CREATOR = new Creator<LoginTable>() {
        @Override
        public LoginTable createFromParcel(Parcel in) {
            return new LoginTable(in);
        }

        @Override
        public LoginTable[] newArray(int size) {
            return new LoginTable[size];
        }
    };

    // Wird für neue User benuttzt
   // Getter & Setter - Methoden
   // Getter - fragt den aktuellen Wert
    // Setter - Wert der id wird auf ein neuen Wert geändert, damit Attribute aktualisiert werden

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Konstruktor Login
    // wenn immer der User sich einloggen möchte, dann speichert er die Variablen
    //LoginTable hat 2 Paramter - dort werden die vorhandenen Variablen den Paramter übergeben
    public LoginTable(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
