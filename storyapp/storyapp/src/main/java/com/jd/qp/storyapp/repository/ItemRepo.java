package com.jd.qp.storyapp.repository;

import com.jd.qp.storyapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepo extends JpaRepository<Item,String> {
}
