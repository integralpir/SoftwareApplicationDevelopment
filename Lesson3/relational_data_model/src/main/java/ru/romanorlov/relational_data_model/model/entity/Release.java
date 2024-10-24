package ru.romanorlov.relational_data_model.model.entity;

public class Release {
    private int id;
    private int amiibo_id;
    private String au;
    private String eu;
    private String jp;
    private String na;

    public Release(int id, int amiibo_id, String au, String eu, String jp, String na) {
        this.id = id;
        this.amiibo_id = amiibo_id;
        this.au = au;
        this.eu = eu;
        this.jp = jp;
        this.na = na;
    }

    public Release() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmiibo_id() {
        return amiibo_id;
    }

    public void setAmiibo_id(int amiibo_id) {
        this.amiibo_id = amiibo_id;
    }


    public String getAu() {
        return au;
    }

    public void setAu(String au) {
        this.au = au;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getNa() {
        return na;
    }

    public void setNa(String na) {
        this.na = na;
    }
}
