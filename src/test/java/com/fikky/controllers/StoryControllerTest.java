package com.fikky.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fikky.models.Story;
import com.fikky.service.StoryService;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StoryControllerTest {

  @Mock
  private StoryService storyService;

  @InjectMocks
  private StoryController storyController;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(storyController).build();
  }

  @Test
  public void testList() throws Exception{

    List<Story> stories = new ArrayList<>();
    stories.add(new Story());
    stories.add(new Story());

    //specific Mockito interaction, tell stub to return list of stories
    when(storyService.listAll()).thenReturn((List) stories); //need to strip generics to keep Mockito happy.

    mockMvc.perform(get("/story/list"))
        .andExpect(status().isOk())
        .andExpect(view().name("story/list"))
        .andExpect(model().attribute("stories", hasSize(2)));
  }

  @Test
  public void testShow() throws Exception{
    Integer id = 1;

    //Tell Mockito stub to return new story for ID 1
    when(storyService.getById(id)).thenReturn(new Story());

    mockMvc.perform(get("/story/show/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("story/show"))
        .andExpect(model().attribute("story", instanceOf(Story.class)));
  }

  @Test
  public void testEdit() throws Exception{
    Integer id = 1;

    //Tell Mockito stub to return new story for ID 1
    when(storyService.getById(id)).thenReturn(new Story());

    mockMvc.perform(get("/story/edit/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("story/storyform"))
        .andExpect(model().attribute("story", instanceOf(Story.class)));
  }

  @Test
  public void testNewStory() throws Exception {

    //should not call service
    verifyZeroInteractions(storyService);

    mockMvc.perform(get("/story/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("story/storyform"))
        .andExpect(model().attribute("story", instanceOf(Story.class)));
  }

  @Test
  public void testSaveOrUpdate() throws Exception {
    Integer id = 1;

    String name = "David's story";
    String description = "Test Description";

    Story returnStory = new Story();
    returnStory.setId(id);
    returnStory.setName(name);
    returnStory.setDescription(description);

    when(storyService.saveOrUpdate(Matchers.<Story>any())).thenReturn(returnStory);

    mockMvc.perform(post("/story")
        .param("id", "1")
        .param("name", name)
        .param("description", description))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/story/show/1"))
        .andExpect(model().attribute("story", instanceOf(Story.class)))
        .andExpect(model().attribute("story", hasProperty("id", is(id))))
        .andExpect(model().attribute("story", hasProperty("name", is(name))))
        .andExpect(model().attribute("story", hasProperty("description", is(description))));

    //verify properties of bound object
    ArgumentCaptor<Story> boundStory = ArgumentCaptor.forClass(Story.class);
    verify(storyService).saveOrUpdate(boundStory.capture());

    assertEquals(id, boundStory.getValue().getId());
    assertEquals(name, boundStory.getValue().getName());
    assertEquals(description, boundStory.getValue().getDescription());
  }

  @Test
  public void testDelete() throws Exception{
    Integer id = 1;

    mockMvc.perform(get("/story/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/story/list"));

    verify(storyService, times(1)).delete(id);
  }
}
