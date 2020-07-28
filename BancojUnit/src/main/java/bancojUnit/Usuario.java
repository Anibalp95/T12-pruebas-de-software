package bancojUnit;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private int clp;
	private int retiro_maximo_clp = 200000;
	private float usd;
	private float retiro_maximo_usd = 100;
	private ArrayList<Transaccion> historial;
	
	public Usuario(String nombre, int clp, float usd) {
		super();
		this.nombre = nombre;
		this.clp = clp;
		this.usd = usd;
		this.historial = new ArrayList<Transaccion>();
	}

	public int getRetiro_maximo_clp() {
		return retiro_maximo_clp;
	}
	
	public float getRetiro_maximo_usd() {
		return retiro_maximo_usd;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getClp() {
		return clp;
	}

	public void setClp(int clp) {
		this.clp = clp;
	}

	public float getUsd() {
		return usd;
	}

	public void setUsd(float usd) {
		this.usd = usd;
	}
	
	public ArrayList<Transaccion> getTransaccionList() {
		return historial;
	}
	
	public void agregarTransacción(Transaccion tran) {
		this.historial.add(tran);
	}
	
	public boolean esPosibleRetiroCLP(int monto) {
		if (monto > this.retiro_maximo_clp || monto > this.clp || monto <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean esPosibleRetiroUSD(float monto) {
		if (monto > this.retiro_maximo_usd || monto > this.usd || monto <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean esPosibleDepositoCLP(int monto) {
		if (monto <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean esPosibleDepositoUSD(float monto) {
		if (monto <= 0) {
			return false;
		}
		return true;
	}
}
