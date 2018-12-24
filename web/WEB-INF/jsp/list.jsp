<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link href="css/style.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>

    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <table class="table">
                    <tr>
                        <th>Name</th>
                        <th>Contacts</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <jsp:useBean id="resumes" scope="request" type="java.util.List"/>
                    <c:forEach items="${resumes}" var="resume">
                        <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
                        <tr>
                            <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                            <td>${HtmlUtil.formatContact(resume.contacts, ContactType.EMAIL)}</td>
                            <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"
                                                                                      alt="edit image"/></a></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"
                                                                                        alt="delete image"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
