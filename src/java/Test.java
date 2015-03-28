
import db.DbUser;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gohzno
 */
public class Test
{

    public static void main(String[] args)
    {
        DbUser dbUser = new DbUser();
        HashMap where = new HashMap();
        where.put("email", "test@o2.pl");

        LinkedList list = dbUser.getAll(where);
        System.out.println(list);
    }
}
