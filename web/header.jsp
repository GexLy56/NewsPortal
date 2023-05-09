<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User currentUser = (User) session.getAttribute("currentUser");
%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <div>
      <a class="navbar-brand " href="/">BITLAB NEWS</a>
    </div>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <%
          if(currentUser!=null){
        %>
        <%
            if(currentUser.getRole_id()==1){
        %>
        <li class="nav-item">
          <a class="nav-link" href="/add_news">Add News</a>
        </li>
        <%
            }
        %>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <%=currentUser.getFull_name()%>
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/profile">Profile</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/logout">Logout</a></li>
          </ul>
        </li>
        <%
        } else {
        %>
        <li class="nav-item">
          <a class="nav-link" href="/login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/register">Register</a>
        </li>
        <%
          }
        %>
      </ul>
    </div>
  </div>
</nav>