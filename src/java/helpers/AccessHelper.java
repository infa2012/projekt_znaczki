/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.servlet.http.HttpSession;

/**
 *  Klasa helpera obsługująca kwestie dostępowe
 */
public class AccessHelper
{

    /**
     * Sprawdzamy czy użytkownik jest zalogowany jako użytkownik
     * @param session
     * @return boolean
     */
    public static boolean checkIfLoggedAsUser(HttpSession session)
    {
        if (session.getAttribute("user_id") == null || session.getAttribute("user_id").equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Sprawdzamy czy użytkownik jest zalogowany jako admin
     * @param session
     * @return boolean
     */
    public static boolean checkIfLoggedAsAdmin(HttpSession session)
    {
        if (session.getAttribute("is_admin") != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
