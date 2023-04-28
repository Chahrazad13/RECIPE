package com.example.tpjavarecipes;

import com.example.tpjavarecipes.bean.Recipe;
import com.example.tpjavarecipes.dao.RecipeDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class RecipeServlet extends HttpServlet {


    private RecipeDao recipeDao;

    public void init() {
        recipeDao = new RecipeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        //Create Recipe
        //Edit Recipe
        //Update Recipe
        //List Recipe
        try {
            switch (action) {
                case "/create-recipe":
                    showNewForm(request, response);
                    break;
                case "/addRecipeDB":
                    insertRecipe(request, response);
                    break;
                case "/edit-recipe":
                    showEditForm(request, response);
                    break;
                case "/updateRecipeDB":
                    updateRecipe(request, response);
                    break;
                case "/view-recipes":
                    listRecipe(request, response);
                    break;
                default:
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listRecipe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Recipe> listRecipe = recipeDao.selectAllRecipe();
        request.setAttribute("listRecipe", listRecipe);
        RequestDispatcher dispatcher = request.getRequestDispatcher("recipe-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("recipe-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Recipe existingRecipe = recipeDao.selectRecipe(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("recipe", existingRecipe);
        dispatcher.forward(request, response);

    }

    private void insertRecipe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String dateTime = request.getParameter("dateTime");
        String pictureUrl = request.getParameter(" pictureUrl");
        Recipe newRecipe = new Recipe(name, description, dateTime, pictureUrl);
        recipeDao.insertRecipe(newRecipe);
        response.sendRedirect("view-recipes");
    }

    private void updateRecipe(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String Date_of_Creation = request.getParameter("dateTime");
        String pictureUrl = request.getParameter(" pictureUrl");

        Recipe book = new Recipe(id, name, description, Date_of_Creation, pictureUrl);
        recipeDao.updateRecipe(book);
        response.sendRedirect("list");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}