package VJAPI.repositorys;

import VJAPI.entities.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {

    List<Evento> findEventoByFecha(LocalDate fechaEvento);
    List<Evento> findEventoByCiudad(String ciudad);


}
