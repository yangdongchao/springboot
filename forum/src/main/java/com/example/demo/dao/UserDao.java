package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:26
 * @Version 1.0
 **/
public interface UserDao extends JpaRepository<User,Long> {
    User  findByUserName(String userName);
    User findByUserId(int userId);

    @Override
    Page<User> findAll(Pageable pageable);

    Page<User> findByUserNameLike(String userName,Pageable pageable);
    List<User> findByUserNameLike(String userName);
    User findByEmail(String emil);

    @Modifying
    @Query(value = "update User as u set u.password=:password where u.userId=:id")
    void updatePasswordById(@Param("password") String password, @Param("id") int id);


    @Modifying
    @Query(value = "update User as u set u.user_type=:utype where u.userId=:id")
    void updateUserTypeById(@Param("utype")int type,@Param("id") int id);


    @Modifying
    @Query(value = "update User as u set u.grade=:grade where u.userId=:id")
    void updateUserGradeById(@Param("grade") String grade,@Param("id") int id);


    @Modifying
    @Query(value = "update User as u set u.academy=:academy where u.userId=:id")
    void updateUserAcademyById(String academy,int id);


    @Modifying
    @Query(value = "update User as u set u.locked=:lock where u.userId=:id")
    void updateUserLockedById(@Param("lock") int sta,@Param("id") int id);

    @Modifying
    @Query(value = "update User as u set u.credits = :credits where u.userId=:id")
    void updateUserCreditById(@Param("credits") int credit,@Param("id") int id);

    @Modifying
    @Query(value = "update User as u set u.photo = :photo where u.userId=:id")
    void updateUserPhoto(@Param("photo") String  photo,@Param("id") int id);

    @Modifying
    @Query(value = "update User as u set u.signature=:signature where u.userId=:id")
    void updateUserSignature(@Param("signature") String  signature,@Param("id") int id);


    @Modifying
    @Query(value = "update User as u set u.userName=:name where u.userId=:id")
    void updateUserName(@Param("name") String  name,@Param("id") int id);

    @Modifying
    @Query(value = "update User as u set u.validcode=:validCode where u.userId=:id")
    void updateUserValidCode(@Param("validCode") String  name,@Param("id") int id);

    @Modifying
    @Query(value = "update User as u set u.backPicture = :backPicture where u.userId=:id")
    void updateUserBg(@Param("backPicture") String backPicture,@Param("id") int id);

}
