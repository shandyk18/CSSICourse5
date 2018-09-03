
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*; 

public class MovieRunnerSimilarRatings {
    
    public void printSimilarRatings()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings("71", 20, 5);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964", 20, 5, f);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
            System.out.println("  " + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120", 10, 2, f);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
            System.out.println("  " + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        Filter f1 = new GenreFilter("Drama");
        Filter f2 = new MinutesFilter(80, 160);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(f1);
        allFilters.addFilter(f2);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168", 10, 3, allFilters);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " Minutes: " + MovieDatabase.getMinutes(r.getItem()) + " " + r.getValue());
            System.out.println("  " + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes()
    {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new MinutesFilter(70, 200);
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(f1);
        allFilters.addFilter(f2);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314", 10, 5, allFilters);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " Year: " + MovieDatabase.getYear(r.getItem())
            + " Minutes: " + MovieDatabase.getMinutes(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatings()
    {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        int numOfMovies = 0;
        ArrayList<Rating> ratings = fr.getAverageRatings(35);
        Collections.sort(ratings);
        for(Rating r : ratings)
        {
            numOfMovies++;
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
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
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        ArrayList<Rating> movieRatings = fr.getAverageRatingsByFilter(8, allFilter);
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
}
