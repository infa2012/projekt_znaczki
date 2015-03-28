/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import helpers.DbHelper;
import java.sql.Connection;
import java.util.HashMap;

public class DbUser implements DbActionsInterface
{

    private final String tableName = "user";
    private final Connection connectionHandler = DbConnection.getInstance().getConnectionHandler();
    private final DbHelper dbHelper = new DbHelper();

    @Override
    public int create(HashMap values)
    {
        String query = dbHelper.getInsertSql(tableName, values);
        int idOfInsertedRow = dbHelper.executeInsert(query, connectionHandler, values);
        if (idOfInsertedRow == 0)
        {
            throw new RuntimeException("Insert of this sql didn't affect. " + query);
        }

        return idOfInsertedRow;
    }

    @Override
    public boolean update(HashMap values, HashMap where)
    {
        String query = dbHelper.getUpdateSql(tableName, values, where);
        return dbHelper.executeUpdate(query, connectionHandler, values);
    }

    @Override
    public boolean delete(HashMap where)
    {
        String query = dbHelper.getDeleteSql(tableName, where);
        return dbHelper.executeDelete(query, connectionHandler);
    }

    @Override
    public boolean get(HashMap where, String orderBy, String limit)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
