<%
    if (session.getAttribute("userSession") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="css/menu.css" rel='stylesheet' type='text/css'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


</head>
<body>

<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Mybay</div>
        <div class="list-group list-group-flush">
            <a href="SearchItem" class="list-group-item list-group-item-action bg-light">Dashboard</a>
            <a href="addItem.jsp" class="list-group-item list-group-item-action bg-light">Add Item</a>
            <a href="MyItems" class="list-group-item list-group-item-action bg-light">My Items</a>

        </div>
    </div>
    <!-- /#sidebar-wrapper -->
    <!-- Page Content -->
    <div id="page-content-wrapper">

        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Hello, ${userSession.name}
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="editAccount.jsp">Preferences</a>
                            <a class="dropdown-item" href="account.jsp">Account</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="Logout">Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>


        <div class="container-fluid">
            <h3 class="mt-2"> My Items</h3>
            <div>
                <div class="table-responsive mt-4">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Origin Country</th>
                            <th>Price</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:choose>
                            <c:when test="${not empty myItems}">
                                <c:forEach var="value" items="${myItems}">
                                    <tr>
                                        <td>
                                            <c:out value="${value.name}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${value.category}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${value.originCountry}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${value.price}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${value.date}"></c:out>
                                        </td>
                                        <td>
                                            <a class="DetailCar" href="EditItem?itemId=${value.id}"> <i
                                                    class="fa fa-edit mr-1"></i></a>
                                            <a class="DetailCar" href="DetailItem?itemId=${value.id}"><i
                                                    class="fa fa-eye"></i></a>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <td colspan="6" class="text-center">No items to Display</td>
                            </c:otherwise>
                        </c:choose>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>
