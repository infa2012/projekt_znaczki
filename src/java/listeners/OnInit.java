/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import db.DbMessage;
import db.DbUser;
import helpers.ConsoleHelper;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Tutaj jest sprawdzane czy pola, które są zdefiniowane w klasach DbNazwaTabeli
 * w zmiennej 'tableFields', faktycznie pokrywają się z realnym stanem w bazie
 * danych.
 */
public class OnInit implements ServletContextListener
{

    public OnInit()
    {
// TODO Auto-generated constructor stub
    }

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        Object tableName = "";
        try
        {
            LinkedList<HashMap> resultsOfChecking = new LinkedList<>();

            HashMap dbUserMap = new HashMap();
            HashMap dbMessageMap = new HashMap();

            DbUser dbUser = new DbUser();
            dbUserMap.put("result", dbUser.checkIfMappedTableFielsAreUpToDateWithDatabase());
            dbUserMap.put("table_name", dbUser.getTableName());
            DbMessage dbMessage = new DbMessage();
            dbMessageMap.put("result", dbMessage.checkIfMappedTableFielsAreUpToDateWithDatabase());
            dbMessageMap.put("table_name", dbMessage.getTableName());

            resultsOfChecking.add(dbUserMap);
            resultsOfChecking.add(dbMessageMap);

            for (HashMap dbClass : resultsOfChecking)
            {
                if (dbClass.get("result") == (Object) false)
                {
                    tableName = dbClass.get("table_name");
                    throw new RuntimeException();
                }
            }
        }
        catch (RuntimeException ex)
        {
            System.out.println("\n\n");
            System.out.println(ConsoleHelper.ANSI_RED + "UWAGA !!!");
            System.out.println("Tabela '" + tableName + "' posiada inne pola niż te zdefiniowane w odpowiedniej klasie. Popraw to!" + ConsoleHelper.ANSI_RESET + "\n\n\n");
            throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
// TODO Auto-generated method stub
    }
}
