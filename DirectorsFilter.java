
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DirectorsFilter implements Filter{
    
    private String myDirectors;
    
    public DirectorsFilter(String directors)
    {
        myDirectors = directors;
    }
    
    public boolean satisfies(String id)
    {
        ArrayList<String> myDirectorsList = new ArrayList<String>(Arrays.asList(myDirectors.split(",")));
        ArrayList<String> directorsList = new ArrayList<String>(Arrays.asList(MovieDatabase.getDirector(id).split(",")));
        for(String d : myDirectorsList)
        {
            for(String d2 : directorsList)
            {
                if(d.trim().equals(d2.trim()))
                {
                    return true;
                }
            }
        }
        return false; 
    }
}
