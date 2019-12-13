package hr.foi.air.core;

import java.sql.Timestamp;

public class Racun {
    private int ID;
    private int BrojRacuna;
    private float Ukupno;
    private String QRkod;
    private Timestamp Datum;
    private int Popust;
    private int Korisnik_ID;
    private String PIN;
    private int RestoranID;
    private int Staus_narudzbeID;
    private boolean IskoristenKod;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getKorisnik_ID() {
        return Korisnik_ID;
    }

    public void setKorisnik_ID(int korisnik_ID) {
        Korisnik_ID = korisnik_ID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public int getRestoranID() {
        return RestoranID;
    }

    public void setRestoranID(int restoranID) {
        RestoranID = restoranID;
    }

    public int getStaus_narudzbeID() {
        return Staus_narudzbeID;
    }

    public void setStaus_narudzbeID(int staus_narudzbeID) {
        Staus_narudzbeID = staus_narudzbeID;
    }

    public int getBrojRacuna() {
        return BrojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.BrojRacuna = brojRacuna;
    }

    public float getUkupno() {
        return Ukupno;
    }

    public void setUkupno(float ukupno) {
        this.Ukupno = ukupno;
    }

    public String getQRkod() {
        return QRkod;
    }

    public void setQRkod(String QRkod) {
        this.QRkod = QRkod;
    }

    public boolean isIskoristenKod() {
        return IskoristenKod;
    }

    public void setIskoristenKod(boolean iskoristenKod) {
        this.IskoristenKod = iskoristenKod;
    }

    public Timestamp getDatum() {
        return Datum;
    }

    public void setDatum(Timestamp datum) {
        this.Datum = datum;
    }

    public int getPopust() {
        return Popust;
    }

    public void setPopust(int popust) {
        this.Popust = popust;
    }

    public Racun(int ID, int brojRacuna, float ukupno, String QRkod, Timestamp datum, int popust, int korisnik_ID, String PIN, int restoranID, int staus_narudzbeID, boolean iskoristenKod) {
        this.ID = ID;
        BrojRacuna = brojRacuna;
        Ukupno = ukupno;
        this.QRkod = QRkod;
        Datum = datum;
        Popust = popust;
        Korisnik_ID = korisnik_ID;
        this.PIN = PIN;
        RestoranID = restoranID;
        Staus_narudzbeID = staus_narudzbeID;
        IskoristenKod = iskoristenKod;
    }
}
