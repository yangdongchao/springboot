package com.example.demo.service.imp;

import com.example.demo.dao.BoardDao;
import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BoardServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:53
 * @Version 1.0
 **/
@Service
@Transactional
public class BoardServiceImp implements BoardService {
    private BoardDao boardDao;

    @Override
    public Board findBoardById(int id) {
        return boardDao.findByBoardId(id);
    }

    /**
     * 查询出所有的版块
     * @return
     */
    @Override
    public List<Board> findAllBoard() {
        return boardDao.findAll();
    }

    /**
     * 根据ID删除版块
     * @param id
     */
    @Override
    public void deleteBoardById(int id) {
        boardDao.deleteById(id);
        boardDao.flush();
    }

    /**
     * 修改版块的相关信息
     * @param newDescrip
     * @param id
     */
    @Override
    public void updateBoardDescrip(String newDescrip, int id) {
        boardDao.updateBoardDescripById(newDescrip,id);
        boardDao.flush();
    }

    @Override
    public void updateBoardName(String newName, int id) {
        boardDao.updateBoardNameById(newName,id);
        boardDao.flush();
    }

    /**
     * 修改版块的话题数目
     * @param num
     * @param id
     */
    @Override
    public void updateBoardTopicNum(int num, int id) {
        boardDao.updateBoardNumById(num,id);
        boardDao.flush();
    }

    @Override
    public Page<Board> getBoardPage(int pageNum, int pageSize) {
        PageRequest pageable = new PageRequest(pageNum-1,pageSize,Sort.Direction.ASC,"topicNum");
        return boardDao.findAll(pageable);
    }

    @Override
    public Board getBoardByName(String name) {
        return boardDao.findByBoardName(name);
    }

    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
}
