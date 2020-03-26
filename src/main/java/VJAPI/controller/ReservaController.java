package VJAPI.controller;

import VJAPI.entities.Coche;
import VJAPI.entities.Evento;
import VJAPI.entities.Reserva;
import VJAPI.repositorys.CocheRepository;
import VJAPI.repositorys.EventoRepository;
import VJAPI.repositorys.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(path = "/vamosJuntos/reserva")
public class ReservaController {

    private static final Logger log = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CocheRepository obtenerDatosCocheRepository;

    @Autowired
    private EventoRepository obtenerDatosEventoRepository;


    //  Agrega una reserva
    @PostMapping("/agregar")
    @ResponseBody
    public String addReserva(@RequestParam String referenciaEvento, @RequestParam String matriculaCoche) {
        // se obtiene los objetos por medio de sus referencia y matricula para luego obtener si id de cada uno.
        Evento eventoReservaBuscado = obtenerDatosEventoRepository.findEventoByReferencia(referenciaEvento);
        Coche cocheReservaBuscado = obtenerDatosCocheRepository.findCocheByMatricula(matriculaCoche);
        Reserva reserva = new Reserva();
        try {
            // Se asigna el id de cada clase a la que esta realcionada con la reserva
            reserva.setReferencia(referenciaEvento + matriculaCoche); // se concatena ambas referencias para obtener la de reserva
            reserva.setFecha(LocalDate.now()); // Sera la fecha en que se realiza la reserva
            reserva.setHora(LocalTime.now()); // Sera la hora en que se realiza la reserva
            reserva.setId_coche(cocheReservaBuscado);
            reserva.setId_evento(eventoReservaBuscado);
            reservaRepository.save(reserva); // Se guarda la reserva
            return "Reserva Hecha.";
        } catch (DataIntegrityViolationException e) {
            return "No se ha podido hacer la reserva: " + e;
        }
    }

    //
    @GetMapping("/mostrar")
    @ResponseBody
    public Iterable<Reserva> getAllReserva() {
        // Esto devuelve un JSON o XML con los usuarios.
        try {
            return reservaRepository.findAll(); // devuelve la lista de usuarios
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ureservas hechas.");
        }
    }

    // Muestra una reserva por su referencia
    @GetMapping("/mostrar/referencia/{referenciaReserva}")
    @ResponseBody
    public Reserva getReservaByReferencia(@PathVariable String referenciaReserva) {
        Reserva reservaBuscada = reservaRepository.findReservaByReferencia(referenciaReserva);
        if (reservaBuscada != null) {
            return reservaBuscada;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la reserva con la referencia ingresada.");
    }

    // Busca las reservas por fecha en formato yyyy-MM-dd, !escribir como esta en el formato¡
    @GetMapping("/mostrar/fecha/{fechaReserva}")
    @ResponseBody
    public Iterable<Reserva> getAllReservasByFecha(@PathVariable String fechaReserva) {
        try {
            log.info("fecha obtenida: " + LocalDate.parse(fechaReserva));
            return reservaRepository.findReservaByFecha(LocalDate.parse(fechaReserva));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay eventos registrados con esa fecha");
        }
    }

    // Actualiza la referencia de una reserva y la fecha y hora
    @PutMapping("/actualizar/referencia/{referenciaReserva}")
    public String UpdateReserva(@RequestBody Reserva reserva, @PathVariable String referenciaReserva) {
        Reserva reservaBuscada = reservaRepository.findReservaByReferencia(referenciaReserva); // se busca la reserva

        if (reservaBuscada != null) {
            reservaBuscada.setReferencia(reserva.getReferencia()); // se asigna la nueva referencia
            // se actualiza las fechas por unas nuevas, indicando el nuevo dia y hora de reserva.
            reservaBuscada.setFecha(LocalDate.now());
            reservaBuscada.setHora(LocalTime.now());
            reservaRepository.save(reservaBuscada);
            return "Reserva actualizada.";
        } else return "No existe la reserva que se quiere actualizar.";
    }

    // Se borra una reserva buscado por su referencia y eliminado por su id
    @DeleteMapping("/eliminar/referencia/{referenciaReserva}")
    @ResponseBody
    public String deleteReserva(@PathVariable String referenciaReserva) {
        Reserva reservaEliminar = reservaRepository.findReservaByReferencia(referenciaReserva);

        if (reservaEliminar != null) {
            reservaRepository.deleteById(reservaEliminar.getId_reservas());
            return "Reserva borrada.";
        } else return "La reserva a borrar no existe.";
    }


}
