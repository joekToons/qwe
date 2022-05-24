package pe.ucs.libreria.daoImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.ucs.libreria.dao.PostDao;
import pe.ucs.libreria.model.Post;


/**
 *
 * @author user
 */
@Repository
public class PostDaoImpl implements PostDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Post autores) {
        String SQL ="INSERT INTO autores( nombre,apellidos,correo) VALUES(?,?,?)";
        return jdbcTemplate.update(SQL,new Object[]{autores.getNombre(),autores.getApellidos(),autores.getCorreo()});
    }

    @Override
    public int update(Post autores) {
        String SQL ="UPDATE autores SET nombre=?,apellidos=?,correo=? WHERE idautor=?";                 
        return jdbcTemplate.update(SQL,new Object[]{autores.getNombre(),autores.getApellidos(),autores.getCorreo(), autores.getIdautor()});
    }

    @Override
    public int delete(int id) {
        String SQL ="DELETE FROM autores WHERE idautor=?"; 
        return jdbcTemplate.update(SQL,id);
        
    }

    @Override
    public Post read(int id) {
        String SQL ="SELECT * FROM autores WHERE idautor=?";
        try {
            Post autores = jdbcTemplate.queryForObject(SQL, BeanPropertyRowMapper.newInstance(Post.class),id);
            return autores;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Post> readAll() {
        String SQL ="SELECT idautor, nombre, apellidos, correo FROM autores ORDER BY idautor ASC";        
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Post.class));
    }
    
}
