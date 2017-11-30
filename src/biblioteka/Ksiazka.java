package biblioteka;


import java.io.Serializable;

public class Ksiazka implements Serializable {
    public Ksiazka() {}
    private Long id;
    private String tytul;
    private String autor;
    private int rok;
    private int liczbastron;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getLiczbastron() {
        return liczbastron;
    }

    public void setLiczbastron(int liczbastron) {
        this.liczbastron = liczbastron;
    }
}