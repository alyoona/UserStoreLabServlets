package com.stroganova.userstore.web.servlet;

import com.stroganova.userstore.entity.User;
import com.stroganova.userstore.service.UserService;
import com.stroganova.userstore.web.templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserServlet extends HttpServlet {

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    User currentUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVars = new HashMap<>();
        currentUser = getUpdatingUser(request);
        pageVars.put("user", currentUser);

        response.getWriter().println(PageGenerator.instance().getPage("update.html", pageVars));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));


        currentUser.setName(name);
        currentUser.setSalary(salary);

        userService.update(currentUser);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("user", currentUser);
        pageVariables.put("updatedUser", currentUser);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("update.html", pageVariables));
    }

    private User getUpdatingUser(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        long userId = Long.parseLong(pathInfo.substring(1));
        return userService.getById(userId);
    }


}
