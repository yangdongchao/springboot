package com.example.demo.dao;

import com.example.demo.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName BoardDao
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:52
 * @Version 1.0
 **/
public interface BoardDao extends JpaRepository<Board,Integer> {
    @Modifying
    @Query(value = "update Board as b set b.boardName=:name where b.boardId=:id")
    void updateBoardNameById(@Param("name") String password, @Param("id") int id);

    @Modifying
    @Query(value = "update Board as b set b.boardDescription=:descrip where b.boardId=:id")
    void updateBoardDescripById(@Param("descrip") String password, @Param("id") int id);

    @Modifying
    @Query(value = "update Board as b set b.topicNum=:num where b.boardId=:id")
    void updateBoardNumById(@Param("num") int num, @Param("id") int id);

    Board findByBoardId(int id);

    @Override
    Page<Board> findAll(Pageable pageable);

    Board findByBoardName(String name);
}
