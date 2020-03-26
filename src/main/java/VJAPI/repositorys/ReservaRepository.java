package VJAPI.repositorys;

import VJAPI.entities.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    List<Reserva> findReservaByFecha(LocalDate fechaReserva);

    Reserva findReservaByReferencia(String referenciaReserva);

}
