package CJV805.BackEnd.Services;

import CJV805.BackEnd.Models.Movies;
import CJV805.BackEnd.Models.MoviesRepository;
import CJV805.BackEnd.Models.TVs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movies> getMovies(){
        return repository.findAll();
    }

    public void deleteMovie(String id) { repository.deleteById(id);}
    public Optional<Movies> getMovie(String id){
        return repository.findById(id);
    }

    public List<Movies> getMoviesWithTitle(String t) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("Title" ).is(t));
        List<Movies> movies = mongoTemplate.find(query, Movies.class);
            return movies;
    }

    public Movies updateMovie(String id, Movies movie) throws Exception {
        movie.set_id(id);
        Optional<Movies> movie1 = repository.findById(id);
        return repository.save(movie);
    }

    public Movies addMovie(Movies movie) {
        return repository.insert(movie);
    }
}
