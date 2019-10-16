package com.xlx.xxwd.service;

import com.xlx.xxwd.dao.QuestionMapper;
import com.xlx.xxwd.entity.Question;
import com.xlx.xxwd.entity.QuestionExample;
import com.xlx.xxwd.session.SessionUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * question
 *
 * @author xielx on 2019/7/2
 */
@Service
public class QuestionService {

   @Resource
   private QuestionMapper questionMapper;


  /**
   * 新增问题
   * @param question qu
   */
  public void createQuestion(Question question){
    question.setGmtCreate(System.currentTimeMillis());
    question.setGmtModified(question.getGmtCreate());
    question.setUserId(SessionUtil.getUser().getId());
    questionMapper.insertSelective(question);
  }

  /**
   * 分页获取问题对象
   * @param page 当前页
   * @param size 页面容量
   * @return list
   */
  public List<Question> list(Integer page,Integer size){
    QuestionExample questionExample = new QuestionExample();
    questionExample.createCriteria().andStatusEqualTo(new Byte("1"));
    questionExample.setOrderByClause("gmt_create desc");
    int offSet = (page - 1) * size;
    return questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offSet,size));
  }


}
