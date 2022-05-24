package pe.ucs.libreria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author user
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int idautor;
    private String nombre;
    private String apellidos;
    private String correo;
    
}
