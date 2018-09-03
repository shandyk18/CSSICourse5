
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Collections.*;
    
public class MovieRunnerAverage {
    
    public void printAverageRatings()
    {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        //System.out.println(sr.getMovieSize());
        //System.out.println(sr.getRaterSize());
        ArrayList<Rating> ratings = sr.getAverageRatings(12);
        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            System.out.println(sr.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void getAverageRatingOneMovie()
    {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        String movieID = sr.getID("Vacation");
        for(Rating r : ratings)
        {
            if(r.getItem().equals(movieID))
            {
                System.out.println(sr.getTitle(movieID) + " " + r.getValue());
            }
        }
        
    }

}
