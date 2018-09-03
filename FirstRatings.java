/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> movieData = new ArrayList<Movie>();
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        for(CSVRecord record : parser)
        {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            String minutes = record.get("minutes");
            movieData.add(new Movie(id, title, year, genres, director, country, poster, Integer.parseInt(minutes)));
        }
        return movieData;
    }

    public ArrayList<Rater> loadRaters(String filename)
    {
        ArrayList<Rater> raterData = new ArrayList<Rater>();
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        for(CSVRecord record : parser)
        {
            if(raterData.isEmpty())
            {
                Rater rater = new EfficientRater(record.get("rater_id"));
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raterData.add(rater);
            }
            else
            {
                for(int ii = 0; ii < raterData.size(); ii++)
                {
                    if(raterData.get(ii).getID().equals(record.get("rater_id")))
                    {
                        raterData.get(ii).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                        break;
                    }
                    else
                    {
                        if(ii == raterData.size()-1)
                        {
                            Rater rater = new EfficientRater(record.get("rater_id"));
                            rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                            raterData.add(rater);
                            break;
                        }
                    }
                }
            }
        }
        return raterData;
    }

    public void testLoadMovies()
    {
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv ");
        int numOfComedy = 0;
        int numOf150 = 0;
        int maxNum = 1;
        HashMap<String,Integer> maxMap = new HashMap<String, Integer>();
        ArrayList<String> directors = new ArrayList<String>();
        /*int num = 0;
        for(Movie mov : movies)
        {
        System.out.println(mov.getTitle());
        num++;
        }
        System.out.println(num);*/
        for(Movie mov : movies)
        {
            if(mov.getGenres().contains("Comedy"))
            {
                numOfComedy++;
            }
            if(mov.getMinutes() > 150)
            {
                numOf150++;
            }
            String director = mov.getDirector();
            ArrayList<String> splitD = new ArrayList(Arrays.asList(director.split(",")));
            for(int ii = 0; ii < splitD.size(); ii++)
            {
                String currD = splitD.get(ii).trim();
                if(maxMap.containsKey(currD))
                {
                    maxMap.put(currD, maxMap.get(currD)+1);
                    if(maxNum < maxMap.get(currD))
                    {
                        maxNum = maxMap.get(currD);   
                    }
                }
                else
                {
                    maxMap.put(currD, 1);
                }
            }
        }
        for (String key : maxMap.keySet()) 
        {
            if (maxMap.get(key).equals(maxNum))
            {
                directors.add(key);
            }
        }
        System.out.println("Number of comedies: " + numOfComedy + "\nNumber of Movies Over 150 Minutes: " + numOf150 +
            "\nMaximum Number of Movies By Any Director: " + maxNum + "\n" + directors.toString());
    }

    public void testLoadRaters()
    {
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        ArrayList<String> ratersMax = new ArrayList<String>();
        ArrayList<String> movies = new ArrayList<String>();
        HashMap<String, Integer> maxMap = new HashMap<String, Integer>();
        int maxNum = 0;
        int hasRating = 0;
        for(Rater r : raters)
        {
            if(r.getID().equals("193"))
            {
                System.out.println("Rater ID 193 has reviews: " + r.numRatings());
            }
            maxMap.put(r.getID(), r.numRatings());
            if(maxNum < r.numRatings())
            {
                maxNum = r.numRatings();
            }
            if(r.hasRating("1798709"))
            {
                hasRating++;
            }
            ArrayList<String> itemsRated = r.getItemsRated();
            for(String item : itemsRated)
            {
                if(!movies.contains(item))
                {
                    movies.add(item);
                }
            }
        }
        System.out.println(hasRating + " users rated movie id 1798709" + "\nMovies rated overall: " + movies.size());
        for(Rater r : raters)
        {
            if (r.numRatings() == maxNum)
            {
                ratersMax.add(r.getID());
            }
        }
        System.out.println("Maximum Number of Reviews: " + maxNum + "\n" + ratersMax.toString());

        /*System.out.println(raters.size());
        for(Rater r : raters)
        {
        System.out.println(r.getID() + " " + r.numRatings());
        ArrayList<String> itemsRated = r.getItemsRated();
        for(String item : itemsRated)
        {
        System.out.println(item + " " + r.getRating(item));
        }
        }*/

    }
}
