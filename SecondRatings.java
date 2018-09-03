/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private FirstRatings fr;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile)
    {
        fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize()
    {
        return myMovies.size();
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public String getTitle(String id)
    {
        for(Movie m : myMovies)
        {
            if(m.getID().equals(id))
            {
                return m.getTitle();
            }
        }
        return "NO SUCH ID";
    }
    
    public String getID(String title)
    {
        for(Movie m : myMovies)
        {
            if(m.getTitle().equals(title))
            {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(Movie m : myMovies)
        {
            double currRating = getAverageByID(m.getID(), minimalRaters);
            if(currRating > 0.0)
            {
                ratings.add(new Rating(m.getID(), currRating));
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