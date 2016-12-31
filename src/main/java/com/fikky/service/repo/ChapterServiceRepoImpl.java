package com.fikky.service.repo;

import com.fikky.models.Chapter;
import com.fikky.repositories.ChapterRepository;
import com.fikky.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("repo")
public class ChapterServiceRepoImpl implements ChapterService {

    private ChapterRepository chapterRepository;

    @Autowired
    public void setChapterRepository(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Override
    public List<?> listAll() {
        List<Chapter> chapters = new ArrayList<>();
        chapterRepository.findAll().forEach(chapters::add);
        return chapters;
    }

    @Override
    public Chapter getById(Integer id) {
        return chapterRepository.findOne(id);
    }

    @Override
    public Chapter saveOrUpdate(Chapter domainObject) {
        return chapterRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        chapterRepository.delete(id);
    }
}
