package com.example.tpjavarecipes;

import com.example.tpjavarecipes.bean.Recipe;
import com.example.tpjavarecipes.dao.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;



public class UserServlet extends HttpServlet {

    private UserDao userDao;


    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();


        //Index
        //Register
        //Update User
        try {
            switch (action) {
                case "/create-user":
                    showCreateUserForm(request, response);
                    break;
                case "/addUserDB":
                    insertUser(request, response);
                    break;
                case "/edit-user":
                    showEditUserForm(request, response);
                    break;
                case "/updateUserDB":
                    updateUser(request, response);
                    break;
                default:
                    break;
            }


        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showCreateUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("create-user-form.jsp");
        dispatcher.forward(request, response);
    }


    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String Date_of_Creation = request.getParameter("dateTime");
        String pictureUrl = request.getParameter(" pictureUrl");
        Recipe newRecipe = new Recipe(name, description, Date_of_Creation, pictureUrl);
// recipeDao.insertRecipe(newRecipe);
        response.sendRedirect("list");
    }


    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
//Recipe existingRecipe = recipeDao.selectRecipe(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//request.setAttribute("recipe", existingRecipe);
        dispatcher.forward(request, response);


    }


    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String Date_of_Creation = request.getParameter("Date_of_Creation");
        String pictureUrl = request.getParameter(" pictureUrl");
        Recipe newRecipe = new Recipe(name, description, Date_of_Creation, pictureUrl);
//recipeDao.insertRecipe(newRecipe);
        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}