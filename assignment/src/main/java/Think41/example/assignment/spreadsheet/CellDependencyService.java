package Think41.example.assignment.spreadsheet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CellDependencyService {
    @Autowired
    private CellDependencyRepository repository;
    public List<String>getCellDependentCellIds(String cellId){
        List<CellDependency>dependencies=repository.findByDependsOnCell(cellId);
        return dependencies.stream().map(CellDependency::getCellId).collect(Collectors.toList());
    }
}
