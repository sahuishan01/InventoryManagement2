package com.example.inventorymanagement;

public class dataHolder {
    String  cFormula, cDescription;
    int cNetQuantity, cBottles;

    public dataHolder(String cFormula, String cDescription, int cNetQuantity, int cBottles) {

        this.cFormula = cFormula;
        this.cDescription = cDescription;
        this.cNetQuantity = cNetQuantity;
        this.cBottles = cBottles;
    }



    public String getcFormula() {
        return cFormula;
    }

    public void setcFormula(String cFormula) {
        this.cFormula = cFormula;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public int getcNetQuantity() {
        return cNetQuantity;
    }

    public void setcNetQuantity(int cNetQuantity) {
        this.cNetQuantity = cNetQuantity;
    }

    public int getcBottles() {
        return cBottles;
    }

    public void setcBottles(int cBottles) {
        this.cBottles = cBottles;
    }
}
