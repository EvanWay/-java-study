package com.evan.study.mybatis.mapper;

import com.evan.study.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Evan
 * @date 2022/1/21
 */
@Repository
@Mapper
public interface UserMapper {

    User sel(int id);

    Integer insert(User user);
}
