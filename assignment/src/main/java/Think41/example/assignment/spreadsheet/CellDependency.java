package Think41.example.assignment.spreadsheet;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CellDependency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cellId;
    private String dependsOnCell;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getDependsOnCell() {
        return dependsOnCell;
    }

    public void setDependsOnCell(String dependsOnCell) {
        this.dependsOnCell = dependsOnCell;
    }

    public CellDependency(Long id, String cellId, String dependsOnCell) {
        this.id = id;
        this.cellId = cellId;
        this.dependsOnCell = dependsOnCell;
    }

    public CellDependency() {
    }
}
