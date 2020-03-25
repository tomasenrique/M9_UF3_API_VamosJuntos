package VJAPI.controller;

import VJAPI.entities.Coche;
import VJAPI.entities.Usuario;
import VJAPI.repositorys.CocheRepository;
import VJAPI.repositorys.EventoRepository;
import VJAPI.repositorys.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vamosJuntos/reserva")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CocheRepository obtenerDatosCocheRepository;

    @Autowired
    private EventoRepository obtenerDatosEventoRepository;




//    // Agrega una reserva
//    @PostMapping("/agregar")
//    @ResponseBody
//    public String addReserva(@RequestParam String aaaa, @RequestParam String sss) {






//        Usuario usuarioBuscado =  reservaRepository.findUsuarioByDni(dniUsuario); // Se obtiene el usuario del coche
//
//        try {
//            coche.setId_usuario(usuarioBuscado); // Se asigna la clase del usuarios e internamente se le agrega el id de este
//            cocheRepository.save(coche);
//            return "Coche guardado en base de datos.";
//        } catch (DataIntegrityViolationException e) {
//            return "El Coche ya existe.";
//        }
//    }







}
