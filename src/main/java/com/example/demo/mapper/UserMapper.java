package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

/**
    int login(String u_name, String u_password);
 */
    /**
     * 根据id查询用户信息
     * @param u_id
     * @return
     */
    User getUserInfo(String u_id);
    /**
     * 新增用户
     * @param user
     * @return
     */
    int save (User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update (User user);

    int updateur (User user);

    /**
     * 根据id删除
     * @param user
     * @return
     */
    int deleteById (User user);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> selectAll ();

    List<User> select_u_r ();

    List<User>  getUserRole(String u_id);

}