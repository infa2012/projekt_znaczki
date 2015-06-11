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
public abstract class DbBase implements DbActionsInterface{
    
    protected Connection connectionHandler;
    protected String tableName;
    protected String[] tableFields;
    protected DbHelper dbHelper;

    protected void init() {
            this.connectionHandler = DbConnection.getInstance().getConnectionHandler();
            this.dbHelper = new DbHelper(this.tableName, this.tableFields, this.connectionHandler);
    }
    
    public LinkedList<HashMap> customGetAll(HashMap<String,String> keys, String query){
        query = dbHelper.getCustomSelectSql(keys, query);
        String[] cols = new String[keys.size()];
        int i = 0;
        for (String k : keys.keySet())
            cols[i++] = k;
        
        return dbHelper.executeCustomSelectWMR(cols, query);
    }
            
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
