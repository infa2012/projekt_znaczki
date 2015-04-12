/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.HashMap;
import javax.servlet.http.HttpSession;

/**
 *
 * @author m.rudz
 */
public class SessionHelper
{

    public static void logInto(HttpSession session, HashMap user)
    {
        synchronized (session)
        {
            session.setAttribute("user_id", user.get("id"));
            session.setAttribute("login", user.get("login"));
            session.setAttribute("name", user.get("name"));
            session.setAttribute("surname", user.get("surname"));
            session.setAttribute("user_type_id", user.get("user_type_id"));
        }
    }
}
