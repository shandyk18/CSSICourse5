
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    private FirstRatings fr;

    public FourthRatings() {
        // default constructor
        //this("ratings.csv");
    }

    public FourthRatings(String ratingsfile)
    {
        fr = new FirstRatings();
        //myMovies = fr.loadMovies(moviefile);
        //myRaters = fr.loadRaters(ratingsfile);
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
        for(Rater r : RaterDatabase.getRaters())
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        double avgRating = 0;
        double numOfRaters = 0;
        for(String m : movies)
        {
            for(Rater r : RaterDatabase.getRaters())
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

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f)
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> positiveRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        for(String m : movies)
        {
            double numRaters = 0;
            double weightedRating = 0;
            String title = MovieDatabase.getTitle(m);
            for(int ii = 0; ii < numSimilarRaters; ii++)
            {
                Rating currRating = positiveRaters.get(ii);
                Rater r = RaterDatabase.getRater(currRating.getItem());
                if(r.hasRating(m))
                {
                    weightedRating += currRating.getValue()*r.getRating(m);
                    numRaters++;
                }
            }
            if(numRaters >= minimalRaters)
            {
                list.add(new Rating(m, weightedRating/numRaters));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> positiveRaters = getSimilarities(id);
        Filter f = new TrueFilter();
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        for(String m : movies)
        {
            double numRaters = 0;
            double weightedRating = 0;
            for(int ii = 0; ii < numSimilarRaters; ii++)
            {
                if(ii < positiveRaters.size())
                {
                    Rating currRating = positiveRaters.get(ii);
                    Rater r = RaterDatabase.getRater(currRating.getItem());
                    if(r.hasRating(m))
                    {
                        weightedRating += currRating.getValue()*r.getRating(m);
                        numRaters++;
                    }
                }
                else
                {
                    break;
                }
            }
            if(numRaters >= minimalRaters)
            {
                list.add(new Rating(m, weightedRating/numRaters));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r: RaterDatabase.getRaters())
        {
            if(r != me)
            {
                double product = dotProduct(me, r);
                if(product > 0)
                {
                    list.add(new Rating(r.getID(), product));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    private double dotProduct(Rater me, Rater r)
    {
        double product = 0;
        ArrayList<String> myRatings = me.getItemsRated();
        for(String s : myRatings)
        {
            if(r.hasRating(s))
            {
                double myRating = me.getRating(s) - 5;
                double rRating = r.getRating(s) - 5;
                product += rRating*myRating;
            }
        }
        return product;
    }
}
