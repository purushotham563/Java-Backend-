package Think41.example.assignment;

import Think41.example.assignment.spreadsheet.Cell;
import Think41.example.assignment.spreadsheet.CellDependency;
import Think41.example.assignment.spreadsheet.CellDependencyRepository;
import Think41.example.assignment.spreadsheet.CellRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(
			CellRepository cellRepository,
			CellDependencyRepository cellDependencyRepository) {
		return args -> {
			// Clear existing data
			cellDependencyRepository.deleteAll();
			cellRepository.deleteAll();

			// Create some sample cells
			Cell a1 = new Cell("A1", "10"); // Constant value
			Cell a2 = new Cell("A2", "20"); // Constant value
			Cell a3 = new Cell("A3", "A1+A2"); // Depends on A1 and A2
			Cell b1 = new Cell("B1", "A3*2"); // Depends on A3
			Cell b2 = new Cell("B2", "B1+A1"); // Depends on B1 and A1
			Cell c1 = new Cell("C1", "B2*3"); // Depends on B2
			Cell d1 = new Cell("D1", "C1+B1"); // Depends on C1 and B1
			Cell e1 = new Cell("E1", null); // Empty cell


			cellRepository.save(a1);
			cellRepository.save(a2);
			cellRepository.save(a3);
			cellRepository.save(b1);
			cellRepository.save(b2);
			cellRepository.save(c1);
			cellRepository.save(d1);
			cellRepository.save(e1);


			cellDependencyRepository.save(new CellDependency(null, "A3", "A1"));
			cellDependencyRepository.save(new CellDependency(null, "A3", "A2"));
			cellDependencyRepository.save(new CellDependency(null, "B1", "A3"));
			cellDependencyRepository.save(new CellDependency(null, "B2", "B1"));
			cellDependencyRepository.save(new CellDependency(null, "B2", "A1"));
			cellDependencyRepository.save(new CellDependency(null, "C1", "B2"));
			cellDependencyRepository.save(new CellDependency(null, "D1", "C1"));
			cellDependencyRepository.save(new CellDependency(null, "D1", "B1"));

			System.out.println("Sample data initialized successfully!");
		};

	}

}
