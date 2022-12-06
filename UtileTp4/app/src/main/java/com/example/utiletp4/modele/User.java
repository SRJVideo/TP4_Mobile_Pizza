package com.example.utiletp4.modele;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a user
 */
@DatabaseTable(tableName = "T_Users")
public class User {
    public String messageError = "";

    @DatabaseField(columnName = "idUser", generatedId = true)
    private int idUser;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "email", canBeNull = false, unique = true)
    private String email;

    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;

    @DatabaseField(columnName = "address", canBeNull = false)
    private String address;

    @DatabaseField(columnName = "phone", canBeNull = false)
    private String phone;

    @DatabaseField(columnName = "points", canBeNull = false)
    private int points;

    /**
     * Constructor sans parameter
     */
    public User() {
    }

    /**
     * Constructor
     * @param name name
     * @param email email
     * @param password password
     * @param address address
     * @param phone phone
     * @param points points
     */

    public User(String name, String email, String password, String address, String phone, int points) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.points = points;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points < 0)
        {
            this.points = 0;
        }
        else
        {
            this.points = points;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", points=" + points +
                '}';
    }

    //Points (chaque 10 $ de la commande vaut 100 points (équivalent 75 cents))
    public void setAndCalculatePoint(float total) {
        int t = Math.round(total);
        int point = (int) ((t/10)*100);
        this.setPoints(point + getPoints());
    }
}
