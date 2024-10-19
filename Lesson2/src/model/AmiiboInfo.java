package model;

public class AmiiboInfo {
    private String amiboSeries;
    private String character;
    private String gameSeries;
    private String head;
    private String image;
    private String name;
    private AmiiboReleaseInfo release;
    private String tail;
    private String type;

    public AmiiboInfo(String amiboSeries, String character, String gameSeries, String head, String image, String name, AmiiboReleaseInfo release, String tail, String type) {
        this.amiboSeries = amiboSeries;
        this.character = character;
        this.gameSeries = gameSeries;
        this.head = head;
        this.image = image;
        this.name = name;
        this.release = release;
        this.tail = tail;
        this.type = type;
    }

    public AmiiboInfo() {
    }

    public String getAmiboSeries() {
        return amiboSeries;
    }

    public void setAmiboSeries(String amiboSeries) {
        this.amiboSeries = amiboSeries;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getGameSeries() {
        return gameSeries;
    }

    public void setGameSeries(String gameSeries) {
        this.gameSeries = gameSeries;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AmiiboReleaseInfo getRelease() {
        return release;
    }

    public void setRelease(AmiiboReleaseInfo release) {
        this.release = release;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
