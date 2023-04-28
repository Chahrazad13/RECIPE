package com.example.tpjavarecipes.dao;

import com.example.tpjavarecipes.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {


    private static final String INSERT_USER_SQL = "INSERT INTO USERS (email, firstname, lastname , password, pictureUrl) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from USERS where id = ?";
    private static final String SELECT_ALL_USERS = "select * from USERS";
    private static final String UPDATE_USER_SQL = "UPDATE USERS set firstname = ?, lastname= ?, password = ?, pictureUrl=? WHERE id = ?;";

    public UserDao() {
    }


    public void insertUser(User user)  {
        System.out.println(INSERT_USER_SQL);

        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPictureUrl());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception Insert User "+e.getMessage());
        }
    }

    public User selectUser(int id) {
        User user = null;

        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String pictureUrl = rs.getString("pictureUrl");
                user = new User(firstname, lastname , email, password, pictureUrl);
                user.setUserId(id);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception Select User "+e.getMessage());
        }
        return user;
    }

    public List<User> selectAllUsers() {


        List<User> users = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {


            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String pictureUrl = rs.getString("pictureUrl");
                users.add(new User(id, firstname, lastname , email, password, pictureUrl));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception Select ALl Users "+e.getMessage());
        }
        return users;
    }

    public boolean updateUser(User user)  {
        boolean rowUpdated = false;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {

            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPictureUrl());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println("SQL Exception Update User "+e.getMessage());
        }
        return rowUpdated;
    }

}