package com.example.labboneskjakarta.repository;


import com.example.labboneskjakarta.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class GameRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Game> findAll() {
        var query = entityManager.createQuery("SELECT g from Game g");
        return (List<Game>) query.getResultList();

    }

    public Optional<Game> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Game.class, id));
    }

    public void insertGame(Game game) {
        entityManager.persist(game);
    }

    public void deleteGame(Long id) {
        var game = findOne(id);
        game.ifPresent((g) -> entityManager.remove(g));
    }

    public List<Game> findAllByName(String name) {
        var query = entityManager.createQuery("SELECT g from Game g where g.name like :name ");
        query.setParameter("name", name);
        return (List<Game>) query.getResultList();

    }
    public Game update(Long id, Game game){
        var entity = entityManager.find(Game.class, id);
        entity.setName(game.getName());
        entity.setShortening(game.getShortening());
        entityManager.persist(entity);
        return entity;
    }

}
