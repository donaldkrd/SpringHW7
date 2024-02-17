package gb.SpringJWT;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {

}