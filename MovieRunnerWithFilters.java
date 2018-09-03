
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Collections.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings()
    {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        int numOfMovies = 0;
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            numOfMovies++;
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
        System.out.println(numOfMovies);
    }
    
    public void printAverageRatingsByYear()
    {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        Filter f = new YearAfterFilter(2000);
        int numOfMovies = 0;
        ArrayList<Rating> movieRatings = getAverageRatingsByFilter(20, f);
        System.out.println("found " + movieRatings.size() + " movies");
        Collections.sort(movieRatings);
        for(Rating r : movieRatings)
        {
            numOfMovies++;
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(numOfMovies);
    }
    
    public void printAverageRatingsByGenre()
    {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        Filter f = new GenreFilter("Comedy");
        int numOfMovies = 0;
        ArrayList<Rating> movieRatings = getAverageRatingsByFilter(20, f);
        System.out.println("found " + movieRatings.size() + " movies");
        Collections.sort(movieRatings);
        for(Rating r : movieRatings)
        {
            numOfMovies++;
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("   " + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println(numOfMovies);
    }
    
    public void printAverageRatingsByMinutes()
    {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        Filter f = new MinutesFilter(105, 135);
        int numOfMovies = 0;
        ArrayList<Rating> movieRatings = getAverageRatingsByFilter(5, f);
        System.out.println("found " + movieRatings.size() + " movies");
        Collections.sort(movieRatings);
        for(Rating r : movieRatings)
        {
            numOfMovies++;
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(numOfMovies);
    }
    
    public void printAverageRatingsByDirectors()
    {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        int numOfMovies = 0;
        ArrayList<Rating> movieRatings = getAverageRatingsByFilter(4, f);
        System.out.println("found " + movieRatings.size() + " movies");
        Collections.sort(movieRatings);
        for(Rating r : movieRatings)
        {
            numOfMovies++;
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("   " + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println(numOfMovies);
    }
    
    public void printAverageRatingsByYearAfterAndGenre()
    {
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(yearFilter);
        allFilter.addFilter(genreFilter);
        int numOfMovies = 0;
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> movieRatings = getAverageRatingsByFilter(8, allFilter);
        System.out.println("found " + movieRatings.size() + " movies");
        Collections.sort(movieRatings);
        for(Rating r : movieRatings)
        {
            numOfMovies++;
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("   " + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println(numOfMovies);
    }
    
    public void printAverageRatingsByDirectorsAndMinutes()
    {
        Filter minutesFilter = new MinutesFilter(90, 180);
        Filter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(directorsFilter);
        int numOfMovies = 0;
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + tr.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> movieRatings = getAverageRatingsByFilter(3, allFilter);
        System.out.println("found " + movieRatings.size() + " movies");
        Collections.sort(movieRatings);
        for(Rating r : movieRatings)
        {
            numOfMovies++;
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("   " + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println(numOfMovies);
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        double avgRating = 0;
        double numOfRaters = 0;
        for(String m : movies)
        {
            for(Rater r : ThirdRatings.myRaters)
            {
                if(r.hasRating(m))
                {
                    avgRating += r.getRating(m);
                    numOfRaters++;
                }
            }
            if(numOfRaters >= minimalRaters)
            {
                double nummm = avgRating/numOfRaters;
                movieRatings.add(new Rating(m, avgRating/numOfRaters));
            }
            avgRating = 0;
            numOfRaters = 0;
        }
        return movieRatings;
    }
}
