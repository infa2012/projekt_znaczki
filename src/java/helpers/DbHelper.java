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

    private String prepareWherePartSql(HashMap where)
    {
        String sql = " WHERE ";
        for (Object key : where.keySet())
        {
            sql += key + " = '" + where.get(key).toString() + "',";
        }
        return StringHelper.cutLastChars(sql, 1);
    }

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

}
