package VJAPI.controller;

import VJAPI.entities.Coche;
import VJAPI.entities.Usuario;
import VJAPI.repositorys.CocheRepository;
import VJAPI.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/vamosJuntos/coche")
public class CocheController {

    @Autowired
    private CocheRepository cocheRepository;

    @Autowired
    private UsuarioRepository obtenerDatosUsuarioRepository;


    // Agrega un coche
    @PostMapping("/agregar")
    @ResponseBody
    public String addCoche(@RequestBody Coche coche, @RequestParam String dniUsuario) {
        Usuario usuarioBuscado =  obtenerDatosUsuarioRepository.findUsuarioByDni(dniUsuario); // Se obtiene el usuario del coche

        try {
            coche.setId_usuario(usuarioBuscado); // Se asigna la clase del usuarios e internamente se le agrega el id de este
            cocheRepository.save(coche);
            return "Coche guardado en base de datos.";
        } catch (DataIntegrityViolationException e) {
            return "El Coche ya existe.";
        }
    }


    // Muestra todos los coches
    @GetMapping("/mostrar")
    @ResponseBody
    public Iterable<Coche> getAllCoches() {
        // Esto devuelve un JSON o XML con los coches.
        try {
            return cocheRepository.findAll(); // devuelve la lista de coches
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados.");
        }
    }

    // Busca un coche por su matricula
    @GetMapping("/mostrar/matricula/{matriculaCoche}")
    @ResponseBody
    public Coche getCocheByMatricula(@PathVariable String matriculaCoche) {
        Coche buscarCoche = cocheRepository.findCocheByMatricula(matriculaCoche);
        if (buscarCoche != null) {
            return buscarCoche;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el coche.");
    }


    // Actualizar datos de coche, buscado por su matricula y se asigna el id de usuario buscado por su dni
    @PutMapping("/actualizar")
    public String UpdateCoche(@RequestBody Coche coche) {
        Coche buscaCocheActualizar = cocheRepository.findCocheByMatricula(coche.getMatricula()); // Se obtiene el coche

        if (buscaCocheActualizar != null) {
            buscaCocheActualizar.setTelf_propietario(coche.getTelf_propietario());
            buscaCocheActualizar.setTipo_vehiculo(coche.getTipo_vehiculo());
            buscaCocheActualizar.setModelo(coche.getModelo());
            buscaCocheActualizar.setAnyo(coche.getAnyo());
            buscaCocheActualizar.setPunto_salida(coche.getPunto_salida());
            buscaCocheActualizar.setNum_plazas_libres(coche.getNum_plazas_libres());
            buscaCocheActualizar.setNum_plazas_ocupadas(coche.getNum_plazas_ocupadas());
            buscaCocheActualizar.setInfo_complementaria_coche(coche.getInfo_complementaria_coche());
            cocheRepository.save(buscaCocheActualizar);
            return "Datos de coche actualizado.";
        } else return "No existe el coche que se quiere actualizar.";
    }


    // Elimina un coche buscado por su matricula y eliminado por su id
    @DeleteMapping("/eliminar")
    public String deleteCoche(@RequestParam String matricula) {
        Coche cocheEliminar = cocheRepository.findCocheByMatricula(matricula);
        if (cocheEliminar != null) {
            cocheRepository.deleteById(cocheEliminar.getId_coche());
            return "Coche Borrado.";
        } else return "El coche a borrar no existe.";
    }

}
