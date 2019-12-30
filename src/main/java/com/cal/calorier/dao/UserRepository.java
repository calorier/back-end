package com.cal.calorier.dao;
import com.cal.calorier.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository
    extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
  List<User> findByUsername(String username);

  List<User> findAll();
  // 修改用户密码
  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(
      value = "update user " + "set password=:password " + "where username=:username ",
      nativeQuery = true)
  int updateUser(@Param("username") String username, @Param("password") String password);
  // 修改用户信息
  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(
      value =
          "update user "
              + "set email=:email ,"
              + "avatar=:avatar ,"
              + "phone=:phone "
              + "where username=:username ",
      nativeQuery = true)
  int updateUserInfo(
      @Param("username") String username,
      @Param("email") String email,
      @Param("avatar") String avatar,
      @Param("phone") String phone);
}

