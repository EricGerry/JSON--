package org.example.org.example.servlet;

import org.example.model.Article;
import org.example.model.Result;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        Result result =new Result();
        PrintWriter pw = resp.getWriter();

        try {
            //请求JSON数据转变为java对象（反序列化）
            Article article = JSONUtil.deserialize(req.getInputStream(), Article.class);
            System.out.println("请求数据："+article);
            //TODO 需要把文章信息保存到数据库

            result.setSuccess(true);
            result.setCode("200xx");//自定义的错误码
            result.setMessage("OK");
        }catch (Exception e){
            result.setCode("500xx");
            result.setMessage("服务器出错了");
            e.printStackTrace();
        }
        pw.println(JSONUtil.serialize(result));
        pw.flush();
    }
}
