package com.jd.qp.storyapp;

import com.jd.qp.storyapp.model.Comments;
import com.jd.qp.storyapp.model.Item;
import com.jd.qp.storyapp.service.HackerNewsConsumerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class StoryappApplicationTests {

	@Autowired
	private HackerNewsConsumerService hackerNewsConsumerService;


	@Test
	void contextLoads() {
	}

	@Test
	public void testIfStoriesAreFetched(){
		List<Item> mockStories = new ArrayList<>();
		Mockito.when(hackerNewsConsumerService.showTopStories()).thenReturn(mockStories);
		Assert.assertEquals(mockStories,hackerNewsConsumerService.showTopStories());
	}

	@Test
	public void testIfCommentsAreFetched(){
		List<Comments> mockStories = new ArrayList<>();
		Mockito.when(hackerNewsConsumerService.showCommentsOnStories("0000")).thenReturn(mockStories);
		Assert.assertEquals(mockStories,hackerNewsConsumerService.showCommentsOnStories("0000"));
	}

	@Test
	public void testIfPastAreFetched(){
		List<Item> mockStories = new ArrayList<>();
		Mockito.when(hackerNewsConsumerService.showPastStories()).thenReturn(mockStories);
		Assert.assertEquals(mockStories,hackerNewsConsumerService.showPastStories());
	}

}
