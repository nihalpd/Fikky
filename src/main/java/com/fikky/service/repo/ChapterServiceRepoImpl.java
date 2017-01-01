package com.fikky.service.repo;

import com.fikky.commands.ChapterForm;
import com.fikky.converters.ChapterFormToChapter;
import com.fikky.models.Chapter;
import com.fikky.models.ChapterLink;
import com.fikky.repositories.ChapterRepository;
import com.fikky.service.ChapterLinkService;
import com.fikky.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("repo")
public class ChapterServiceRepoImpl implements ChapterService {

    private ChapterRepository chapterRepository;
    private ChapterFormToChapter chapterFormToChapter;
    private ChapterLinkService chapterLinkService;

    @Autowired
    public void setChapterRepository(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    @Autowired
    public void setChapterFormToChapter(ChapterFormToChapter chapterFormToChapter) {
        this.chapterFormToChapter = chapterFormToChapter;
    }

    @Autowired
    public void setChapterLinkService(ChapterLinkService chapterLinkService) {
        this.chapterLinkService = chapterLinkService;
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

    @Override
    public Chapter saveOrUpdateChapterForm(ChapterForm chapterForm) {
        Chapter newChapter = chapterFormToChapter.convert(chapterForm);
        return saveOrUpdate(newChapter);
    }

    @Override
    public List<Chapter> getChildren(Integer id) {
        List<Chapter> children = new ArrayList<>();
        List<ChapterLink> childLinks = chapterLinkService.getChildLinks(id);
        children.addAll(childLinks.stream().map(link -> getById(link.getChildId())).collect(Collectors.toList()));
        return children;
    }

    @Override
    public List<Chapter> getParents(Integer id) {
        List<Chapter> parents = new ArrayList<>();
        List<ChapterLink> parentLinks = chapterLinkService.getParentLinks(id);
        parents.addAll(parentLinks.stream().map(link -> getById(link.getParentId())).collect(Collectors.toList()));
        return parents;
    }
}
