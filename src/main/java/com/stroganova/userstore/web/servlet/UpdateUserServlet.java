package com.stroganova.userstore.web.servlet;

import com.stroganova.userstore.entity.User;
import com.stroganova.userstore.service.UserService;
import com.stroganova.userstore.web.pages.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = new HashMap<>();
        User currentUser = getUpdatingUser(request);
        pageVariables.put("user", currentUser);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("update.html", pageVariables));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));

        User currentUser = new User();
        currentUser.setId(id);
        currentUser.setName(name);
        currentUser.setSalary(salary);
        currentUser.setDateOfBirth(dateOfBirth);

        userService.update(currentUser);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("user", currentUser);
        pageVariables.put("updatedUser", true);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("update.html", pageVariables));
    }

    private User getUpdatingUser(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        long userId = Long.parseLong(pathInfo.substring(1));
        return userService.getById(userId);
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
