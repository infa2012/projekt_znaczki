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
public class DbCollection extends DbBase{
    public DbCollection(){
        this.tableName = "collection";
        this.tableFields = new String[]{
            "id", "name", "user_id"
            };
        this.init();
    }
    
    public LinkedList<HashMap> allCollectionsStats(int userId)
    {
        HashMap<String,String> s = new HashMap<>();
        s.put("size", "COUNT(chs.stamp_id)");
        s.put("user_id", "c.user_id");
        s.put("name", "c.name");
        s.put("id","c.id");
        String q2 = "FROM collection c LEFT JOIN collection_has_stamp chs\n" +
                    "ON c.id=chs.collection_id\n" +
                    "WHERE c.user_id="+userId+"\n" +
                    "GROUP BY c.id;";
        
        return this.customGetAll(s, q2);
    }

    public LinkedList<HashMap> allCollectionsStats()
    {
        HashMap<String,String> s = new HashMap<>();
        s.put("size", "COUNT(chs.stamp_id)");
        s.put("user_id", "c.user_id");
        s.put("name", "c.name");
        s.put("id","c.id");
        String q2 = "FROM collection c LEFT JOIN collection_has_stamp chs\n" +
                    "ON c.id=chs.collection_id\n" +
                    "GROUP BY c.id;";
        
        return this.customGetAll(s, q2);
    }

}