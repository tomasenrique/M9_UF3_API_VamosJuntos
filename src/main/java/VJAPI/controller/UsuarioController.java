package VJAPI.controller;

import VJAPI.entities.Usuario;
import VJAPI.repositorys.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Busca un Usuario por su dni
    @GetMapping("/mostrar/dni/{dniUsuario}")
    @ResponseBody
    public Usuario getUserByDni(@PathVariable String dniUsuario) {
        Usuario buscarUsuario = usuarioRepository.findUsuarioByDni(dniUsuario);
        if (buscarUsuario != null) {
            return buscarUsuario; // Devuelve un usuario buscado por su dni
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario.");
    }

    // Actualizar datos de usuario, buscado por su email. Se actualiza nombre, apellido y dni
    @PutMapping("/actualizar/dni")
    public String UpdateUserByEmail(@RequestBody Usuario usuario) {
        Usuario buscarUsuarioActualizarDni = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (buscarUsuarioActualizarDni != null) {
            buscarUsuarioActualizarDni.setNombre(usuario.getNombre());
            buscarUsuarioActualizarDni.setApellido(usuario.getApellido());
            buscarUsuarioActualizarDni.setDni(usuario.getDni());
            usuarioRepository.save(buscarUsuarioActualizarDni); // se guarda el objeto con los nuevos datos
            return "Usuario actualizado.";
        } else return "No existe el usuario que se quiere actualizar.";
    }

    // Actualizar datos de usuario, buscado por su dni. Se actualiza nombre, apellido y email
    @PutMapping("/actualizar/email")
    public String UpdateUserByDni(@RequestBody Usuario usuario) {
        Usuario buscarUsuarioActualizarEmail = usuarioRepository.findUsuarioByDni(usuario.getDni());
        if (buscarUsuarioActualizarEmail != null) {
            buscarUsuarioActualizarEmail.setNombre(usuario.getNombre());
            buscarUsuarioActualizarEmail.setApellido(usuario.getApellido());
            buscarUsuarioActualizarEmail.setEmail(usuario.getEmail());
            usuarioRepository.save(buscarUsuarioActualizarEmail); // se guarda el objeto con los nuevos datos
            return "Usuario actualizado.";
        } else return "No existe el usuario que se quiere actualizar.";
    }


    // Eliminar un usuario buscado por su email y eliminado por su id
    @DeleteMapping("/eliminar/email/{emailUsuario}")
    public String deleteUserByEmail(@PathVariable String emailUsuario) {
        Usuario usuarioEliminarEmail = usuarioRepository.findUsuarioByEmail(emailUsuario);
        if (usuarioEliminarEmail != null) {
            this.usuarioRepository.deleteById(usuarioEliminarEmail.getId_usuario());
            return "Usuario Borrado.";
        } else return "El usuario no existe.";
    }


    // Eliminar un usuario buscado por su dni y eliminado por su id
    @DeleteMapping("/eliminar/dni/{dnilUsuario}")
    public String deleteUserByDni(@PathVariable String dnilUsuario) {
        Usuario usuarioEliminarDni = usuarioRepository.findUsuarioByDni(dnilUsuario);
        if (usuarioEliminarDni != null) {
            this.usuarioRepository.deleteById(usuarioEliminarDni.getId_usuario());
            return "Usuario Borrado.";
        } else return "El usuario no existe.";
    }

}
