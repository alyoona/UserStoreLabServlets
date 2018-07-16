package com.servletlabusers.web.servlet;

import com.servletlabusers.entity.User;
import com.servletlabusers.service.UserService;
import com.servletlabusers.web.templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteUserServlet extends HttpServlet {
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getPathInfo();
        long id = Long.parseLong(userId.substring(1));

        User user = userService.getById(id);

        userService.delete(user);

        /*the same code as in UsersServlet.doGet(resp,req) method, is it correct?
        what should be in the resp and req parameters, If use next:
        "
        UsersServlet usersServlet = new UsersServlet();
        usersServlet.doGet(resp,req);
        "

        */

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("users", userService.getAll());

        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("users.html", parameters);

        response.getWriter().write(page);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
