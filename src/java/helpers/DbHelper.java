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

public class DbHelper
{

    /**
     * @param tableName
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return String sql
     */
    public String getInsertSql(String tableName, HashMap values)
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
     * @param tableName
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return String sql
     */
    public String getUpdateSql(String tableName, HashMap values, HashMap where)
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
     * @param tableName
     * @param where - klucze muszą mieć nazwy pól danej tablicy
     * @return String sql
     */
    public String getDeleteSql(String tableName, HashMap where)
    {
        String sql = "DELETE FROM " + tableName;
        sql += this.prepareWherePartSql(where);

        return sql;
    }

    public String getSelectSql(String tableName, HashMap where)
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
     * @param connectionHandler
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return
     */
    public int executeInsert(String query, Connection connectionHandler, HashMap values)
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
     * @param connectionHandler
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return
     */
    public boolean executeUpdate(String query, Connection connectionHandler, HashMap values)
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
     * @param connectionHandler
     * @return
     */
    public boolean executeDelete(String query, Connection connectionHandler)
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
     * @param connectionHandler
     * @return
     */
    public HashMap executeSelect(String query, Connection connectionHandler, String[] tableFields)
    {
        HashMap returnList = new HashMap();
        try
        {

            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                for(int i = 0; i < tableFields.length; i++)
                {
                    returnList.put(tableFields[i], rs.getString(tableFields[i]));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }
        
        return returnList;
    }

}
