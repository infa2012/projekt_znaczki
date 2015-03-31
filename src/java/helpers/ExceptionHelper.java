/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.SQLException;

/**
 *
 * @author gohzno
 */
public class ExceptionHelper
{

    public static void displaySqlExpcetionError(SQLException ex, String query)
    {
        System.out.println("\n\n" + ConsoleHelper.ANSI_RED + "Mysql ERROR:" + ConsoleHelper.ANSI_RESET);
        System.out.println(ConsoleHelper.ANSI_PURPLE + "Query: " + query + ConsoleHelper.ANSI_RESET);
        System.out.println(ConsoleHelper.ANSI_RED + "Error " + ex + ConsoleHelper.ANSI_RESET + "\n");
    }
}
