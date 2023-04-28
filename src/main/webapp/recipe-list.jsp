
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Recipes List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
    <body>


        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: lightpink">
                <div>
                    <a href="/" class="navbar-brand"> Recipe Management </a>
                </div>

                
                <li class="nav-item">
                    <a href="/view-recipes" style="text-decoration: white; color: white;">   View Recipes </a>
                </li>

                <li class="nav-item">
                    <a href="/create-recipe" style="text-decoration: white; color: white;">  Create Recipe </a>
                </li>

            </nav>
        </header>
        <br> <br>
        <h1 style="text-align: center">Recipes List</h1>
        <br /><br />
        <table id="example" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Picture Url</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="recipes" items="${listRecipe}">
                    <tr>
                        <td> <c:out value="${recipes.id}" /> </td>
                        <td> <c:out value="${recipes.name}" /> </td>
                        <td> <c:out value="${recipes.description}" /> </td>
                        <td> <c:out value="${recipes.pictureUrl}" /> </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </body>
</html>
