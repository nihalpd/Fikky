package com.fikky.service.repo;

import com.fikky.models.Chapter;
import com.fikky.models.ChapterLink;
import com.fikky.repositories.ChapterLinkRepository;
import com.fikky.service.ChapterLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("repo")
public class ChapterLinkServiceRepoImpl implements ChapterLinkService {

    private ChapterLinkRepository chapterLinkRepository;

    @Autowired
    private void setChapterLinkRepository(ChapterLinkRepository chapterLinkRepository) {
        this.chapterLinkRepository = chapterLinkRepository;
    }

    @Override
    public List<ChapterLink> listAll() {
        List<ChapterLink> chapterLinks = new ArrayList<>();
        chapterLinkRepository.findAll().forEach(chapterLinks::add);
        return chapterLinks;
    }

    @Override
    public List<ChapterLink> getChildren(Integer parentId) {
        return chapterLinkRepository.findByParentId(parentId);
    }

    @Override
    public List<ChapterLink> getParents(Integer childId) {
        return chapterLinkRepository.findByChildId(childId);
    }

    @Override
    public ChapterLink addLink(Chapter parent, Chapter child) {
        ChapterLink newLink = new ChapterLink(parent.getId(), child.getId());
        return chapterLinkRepository.save(newLink);
    }

    @Override
    public void delete(Integer id) {
        chapterLinkRepository.delete(id);
    }
}
