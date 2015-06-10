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

}