package VJAPI.repositorys;

import VJAPI.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findUsuarioByEmail(String email);
    Usuario findUsuarioByDni(String dni);


}
