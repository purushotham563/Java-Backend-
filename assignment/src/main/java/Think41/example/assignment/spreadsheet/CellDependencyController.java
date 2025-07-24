package Think41.example.assignment.spreadsheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cells")
public class CellDependencyController {
    @Autowired
    private CellDependencyService service;
    @GetMapping("/{cellId}/dependents")
    public ResponseEntity<List<String>>getDependency(@PathVariable String cellId){
        List<String>dependents=service.getCellDependentCellIds(cellId);
        return ResponseEntity.ok(dependents);
    }
}
