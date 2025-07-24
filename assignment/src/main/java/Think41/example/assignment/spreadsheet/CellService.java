package Think41.example.assignment.spreadsheet;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CellService {
    @Autowired
    private CellRepository repository;
    private static final Pattern cellPat=Pattern.compile("([A-Z]+[0-9])");
    public List<String>getPrecedents(String cellId){
        Optional<Cell>cellOptional=repository.findById(cellId);
        if(cellOptional.isEmpty()){
            throw new EntityNotFoundException("cell not found");
        }
        String formula=cellOptional.get().getFormula();
        if(formula==null){
            return Collections.emptyList();
        }
        Matcher matcher=cellPat.matcher(formula);
        Set<String>predicates=new HashSet<>();
        while (matcher.find()){
            String ref=matcher.group(1);
            if(!ref.equalsIgnoreCase(cellId)){
                predicates.add(ref);
            }
        }
        return new ArrayList<>(predicates);
    }
}
