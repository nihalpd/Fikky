package com.fikky.service;

import com.fikky.FikkyConstants;
import com.fikky.models.Story;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StoryReader implements StoryReaderService {



    private GitHubClient client;
    private RepositoryService repositoryService;
    private ContentsService contentsService;


    public void setClient(GitHubClient client) {
        this.client = client;
    }


    /**
     * Gets the stories currently associated with the user.
     * @return set of stories owned by user
     */
    @Override
    public Set<Story> getUserStories() {

        RepositoryService repositoryService = new RepositoryService(client);

        Set<Story> userStories = new HashSet<>();

        try {
            // TODO: NEED TO FILTER OUT NON-STORY REPOS
            userStories.addAll(repositoryService.getRepositories().stream().map(this::extractStoryFromRepo).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userStories;
    }

    @Override
    public Story getStorybyID(int storyID){
        return null;
    }

    private Story extractStoryFromRepo(Repository repo) {

        ContentsService contentsService = new ContentsService(client);
        List<RepositoryContents> repoContents = null;
        try {
            repoContents = contentsService.getContents(repo, FikkyConstants.STORY_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Story extractedStory  = new Story(repo.getId());
        // TODO: SET OWNER?

        if (repoContents.size() != 1) {
            // TODO: THROW ERROR
        }
        else {
            //TODO: WHEN STORIES BECOME MORE COMPLICATED WE CAN MAKE OUR OWN STORYCONTENT OBJECTS
            extractedStory.setContents(repoContents.get(0).getContent());
        }

        return extractedStory;


    }

}
