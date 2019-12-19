package hr.foi.air.core;

import java.sql.Timestamp;

public class Racun {
    private int id;
    private int brojRacuna;
    private float ukupno;
    private String qrKod;
    private boolean iskoristenQrKod;
    private Timestamp datum;
    private int popust;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public float getUkupno() {
        return ukupno;
    }

    public void setUkupno(float ukupno) {
        this.ukupno = ukupno;
    }

    public String getQrKod() {
        return qrKod;
    }

    public void setQrKod(String qrKod) {
        this.qrKod = qrKod;
    }

    public boolean isIskoristenQrKod() {
        return iskoristenQrKod;
    }

    public void setIskoristenQrKod(boolean iskoristenQrKod) {
        this.iskoristenQrKod = iskoristenQrKod;
    }

    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public Racun(int id, int brojRacuna, float ukupno, String qrKod, boolean iskoristenQrKod, Timestamp datum, int popust) {
        this.id = id;
        this.brojRacuna = brojRacuna;
        this.ukupno = ukupno;
        this.qrKod = qrKod;
        this.iskoristenQrKod = iskoristenQrKod;
        this.datum = datum;
        this.popust = popust;
    private String ID;
    private String BrojRacuna;
    private float Ukupno;
    private String QRkod;
    private String Datum;
    private int Popust;
    private int Korisnik_ID;
    private String PIN;
    private int RestoranID;
    private int Staus_narudzbeID;
    private boolean IskoristenKod;

    public String getID() {
        return ID;
    }

    public void setID(String ID) { this.ID = ID; }

    public int getKorisnik_ID() {
        return Korisnik_ID;
    }

    public void setKorisnik_ID(int Korisnik_ID) {
        this.Korisnik_ID = Korisnik_ID;
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

    public void setRestoranID(int RestoranID) {
        this.RestoranID = RestoranID;
    }

    public int getStaus_narudzbeID() {
        return Staus_narudzbeID;
    }

    public void setStaus_narudzbeID(int Staus_narudzbeID) { this.Staus_narudzbeID = Staus_narudzbeID; }

    public String getBrojRacuna() {
        return BrojRacuna;
    }

    public void setBrojRacuna(String BrojRacuna) {
        this.BrojRacuna = BrojRacuna;
    }

    public float getUkupno() {
        return Ukupno;
    }

    public void setUkupno(float Ukupno) {
        this.Ukupno = Ukupno;
    }

    public String getQRkod() {
        return QRkod;
    }

    public void setQRkod(String QRkod) {
        this.QRkod = QRkod;
    }

    public boolean getIskoristenKod() {
        return IskoristenKod;
    }

    public void setIskoristenKod(boolean IskoristenKod) {
        this.IskoristenKod = IskoristenKod;
    }

    public String getDatum() {
        return Datum;
    }

    public void setDatum(String Datum) {
        this.Datum = Datum;
    }

    public int getPopust() {
        return Popust;
    }

    public void setPopust(int Popust) {
        this.Popust = Popust;
    }

    public Racun(String ID, String BrojRacuna, float Ukupno, String QRkod, String Datum, int Popust, int Korisnik_ID, String PIN, int RestoranID, int Staus_narudzbeID, boolean IskoristenKod) {
        this.ID = ID;
        this.BrojRacuna = BrojRacuna;
        this.Ukupno = Ukupno;
        this.QRkod = QRkod;
        this.Datum = Datum;
        this.Popust = Popust;
        this.Korisnik_ID = Korisnik_ID;
        this.PIN = PIN;
        this.RestoranID = RestoranID;
        this.Staus_narudzbeID = Staus_narudzbeID;
        this.IskoristenKod = IskoristenKod;
    }

    public Racun(){

    }
}
