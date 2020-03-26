package VJAPI.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String nombre;
    private String apellido;

    @Size(max = 20)
    @Column(unique = true)
    private String dni;

    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "id_usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Coche.class)
    private List<Coche> listaCoches = new ArrayList<>();

    // Builders
    public Usuario() {
    }

    public Usuario(String nombre, String apellido, @Size(max = 20) String dni, @Size(max = 50) String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    // Setter and Getter
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * NOTA: ==>> IMPORTANTE
     * No poner los getter y setter de la variable o atributo de clase que tiene una relacion con otra clase,
     * en este caso este ==>> private List<Coche> listaCoches = new ArrayList<>();
     * Dato que si se hace, esto generaria un error de redundancia colapsando la memoria, este atributo representa la
     * relacion 1:N con la clase Coche y si se poner los metodos todo el programa fallara.
     */
}
