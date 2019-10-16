package com.xlx.xxwd.service;

import com.xlx.xxwd.dao.UserMapper;
import com.xlx.xxwd.entity.User;
import com.xlx.xxwd.entity.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户
 *
 * @author xielx on 2019/7/2
 */
@Service
public class UserService {

  @Resource
  private UserMapper userMapper;


  /**
   * 新增或者修改
   * @param user User
   */
  public void insertOrUpdate(User user) {

    UserExample userExample = new UserExample();
    userExample.createCriteria().andOpenidEqualTo(user.getOpenid());
    List<User> userList = userMapper.selectByExample(userExample);
    if (userList != null && userList.size() != 0) {
      //修改
      user.setGmtModified(System.currentTimeMillis());
      userMapper.updateByExampleSelective(user,userExample);

    }else {
      //新增
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userMapper.insert(user);
    }

  }


  /**
   * 根据token获取User 对象
   * @param token token
   * @return User
   */
  public User getUserByToken(String token){
    UserExample userExample = new UserExample();
    userExample.createCriteria().andTokenEqualTo(token);
    List<User> userList = userMapper.selectByExample(userExample);

    if (userList != null && userList.size() != 0){
      return userList.get(0);
    }

    return null;
  }


}
