package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.entities.Customer;
import app.entities.Order;
import app.entities.Product;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(app.Application.class);

	public static void main(String[] args) {

		Product p1 = new Product(123_001L, "Libro1", "Books", 50.00);
		Product p2 = new Product(123_002L, "Libro2", "Books", 120.00);
		Product p3 = new Product(123_003L, "Gioco1", "Baby", 35.00);
		Product p4 = new Product(123_004L, "Gioco2", "Baby", 50.00);
		Product p5 = new Product(123_005L, "Console", "Boys", 600.00);
		Product p6 = new Product(123_006L, "Gioco console", "Boys", 79.00);
		Product p7 = new Product(123_007L, "Libro3", "Books", 150.00);
		Product p8 = new Product(123_008L, "Scarpettine", "Baby", 60.00);
		Product p9 = new Product(123_009L, "Palla da basket", "Boys", 45.00);
		Product p10 = new Product(123_010L, "Triciclo", "Baby", 39.00);

		List<Product> allProducts = new ArrayList<Product>();
		allProducts.add(p1);
		allProducts.add(p2);
		allProducts.add(p3);
		allProducts.add(p4);
		allProducts.add(p5);
		allProducts.add(p6);
		allProducts.add(p7);
		allProducts.add(p8);
		allProducts.add(p9);
		allProducts.add(p10);

		Customer c1 = new Customer(111_001L, "Giovanni", 1);
		Customer c2 = new Customer(111_002L, "Aldo", 2);
		Customer c3 = new Customer(111_003L, "Giacomo", 1);
		Customer c4 = new Customer(111_004L, "Giuseppe", 2);
		Customer c5 = new Customer(111_005L, "Martina", 2);

		List<Product> order1Products = new ArrayList<Product>();
		order1Products.add(p1);
		order1Products.add(p3);

		List<Product> order2Products = new ArrayList<Product>();
		order2Products.add(p2);
		order2Products.add(p5);
		order2Products.add(p4);

		Order o1 = new Order(222_001L, "ordine ricevuto", LocalDate.now().plusDays(1), LocalDate.now().plusWeeks(1),
				order1Products, c4);

		Order o2 = new Order(222_002L, "in consegna", LocalDate.now().minusWeeks(2), LocalDate.now(), order2Products,
				c1);

		List<Order> allOrders = new ArrayList<Order>();
		allOrders.add(o1);
		allOrders.add(o2);

		List<Product> booksPricedOverOneHundred = getListBooksProductPriceOverOneHundred(allProducts);
		logger.info("LIBRI CATEGORIA BOOKS CON PREZZO > 100:");
		for (Product p : booksPricedOverOneHundred) {
			logger.info("CATEGORIA: " + " " + p.getCategory() + " NOME: " + p.getName() + " PREZZO: " + p.getPrice());
		}

		List<Order> orderWithBabyProducts = getListOrderWithBabyProducts(allOrders);
		logger.info("ORDINI CON PRODOTTI CATEGORIA BABY:");
		for (Order o : orderWithBabyProducts) {
			logger.info("ID_ORDINE: " + o.getId() + " STATO_ORDINE: " + o.getStatus() + " NOME_CLIENTE: "
					+ o.getCustomer().getName());
		}

		List<Product> boysProducts = getListBoysProducts(allProducts);

		logger.info("PRODOTTI CATEGORIA BOYS A CUI APPLICO SCONTO 10%:");
		for (Product p : boysProducts) {
			logger.info("ID_PRODOTTO: " + p.getId() + " CATEGORIA_PRODOTTO: " + p.getCategory() + " PREZZO_SCONTATO: "
					+ p.getPrice());
		}

		List<Product> productsOrderByDate = getListProductOrderByDate(allOrders);

		logger.info("LISTA PRODOTTI ORDINATI DA CLIENTI TIERS = 2 COMPRESI TRA DUE DATE:");
		for (Product p : productsOrderByDate) {
			logger.info("ID_PRODOTTO: " + p.getId() + " CATEGORIA_PRODOTTO: " + p.getCategory() + " PREZZO_PRODOTTO: "
					+ p.getPrice());
		}

	}

	private static List<Product> getListBooksProductPriceOverOneHundred(List<Product> allProducts) {
		/*
		 * List<Product> booksPricedOverOneHundred = allProducts.stream()
		 * .filter(product -> product.getCategory().equals("Books") &&
		 * product.getPrice() > 100.00).toList();
		 */

		List<Product> booksPricedOverOneHundred = new ArrayList<Product>();

		Iterator<Product> i = allProducts.iterator();

		Product p = null;

		while (i.hasNext()) {

			p = i.next();

			if (p.getCategory().equals("Books") && p.getPrice() > 100.00) {
				booksPricedOverOneHundred.add(p);
			}
		}

		return booksPricedOverOneHundred;
	}

	private static List<Order> getListOrderWithBabyProducts(List<Order> allOrders) {
		/*
		 * List<Order> orderWithBabyProducts = allOrders.stream() .filter(order ->
		 * order.getProducts().stream().anyMatch(product ->
		 * product.getCategory().equals("Baby"))) .toList();
		 */
		List<Order> orderWithBabyProducts = new ArrayList<Order>();

		Iterator<Order> i = allOrders.iterator();

		Order o = null;

		while (i.hasNext()) {

			o = i.next();

			for (Product p : o.getProducts()) {
				if (p.getCategory().equals("Baby")) {
					orderWithBabyProducts.add(o);
				}
			}
		}

		return orderWithBabyProducts;
	}

	private static List<Product> getListBoysProducts(List<Product> allProducts) {
		/*
		 * List<Product> boysProducts = allProducts.stream().filter(product ->
		 * product.getCategory().equals("Boys")) .map(product -> { double sconto =
		 * product.getPrice() * 0.9; product.setPrice(product.getPrice() - sconto);
		 * return product; }).toList();
		 */

		List<Product> boysProducts = new ArrayList<Product>();

		Iterator<Product> i = allProducts.iterator();

		Product p = null;

		while (i.hasNext()) {

			p = i.next();

			if (p.getCategory().equals("Boys")) {
				p.setPrice(p.getPrice() * 0.9);
				boysProducts.add(p);
			}
		}

		return boysProducts;
	}

	private static List<Product> getListProductOrderByDate(List<Order> allOrders) {
		/*
		 * List<Product> productsOrderByDate = allOrders.stream() .filter(order ->
		 * order.getOrderDate().isAfter(LocalDate.now()) &&
		 * order.getDeliveryDate().isBefore(LocalDate.now().plusMonths(1)))
		 * .filter(order -> order.getCustomer().getTier() == 2).flatMap(order ->
		 * order.getProducts().stream()) .toList();
		 */
		List<Product> productsOrderByDate = new ArrayList<Product>();
		Iterator<Order> i = allOrders.iterator();
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusMonths(1);
		Order o = null;
		while (i.hasNext()) {
			o = i.next();
			if (o.getOrderDate().isAfter(startDate) && o.getDeliveryDate().isBefore(endDate)
					&& o.getCustomer().getTier() == 2) {

				List<Product> prodottiOrdine = o.getProducts();

				for (Product p : prodottiOrdine) {
					productsOrderByDate.add(p);
				}
			}
		}
		return productsOrderByDate;
	}

}
