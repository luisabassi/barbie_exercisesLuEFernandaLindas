package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	private GuardaRoupa guardaRoupa;

	@BeforeEach
	void setUp() {
		guardaRoupa = new GuardaRoupa(500);
	}

	@Test
	@DisplayName("Deve aplicar desconto corretamente")
	void aplicaDesconto() {
		assertEquals(180.00, guardaRoupa.precoComDesconto(200, 10), 0.001);
	}

	@Test
	@DisplayName("Desconto acima de 100% deve lançar exceção")
	void aplicaDesconto110() {
		assertThrows(IllegalArgumentException.class, () -> guardaRoupa.precoComDesconto(200, 110));
	}

	@Test
	@DisplayName("Desconto negativo deve lançar exceção")
	void aplicaDescontoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> guardaRoupa.precoComDesconto(200, -5));
	}

	@Test
	@DisplayName("Look acima do orçamento não cabe")
	void naoCabeNoOrcamento() {
		assertFalse(guardaRoupa.cabeNoOrcamento(510));
	}

	@Test
	@DisplayName("Look com valor exato do orçamento cabe")
	void cabeNoOrcamentoValorExato() {
		assertTrue(guardaRoupa.cabeNoOrcamento(500));
	}

	@Test
	@DisplayName("Vestido acima de 200 é de gala")
	void ehGala() {
		assertTrue(guardaRoupa.ehDeGala(200.01));
	}

	@Test
	@DisplayName("Vestido de exatamente 200 não é de gala")
	void naoEhGala() {
		assertFalse(guardaRoupa.ehDeGala(200));
	}

	@Test
	@DisplayName("Compra desconta do orçamento")
	void registraCompra() {
		guardaRoupa.registrarCompra(120);
		assertEquals(380.00, guardaRoupa.getOrcamento(), 0.001);
	}
}