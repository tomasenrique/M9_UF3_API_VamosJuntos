package VJAPI.repositorys;

import VJAPI.entities.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends CrudRepository<Coche, Long> {

    Coche findCocheByMatricula(String matricula);

}
