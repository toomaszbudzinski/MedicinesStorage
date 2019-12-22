package sample;

public class Leki {


    private int id;
    private String name;
    private String ean1;
    private String ean2;
    private String ean3;
    private String ean4;
    private String ean5;


    public Leki(int id, String name, String ean1, String ean2, String ean3, String ean4, String ean5) {
        this.id = id;
        this.name = name;
        this.ean1 = ean1;
        this.ean2 = ean2;
        this.ean3 = ean3;
        this.ean4 = ean4;
        this.ean5 = ean5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan1() {
        return ean1;
    }

    public void setEan1(String ean1) {
        this.ean1 = ean1;
    }

    public String getEan2() {
        return ean2;
    }

    public void setEan2(String ean2) {
        this.ean2 = ean2;
    }

    public String getEan3() {
        return ean3;
    }

    public void setEan3(String ean3) {
        this.ean3 = ean3;
    }

    public String getEan4() {
        return ean4;
    }

    public void setEan4(String ean4) {
        this.ean4 = ean4;
    }

    public String getEan5() {
        return ean5;
    }

    public void setEan5(String ean5) {
        this.ean5 = ean5;
    }
}
