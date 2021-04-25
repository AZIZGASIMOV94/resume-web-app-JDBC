
<%@page import="com.mycompany.resumeapp.dao.inter.UserDaoInter"%>
<%@page import="com.mycompany.bean.User"%>
<%@page import="com.mycompany.resumeapp.Context"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="assets/js/users.js"></script>
        <script src="https://kit.fontawesome.com/2b4e22258b.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
     <body>

     <%--   <%
           User u = (User) session.getAttribute("loggedInUser");
        %>
        <%="welcome "+u.getName()%>
--%>
        <%
            List<User> userList = (List<User>) request.getAttribute("userList");
        %>


        <div  class="container col-8">
        <div class="col-4">
            <form action="logout" method="GET">
                <button type="submit" name="logout" value="Logout">
                    Log out
                </button>
            </form>

            <form action="users" method="GET">
                <div class="form-group ">
                    <label placehoder="Enter name" for="name">name:</label>
                    <input type="text" class="form-control" name="name" value=""/>
                    <br>

                    <label placeholder="Enter your Password" for="surname">surname:</label>
                    <input type="text" class="form-control" name="surname" value=""/>
                </div>
                <button  style="margin: 5px" type="submit"  class="btn btn-info" name="search" value="Search"> Search </button>
            </form>
        </div>
        <div>
            <table class="table table-sm">
                <thead>
                    <tr>
                        <th scope="col">name </th>
                        <th scope="col">surname</th>
                        <th scope="col">nationality</th>
                        <th scope="col">operations</th>
                    </tr>
                </thead>
                <tbody>
                <%
                   for(User user : userList){
                %>
                    <tr>
                        <td><%=user.getName()%></td>
                        <td><%=user.getSurname()%></td>
                        <td><%=user.getNationality().getName()==null? "N/A" : user.getNationality().getName()%></td>
                        <td width="5px">
                                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                                <input type="hidden" name="action" value="delete"/>
                                <button  class="btn btn-danger" type="submit" value="delete"
                                         data-toggle="modal" data-target="#exampleModal"
                                onclick="setIdForDelete(<%=user.getId()%>)">
                                    <i class="far fa-trash-alt"></i>
                                </button>
                        </td>
                        <td>

                            <form action="userdetail" method="GET">
                                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                                <input type="hidden" name="action" value="update"/>//<--! !-->
                                <button class="btn btn-info" type="submit" value="update">
                                    <i class="far fa-edit" ></i>
                                </button>
                            </form>

                        </td>
                        <td>
                            <button  class="btn btn-light"><i class="fas fa-info"></i></button>
                        </td>

                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                       Are you sure?
                    </div>
                    <div class="modal-footer">
                        <form action="userdetail" method="POST">
                            <input type="hidden" name="id" value="" id="idForDelete">
                            <input type="hidden" name="action" value="delete"/>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-danger" value="Delete"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
