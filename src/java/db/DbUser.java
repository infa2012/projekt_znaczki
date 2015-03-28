/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import helpers.DbHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class DbUser implements DbActionsInterface
{

    private final String tableName = "user";
    private final String[] tableFields = {"id", "name", "email"};
    private final Connection connectionHandler = DbConnection.getInstance().getConnectionHandler();
    private final DbHelper dbHelper = new DbHelper(tableName, tableFields, connectionHandler);

    @Override
    public int create(HashMap values)
    {
        String query = dbHelper.getInsertSql(values);
        int idOfInsertedRow = dbHelper.executeInsert(query, values);
        if (idOfInsertedRow == 0)
        {
            try
            {
                throw new SQLException("Insert of this sql didn't affect. " + query);
            }
            catch (SQLException ex)
            {
                System.out.println("SQL error: " + ex.getMessage());
            }
        }

        return idOfInsertedRow;
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
        return dbHelper.executeSelect(query);
    }
}
