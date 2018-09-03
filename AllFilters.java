import java.util.ArrayList;

public class AllFilters implements Filter {
    /**
    * List of movie Filters
    */
    ArrayList<Filter> filters;
    
    /**
    * Constructor for AllFilters class
    *
    */
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }
    
    /**
    * Adds each filter to an array list so multiple filters may be used at once
    *
    * @param f a Filter given by user to use
    */
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    /**
    * Checks if movie satisfies criteria
    *
    * @param id String ID of movie
    * @return returns a boolean of whether movie satisfies filter criteria
    */
    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
}
