<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
  <%@include file="title.jsp"%>
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="container mt-3">
    <div class="row">
      <div class="col-12 mx-auto">
        <h1>Hello, <%=currentUser!=null?currentUser.getFull_name():""%></h1>
      </div>
    </div>
    <div>
      <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
              data-bs-target="#editProfile">
        EDIT PROFILE
      </button>
      <div class="modal fade" id="editProfile" data-bs-backdrop="static" data-bs-keyboard="false"
           tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <form action="/profile" method="post">
              <input type="hidden" name="id" value="<%=currentUser.getId()%>">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit Profile</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="row">
                  <div class="col-12">
                    <label>
                      FULL NAME :
                    </label>
                  </div>
                </div>
                <div class="row mt-2">
                  <div class="col-12">
                    <input type="text" class="form-control" name="full_name" required
                           placeholder="<%=currentUser.getFull_name()%>">
                  </div>
                </div>
                <div class="row mt-3">
                  <div class="col-12">
                    <label>
                      PASSWORD :
                    </label>
                  </div>
                </div>
                <div class="row mt-2">
                  <div class="col-12">
                    <input type="password" class="form-control" name="password">
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                </button>
                <button class="btn btn-success">Update</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
