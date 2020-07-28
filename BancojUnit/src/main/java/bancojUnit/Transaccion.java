package bancojUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
	private LocalDateTime hora;
	private String tipoTransaccion;
	private String tipoMoneda;
	private float monto;	
	
	public Transaccion(String tipoTransaccion, float monto, String tipoMoneda) {
		super();
		this.hora = LocalDateTime.now();
		this.tipoTransaccion = tipoTransaccion;
		this.monto = monto;
		this.tipoMoneda = tipoMoneda;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}
	
	public void mostrarTransaccion() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		System.out.println(dtf.format(this.hora));
		System.out.println(this.tipoTransaccion);
		if (this.tipoMoneda.equals("usd")) {
			System.out.println(String.format("%s %.2f", this.tipoMoneda, this.monto));
		}
		else {
			System.out.println(String.format("%s %d", this.tipoMoneda, (int)this.monto));
		}
		System.out.println();
	}
}
