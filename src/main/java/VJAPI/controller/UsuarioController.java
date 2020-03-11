package VJAPI.controller;

import VJAPI.entities.Usuario;
import VJAPI.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/vamosJuntos/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    // Agrega un Usuario
    @PostMapping("/agregar")
    @ResponseBody
    public String addUser(@RequestBody Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return "Usuario guardado en base de datos";
        } catch (DataIntegrityViolationException e) {
            return "El Usuario ya existe";
        }
    }

    // Muestra todos los Usuarios
    @GetMapping("/mostrar")
    @ResponseBody
    public Iterable<Usuario> getAllUsers() {
        // Esto devuelve un JSON o XML con los usuarios.
        try {
            return usuarioRepository.findAll(); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        }
    }


    // Busca un Usuario por su email
    @GetMapping("/mostrar/email/{emailUsuario}")
    @ResponseBody
    public Usuario getUserByEmail(@PathVariable String emailUsuario) {
        Usuario buscarUsuario = usuarioRepository.findUsuarioByEmail(emailUsuario);
        if (buscarUsuario != null) {
            return buscarUsuario; // Devuelve un usuario buscado por su email
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario.");
    }

    // Actualizar datos de usuario, buscado por su email
    @PutMapping("/actualizar")
    public String UpdateUser(@RequestBody Usuario usuario) {
        Usuario buscarUsuarioActualizar = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (buscarUsuarioActualizar != null) {
            buscarUsuarioActualizar.setNombre(usuario.getNombre());
            buscarUsuarioActualizar.setApellido(usuario.getApellido());
            usuarioRepository.save(buscarUsuarioActualizar); // se guarda el objeto con los nuevos datos
            return "Usuario actualizado.";
        } else return "No existe el usuario que se quiere actualizar.";
    }


    // Eliminar un usuario buscado por su email y eliminado por su id
    @DeleteMapping("/eliminar/{emailUsuario}")
    public String deleteUser(@PathVariable String emailUsuario) {
        Usuario usuarioEliminar = usuarioRepository.findUsuarioByEmail(emailUsuario);
        if (usuarioEliminar != null) {
            this.usuarioRepository.deleteById(usuarioEliminar.getId_usuario());
            return "Usuario Borrado.";
        } else return "El usuario no existe.";
    }

}
