/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import db.DbConnection;
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
        sql += where != null ? this.prepareWherePartSql(where) : "";

        return sql;
    }
    
    public String getCustomSelectSql(HashMap<String,String> cols, String whereSQL)
    {
        return this.prepareSelectPart(cols) + " " + whereSQL;
    }
    
    private String prepareSelectPart(HashMap<String,String> what)
    {
        String sql = "SELECT ";
        int i = what.size();
        
        for (String key : what.keySet()){
            String value;
            value = what.get(key);
            if ( --i == 0 )
            {
                if (!value.equals(""))
                    sql += value + " AS " + key;
                else
                    sql += key;
            }
            else
            {
                if (!value.equals(""))
                    sql += value + " AS " + key + ", ";
                else
                    sql += key + ", ";
            }
        }
        
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
            sql += key + " = '" + where.get(key).toString() + "' AND ";
        }
        return StringHelper.cutLastChars(sql, 5);
    }

    /**
     *
     * @param query
     * @param values - klucze muszą mieć nazwy pól danej tablicy
     * @return Zwraca ID stworzenego wpisu
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
            ExceptionHelper.displaySqlExpcetionError(e, query);
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
            ExceptionHelper.displaySqlExpcetionError(e, query);
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
            ExceptionHelper.displaySqlExpcetionError(e, query);
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
            ExceptionHelper.displaySqlExpcetionError(e, query);
        }

        return returnMap;
    }

    public HashMap executeCustomSelectWSR(String[] cols, String query)
    {
        HashMap returnMap = new HashMap();
        
        try
        {
            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
                for (int i = 0; i < cols.length; i++)
                {
                    returnMap.put(cols[i], rs.getString(tableFields[i]));
                }
        }
        catch (SQLException e)
        {
            ExceptionHelper.displaySqlExpcetionError(e, query);
        }

        return returnMap;
    }
    
    public HashMap executeCustomSelectWSR(HashMap<String,String> keys, String query)
    {
        query = getCustomSelectSql(keys, query);
        String[] cols = keys.keySet().toArray(new String[0]);
        return executeCustomSelectWSR(cols, query);
    }
    
    /**
     *
     * @param query
     * @return Lista z HashMapami w środku
     */
    public LinkedList<HashMap> executeSelectWithMultipleRows(String query)
    {
        LinkedList list = new LinkedList();

        try
        {
            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                HashMap map = new HashMap();
                for (int i = 0; i < tableFields.length; i++)
                {
                    map.put(tableFields[i], rs.getString(tableFields[i]));
                }
                list.add(map);
            }
        }
        catch (SQLException e)
        {
            ExceptionHelper.displaySqlExpcetionError(e, query);
        }

        return list;
    }

    public LinkedList<HashMap> executeCustomSelectWMR(String[] cols, String query)
    {
        LinkedList list = new LinkedList();
        
        try
        {
            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                HashMap map = new HashMap();
                for (int i = 0; i < cols.length; i++)
                {
                    map.put(cols[i], rs.getString(cols[i]));
                }
                list.add(map);
            }
        }
        catch (SQLException e)
        {
            ExceptionHelper.displaySqlExpcetionError(e, query);
        }

        return list;
    }
    
    public LinkedList<HashMap> executeCustomSelectWMR(HashMap<String,String> keys, String query)
    {
        query = getCustomSelectSql(keys, query);
        String[] cols = keys.keySet().toArray(new String[0]);
        return executeCustomSelectWMR(cols, query);
    }
    
    private LinkedList getTableColumns()
    {
        DbConnection dbConnection = DbConnection.getInstance();
        LinkedList columns = new LinkedList();

        String query = "SELECT `COLUMN_NAME` \n"
                + "FROM `INFORMATION_SCHEMA`.`COLUMNS` \n"
                + "WHERE `TABLE_SCHEMA`='" + dbConnection.getDbName() + "' \n"
                + "    AND `TABLE_NAME`='" + tableName + "'";

        try
        {
            Statement statement = connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                columns.add(rs.getString("COLUMN_NAME"));
            }
        }
        catch (SQLException e)
        {
            ExceptionHelper.displaySqlExpcetionError(e, query);
        }

        return columns;
    }

    /**
     * Sprawdza czy definicje pól zawarte w zmiennej 'tableFields' są takie same
     * jak nazwy kolumn w bazie danych
     *
     * @return
     */
    public boolean checkIfMappedTableFielsAreUpToDateWithDatabase()
    {
        LinkedList tableColumns = this.getTableColumns();
        // Najpierw jest sprawdzane czy lista i tablica posiadają tyle same elementów. Jeśli nie to oczywiste jest, że ich zawartość jest różna.
        if (tableColumns.size() != tableFields.length)
        {
            return false;
        }

        // Sprawdzamy czy zawartość listy i tabeli jest taka sama.
        for (String tableField : tableFields)
        {
            if (!tableColumns.contains(tableField))
            {
                return false;
            }
        }

        return true;
    }

}
