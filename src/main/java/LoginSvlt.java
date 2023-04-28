/*

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
public class LoginSvlt extends HttpServlet {
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("login-form.jsp");
            dispatcher.forward(request, response);
            if(action == "login"){
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPwd");

                List<User> users = userDao.selectAllUsers();

                boolean result = false;

                for (int i = 0; i < users.size(); i++) {
                    if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(pass)){
                        result = true;
                        break;
                    }

                    if(result) {
                        System.out.println("User Login Success");
                        response.sendRedirect("index.jsp");
                    }

        } } }
            catch (SQLException ex) {
                throw new ServletException(ex);
            }

}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

 */