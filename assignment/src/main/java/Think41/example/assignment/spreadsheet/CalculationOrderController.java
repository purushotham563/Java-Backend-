package Think41.example.assignment.spreadsheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cells")
public class CalculationOrderController {
    @Autowired
    private CalculationOrderService service;
    @GetMapping("/recalculation-order")
    public ResponseEntity<List<String>>getRecalculatedOrder(){
        List<String>order=service.getRecalculationOrder();
        return ResponseEntity.ok(order);
    }
}
