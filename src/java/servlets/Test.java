/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbUser;
import java.util.HashMap;

/**
 *
 * @author gohzno
 */
public class Test
{

    public static void main(String[] args)
    {
        DbUser dbUser = new DbUser();
        
        HashMap values = new HashMap();
        values.put("name", "John");
        values.put("email", "johny@o2.pl");
        HashMap where = new HashMap();
        where.put("id", 5);
        dbUser.update(values, where);
//        System.out.println(id);
        
    }
}
