<%@ page import="entity.News" %>
<%@ page import="entity.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NewsPage</title>
  <%@include file="title.jsp"%>
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="container mt-3">
    <div class="row mt-3">
      <div class="col-12">
        <%
          News news = (News) request.getAttribute("news");
          if (news != null) {

        %>
        <div class="p-5 mb-3" style="background-color: #557596;">

          <h3 style="color: aliceblue"><%=news.getTitle()%>
          </h3>

          <p>
            <%=news.getContent()%>
          </p>
          <p>
            At <strong><%=news.getPostDate()%></strong>
          </p>
          <%
            if (currentUser != null && currentUser.getRole_id()==1) {
          %>
          <div>
            <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editNews">
              EDIT
            </button>
            <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#deleteNews">
              Delete
            </button>
            <div class="modal fade" id="editNews" data-bs-backdrop="static" data-bs-keyboard="false"
                 tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog modal-lg">
                <div class="modal-content">
                  <form action="/edit_news" method="post">
                    <input type="hidden" name="id" value="<%=news.getId()%>">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal"
                              aria-label="Close">
                      </button>
                    </div>
                    <div class="modal-body">
                      <div class="row">
                        <div class="col-12">
                          <label>
                            TITLE :
                          </label>
                        </div>
                      </div>
                      <div class="row mt-2">
                        <div class="col-12">
                          <input type="text" class="form-control" name="title" required
                                 placeholder="Insert title:" value="<%=news.getTitle()%>">
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
                          <textarea class="form-control" name="content" placeholder="Insert content:" required rows="10">
                            <%=news.getContent()%></textarea>
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

            <div class="modal fade" id="deleteNews" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <form action="/delete_news" method="post">
                    <input type="hidden" name="id" value="<%=news.getId()%>">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5">Подтвердите удаление</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <h5 class="text-center">Вы уверены?</h5>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Нет</button>
                      <button class="btn btn-danger">Да</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <%
            }
          %>
        </div>
        <%
          if (currentUser != null) {
        %>
        <div>
          <form action="/add_comment" method="post">
            <input type="hidden" name="news_id" value="<%=news.getId()%>">
            <div class="row">
              <div class="col-12">
                <textarea class="form-control" name="comment"></textarea>
              </div>
            </div>
            <div class="row mt-3">
              <div class="col-12">
                <button class="btn btn-primary btn-sm">ADD COMMENT</button>
              </div>
            </div>
          </form>
        </div>
        <%
          }
        %>
        <div class="row">
          <div class="col-12">
            <%
              ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
              if (comments != null){
                for(Comment comment : comments){
            %>
            <div class="list-group mt-2" >
              <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                <div class="d-flex w-100 justify-content-between">
                  <h5 class="mb-1"><%=comment.getUser().getFull_name()%></h5>
                  <small class="text-body-secondary"><%=comment.getPostDate()%></small>
                </div>
                <p class="mb-1"><%=comment.getComment()%></p>
              </a>
            </div>
            <%
                }
              }
            %>
          </div>
        </div>
        <%
          }
        %>
      </div>
    </div>
  </div>
</body>
</html>
