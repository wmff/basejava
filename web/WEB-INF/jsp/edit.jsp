<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <script src="js/scripts.js"></script>
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>редактирвоание резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <label>Имя:
            <input type="text" name="fullName" size="50" value="${resume.fullName}">
        </label>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <label>${type.title}
                <input type="text" name="${type.name()}" value="${resume.getContact(type)}">
            </label>
            <br/>
        </c:forEach>
        <hr/>

        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
            <h3>${sectionType.title}</h3>
            <c:set var="section" value="${resume.getSection(sectionType)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <c:choose>
                <c:when test="${sectionType.name() == 'OBJECTIVE' || sectionType.name() == 'PERSONAL'}">
                    <label>
                        <input type="text" size="100" name="${sectionType.name()}"
                               value="<%=section%>">
                    </label>
                </c:when>
                <c:when test="${sectionType.name() == 'QUALIFICATIONS' || sectionType.name() == 'ACHIEVEMENT'}">
                    <ul id="container${sectionType.name()}">
                        <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                            <li><label for="${sectionType.name()}"></label><input type="text" size="100" id="${sectionType.name()}" name="${sectionType.name()}"
                                                                                  value="${item}"></li>
                        </c:forEach>
                    </ul>
                    <a href="#" onclick="addFields('container${sectionType.name()}', '${sectionType.name()}')">add
                        line</a>
                </c:when>
                <c:when test="${sectionType.name() == 'EXPERIENCE' || sectionType.name() == 'EDUCATION'}">

                    <%-- TODO edit organizations --%>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">save</button>
        <button onclick="window.history.back()">cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
