package Think41.example.assignment.spreadsheet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CalculationOrderService {
    @Autowired
    private CellRepository repository;
    private static  final Pattern cellRefPattern=Pattern.compile("([A-Z]+[0-9])");
    public List<String>getRecalculationOrder(){
        List<Cell>allCells=repository.findAll();
        Map<String,List<String>>adjList=new HashMap<>();
        Map<String,Integer>inDegree=new HashMap<>();
        for(Cell cell:allCells){
            String cellId=cell.getCellId();
            adjList.putIfAbsent(cellId,new ArrayList<>());

            inDegree.putIfAbsent(cellId,0);
            String formula=cell.getFormula();
            if(formula==null){
                continue;
            }
            Matcher matcher=cellRefPattern.matcher(formula);
            while (matcher.find()){
                String dependency=matcher.group(1);
                adjList.putIfAbsent(dependency,new ArrayList<>());
                adjList.get(dependency).add(cellId);
                inDegree.put(cellId,inDegree.getOrDefault(cellId,0)+1);
                inDegree.putIfAbsent(dependency,0);


            }

        }
        Queue<String>queue=new LinkedList<>();
        for(Map.Entry<String,Integer>entry:inDegree.entrySet()){
            if(entry.getValue()==0){
                queue.add(entry.getKey());
            }
        }
        List<String>sortedOrder=new ArrayList<>();
        while (!queue.isEmpty()){
            String current=queue.poll();
            sortedOrder.add(current);
            for (String nei:adjList.getOrDefault(current,new ArrayList<>())){
                inDegree.put(nei,inDegree.get(nei)-1);
                if(inDegree.get(nei)==0){
                    queue.add(nei);
                }
            }
        }
        return sortedOrder;
    }
}
