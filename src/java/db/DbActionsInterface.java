/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Podstawowe akcje, które powinny się znaleźć w każdej klasie reprezentującą daną tabelkę bazodanową
 * 
 * @author gohzno
 */
interface DbActionsInterface
{
    
    /**
     * @param values
     * @return 
     */
    public int create(HashMap values);
    
    /**
     * @param values
     * @param where
     * @return 
     */
    public boolean update(HashMap values, HashMap where);
    
    /**
     * @param where
     * @return 
     */
    public boolean delete(HashMap where);
    
    /**
     * @param where
     * @return 
     */
    public HashMap get(HashMap where);
    
    /**
     * Jeśli ma nie być warunki w tej metodzie, dajemy jako argument nulla'
     * 
     * @param where - HashMap lub null
     * @return 
     */
    public LinkedList<HashMap> getAll(HashMap where);
    
    public String getTableName();
}
