package com.example.tpjavarecipes;

import com.example.tpjavarecipes.bean.User;
import com.example.tpjavarecipes.dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;


    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();


        //Login
        //Logout Button ==> To Index
        try{
            loginForm(request, response);
            if(action == "login"){
                verifyUserCredentials(request, response);
            }else if(action == "create-recipe"){
                showNewForm(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void loginForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("login-form.jsp");
        dispatcher.forward(request, response);
    }


    private void verifyUserCredentials(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("txtEmail");
        String pass = request.getParameter("txtPwd");

        List<User> users = userDao.selectAllUsers();

        boolean result = false;

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(pass)){
                result = true;
                break;
            }
        }

        if(result) {
            System.out.println("User Login Success");
            response.sendRedirect("create-recipe");
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("recipe-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
