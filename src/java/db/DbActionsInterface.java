/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.HashMap;


interface DbActionsInterface
{
    public int create(HashMap values);
    public boolean update(HashMap values, HashMap where);
    public boolean delete(HashMap where);
    public boolean get(HashMap where, String orderBy, String limit);
}
