/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

public class StringHelper
{

    public static String cutLastChars(String string, Integer number)
    {
        if (string != null)
        {
            return string.substring(0, (string.length() - number));
        }
        else
        {
            throw new RuntimeException("Illegal argument.");
        }
    }
}
