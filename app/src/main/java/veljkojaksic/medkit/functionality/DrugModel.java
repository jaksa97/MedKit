package veljkojaksic.medkit.functionality;

import java.util.List;

public class DrugModel {

    private String name;
    private String manufacturer;
    private String type;
    private String dose;
    private String warning;
    private String usage;
    private String price;
    private List<String> sideEffects;
    private List<String> symptoms;

    public DrugModel() { }

    public DrugModel(String name, String manufacturer, String type, String dose, String warning, String usage, String price, List<String> sideEffects, List<String> symptoms) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.dose = dose;
        this.warning = warning;
        this.usage = usage;
        this.price = price;
        this.sideEffects = sideEffects;
        this.symptoms = symptoms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(List<String> sideEffects) {
        this.sideEffects = sideEffects;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
