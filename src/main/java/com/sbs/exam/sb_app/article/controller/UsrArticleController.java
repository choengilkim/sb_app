package com.sbs.exam.sb_app.article.controller;

import com.sbs.exam.sb_app.article.service.ArticleService;
import com.sbs.exam.sb_app.article.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsrArticleController {
  @Autowired
  private ArticleService articleService;

  @RequestMapping("/usr/article/doAdd")
  @ResponseBody
  public Article doAdd(String title, String body) {
    int id = articleService.writeArticle(title, body);

    Article article = articleService.getArticle(id);

    return article;
  }

  @RequestMapping("/usr/article/getArticles")
  @ResponseBody
  public List<Article> getArticles() {
    return articleService.getArticles();
  }

  @RequestMapping("/usr/article/getArticle")
  @ResponseBody
  public Object getArticle(int id) { //String과 article을 두개다 받을수있게 object사용
    Article article = articleService.getArticle(id);

    if(article == null) {
      return id + "번 게시물이 존재하지 않습니다.";
    }
    return article;
  }

  @RequestMapping("/usr/article/doDelete")
  @ResponseBody
  public String doDelete(int id) {
    Article article = articleService.getArticle(id);

    if(article == null) {
      return id + "번 게시물이 존재하지 않습니다.";
    }
    articleService.deleteArticle(id);

    return id + "번 게시물을 삭제하였습니다.";
  }

  @RequestMapping("/usr/article/doModify")
  @ResponseBody
  public String doModify(int id, String title, String body) {
    Article article = articleService.getArticle(id);

    if(article == null) {
      return id + "번 게시물이 존재하지 않습니다.";
    }
    articleService.modifyArticle(id, title, body);

    return id + "번 게시물을 수정하였습니다.";
  }

}
