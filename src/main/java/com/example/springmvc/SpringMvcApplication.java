package com.example.springmvc;

import com.example.springmvc.domain.Product;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringMvcApplication {
    public static SessionFactory factory;

    private static void init() {
        factory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    private static void shutdown() {
        factory.close();
    }

    private static void createProduct() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product("привет", 212);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    private static void getProductById() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1);
            System.err.println(product);
            session.getTransaction().commit();
        }
    }

    private static void updateProductById() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1);
            product.setPrice(100);
            session.getTransaction().commit();
        }
    }

    private static void deleteProductById() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    private static void getCollectionProduct() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> resultList = session.createQuery(
                    "select p from Product p where p.price<9000", Product.class).getResultList();
            System.out.println(resultList);
            session.getTransaction().commit();
        }
    }


    public static void main(String[] args) {
//        Flyway flyway = Flyway.configure().dataSource(
//                "jdbc:postgresql://localhost:5435/spring", "postgres", "postgrespass").load();
//        flyway.migrate();

//        SpringApplication.run(SpringMvcApplication.class, args);
        try {
            init();
            createProduct();
//          getCollectionProduct();
        } finally {
            shutdown();
        }
    }
}