package com.example.inventorymanagement;

public class modelClassChemicalNames {
    String cFormula,cState,cPotentialHazard, cRefer;
    int cNetQuantity, cBottles, cMolWeight, cEqiWeight;

    float cPH, cMeltingPoint, cBoilingPoint;

    public modelClassChemicalNames(String cFormula, String cState, String cPotentialHazard, String cRefer, int cNetQuantity, int cBottles, int cMolWeight, int cEqiWeight, float cPH, float cMeltingPoint, float cBoilingPoint) {
        this.cFormula = cFormula;
        this.cState = cState;
        this.cPotentialHazard = cPotentialHazard;
        this.cRefer = cRefer;
        this.cNetQuantity = cNetQuantity;
        this.cBottles = cBottles;
        this.cMolWeight = cMolWeight;
        this.cEqiWeight = cEqiWeight;
        this.cPH = cPH;
        this.cMeltingPoint = cMeltingPoint;
        this.cBoilingPoint = cBoilingPoint;

    }

    public String getcFormula() {
        return cFormula;
    }

    public void setcFormula(String cFormula) {
        this.cFormula = cFormula;
    }

    public String getcState() {
        return cState;
    }

    public void setcState(String cState) {
        this.cState = cState;
    }

    public String getcPotentialHazard() {
        return cPotentialHazard;
    }

    public void setcPotentialHazard(String cPotentialHazard) {
        this.cPotentialHazard = cPotentialHazard;
    }

    public String getcRefer() {
        return cRefer;
    }

    public void setcRefer(String cRefer) {
        this.cRefer = cRefer;
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

    public int getcMolWeight() {
        return cMolWeight;
    }

    public void setcMolWeight(int cMolWeight) {
        this.cMolWeight = cMolWeight;
    }

    public int getcEqiWeight() {
        return cEqiWeight;
    }

    public void setcEqiWeight(int cEqiWeight) {
        this.cEqiWeight = cEqiWeight;
    }

    public float getcPH() {
        return cPH;
    }

    public void setcPH(float cPH) {
        this.cPH = cPH;
    }

    public float getcMeltingPoint() {
        return cMeltingPoint;
    }

    public void setcMeltingPoint(float cMeltingPoint) {
        this.cMeltingPoint = cMeltingPoint;
    }

    public float getcBoilingPoint() {
        return cBoilingPoint;
    }

    public void setcBoilingPoint(float cBoilingPoint) {
        this.cBoilingPoint = cBoilingPoint;
    }
}