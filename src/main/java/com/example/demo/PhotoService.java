// copyright www.codejava.net
package com.example.demo;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class PhotoService {
 
    @Autowired
    private PhotoRepository repo;
     
    public List<Photo> listAll() {
        return repo.findAll();
    }
     
    public Photo save(Photo product) {
        return repo.save(product);
    }
     
    public Photo get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}