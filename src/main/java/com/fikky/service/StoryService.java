package com.fikky.service;

import com.fikky.FikkyConstants;
import com.fikky.models.Story;
import com.fikky.models.Writer;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StoryService {


    /**
     * Gets the stories currently associated with the user.
     * @param writer
     * @return set of stories owned by user
     * @throws IOException
     */
    // TODO: WHAT OBJECT(S) SHOULD BE INPUT INTO THIS METHOD IF ANY?
    // TODO: 1. WRITER DOESNT MAKE SENSE
    // TODO: 2. HOW SHOULD WE HANDLE GitHubClient ACROSS FILES?
    public Set<Story> getUserStories(GitHubClient client, Writer writer ) throws IOException {
        RepositoryService repositoryService = new RepositoryService(client);

        Set<Story> userStories = new HashSet<>();

        for (Repository repo : repositoryService.getRepositories()) {
            userStories.add(extractStoryFromRepo(client, repo));
        }
        return userStories;
    }

    private Story extractStoryFromRepo(GitHubClient client, Repository repo) throws IOException {

        ContentsService contentsService = new ContentsService(client);
        List<RepositoryContents> repoContents = contentsService.getContents(repo, FikkyConstants.STORY_PATH);
        Story extractedStory  = new Story(repo.getId());
        // TODO: SET OWNER?

        if (repoContents.size() != 1) {
            // TODO: THROW ERROR
        }
        else {
            extractedStory.setContents(repoContents.get(0).getContent());
        }

        return extractedStory;


    }

}
