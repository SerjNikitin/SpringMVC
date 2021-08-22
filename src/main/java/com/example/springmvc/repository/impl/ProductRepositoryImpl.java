package com.example.springmvc.repository.impl;

import com.example.springmvc.utils.SessionFactoryUtils;
import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private SessionFactoryUtils factory;

    @Override
    public List<Product> findProducts() {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Product> resultList = session.createQuery(
                    "select p from Product p", Product.class).getResultList();
            session.getTransaction().commit();
            if (resultList.size() != 0) {
                return resultList;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(product);
        }
//            if (product != (null)) {
//                Optional<Product> optionalProduct = Optional.of(product);
//                session.getTransaction().commit();
//                return optionalProduct;
//            }
//        }
//        return Optional.empty();
    }


    @Override
    public void saveProduct(Product product) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateProductById(Integer id, String title, Integer price) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != (null)) {
                product.setTitle(title);
                product.setPrice(price);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProductById(Integer id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != (null)) {
                session.delete(product);
            }
            session.getTransaction().commit();
        }
    }
}