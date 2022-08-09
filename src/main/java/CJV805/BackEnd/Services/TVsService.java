package CJV805.BackEnd.Services;

import CJV805.BackEnd.Models.TVs;
import CJV805.BackEnd.Models.TVsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVsService {

    @Autowired
    private TVsRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TVs> getTVs(){
        return repository.findAll();
    }

    public Optional<TVs> getTV(String id){
        return repository.findById(id);
    }

    public List<TVs> gettvWithTitle(String t) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("Title" ).is(t));
        List<TVs> tv = mongoTemplate.find(query, TVs.class);
        return tv;
    }

    public void deletetv(String id) { repository.deleteById(id);}


    public TVs updatetv(String id, TVs tvs) throws Exception {
        tvs.set_id(id);
        Optional<TVs> tv = repository.findById(id);
        return repository.save(tvs);
    }

    public TVs addTV(TVs tv) {
        return repository.insert(tv);
    }
}
