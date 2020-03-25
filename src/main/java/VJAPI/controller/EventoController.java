package VJAPI.controller;

import VJAPI.entities.Evento;
import VJAPI.repositorys.EventoRepository;
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
@RequestMapping(path = "/vamosJuntos/evento")
public class EventoController {

    private static final Logger log = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    EventoRepository eventoRepository;


    // Agrega un evento
    @PostMapping("/agregar")
    @ResponseBody
    public String addEvent(@RequestBody Evento evento) {
        try {
            evento.setFecha(LocalDate.now());
            log.info("fecha: " + LocalDate.now());
            evento.setHora(LocalTime.now());
            eventoRepository.save(evento);
            return "Evento guardado en base de datos";
        } catch (DataIntegrityViolationException e) {
            return "El Evento ya existe";
        }
    }


    // Muestra todos los Eventos disponibles
    @GetMapping("/mostrar")
    @ResponseBody
    public Iterable<Evento> getAllEvents() {
        // Esto devuelve un JSON o XML con los eventos.
        try {
            return eventoRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay eventos registrados");
        }
    }

    // Busca los eventos por fecha en formato yyyy-MM-dd, !escribir como esta en el formato¡
    @GetMapping("/mostrar/fecha/{fechaEvento}")
    @ResponseBody
    public Iterable<Evento> getAllEventsByFecha(@PathVariable String fechaEvento) {
        try {
            log.info("fecha obtenida: " + LocalDate.parse(fechaEvento));
            return eventoRepository.findEventoByFecha(LocalDate.parse(fechaEvento));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay eventos registrados con esa fecha");
        }
    }

    // Busca los eventos por ciudad
    @GetMapping("/mostrar/ciudad/{ciudad}")
    @ResponseBody
    public Iterable<Evento> getAllEventsByCiudad(@PathVariable String ciudad) {
        try {
            return eventoRepository.findEventoByCiudad(ciudad);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay eventos registrados en esa ciudad");
        }
    }

    // Mostrara un evento por medio de su referencia
    @GetMapping("/mostrar/referencia/{referenciaEvento}")
    @ResponseBody
    public Evento getEventByReferencia(@PathVariable String referenciaEvento) {
        Evento eventoBuscado = eventoRepository.findEventoByReferencia(referenciaEvento); // buca el evento por su referencia
        if (eventoBuscado != null) {
            return eventoBuscado;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el evento solicitado.");
    }

    // Actualiza los datos de un evento por medio de su referencia
    @PutMapping("/actualizar")
    @ResponseBody
    public String updateEvent(@RequestBody Evento evento) {
        Evento eventoActualizar = eventoRepository.findEventoByReferencia(evento.getReferencia());

        if (eventoActualizar != null) {
            // Obteniendo los datos para actualizar el evento
            eventoActualizar.setNombre_evento(evento.getNombre_evento());
            eventoActualizar.setRecinto(evento.getRecinto());
            eventoActualizar.setCiudad(evento.getCiudad());
            eventoActualizar.setPath_imagen(evento.getPath_imagen());
            eventoActualizar.setInfo_complementaria_evento(evento.getInfo_complementaria_evento());
            // se actualiza la fecha y la hora tomadas del sistema
            eventoActualizar.setHora(LocalTime.now());
            eventoActualizar.setFecha(LocalDate.now());
            eventoRepository.save(eventoActualizar); // Se actualiza el evento con los datos proporcionados
            return "Evento actualizado.";
        } else return "El evento a actualizar no existe.";
    }

    // Se borra un evento por buscado por su referencia y eliminado por su id
    @DeleteMapping("/eliminar/referencia/{referenciaEvento}")
    @ResponseBody
    public String deleteEvent(@PathVariable String referenciaEvento) {
        Evento eventoEliminar = eventoRepository.findEventoByReferencia(referenciaEvento);

        if (eventoEliminar != null) {
            eventoRepository.deleteById(eventoEliminar.getId_evento());
            return "Evento borrado.";
        } else return "El evento a borrar no existe.";
    }

}
