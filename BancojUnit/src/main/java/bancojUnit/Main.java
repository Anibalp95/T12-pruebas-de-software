package bancojUnit;

import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int eleccion = 0;
		float monto;
		int n_operacion = 0;
		String ans;
		String data;
		String tipoMoneda;
		String[] data_arr;
		Transaccion trans;
		Scanner scan = new Scanner(System.in);
		Usuario juan = new Usuario("Juan",1000000,1000);
		System.out.println("Bienvenido a Banco Azul, selecciona operación a realizar:");		
		while (eleccion < 1 || eleccion >4){
			if (n_operacion >=4) {
				System.out.println("Excedido máximo número de operaciones, inicie sesión nuevamente si quiere hacer más");
				scan.close();
				return;
			}
			System.out.println(juan.getNombre());
			System.out.println(String.format("CLP: %d", juan.getClp()));
			System.out.println(String.format("USD: %.2f", juan.getUsd()));
			System.out.println("1.- Depósito");
			System.out.println("2.- Retiro");
			System.out.println("3.- Ver transacciones");
			System.out.println("4.- Cerrar sesión");
			try {
				eleccion = scan.nextInt();
				scan.nextLine();
			} 
			catch(Exception e) {
				System.out.println("Debe elegir un número entre 1 y 4");
				eleccion = 0;
				scan.nextLine();
				continue;
			}
			switch(eleccion) {
				case 1:
					System.out.println("Depósito");
					System.out.println("Ingrese monto a depositar y moneda (ej: CLP 1000):");
					data = scan.nextLine();
					data_arr = data.split(" ");
					tipoMoneda = data_arr[0].toLowerCase();
					monto = Float.parseFloat(data_arr[1]);
					if (tipoMoneda.equals("usd")){
						if (juan.esPosibleDepositoUSD(monto)) {
							juan.setUsd(juan.getUsd() + monto);
							trans = new Transaccion("Depósito", monto, tipoMoneda);
						}	
						else {
							System.out.println("Error en la transacción, el monto debe ser positivo");
							eleccion = 0;
							continue;
						}
					}
					else if (tipoMoneda.equalsIgnoreCase("clp")) {
						if (juan.esPosibleDepositoCLP((int)monto)) {
							juan.setClp(juan.getClp() + (int)monto);
							trans = new Transaccion("Depósito", (int)monto, tipoMoneda);
						}
						else {
							System.out.println("Error en la transacción, el monto debe ser positivo");
							eleccion = 0;
							continue;
						}
					}
					else {
						System.out.println("Tipo de moneda no existe, intente otra vez");
						break;
					}
					juan.agregarTransacción(trans);
					n_operacion ++;
					System.out.println("Depósito realizado exitosamente");
					break;
				case 2:
					System.out.println("Retiro");
					System.out.println("Ingrese monto a retirar y moneda (ej: CLP 1000):");
					data = scan.nextLine();
					data_arr = data.split(" ");
					tipoMoneda = data_arr[0].toLowerCase();
					monto = Float.parseFloat(data_arr[1]);
					if (tipoMoneda.equals("usd")){
						if (juan.esPosibleRetiroUSD(monto)) {
							juan.setUsd(juan.getUsd() - monto);
							trans = new Transaccion("Retiro", monto, tipoMoneda);
						}
						else {
							System.out.println("Error en la transacción, revisa que tengas el saldo necesario y recuerda que solo se pueden retirar 100 USD como máximo transacción.");
							eleccion = 0;
							continue;
						}
					}
					else if (tipoMoneda.equalsIgnoreCase("clp")) {
						if (juan.esPosibleRetiroCLP((int)monto)) {
							juan.setClp(juan.getClp() - (int)monto);
							trans = new Transaccion("Retiro", (int)monto,tipoMoneda);
						}
						else {
							System.out.println("Error en la transacción, revisa que tengas el saldo necesario y recuerda que solo se pueden retirar 200000 CLP como máximo por transacción.");
							eleccion = 0;
							continue;
						}
					}
					else {
						System.out.println("Tipo de moneda no existe, intente otra vez");
						break;
					}
					juan.agregarTransacción(trans);
					n_operacion ++;
					System.out.println("Retiro realizado exitosamente");
					break;
				case 3:
					System.out.println("Transacciones realizadas:");
					if (juan.getTransaccionList().isEmpty()) {
						System.out.println("No se han realizado transacciones");
					}
					else {
						Iterator<Transaccion> i = juan.getTransaccionList().iterator();
						while(i.hasNext()) {
							i.next().mostrarTransaccion();
						}
					}
					break;
				case 4:
					System.out.println("Se ha cerrado la sesión, para volver a iniciar sesión presione 1");
					eleccion = scan.nextInt();
					if (eleccion == 1) {
						scan.nextLine();
						eleccion = 0;
						continue;
					}
					else {
						System.out.println("Adios");
						scan.close();
						return;
					}
				default:
					System.out.println("Debe elegir una opcion entre 1 y 4");
			}
			System.out.println("Desea realizar otra operación? (s/n)");
			ans = scan.nextLine();
			if (ans.equals("n") || ans.equals("no")) {
				break;
			}
			else {
				eleccion = 0;
			}
		}
		System.out.println("Adios");
		scan.close();
	}
}
