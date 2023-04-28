<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>recipe Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
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
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${recipe == null}">
            <form action="addRecipeDB" method="post">
            </c:if>

                <caption>
                    <h2>
                        Add New Recipe
                    </h2>
                </caption>

                <c:if test="${recipe != null}">
                    <input type="hidden" name="id" value="<c:out value='${recipe.id}' />"/>
                </c:if>

                <fieldset class="form-group">
                    <label>Recipe Name</label>
                    <input type="text"
                           value="<c:out value='${recipe.name}' />" class="form-control"
                           name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>recipe Description</label>
                    <input type="text"
                           value="<c:out value='${recipe.description}' />"
                           class="form-control"
                           name="description">
                </fieldset>

                <fieldset class="form-group">
                    <label>Recipe Date</label>
                    <input type="datetime-local"
                           value="<c:out value='${recipe.dateTime}' />"
                           class="form-control"
                           name="dateTime">
                </fieldset>


                <fieldset class="form-group">
                    <label>Recipe Picture Url</label>
                    <input type="text"
                           value="<c:out value='${recipe.pictureUrl}' />"
                           class="form-control"
                           name="PictureUrl">
                </fieldset>


                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>