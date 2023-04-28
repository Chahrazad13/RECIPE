package com.example.tpjavarecipes.dao;


import com.example.tpjavarecipes.bean.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class RecipeDao {

        private static final String INSERT_RECIPES_SQL = "INSERT INTO RECIPES (name, description, dateCreation, pictureUrl) VALUES (?, ?, ?, ?);";
        private static final String SELECT_RECIPE_BY_ID = "select * from RECIPES where id =?";
        private static final String SELECT_ALL_RECIPE = "select * from RECIPES";
        private static final String UPDATE_RECIPE_SQL = "update RECIPES set name = ?, description= ?, dateCreation=?, pictureUrl=?  where id = ?;";

        public RecipeDao() {

        }

        public void insertRecipe(Recipe recipe)  {
            System.out.println(INSERT_RECIPES_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = Database.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECIPES_SQL);
                preparedStatement.setString(1, recipe.getName());
                preparedStatement.setString(2, recipe.getDescription());
                preparedStatement.setString(3, String.valueOf(recipe.getDatetime()));
                preparedStatement.setString(4, recipe.getPictureUrl());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("SQL Exception Insert Recipe "+e.getMessage());
            }
        }

        public Recipe selectRecipe(int id) {
            Recipe recipe = null;

            try (Connection connection = Database.getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIPE_BY_ID);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);

                ResultSet rs = preparedStatement.executeQuery();


                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    LocalDateTime dateCreation = rs.getTimestamp("dateCreation").toLocalDateTime();
                    String pictureUrl = rs.getString("pictureUrl");
                    recipe = new Recipe(id, name, description, dateCreation.toString(), pictureUrl);
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception Select User "+e.getMessage());
            }
            return recipe;
        }

        public List<Recipe> selectAllRecipe() {


            List<Recipe> recipes = new ArrayList<>();

            try (Connection connection = Database.getConnection();


                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECIPE);) {
                System.out.println(preparedStatement);


                ResultSet rs = preparedStatement.executeQuery();


                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    LocalDateTime dateCreation = rs.getTimestamp("dateCreation").toLocalDateTime();
                    String pictureUrl = rs.getString("pictureUrl");
                    recipes.add(new Recipe(id, name, description, dateCreation.toString(), pictureUrl));
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception Select All User "+e.getMessage());
            }
            return recipes;
        }

        public boolean updateRecipe(Recipe recipe) throws SQLException {
            boolean rowUpdated;
            try (Connection connection = Database.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_SQL);
                System.out.println("updated Recipe:"+statement);
                statement.setString(1, recipe.getName());
                statement.setString(2, recipe.getDescription());
                statement.setString(3, String.valueOf(recipe.getDatetime()));
                statement.setString(4, recipe.getPictureUrl());

                rowUpdated = statement.executeUpdate() > 0;
            }
            return rowUpdated;
        }

    }