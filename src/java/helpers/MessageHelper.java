/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author m.rudz
 */
public class MessageHelper
{
    public static String generateDangerMessage(String message)
    {
        return "<div class=\"alert alert-dismissable alert-danger\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>" + message + "</div>";    
    }
    
    public static String generateSuccessMessage(String message)
    {
        return "<div class=\"alert alert-dismissable alert-success\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>" + message + "</div>";
    }
}
