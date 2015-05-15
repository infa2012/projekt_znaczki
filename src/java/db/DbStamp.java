/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import helpers.DbHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author krzysztof
 */
public class DbStamp implements DbActionsInterface{

    private final String tableName = "stamp";
    private final String[] tableFields =
    {
        "id", "name", "added_on", "modified_on", "print_year", "notes", "user_id"
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
    
    public LinkedList<HashMap> getUserStamps(int userId)
    {
        String query = "SELECT id, name, print_year, notes, user_id, added_on, modified_on FROM stamp WHERE user_id = '" + userId + "'";

        LinkedList<HashMap> stampList = new LinkedList<>();

        try
        {
            Statement statement = this.connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                HashMap stamp = new HashMap();
                stamp.put("id", rs.getString("id"));
                stamp.put("name", rs.getString("name"));
                stamp.put("notes", rs.getString("notes"));
                stamp.put("print_year", rs.getString("print_year"));
                stamp.put("user_id", rs.getString("user_id"));
                stamp.put("added_on", rs.getString("added_on"));
                stamp.put("modified_on", rs.getString("modified_on"));
                stampList.add(stamp);
            }

        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }

        return stampList;
    }
}
