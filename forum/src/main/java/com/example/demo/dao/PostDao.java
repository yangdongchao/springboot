package com.example.demo.dao;

import com.example.demo.domain.Post;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface PostDao extends JpaRepository<Post,Integer> {
    List<Post> findByUserId(int userId);
    Post findByPostId(int postId);
    List<Post> findByBoardId(int boardId);

    Page<Post> findAllByTopicId(int topicId,Pageable pageable);


    List<Post> findByTopicId(int topicId);

    void deleteAllByTopicId(int topicId);


//    @Modifying
//    @Query(value = "update Post set Post.postTitle=:title where Post.postId=:id")
//    void updateTitleByPostId(@Param("title") String title,@Param("id") int id);

//
//
//    @Modifying
//    @Query(value = "update Post set Post.postText=:text where Post.postId=:id")
//    void updateTextByPostId(@Param("text") String text,@Param("id") int id);




}
