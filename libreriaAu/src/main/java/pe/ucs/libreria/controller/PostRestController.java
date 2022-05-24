/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.ucs.libreria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.ucs.libreria.model.Post;
import pe.ucs.libreria.service.PostService;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/autores")
public class PostRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public List<Post> getPosts() {
        return postService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id) {
        Post autores = postService.read(id);
        return ResponseEntity.ok(autores);
    }

    @DeleteMapping("/{id}")
    public int deletePost(@PathVariable int id) {        
        return postService.delete(id);
    }
    @PostMapping("/add")
    public int addPost(@RequestBody Post autores) {  
        System.out.println(autores.getNombre());
        System.out.println(autores.getApellidos());
        System.out.println(autores.getCorreo());
        
       return postService.create(autores);
    }
    @PutMapping("/edit")
    public int editPost(@RequestBody Post autores) {  
        Post autor = new Post(autores.getIdautor(),autores.getNombre(), autores.getApellidos(), autores.getCorreo());
        System.out.println(autores.getIdautor()+" , "+autores.getNombre()+" , "+autores.getApellidos()+" , "+autores.getCorreo());
        return postService.update(autores);
    }
}
