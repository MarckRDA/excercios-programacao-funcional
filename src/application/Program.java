package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		List<Employee> emp = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.print("Enter the Salary: ");
			double mostSalary = sc.nextDouble();
			
			List<String> emailsMostSalaries = emp.stream().filter(p -> p.getSalary() > mostSalary)
					.map(p -> p.getEmail()).sorted().collect(Collectors.toList());
			emailsMostSalaries.forEach(System.out::println);
			
			Double salariesEmpBeginsM = emp.stream().filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary()).reduce(0.0,(x, y)-> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + salariesEmpBeginsM);
			
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}

}
