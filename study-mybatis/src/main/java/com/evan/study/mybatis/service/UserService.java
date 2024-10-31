package com.evan.study.mybatis.service;

import com.evan.study.mybatis.entity.User;
import com.evan.study.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evan
 * @date 2022/1/21
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User sel(int id) {
        return userMapper.sel(id);
    }

    public Integer insert(User user) {
        return userMapper.insert(user);
    }


    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    SqlSession sqlSession;

    /**
     * 通过SqlSessionFactory，直接调用Mapper
     */
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User sel = mapper.sel(1);
        System.out.println(sel);
        sqlSession.close();
    }
    /**
     * 通过SqlSessionFactory，直接调用Mapper
     * 批量插入batch
     */
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setUserName("username11");
        user1.setPassWord("password11");
        user1.setRealName("realname11");
        list.add(user1);
        User user2 = new User();
        user2.setUserName("username22");
        user2.setPassWord("password22");
        user2.setRealName("realname22");
        list.add(user2);

        for (User user : list) {
            mapper.insert(user);
        }
        //一起批量提交
        sqlSession.commit();
        //或者用flushStatements，有返回值
        //List<BatchResult> batchResults = sqlSession.flushStatements();

        sqlSession.clearCache();
        sqlSession.close();
    }
    /**
     * 通过SqlSession，直接调用Mapper
     */
    public void test3() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User sel = mapper.sel(1);
        System.out.println(sel);
        sqlSession.close();
    }
}
