package com.example.utiletp4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.utiletp4.modele.Commande;
import com.example.utiletp4.modele.Pizza;
import com.example.utiletp4.modele.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

/**
 * Database actions and user and order operations
 */
public class DatabaseManager extends OrmLiteSqliteOpenHelper {

    // Info sur la BD
    private static final String DATABASE_NAME = "TP4.db";
    private static final int DATABASE_VERSION = 6;

    /**
     * Construtor
     * @param context context
     */
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * @param database database
     * @param connectionSource connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Pizza.class);
            TableUtils.createTable(connectionSource, Commande.class);

            Log.i("DATABASE", "onCreate invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't create Database", exception);
        }
    }

    /**
     * Called when the database needs to be upgraded.
     * @param database database
     * @param connectionSource connectionSource
     * @param oldVersion oldVersion
     * @param newVersion newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Pizza.class, true);
            TableUtils.dropTable(connectionSource, Commande.class, true);
            onCreate(database, connectionSource);
            Log.i("DATABASE", "onUpgrade invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't upgrade Database", exception);
        }
    }

    /**
     * Insert user in DB
     * @param user user
     */
    public void insertUser(User user) {
        try {
            Dao<User, Integer> dao = getDao(User.class);
            dao.create(user);
            Log.i("DATABASE", "insertUser invoked");
        } catch (Exception exception) {
            user.messageError = "Email d??j?? existant dans la base de donn??e";
            Log.e("DATABASE", "Can't insert user into Database");
        }
    }

    /**
     * Insert pizza in DB
     * @param pizza user
     */
    public void insertPizza(Pizza pizza) {
        try {
            Dao<Pizza, Integer> dao = getDao(Pizza.class);
            dao.create(pizza);
            Log.i("DATABASE", "insertPizza invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert pizza into Database");
        }
    }

    /**
     * Insert commande in DB
     * @param commande user
     */
    public void insertCommande(Commande commande) {
        try {
            Dao<Commande, Integer> dao = getDao(Commande.class);
            dao.create(commande);
            Log.i("DATABASE", "insertCommande invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't insert commande into Database");
        }
    }

    /**
     * Update user in DB
     * @param user user
     */
    public void updateUser(User user) {
        try {
            Dao<User, Integer> dao = getDao(User.class);
            dao.update(user);
            Log.i("DATABASE", "updatetUser invoked");
        } catch (Exception exception) {
            user.messageError = "Email d??j?? existant dans la base de donn??e";
            Log.e("DATABASE", "Can't update user into Database");
        }
    }

    /**
     * Update commande in DB
     * @param commande commande
     */
    public void updateCommande(Commande commande) {
        try {
            Dao<Commande, Integer> dao = getDao(Commande.class);
            dao.update(commande);
            Log.i("DATABASE", "update commande invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't update user into Database");
        }
    }





    /**
     * Return le User qui a ce login
     * @param email email
     * @return User
     */
    public User findUserByEmail(String email) {
        User u = null;
        try {
            Dao<User, Integer> dao = getDao(User.class);
            u =  dao.queryBuilder().where().eq("email", email).queryForFirst();

            Log.i("DATABASE", "findUser invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't find user into Database", exception);
        }
        return u;
    }

    /**
     * get tous les User de la table user
     * @return List<User>
     */
    public List<User> readUsers() {
        List<User> users = null;
        try {
            Dao<User, Integer> dao = getDao(User.class);
            users =  dao.queryForAll();
            Log.i("DATABASE", "readUsers invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't read user from Database", exception);
        }
        return users;
    }

    /**
     * get tous les commande de la table commande
     * @return List<User>
     */
    public List<Commande> readCommande() {
        List<Commande> commandes = null;
        try {
            Dao<Commande, Integer> dao = getDao(Commande.class);
            commandes =  dao.queryForAll();
            Log.i("DATABASE", "readCommande invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't read commande from Database", exception);
        }
        return commandes;
    }

    /**
     * get tous les pizzas de la table commande
     * @return List<User>
     */
    public List<Pizza> readPizza() {
        List<Pizza> pizzas = null;
        try {
            Dao<Pizza, Integer> dao = getDao(Pizza.class);
            pizzas =  dao.queryForAll();
            Log.i("DATABASE", "readCommande invoked");
        } catch (Exception exception) {
            Log.e("DATABASE", "Can't read pizza from Database", exception);
        }
        return pizzas;
    }

}
