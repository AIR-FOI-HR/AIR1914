package hr.foi.air.food2go.model;

public class StavkeRacuna {
    private int id;
    private int kolicina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public StavkeRacuna(int id, int kolicina) {
        this.id = id;
        this.kolicina = kolicina;
    }
}
