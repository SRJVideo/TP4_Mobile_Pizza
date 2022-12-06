package com.example.utiletp4.modele;

/**
 * Enum pour les messages des erreurs
 */
public enum Field {

    EMAIL("Addresse Courriel non valide", 0),
    NAME("Minimum de caractères recquis: ", 5),
    PASSWORD("Minimum de caractères recquis: ", 6);

    public final String validationMessage;
    public final int minLength;

    Field(String validationMessage, int minLength) {
        this.validationMessage = validationMessage + (minLength > 0 ? minLength : "");
        this.minLength = minLength;
    }
}
