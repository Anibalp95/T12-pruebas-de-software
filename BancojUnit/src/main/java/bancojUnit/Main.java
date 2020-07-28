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
		System.out.println("Bienvenido a Banco Azul, selecciona operaci�n a realizar:");		
		while (eleccion < 1 || eleccion >4){
			if (n_operacion >=4) {
				System.out.println("Excedido m�ximo n�mero de operaciones, inicie sesi�n nuevamente si quiere hacer m�s");
				scan.close();
				return;
			}
			System.out.println(juan.getNombre());
			System.out.println(String.format("CLP: %d", juan.getClp()));
			System.out.println(String.format("USD: %.2f", juan.getUsd()));
			System.out.println("1.- Dep�sito");
			System.out.println("2.- Retiro");
			System.out.println("3.- Ver transacciones");
			System.out.println("4.- Cerrar sesi�n");
			try {
				eleccion = scan.nextInt();
				scan.nextLine();
			} 
			catch(Exception e) {
				System.out.println("Debe elegir un n�mero entre 1 y 4");
				eleccion = 0;
				scan.nextLine();
				continue;
			}
			switch(eleccion) {
				case 1:
					System.out.println("Dep�sito");
					System.out.println("Ingrese monto a depositar y moneda (ej: CLP 1000):");
					data = scan.nextLine();
					data_arr = data.split(" ");
					tipoMoneda = data_arr[0].toLowerCase();
					monto = Float.parseFloat(data_arr[1]);
					if (tipoMoneda.equals("usd")){
						if (juan.esPosibleDepositoUSD(monto)) {
							juan.setUsd(juan.getUsd() + monto);
							trans = new Transaccion("Dep�sito", monto, tipoMoneda);
						}	
						else {
							System.out.println("Error en la transacci�n, el monto debe ser positivo");
							eleccion = 0;
							continue;
						}
					}
					else if (tipoMoneda.equalsIgnoreCase("clp")) {
						if (juan.esPosibleDepositoCLP((int)monto)) {
							juan.setClp(juan.getClp() + (int)monto);
							trans = new Transaccion("Dep�sito", (int)monto, tipoMoneda);
						}
						else {
							System.out.println("Error en la transacci�n, el monto debe ser positivo");
							eleccion = 0;
							continue;
						}
					}
					else {
						System.out.println("Tipo de moneda no existe, intente otra vez");
						break;
					}
					juan.agregarTransacci�n(trans);
					n_operacion ++;
					System.out.println("Dep�sito realizado exitosamente");
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
							System.out.println("Error en la transacci�n, revisa que tengas el saldo necesario y recuerda que solo se pueden retirar 100 USD como m�ximo transacci�n.");
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
							System.out.println("Error en la transacci�n, revisa que tengas el saldo necesario y recuerda que solo se pueden retirar 200000 CLP como m�ximo por transacci�n.");
							eleccion = 0;
							continue;
						}
					}
					else {
						System.out.println("Tipo de moneda no existe, intente otra vez");
						break;
					}
					juan.agregarTransacci�n(trans);
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
					System.out.println("Se ha cerrado la sesi�n, para volver a iniciar sesi�n presione 1");
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
			System.out.println("Desea realizar otra operaci�n? (s/n)");
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
