package bancojUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UsuarioTest {
	
	private Usuario humano;
	private static Logger logger;

	@BeforeAll
	protected static void init() {
		InputStream stream = UsuarioTest.class.getClassLoader().
	            getResourceAsStream("logging.properties");
	    try {
	        LogManager.getLogManager().readConfiguration(stream);
	        logger = Logger.getLogger(UsuarioTest.class.getName());

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");
		Handler fileHandler = null;
		try {
			fileHandler = new FileHandler("./src/test/resources/Usuario/" + dtf.format(LocalDateTime.now()) + ".log");
			logger.addHandler(fileHandler);
			fileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);
		} catch (IOException exception) {
			System.out.println(exception);
		}
		logger.info("Testing para funciones de la clase Usuario");
	}

	@BeforeEach
	protected void setUp() throws Exception {
		humano = new Usuario("Jose", 1000000, 1000);
	}

	@Test
	public void testEmptyGetTransaccionList() throws Exception {
		// Given
		String testName = "testEmptyGetTransaccionList, input: None";
		// When

		// Then
		try {
			assertTrue(humano.getTransaccionList().isEmpty());
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@Test
	public void testNotEmptyGetTransaccionList() throws Exception {
		// Given
		String testName = "testNotEmptyGetTransaccionList, input: Transaccion(\"deposito\", 100, \"usd\") \n";
		Transaccion trans = new Transaccion("deposito", 100, "usd");
		// When
		humano.agregarTransacción(trans);
		// Then
		try {
			assertNotNull(humano.getTransaccionList());
			logger.info(testName + "Resultado: Éxito\n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 200000, 1 })
	public void testValorLimiteTrueEsPosibleRetiroCLP(int monto) throws Exception {
		// Given
		String testName = "testValorLimiteTrueEsPosibleRetiroCLP, input:" + monto + "\n";
		int input = monto;
		// When
		boolean resultado = humano.esPosibleRetiroCLP(input);
		// Then
		try {
			assertTrue(resultado);
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 200001, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE })
	public void testValorLimiteFalseEsPosibleRetiroCLP(int monto) throws Exception {
		// Given
		String testName = "testValorLimiteFalseEsPosibleRetiroCLP , input:" + monto + "\n";
		// When
		boolean resultado = humano.esPosibleRetiroCLP(monto);
		// Then
		try {
			assertFalse(resultado);
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@ParameterizedTest
	@ValueSource(floats = {100 + Float.MIN_NORMAL, 100, 1 })
	public void testValorLimiteTrueEsPosibleRetiroUSD(float monto) throws Exception {
		// Given
		float montoRedondeado = Math.round(monto * 100) / 100; // 2 decimales
		String testName = "testValorLimiteTrueEsPosibleRetiroUSD , input:" + monto + "\n";// When
		boolean resultado = humano.esPosibleRetiroUSD(montoRedondeado);
		// Then
		try {
			assertTrue(resultado);
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@ParameterizedTest
	@ValueSource(floats = { 0, -1, Float.MAX_VALUE, Float.MIN_VALUE })
	public void testValorLimiteFalseEsPosibleRetiroUSD(float monto) throws Exception {
		// Given
		float montoRedondeado = Math.round(monto * 100) / 100; // 2 decimales
		String testName = "testValorLimiteFalseEsPosibleRetiroUSD , input:" + monto + "\n";// When
		// When
		boolean resultado = humano.esPosibleRetiroUSD(montoRedondeado);
		// Then
		try {
			assertFalse(resultado);
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1})
	public void testValorLimiteFalseEsPosibleDepositoCLP(int monto) throws Exception {
		// Given
		String testName = "testValorLimiteFalseEsPosibleDepositoCLP , input:" + monto + "\n";// When
		// When
		boolean resultado = humano.esPosibleDepositoCLP(monto);
		// Then
		try {
			assertFalse(resultado);
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

	@ParameterizedTest
	@ValueSource(floats = {0 + Float.MIN_NORMAL, -1})
	public void testValorLimiteFalseEsPosibleDepositoUSD(float monto) throws Exception {
		// Given
		float montoRedondeado = Math.round(monto * 100) / 100; // 2 decimales
		String testName = "testValorLimiteFalseEsPosibleDepositoUSD , input:" + monto + "\n";// When
		
		// When
		boolean resultado = humano.esPosibleDepositoUSD(montoRedondeado);
		// Then
		try {
			assertFalse(resultado);
			logger.info(testName + "Resultado: Éxito \n");
		} catch (AssertionError error) {
			logger.severe(testName + "Resultado: Error (" + error.getMessage() + ") \n");
			throw new Exception("Assert failed");
		}
	}

}
