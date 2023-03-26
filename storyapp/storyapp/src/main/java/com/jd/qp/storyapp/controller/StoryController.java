package com.jd.qp.storyapp.controller;

import com.google.gson.JsonObject;
import com.jd.qp.storyapp.model.Comments;
import com.jd.qp.storyapp.model.Item;
import com.jd.qp.storyapp.service.HackerNewsConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class StoryController {


    @Autowired
    public HackerNewsConsumerService hackerNewsConsumerService;

    @GetMapping("/top-stories")
    private List<Item> getTopStories() {
        return hackerNewsConsumerService.showTopStories();
    }

    @GetMapping("/past-stories")
    private List<Item> getPastStories() {

        return hackerNewsConsumerService.showPastStories();
    }

    @GetMapping("/comments/{storyId}")
    private List<Comments> getComments(@PathVariable String storyId) {
        return hackerNewsConsumerService.showCommentsOnStories(storyId);
    }
}
