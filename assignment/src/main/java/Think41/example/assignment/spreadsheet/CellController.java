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
public class CellController {
    @Autowired
    private CellService cellService;
    @GetMapping("/{cellId}/precedents")
    public ResponseEntity<List<String>>getPrecendents(@PathVariable String cellId){
        List<String>precedents=cellService.getPrecedents(cellId);
        return ResponseEntity.ok(precedents);
    }
}
