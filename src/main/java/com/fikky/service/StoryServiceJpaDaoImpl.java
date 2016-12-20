package com.fikky.service;

import com.fikky.models.Story;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class StoryServiceJpaDaoImpl extends AbstractJpaDaoService implements StoryService {
    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Story", Story.class).getResultList();
    }

    @Override
    public Story getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Story.class, id);
    }

    @Override
    public Story saveOrUpdate(Story domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Story savedStory = em.merge(domainObject);
        em.getTransaction().commit();

        return savedStory;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Story.class, id));
        em.getTransaction().commit();
    }
}
