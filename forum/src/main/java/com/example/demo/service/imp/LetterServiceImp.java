package com.example.demo.service.imp;

import com.example.demo.dao.LetterDao;
import com.example.demo.domain.Letter;
import com.example.demo.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName LetterServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/4 13:49
 * @Version 1.0
 **/
@Service
@Transactional
public class LetterServiceImp implements LetterService {
    private LetterDao letterDao;

    @Autowired
    public void setLetterDao(LetterDao letterDao) {
        this.letterDao = letterDao;
    }

    @Override
    public void save(Letter letter) {
        letterDao.save(letter);
    }

    @Override
    public Page<Letter> getLettersByReceiverId(int page, int pageSize, int receiverId) {
        Pageable pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Direction.DESC,"createTime"));
        return letterDao.findAllByReceiverId(receiverId,pageable);
    }
}
