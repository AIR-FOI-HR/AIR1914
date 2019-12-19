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
    }
}