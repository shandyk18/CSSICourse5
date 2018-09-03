
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    public static ArrayList<Rater> myRaters;
    private FirstRatings fr;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile)
    {
        fr = new FirstRatings();
        //myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : movies)
        {
            double currRating = getAverageByID(m, minimalRaters);
            if(currRating > 0.0)
            {
                ratings.add(new Rating(m, currRating));
            }
        }
        return ratings;
    }
    
    private double getAverageByID(String id, int minimalRaters)
    {
            double averageRating = 0;
            double numOfRaters = 0;
            for(Rater r : myRaters)
            {
                if(r.hasRating(id))
                {
                    averageRating += r.getRating(id);
                    numOfRaters++;
                }
            }
            if(numOfRaters < minimalRaters)
            {
                return 0.0;
            }
            else
            {
                return averageRating/numOfRaters;
            }
    }
}
