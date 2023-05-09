<%@ page import="entity.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="title.jsp"%>
    <title>BITLAB NEWS</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="container mt-3">
        <div class="row mt-3">
            <div class="col-12">
                <%
                    ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                    if(news!=null){
                        for(News n : news){
                %>
                <div class="p-5 mb-3" style="background-color: #557596;">
                    <a style="color: aliceblue" href="/news_details?id=<%=n.getId()%>">
                        <h3><%=n.getTitle()%></h3>
                    </a>
                    <p><%=n.getContent()%></p>
                    <p>
                        Category: <%=n.getNewsCategory().getName()%>
                    </p>
                    <p>
                        Posted at <strong><%=n.getPostDate()%></strong>
                    </p>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
