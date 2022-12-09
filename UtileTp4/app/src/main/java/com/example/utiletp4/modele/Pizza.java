package com.example.utiletp4.modele;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_pizza")
public class Pizza {

    @DatabaseField(columnName = "idPizza", generatedId = true)
    private int idPizza;
    @DatabaseField(columnName = "sortePizza", canBeNull = false)
    private String sortePizza;
    @DatabaseField(columnName = "type", canBeNull = false)
    private String type;
    @DatabaseField(columnName = "prix", canBeNull = false)
    private double prix;

    public String getSortePizza() {
        return sortePizza;
    }

    public void setSortePizza(String sortePizza) {
        this.sortePizza = sortePizza;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Pizza(String sortePizza, String type, double prix) {
        this.sortePizza = sortePizza;
        this.type = type;
        this.prix = prix;
    }

    public Pizza() {
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "sortePizza='" + sortePizza + '\'' +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                '}';
    }
}
