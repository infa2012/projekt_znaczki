/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import helpers.DbHelper;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;

public class DbUser implements DbActionsInterface
{

    private final String tableName = "user";
    private final String[] tableFields =
    {
        "id", "name", "email"
    };
    private final Connection connectionHandler = DbConnection.getInstance().getConnectionHandler();
    private final DbHelper dbHelper = new DbHelper(tableName, tableFields, connectionHandler);

    @Override
    public int create(HashMap values)
    {
        String query = dbHelper.getInsertSql(values);

        return dbHelper.executeInsert(query, values);
    }

    @Override
    public boolean update(HashMap values, HashMap where)
    {
        String query = dbHelper.getUpdateSql(values, where);

        return dbHelper.executeUpdate(query, values);
    }

    @Override
    public boolean delete(HashMap where)
    {
        String query = dbHelper.getDeleteSql(where);

        return dbHelper.executeDelete(query);
    }

    @Override
    public HashMap get(HashMap where)
    {
        String query = dbHelper.getSelectSql(where);
        query += " LIMIT 1";

        return dbHelper.executeSelectWithSingleRow(query);
    }

    @Override
    public LinkedList<HashMap> getAll(HashMap where)
    {
        String query = dbHelper.getSelectSql(where);
        
        return dbHelper.executeSelectWithMultipleRows(query);
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean checkIfEmailOccupied(String email)
    {
        HashMap where = new HashMap();
        where.put("email", email);
        HashMap result = this.get(where);

        return !result.isEmpty();
    }

    /**
     *
     * @param login
     * @return
     */
    public boolean checkIfLoginOccupied(String login)
    {
        HashMap where = new HashMap();
        where.put("login", login);
        HashMap result = this.get(where);

        return !result.isEmpty();
    }

    /**
     *
     * @param login
     * @param password
     * @return HashMap jeśli użytkownik istnieje, null jeśli nie.
     */
    public HashMap accountAuthetication(String login, String password)
    {
        HashMap where = new HashMap();
        where.put("login", login);
        where.put("password", password);
        HashMap result = this.get(where);

        return !result.isEmpty() ? result : null;
    }
}
