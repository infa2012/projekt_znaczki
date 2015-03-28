/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

public class DbHelper
{

    private final String tableName;
    private final String[] tableFields;
    private final Connection connectionHandler;

    public DbHelper(String tableName, String[] tableFields, Connection connectionHandler)
    {
        this.tableName = tableName;
        this.tableFields = tableFields;
        this.connectionHandler = connectionHandler;
    }

    /**
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return String sql
     */
    public String getInsertSql(HashMap values)
    {
        String sql = "INSERT INTO " + tableName + " (";
        for (Object key : values.keySet())
        {
            sql += key + ",";
        }
        sql = StringHelper.cutLastChars(sql, 1);
        sql += ") VALUES(";

        for (int i = 0; i < values.size(); i++)
        {
            sql += "?,";
        }
        sql = StringHelper.cutLastChars(sql, 1);
        sql += ")";

        return sql;
    }

    /**
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return String sql
     */
    public String getUpdateSql(HashMap values, HashMap where)
    {
        String sql = "UPDATE " + tableName + " SET ";
        for (Object key : values.keySet())
        {
            sql += key + " = ?,";
        }
        sql = StringHelper.cutLastChars(sql, 1);
        sql += this.prepareWherePartSql(where);

        return sql;
    }

    /**
     * @param where - klucze muszą mieć nazwy pól danej tablicy
     * @return String sql
     */
    public String getDeleteSql(HashMap where)
    {
        String sql = "DELETE FROM " + tableName;
        sql += this.prepareWherePartSql(where);

        return sql;
    }

    public String getSelectSql(HashMap where)
    {
        String sql = "SELECT * FROM " + tableName;
        sql += this.prepareWherePartSql(where);

        return sql;
    }

    /**
     * Generuje część sql'a związanego z warunkami WHERE
     *
     * @param where - kluczem jest nazwa pola w bazie danych, a z wartością
     * wiadomo.
     * @return
     */
    private String prepareWherePartSql(HashMap where)
    {
        String sql = " WHERE ";
        for (Object key : where.keySet())
        {
            sql += key + " = '" + where.get(key).toString() + "',";
        }
        return StringHelper.cutLastChars(sql, 1);
    }

    /**
     *
     * @param query
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return
     */
    public int executeInsert(String query, HashMap values)
    {
        int idOfInsertedRow = 0;
        try
        {
            PreparedStatement statement = null;
            statement = connectionHandler.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Object[] arrayWithValues = values.values().toArray();
            for (int i = 0; i < values.size(); i++)
            {
                statement.setString(i + 1, arrayWithValues[i].toString());
            }
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
            {
                idOfInsertedRow = generatedKeys.getInt(1);
            }

        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }

        return idOfInsertedRow;
    }

    /**
     *
     * @param query
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return
     */
    public boolean executeUpdate(String query, HashMap values)
    {
        try
        {
            PreparedStatement statement = null;
            statement = connectionHandler.prepareStatement(query);
            Object[] arrayWithValues = values.values().toArray();
            for (int i = 0; i < values.size(); i++)
            {
                statement.setString(i + 1, arrayWithValues[i].toString());
            }
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }

        return true;
    }

    /**
     *
     * @param query
     * @return
     */
    public boolean executeDelete(String query)
    {
        try
        {
            PreparedStatement statement = null;
            statement = connectionHandler.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }

        return true;
    }

    /**
     *
     * @param query
     * @return
     */
    public HashMap executeSelectWithSingleRow(String query)
    {
        HashMap returnMap = new HashMap();
        try
        {
            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                for (int i = 0; i < tableFields.length; i++)
                {
                    returnMap.put(tableFields[i], rs.getString(tableFields[i]));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error " + e);
        }

        return returnMap;
    }

    /**
     *
     * @param query
     * @return
     */
    public LinkedList<HashMap> executeSelectWithMultipleRows(String query)
    {
        LinkedList list = new LinkedList();
        HashMap map = new HashMap();
        try
        {
            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                for (int i = 0; i < tableFields.length; i++)
                {
                    map.put(tableFields[i], rs.getString(tableFields[i]));
                }
                list.add(map);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error " + e);
        }

        return list;
    }

}
