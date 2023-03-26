package com.jd.qp.storyapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jd.qp.storyapp.model.Comments;
import com.jd.qp.storyapp.model.Item;
import com.jd.qp.storyapp.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@EnableScheduling
public class HackerNewsConsumerService {

    public static boolean ttlElapsed = true;
    @Autowired
    public ItemRepo itemRepo;

    @Autowired
    public CacheManager cacheManager;

    @Cacheable("stories")
    public List<Item> showTopStories(){
        String url = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        result = result.replaceAll("[\\[\\]\\(\\)]", "");
        String[] stories = result.split(",");
        List<Item> items = new ArrayList<Item>();
        int count = 0;
        for(String storyId: stories){
            if(count < 10){
                String itemUrl = "https://hacker-news.firebaseio.com/v0/item/"+storyId+".json?print=pretty";
                String story =  restTemplate.getForObject(itemUrl, String.class);
                JsonObject storyObj = new JsonParser().parse(story).getAsJsonObject();
                    Item item = new Item(storyObj.has("id")?storyObj.get("id").getAsString():"",
                            storyObj.has("title")?storyObj.get("title").getAsString():"",
                            storyObj.has("url")?storyObj.get("url").getAsString():"",
                            storyObj.has("score")?storyObj.get("score").getAsInt():0,
                            storyObj.has("time")?storyObj.get("time").getAsLong():0L,
                            storyObj.has("by")?storyObj.get("by").getAsString():"");
                    itemRepo.save(item);
                    items.add(item);
                    count++;
            }else
                break;
        }
        Collections.sort(items);
        return items;
    }
    @CacheEvict(value = "stories", allEntries = true)
    @Scheduled(fixedRate = 900000)
    public void emptyStoriesCache() {
        System.out.println("emptying stories cache");
        cacheManager.getCache("stories").clear();
    }

    public List<Item> showPastStories(){
        return itemRepo.findAll();
    }

    public List<Comments> showCommentsOnStories(String storyId){
        String url = "https://hacker-news.firebaseio.com/v0/item/"+storyId+".json?print=pretty";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        JsonObject storyobj = new JsonParser().parse(result).getAsJsonObject();
        JsonArray commentIds = storyobj.get("kids").getAsJsonArray();
        JsonArray comments = new JsonArray();
        commentIds.forEach(commentId->{
            String commentUrl = "https://hacker-news.firebaseio.com/v0/item/"+commentId+".json?print=pretty";
            comments.add(new JsonParser().parse(restTemplate.getForObject(commentUrl, String.class)));
        });
        List<Comments> commentsList = StreamSupport.stream(comments.spliterator(),false)
                .map(comment->new Comments(comment.getAsJsonObject().get("by").getAsString(),comment.getAsJsonObject().get("text").getAsString(),comment.getAsJsonObject().get("kids").getAsJsonArray()))
                .collect(Collectors.toList());
        Collections.sort(commentsList);
        return getTopTenComments(commentsList);
    }

    private List<Comments> getTopTenComments(List<Comments> commentsList){
        List<Comments> comments = new ArrayList<>();
        if(commentsList.size() <= 10)
            return commentsList;
        for(int i = 0; i < 10; i++  ){
            comments.add(commentsList.get(i));
        }
        return comments;
    }
}
