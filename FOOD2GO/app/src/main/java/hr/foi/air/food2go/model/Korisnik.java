package hr.foi.air.food2go.model;

public class Korisnik {

    private int id;
    private String ime;
    private String prezime;
    private String username;
    private String lozinka;
    private String oib;
    private String email;
    private String adresa;
    private String mobitel;
    private int brojPokusaja;
    private int statusKorisnika;
    private int aktivacijskiKod;
    private int brojBodova;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMobitel() {
        return mobitel;
    }

    public void setMobitel(String mobitel) {
        this.mobitel = mobitel;
    }

    public int getBrojPokusaja() {
        return brojPokusaja;
    }

    public void setBrojPokusaja(int brojPokusaja) {
        this.brojPokusaja = brojPokusaja;
    }

    public int getStatusKorisnika() {
        return statusKorisnika;
    }

    public void setStatusKorisnika(int statusKorisnika) {
        this.statusKorisnika = statusKorisnika;
    }

    public int getAktivacijskiKod() {
        return aktivacijskiKod;
    }

    public void setAktivacijskiKod(int aktivacijskiKod) {
        this.aktivacijskiKod = aktivacijskiKod;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Korisnik(int id, String ime, String prezime, String username, String lozinka, String oib, String email, String adresa, String mobitel, int brojPokusaja, int statusKorisnika, int aktivacijskiKod, int brojBodova) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.lozinka = lozinka;
        this.oib = oib;
        this.email = email;
        this.adresa = adresa;
        this.mobitel = mobitel;
        this.brojPokusaja = brojPokusaja;
        this.statusKorisnika = statusKorisnika;
        this.aktivacijskiKod = aktivacijskiKod;
        this.brojBodova = brojBodova;
    }
}
