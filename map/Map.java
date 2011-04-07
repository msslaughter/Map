package map;

import java.io.*;
import java.util.*;

import polynomial.PolyNode;

public class Map
{
    //constructor
    public Map(String cityFile, String flightFile)
    {
       try
       {
           BufferedReader cities = new BufferedReader(new FileReader(cityFile));
           BufferedReader flights = new BufferedReader(new FileReader(flightFile));
       
           ar = new CityNode[20];
       
           StringTokenizer line;
           int counter = 0;
           String s;
           String[] strar;
       
           while (cities.ready())
           {
               line = new StringTokenizer(cities.readLine());
           
               while (line.hasMoreTokens())
               {
                   s = s + " " + line.nextToken();
               }
           
               s = s.substring(1);
               ar[counter] = new CityNode(s, null);
           }
           
           while (flights.ready())
           {
               line = new StringTokenizer(flights.readLine());
               
               while (line.hasMoreTokens())
               {
                   s = s + " " + line.nextToken();
               }
               
               strar = s.split(",");
               strar[0].trim();
               strar[1].trim();
               
               int i = 0;
               while(i < ar.length)
                   if (ar[i] != null)
                       if (!(strar[0].equals(ar[i].city)))
                               i++;
               CityNode temp = ar[i];
               while (temp.next != null)
                   temp = temp.next;
               temp.next = new CityNode(strar[1], null);
               
               
            }
           
       }
       catch(FileNotFoundException e)
       {
           System.out.println(e);
       }
       
    }
    
    
    public static void request(String requestFile)
    {
        try
        {
        BufferedReader requests = new BufferedReader(new FileReader(requestFile));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        
        
    }
    
    private boolean checkForService(String city)
    {
        for (int i = 0; i < ar.length; i++)
        {
            if(ar[i] != null)
                if (ar[i].city.equals(city));
                    return true;
        }
        
        return false;
    }
    
    private boolean checkPath(String origin, String dest)
    {
        Stack path = new Stack<String>();
        
        
        int i = 0;
        while(i < ar.length)
        
            if (ar[i] != null)
            
                if (!(origin.equals(ar[i].city)))
                        i++;
                else
                    break;
        
        return checkPathR(origin, dest, path);
        
        return true;
    }
    
    private boolean checkPathR(String origin, String dest, Stack path)
    {
        path.push(origin);
        
        int i = 0;
        while(i < ar.length)
        
            if (ar[i] != null)
            
                if (!(origin.equals(ar[i].city)))
                        i++;
                else
                    break;
        
        
        if(origin.equals(dest))
            return true;
        else if (ar[i].next == null)
            return false;
        else
        {
            CityNode temp = ar[i].next;
            while (temp.next != null)
            if(checkPathR(temp.city, dest, path))
                return true;
            else
                temp = temp.next;
        }
    }
    
    private CityNode[] ar;
    
    class CityNode
    {
        //instance fields
        String city;
        CityNode next;
        
        //constructor
        public CityNode(String s, CityNode n)
        {
            city = s;
            next = n;
        }
    }
}
