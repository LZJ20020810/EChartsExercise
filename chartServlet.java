package org.example.Servlet;

import org.example.Dao.chartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.example.Bean.ec;

@WebServlet("/chartServlet")
public class chartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    chartDao cd=new chartDao();

    protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String method=req.getParameter("method");
        if("search".equals(method)){
            search(req,resp);
        }


    }

    private void search(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        List<ec> list=cd.search();
        System.out.println(list);
        req.setAttribute("list",list);//成功，问题出在chart1.jsp页面里面
        req.getRequestDispatcher("chart1.jsp").forward(req,resp);
    }
}
