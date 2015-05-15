/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author krzysztof
 */
public class FormHelper {
    public static void setMapToAttributes(Map<String,Object> values, HttpServletRequest request){
        for (Map.Entry<String,Object> e : values.entrySet())
            request.setAttribute(e.getKey(), e.getValue());
    }
    
    public static void setMapToAttributesString(Map<String,Object> values, HttpServletRequest request){
        for (Map.Entry<String,Object> e : values.entrySet())
            request.setAttribute(e.getKey(), (String)e.getValue());
    }
    
    public static void mapToRequestAttribute(Map<String,Object> values, Map<String,String> formKeys, 
            HttpServletRequest request){
        HashMap<String,Object> vtf = mapReplaceKeys(formKeys, values);
        setMapToAttributes(vtf, request);
    }
    
    public static void mapToRequestAttributeString(Map<String,Object> values, Map<String,String> formKeys, 
            HttpServletRequest request){
        HashMap<String,Object> vtf = mapReplaceKeys(formKeys, values);
        setMapToAttributesString(vtf, request);
    }
    
    public static HashMap<String,Object> mapReplaceKeys(Map<String,String> mapTo, Map<String,Object> mapValues){
        HashMap<String,Object> result = new HashMap<String,Object>();
        for (Map.Entry<String,String> e : mapTo.entrySet())
            if (mapValues.containsKey(e.getKey()))
                result.put(e.getValue(), mapValues.get(e.getKey()));
        
        if (result.isEmpty())
            return null;
        
        return result;
    }
}
