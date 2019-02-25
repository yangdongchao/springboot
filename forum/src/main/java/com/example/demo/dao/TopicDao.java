package com.example.demo.dao;

import com.example.demo.domain.Topic;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TopicDao extends JpaRepository<Topic,Integer> {
    /**
     * 更新话题标题
     * @param title
     * @param id
     */
    @Modifying
    @Query(value = "update Topic as T set T.topicTitle=:title where T.topicId=:id")
    void updateTitleById(@Param("title") String title,@Param("id") int id);

    /**
     * 更新是否是热门话题
     * @param hot
     * @param id
     */
    @Modifying
    @Query(value = "update Topic as T set T.hot=:hot where T.topicId=:id")
    void updateHotById(@Param("hot") int hot,@Param("id") int id);


    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update Topic as T set T.backGroundId=:pictureId where T.topicId=:id")
    void updateBackGroundById(@Param("pictureId") int pictureId,@Param("id") int id);

    @Modifying
    @Query(value = "update Topic as T set  T.praise=:newValue where T.topicId=:id")
    void updatePraiseByTopicId(@Param("newValue") int newValue, @Param("id") int topicId);

    @Modifying
    @Query(value = "update Topic as T set T.decription=:newDescription where T.topicId=:id")
    void updateDescriptionByTopicId(@Param("newDescription") String newDescription,@Param("id") int topicId);


    @Modifying
    @Query(value = "update Topic as T set T.content =:newContent where T.topicId=:id")
    void updateContentByTopicId(@Param("newContent") String  newContent,@Param("id") int topicId);

    @Modifying
    @Query(value = "update Topic as T set T.boardId=:newId where  T.topicId=:id")
    void updateBoardIdByTopicId(@Param("newId") int newId,@Param("id") int topicId);

    @Modifying
    @Query(value = "update Topic as T set T.topicReplies=:reply where  T.topicId=:id")
    void updateTopicRepliesByTopicId(@Param("reply") int reply,@Param("id") int topicId);


    @Modifying
    @Query(value = "update Topic as T set T.label=:label where  T.topicId=:id")
    void updateTopicLabelByTopicId(@Param("label") String label,@Param("id") int topicId);


    @Modifying
    @Query(value = "update Topic as T set T.topicViews=:view where  T.topicId=:id")
    void updateTopicReviewByTopicId(@Param("view") int view,@Param("id") int topicId);
    Topic findByTopicId(int id);

    List<Topic> findByBoardId(int id);

    List<Topic> findAllByUserId(int id);

    Page<Topic> findAllByBoardId(int id, Pageable pageable);

    Page<Topic> findAll(Pageable pageable);

    Page<Topic> findByTopicTitleLike(String topicTitle,Pageable pageable);

    void deleteByTopicId(int topicId);

    Page<Topic> findAllByUserId(int userId,Pageable pageable);
}
