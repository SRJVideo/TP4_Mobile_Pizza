package com.example.utiletp4.modele;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "T_commande")
public class Commande {
    @DatabaseField(columnName = "idCommande", generatedId = true)
    private int idCommande;
    @DatabaseField(columnName = "numeroCommande", canBeNull = false)
    private String numeroCommande;
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idUser", foreignAutoCreate = true )
    private User user;
    @DatabaseField(columnName = "montantPaye", canBeNull = false)
    private String montantPaye;
    @DatabaseField
    private Date when;

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(String numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(String montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public Commande(String numeroCommande, User user, String montantPaye, Date when) {
        this.numeroCommande = numeroCommande;
        this.user = user;
        this.montantPaye = montantPaye;
        this.when = when;
    }

    public Commande() {
    }
}
