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
            <a href="menu.jsp" class="list-group-item list-group-item-action bg-light">Dashboard</a>
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
            <div>
                <form action="SearchItem" method="post">
                    <div class="input-group-prepend mt-4 ">
                        <input id="inputSearch1" type="text" class="form-control " placeholder="Search"
                               style="max-width: 300px;" name="search1" required>
                        <input id="inputSearch2" type="text" class="form-control ml-1" placeholder="Search"
                               style="max-width: 300px;display: none" name="search2">
                        <select onchange="showRangeInputs()" id="selectType" class="custom-select ml-1" name="searchBy"
                                style="max-width: 150px;"
                                required>
                            <option value="" selected hidden disabled>Search by</option>
                            <option value="All">All</option>
                            <option value="Category">Category</option>
                            <option value="Country">Country</option>
                            <option value="Name">Name</option>
                            <option value="Date">Date</option>
                            <option value="Price">Price</option>
                        </select>
                        <select id="SelectSort" class="custom-select ml-1" name="sort"
                                style="max-width: 165px;"
                                required>
                            <option value="" selected hidden disabled>Sort by</option>
                            <option value="asc">Ascending order</option>
                            <option value="desc">Descending order</option>

                        </select>
                        <button class="btn btn-secondary ml-1" type="submit">
                            <i class="fa fa-search"></i>
                        </button>

                    </div>
                </form>
                <div class="table-responsive mt-4">
                    <a class="btn" href="SearchItem"></a>
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
                            <c:when test="${not empty Items}">
                                <c:forEach var="value" items="${Items}">
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
                                            <a class="DetailCar" href="BuyItem?itemId=${value.id}"><i
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
<script type="text/javascript">
    function showRangeInputs() {

        switch (document.getElementById("selectType").value) {
            case"Price":
                document.getElementById('inputSearch1').style.display = 'block';
                document.getElementById('inputSearch2').style.display = 'block';
                document.getElementById('inputSearch2').required = true;
                document.getElementById('inputSearch2').type = 'number';
                document.getElementById('inputSearch1').type = 'number';
                document.getElementById('inputSearch2').placeholder = 'max price';
                document.getElementById('inputSearch1').placeholder = 'min price';
                break;
            case "Date":
                document.getElementById('inputSearch1').style.display = 'block';
                document.getElementById('inputSearch2').required = false;
                document.getElementById('inputSearch2').style.display = 'none';
                document.getElementById('inputSearch1').type = 'date';
                document.getElementById('inputSearch1').placeholder = 'search';
                break;
            case "All":
                document.getElementById('inputSearch2').required = false;
                document.getElementById('inputSearch2').style.display = 'none';
                document.getElementById('inputSearch1').required = false;
                document.getElementById('inputSearch1').style.display = 'none';
                break;
            default:
                document.getElementById('inputSearch2').required = false;
                document.getElementById('inputSearch1').type = 'text';
                document.getElementById('inputSearch2').style.display = 'none';
                document.getElementById('inputSearch1').style.display = 'block';
                document.getElementById('inputSearch1').placeholder = 'Search';

        }

    }
</script>

</body>
</html>

