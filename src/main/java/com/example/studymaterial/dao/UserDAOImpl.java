package com.example.studymaterial.dao;

import com.example.studymaterial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * DAO implementation for User entity using JPA EntityManager.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
    
    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        List<User> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
    
    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }
    
    @Override
    public List<User> findByRole(String role) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.role = :role");
        query.setParameter("role", role);
        return query.getResultList();
    }
    
    @Override
    public void delete(Long userId) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.remove(user);
        }
    }
    
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
