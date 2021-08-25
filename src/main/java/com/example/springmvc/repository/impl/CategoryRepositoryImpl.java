package com.example.springmvc.repository.impl;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.CategoryRepository;
import com.example.springmvc.utils.SessionFactoryUtils;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private SessionFactoryUtils factory;

    @Override
    public List<Category> findCategory() {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Category> resultList = session.createQuery("" +
                    "select c from Category c", Category.class).getResultList();
            session.getTransaction().commit();
            if (resultList.size() != 0) {
                return resultList;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void createCategory(Category category) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Category> findCategoryByTitle(String title) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Query<Category> query = session.createQuery("select c from Category c where c.title=:title", Category.class);
            query.setParameter("title", title);
            Optional<Category> singleResult = Optional.of(query.getSingleResult());
            session.getTransaction().commit();
            return singleResult;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(category);
        }
    }
}