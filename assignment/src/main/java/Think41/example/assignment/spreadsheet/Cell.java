package Think41.example.assignment.spreadsheet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cell {
    @Id
    private String cellId;
    private String formula;

    public Cell() {
    }

    public Cell(String cellId, String formula) {
        this.cellId = cellId;
        this.formula = formula;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
