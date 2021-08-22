package com.example.springmvc.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SessionFactoryUtils {

    private SessionFactory factory;

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @PostConstruct
    private void init() {
        factory = new Configuration()
//                .addAnnotatedClass(Product.class)   //так же прописанно в xml файле
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @PreDestroy
    private void shutdown() {
        factory.close();
    }
}
