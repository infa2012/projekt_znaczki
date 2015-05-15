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

/**
 *
 * @author krzysztof
 */
public class DbCollection implements DbActionsInterface{
    private final String tableName = "collection";
    private final String[] tableFields =
    {
        "id", "name", "for_sale", "user_id"
    };
    private final Connection connectionHandler = DbConnection.getInstance().getConnectionHandler();
    private final DbHelper dbHelper = new DbHelper(tableName, tableFields, connectionHandler);
    
    @Override
    public int create(HashMap values) {
        String query = dbHelper.getInsertSql(values);

        return dbHelper.executeInsert(query, values);    }

    @Override
    public boolean update(HashMap values, HashMap where) {
        String query = dbHelper.getUpdateSql(values, where);

        return dbHelper.executeUpdate(query, values);
    }

    @Override
    public boolean delete(HashMap where) {
        String query = dbHelper.getDeleteSql(where);

        return dbHelper.executeDelete(query);
    }

    @Override
    public HashMap get(HashMap where) {
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

    @Override
    public String getTableName()
    {
        return tableName;
    }

    @Override
    public boolean checkIfMappedTableFielsAreUpToDateWithDatabase()
    {
        return dbHelper.checkIfMappedTableFielsAreUpToDateWithDatabase();
    }
    
}
