package Think41.example.assignment.spreadsheet;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CellRepository extends JpaRepository<Cell,String> {

}
