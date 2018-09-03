
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate()
    {
        Random rand = new Random();
        TrueFilter f = new TrueFilter();
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(f);
        ArrayList<String> movies = new ArrayList<String>();
        for(int ii = 0; ii < 15; ii++)
        {
            movies.add(movieDatabase.get(rand.nextInt(MovieDatabase.size())));
        }
        return movies;
    }
    
    public void printRecommendationsFor(String webRaterID)
    {
        int num = 0;
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> recMovies = fr.getSimilarRatings(webRaterID, 20, 3);
        System.out.println("<table>");
        System.out.println("<tr>");
        System.out.println("<th><strong>Movie</strong></th>");
        System.out.println("<th><strong>Rating</strong></th>");
        System.out.println("<th><strong>Director(s)</strong></th>");
        System.out.println("<th><strong>Year</strong></th>");
        System.out.println("</tr>");
        for(Rating r : recMovies)
        {
            System.out.println("<tr>"); 
            System.out.println("<td>" + MovieDatabase.getTitle(r.getItem()) + "</td>");
            System.out.println("<td>" + Math.round(r.getValue()) + "</td>");
            System.out.println("<td>" + MovieDatabase.getDirector(r.getItem()) + "</td>");
            System.out.println("<td>" + MovieDatabase.getYear(r.getItem()) + "</td>");
            System.out.println("</tr");
            num++;
            if(num >= 15)
            {
                break;
            }
        }
        if(recMovies.isEmpty())
        {
            System.out.println("<p>No movies found ):</p>");
        }
        System.out.println("</table>");
        System.out.println("<style>");
        System.out.println("table{width: 100%;}");
        System.out.println("font-family: monospace");
        System.out.println("</style>");
    }
}
