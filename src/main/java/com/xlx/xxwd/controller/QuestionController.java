package com.xlx.xxwd.controller;

import com.xlx.xxwd.dto.ResultDTO;
import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.entity.Question;
import com.xlx.xxwd.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * question
 *
 * @author xielx on 2019/7/2
 */
@RestController
@Slf4j
public class QuestionController {


  @Resource
  private QuestionService questionService;

  /**
   * 新增问题
   *
   * @param question 微信端返回的数据
   * @return dto
   */
  @RequestMapping(value = "/api/question", method = RequestMethod.POST)
  public ResultDTO createQuestion(@RequestBody Question question) {
    try {
      questionService.createQuestion(question);
      return ResultDTO.success(null);
    } catch (Exception e) {
      log.error("QuestionController createQuestion error,question:[{}]", question, e);
      return ResultDTO.failed(ErrorCodeEnum.UNKNOWN_ERROR);
    }

  }


  /**
   * 分页获取问题数据
   *
   * @return
   */
  @RequestMapping(value = "/api/question/list", method = RequestMethod.GET)
  public ResultDTO list(@RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size) {
    try {
      List<Question> questions = questionService.list(page, size);
      log.info("问题数据:[{}]",questions);
      return ResultDTO.success(questions);
    } catch (Exception e) {
      log.error("QuestionController list error,[{}]", e);
      return ResultDTO.failed(ErrorCodeEnum.UNKNOWN_ERROR);
    }

  }
}
