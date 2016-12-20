package com.fikky.service;

import com.fikky.models.Chapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class ChapterServiceJpaDaoImpl extends AbstractJpaDaoService implements ChapterService {
    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Chapter", Chapter.class).getResultList();
    }

    @Override
    public Chapter getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Chapter.class, id);
    }

    @Override
    public Chapter saveOrUpdate(Chapter domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Chapter savedChapter = em.merge(domainObject);
        em.getTransaction().commit();

        return savedChapter;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Chapter.class, id));
        em.getTransaction().commit();
    }
}
