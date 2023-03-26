package com.jd.qp.storyapp;

import com.google.gson.JsonObject;
import com.jd.qp.storyapp.model.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.*;

@SpringBootApplication
public class StoryappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoryappApplication.class, args);
	}

}
