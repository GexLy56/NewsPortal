<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddNews</title>
  <%@include file="title.jsp"%>
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="container mt-5">
    <div class="row">
      <div class="col-6 mx-auto">
        <form action="/add_news" method="post">
          <div class="row">
            <div class="col-12">
              <label>
                TITLE :
              </label>
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <input type="text" class="form-control" name="title" required placeholder="Insert title:">
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <label>
                CATEGORY :
              </label>
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <select name="category" class="form-select" aria-label="Default select example">
                <option value="polytics">Polytics</option>
                <option value="economics">Economics</option>
                <option value="health">Health</option>
                <option value="sport">Sport</option>
                <option value="internet">Internet</option>
              </select>
            </div>
          </div>
          <div class="row mt-3">
            <div class="col-12">
              <label>
                CONTENT :
              </label>
            </div>
          </div>
          <div class="row mt-2">
            <div class="col-12">
              <textarea class="form-control" name="content" placeholder="Insert content:" required rows="10"></textarea>
            </div>
          </div>
          <div class="row mt-3">
            <div class="col-12">
              <button class="btn btn-success">ADD POST</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
