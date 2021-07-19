<%--
  Created by IntelliJ IDEA.
  User: gui
  Date: 30/10/19
  Time: 00:50
  To change this template use File | Settings | File Templates.
--%>
<%
    if (session.getAttribute("userSession") == null) {
        response.sendRedirect("login.jsp");
    }
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Account</title>
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
            <h3 class="mt-2">Add New Item </h3>
            <form class="mt-4" action="AddItem" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" id="name" class="form-control" placeholder="Name" name="name" required>
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <input type="text" id="inputCategory" class="form-control" placeholder="Category"
                           name="category" required>
                </div>

                <div class="form-group">
                    <label>Origin Country</label>
                    <input type="text" id="country" class="form-control" placeholder="Origin country"
                           name="originCountry"
                           required>
                </div>

                <div class="form-group">
                    <label>Price</label>
                    <input type="text" id="price" class="form-control" placeholder="Price (Euros)"
                           name="price"
                           required>
                </div>


                <div class="form-group">
                    <label>Photograph</label>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="inputGroupFile01"
                               aria-describedby="inputGroupFileAddon01" name="photograph">
                        <label class="custom-file-label" for="inputGroupFile01">Choose Photograph</label>
                    </div>
                </div>
                <button class="btn  btn-primary mt-4" type="submit">add item</button>

            </form>

        </div>
    </div>

</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function () {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>

</body>
</html>
