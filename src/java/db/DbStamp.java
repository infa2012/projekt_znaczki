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
import java.util.List;

/**
 *
 * @author krzysztof
 */
public class DbStamp extends DbBase{

    public DbStamp(){
        this.tableName = "stamp";
        this.tableFields = new String[]{"id", "name", "added_on", "modified_on",
                            "print_year", "notes", "price", "user_id"};
        this.init();
    }
    
    
    public LinkedList<HashMap> getUserStamps(int userId)
    {
        String query = "SELECT id, name, print_year, notes, user_id, added_on, modified_on, price FROM stamp WHERE user_id = '" + userId + "'";

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
                stamp.put("price", rs.getString("price"));
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
    
    public int getUserStampNum(int userId)
    {
        String query = "SELECT COUNT(*) as num FROM stamp WHERE user_id=" + userId + ";";
        int num = -1;
        
        try{
            Statement statement = this.connectionHandler.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
                num = rs.getInt("num");
        }
        catch (SQLException e)
        {
            System.out.print("Error " + e);
        }
        
        return num;

    }
        
    public HashMap getAllAboutStamp(int stampId)
    {
        HashMap retval;
        
        HashMap<String,String> keys = new HashMap<>();
        keys.put("stamp_id", "s.id");
        keys.put("name", "name");
        keys.put("added_on", "added_on");
        keys.put("modified_on", "modified_on");
        keys.put("print_year", "print_year");
        keys.put("notes", "notes");
        keys.put("price", "price");
        keys.put("image_id", "id");
        
        String query = "stamp s LEFT JOIN image i ON s.id=i.stamp_id";
        
        retval = dbHelper.executeCustomSelectWSR(keys, query);
        //List kolekcje = new LinkedList<HashMap>();
        //retval.put("kolekcje", kolekcje);
        return retval;
    }
    
    public LinkedList<HashMap> getAllForSklep(double price)
    {
        String query = "SELECT id, name, print_year, notes, user_id, added_on, modified_on, price FROM stamp WHERE price > '" + price + "'";

        return dbHelper.executeSelectWithMultipleRows(query);
    }
    
}
