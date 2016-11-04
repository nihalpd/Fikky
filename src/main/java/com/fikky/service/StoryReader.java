package com.fikky.service;

import com.fikky.FikkyConstants;
import com.fikky.models.Story;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class StoryReader implements StoryReaderService {

    private GitHubClient client;
    private RepositoryService repositoryService;
    private ContentsService contentsService;

    @Autowired
    @Qualifier("GHClient")
    public void setClient(GitHubClient client) {
        this.client = client;
        repositoryService = new RepositoryService(client);
        contentsService = new ContentsService(client);
    }


    /**
     * Gets the stories currently associated with the user.
     * @return set of stories owned by user
     */
    @Override
    public Set<Story> getUserStories() {
        Set<Story> userStories = new HashSet<>();
        List<Repository> repositories;
        try {
            repositories = repositoryService.getRepositories();

            // Unfortunately github api does not have an efficient way to filter
            // so I am doing it by repo name in java.
            repositories.removeIf(r -> !r.getName().contains("fikky"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for (Repository repository : repositories) {
                userStories.add(extractStoryFromRepo(repository));
        }
        return userStories;
    }

    @Override
    public Story getStorybyID(int storyID){
        return null;
    }

    private Story extractStoryFromRepo(Repository repository) {
        List<RepositoryContents> repoContents = null;
        try {
            repoContents = contentsService.getContents(repository, FikkyConstants.STORY_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Story extractedStory  = new Story(repository.getId());
        // TODO: SET OWNER?
        if (repoContents.size() == 1) {
            extractedStory.setContents(pullStoryContentFromRepoContents(repoContents));
            return extractedStory;
        }
        return null;
    }

    private String pullStoryContentFromRepoContents(List<RepositoryContents> repositoryContents) {
        for (RepositoryContents content : repositoryContents) {
            if (FikkyConstants.STORY_PATH.equalsIgnoreCase(content.getName())) {
                return parseContent(content.getContent());
            }
        }
        return null;
    }

    private String parseContent(String encodedContent) {
        String[] contentLines = encodedContent.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : contentLines) {
            sb.append(new String(Base64.getDecoder().decode(line)));
            sb.append("\n");
        }
        return sb.toString();
    }

}
