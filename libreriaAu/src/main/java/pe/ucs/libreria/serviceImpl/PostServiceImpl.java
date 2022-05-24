/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.ucs.libreria.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ucs.libreria.dao.PostDao;
import pe.ucs.libreria.model.Post;
import pe.ucs.libreria.service.PostService;

/**
 *
 * @author user
 */
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostDao postDao;
    @Override
    public int create(Post autores) {
        return postDao.create(autores);
    }

    @Override
    public int update(Post autores) {
        return postDao.update(autores);
    }

    @Override
    public int delete(int id) {
        return postDao.delete(id);
    }

    @Override
    public Post read(int id) {
        return postDao.read(id);
    }

    @Override
    public List<Post> readAll() {
        return postDao.readAll();
    }
    
}
