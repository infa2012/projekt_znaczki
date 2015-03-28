/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.HashMap;
import java.util.LinkedList;


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
     * @param where
     * @return 
     */
    public LinkedList<HashMap> getAll(HashMap where);
}
