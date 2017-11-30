package biblioteka;

import java.io.Serializable;

public class Wydane implements Serializable {
    public Wydane() {}
    
    private Long id;
    private Long id_ksiazki;
    private Long id_osoby;
    private String wydano;
    private String zwrot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(Long id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public Long getId_osoby() {
        return id_osoby;
    }

    public void setId_osoby(Long id_osoby) {
        this.id_osoby = id_osoby;
    }

    public String getWydano() {
        return wydano;
    }

    public void setWydano(String wydano) {
        this.wydano = wydano;
    }

    public String getZwrot() {
        return zwrot;
    }

    public void setZwrot(String zwrot) {
        this.zwrot = zwrot;
    }


}