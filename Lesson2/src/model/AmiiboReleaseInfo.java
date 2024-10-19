package model;

public class AmiiboReleaseInfo {
    private String au;
    private String eu;
    private String jp;
    private String na;

    public AmiiboReleaseInfo(String au, String eu, String jp, String na) {
        this.au = au;
        this.eu = eu;
        this.jp = jp;
        this.na = na;
    }

    public AmiiboReleaseInfo() {
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
