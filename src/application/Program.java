package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("\033[0;1mEntre com os dados do aluguel\033[0;0m");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.print("Devolução (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
		
		CarRental cR = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rS = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rS.processInvoice(cR);
		
		System.out.println("\033[0;1mFATURA\033[0;0m");
		System.out.printf("Pagamento básico: %.2f%n", cR.getInvoice().getBasicPayment());
		System.out.printf("Imposto: %.2f%n", cR.getInvoice().getTax());
		System.out.printf("Pagammento total: %.2f%n", cR.getInvoice().getTotalPayment());
		
		sc.close();

	}

}
