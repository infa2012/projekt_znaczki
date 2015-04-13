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

public class DbMessage implements DbActionsInterface
{

    private final String tableName = "message";
    private final String[] tableFields =
    {
        "id", "content", "topic", "created_at", "sender", "recipient"
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

    public LinkedList<HashMap> getReceivedMessages(int userId)
    {
        String query = "SELECT m.id as message_id, m.topic, m.content, m.created_at, u.login, u.id as sender_id FROM message m, user u WHERE m.recipient = '" + userId + "' AND u.id = m.sender ORDER BY m.created_at DESC";

        LinkedList<HashMap> messagesList = new LinkedList<>();

        try
        {
            Statement statement = this.connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                HashMap message = new HashMap();
                message.put("login", rs.getString("login"));
                message.put("sender_id", rs.getString("sender_id"));
                message.put("id", rs.getString("message_id"));
                message.put("topic", rs.getString("topic"));
                message.put("content", rs.getString("content"));
                message.put("created_at", rs.getString("created_at"));
                messagesList.add(message);
            }

        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }

        return messagesList;
    }

    public LinkedList<HashMap> getSendedMessages(int userId)
    {
        String query = "SELECT m.id as message_id, m.topic, m.content, m.created_at, u.login, u.id as recipient_id FROM message m, user u WHERE m.sender = '" + userId + "' AND u.id = m.recipient ORDER BY m.created_at DESC";

        LinkedList<HashMap> messagesList = new LinkedList<>();

        try
        {
            Statement statement = this.connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                HashMap message = new HashMap();
                message.put("login", rs.getString("login"));
                message.put("recipient_id", rs.getString("recipient_id"));
                message.put("id", rs.getString("message_id"));
                message.put("topic", rs.getString("topic"));
                message.put("content", rs.getString("content"));
                message.put("created_at", rs.getString("created_at"));
                messagesList.add(message);
            }

        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }

        return messagesList;
    }

}
