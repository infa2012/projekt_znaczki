/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Klasa helpera obsługująca kwestie dostępowe
 */
public class AccessHelper
{

    /**
     * Sprawdzamy czy użytkownik jest zalogowany jako użytkownik
     *
     * @param session
     * @return boolean
     */
    public static boolean checkIfLoggedAsUser(HttpSession session)
    {
        if (session.getAttribute("user_type_id") != null && Integer.parseInt(session.getAttribute("user_type_id").toString()) == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Sprawdzamy czy użytkownik jest zalogowany jako moderator
     *
     * @param session
     * @return boolean
     */
    public static boolean checkIfLoggedAsModerator(HttpSession session)
    {
        if (session.getAttribute("user_type_id") != null && Integer.parseInt(session.getAttribute("user_type_id").toString()) == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Sprawdzamy czy użytkownik jest zalogowany jako admin
     *
     * @param session
     * @return boolean
     */
    public static boolean checkIfLoggedAsAdmin(HttpSession session)
    {
        if (session.getAttribute("user_type_id") != null && Integer.parseInt(session.getAttribute("user_type_id").toString()) == 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Sprawdzamy czy użytkownik jest zalogowany jako ktokolwiek :D
     *
     * @param session
     * @return boolean
     */
    public static boolean checkIfLoggedAsSomeone(HttpSession session)
    {
        if (session.getAttribute("user_id") != null || "".equals(session.getAttribute("user_id")))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static boolean checkIfLoggedInAs(HttpSession session, String who)
    {
        if (session.getAttribute("user_id") != null && who.equals(session.getAttribute("user_id")))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkGETParamNumberNotNull(HttpServletRequest request, String param)
    {
        if (request.getParameter(param) == null || !request.getParameter(param).matches("^\\d+$"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static boolean checkGETParamNumberCanBeNull(HttpServletRequest request, String param)
    {
        if (request.getParameter(param) != null)
        {
            if(!request.getParameter(param).matches("^\\d+$"))
            {
                return false;
            }
            else
            {
                return true;
            }
            
        }
        else
        {
            return true;
        }
    }
}
