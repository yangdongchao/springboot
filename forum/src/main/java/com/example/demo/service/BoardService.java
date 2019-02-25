package com.example.demo.service;

import com.example.demo.domain.Board;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName BoardService
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/10 15:14
 * @Version 1.0
 **/
public interface BoardService {
    void deleteBoardById(int id);

    List<Board> findAllBoard();

    Board findBoardById(int id);

    void updateBoardName(String newName,int id);

    void updateBoardDescrip(String newDescrip,int id);

    void updateBoardTopicNum(int num,int id);

    Page<Board> getBoardPage(int pageNum,int pageSize);
    Board getBoardByName(String name);
}
