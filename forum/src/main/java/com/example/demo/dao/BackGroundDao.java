package com.example.demo.dao;

import com.example.demo.domain.BackGround;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @ClassName BackGroundDao
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/8 11:06
 * @Version 1.0
 **/
public interface BackGroundDao extends JpaRepository<BackGround,Integer> {


}
