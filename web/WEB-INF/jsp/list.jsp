<%@ page import="ru.javawebinar.basejava.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table border="0">
        <tr>
            <th>Name</th>
            <th>Contacts</th>
            <th></th>
            <th></th>
        </tr>
        <%--<%--%>
        <%--for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {--%>
        <%--%>--%>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td>${resume.getContactHtml(ContactType.EMAIL)}</td>
                <td><a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
        <%--<%--%>
        <!--}-->
        <!--%>-->
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
