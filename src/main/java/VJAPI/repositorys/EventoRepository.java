package VJAPI.repositorys;

import VJAPI.entities.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {

    /**
     * OJO ==>> IMPORTANTE
     * No se puede crear metodos que sean compuestos por '_' ya que estos no funcionaran y aparecera errores a la hora
     * de ejecuatr el programa, tambien hay que tenerlo en cuenta cuando se crean los nombre de los campos en las clases
     * ya que lo que se ponga ahi como nombre de propiedad aparecera aqui para crear un metodo.
     */
    
    List<Evento> findEventoByFecha(LocalDate fechaEvento);

    List<Evento> findEventoByCiudad(String ciudad);

    Evento findEventoByReferencia(String referenciaEvento);


}
