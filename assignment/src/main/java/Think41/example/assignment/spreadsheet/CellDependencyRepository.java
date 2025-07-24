package Think41.example.assignment.spreadsheet;

import jakarta.transaction.Transactional;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Transactional
@Repository
public interface CellDependencyRepository extends JpaRepository<CellDependency,Long> {
    List<CellDependency> findByDependsOnCell(String cellId);

}
