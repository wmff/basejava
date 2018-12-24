<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>редактирвоание резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="uuid" value="${resume.uuid}">
                    <div class="form-group">
                        <label for="fullName">Имя:</label>
                        <input class="form-control" type="text" id="fullName" name="fullName" size="50"
                               value="${resume.fullName}">
                    </div>
                    <h3>Контакты:</h3>
                    <c:forEach var="type" items="<%=ContactType.values()%>">
                        <label for="${type.name()}">${type.title}</label>
                        <input class="form-control" type="text" id="${type.name()}" name="${type.name()}"
                               value="${resume.getContact(type)}">
                    </c:forEach>
                    <hr/>

                    <div class="form-group">
                        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
                            <h3>${sectionType.title}</h3>
                            <c:set var="section" value="${resume.getSection(sectionType)}"/>
                            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
                            <c:choose>
                                <c:when test="${sectionType.name() == 'OBJECTIVE' || sectionType.name() == 'PERSONAL'}">
                                    <label for="${sectionType.name()}"></label>
                                    <input class="form-control" type="text" size="100" id="${sectionType.name()}"
                                           name="${sectionType.name()}"
                                           value="<%=section%>">
                                    <hr/>
                                </c:when>
                                <c:when test="${sectionType.name() == 'QUALIFICATIONS' || sectionType.name() == 'ACHIEVEMENT'}">
                                    <label for="${sectionType.name()}"></label>
                                    <textarea class="form-control" rows="10"
                                              id="${sectionType.name()}"
                                              name="${sectionType.name()}"><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                                    <hr/>
                                </c:when>
                                <c:when test="${sectionType.name() == 'EXPERIENCE' || sectionType.name() == 'EDUCATION'}">
                                    <% List<Organization> organizations = ((OrganizationSection) section).getOrganizations(); %>
                                    <c:forEach var="organization"
                                               items="<%=organizations%>"
                                               varStatus="counter">
                                        <label for="${sectionType}"><b>Наименование организации</b></label>
                                        <input class="form-control" type="text" id="${sectionType}"
                                               name="${sectionType}"
                                               value="${organization.name.name}"/>
                                        <label for="${sectionType}URL">URL</label>
                                        <input class="form-control" type="text" id="${sectionType}URL" name="${sectionType}URL"
                                               value="${organization.name.url}"/>
                                        <c:forEach var="position" items="${organization.positions}">
                                            <jsp:useBean id="position"
                                                         type="ru.javawebinar.basejava.model.Organization.Position"/>
                                            <label for="${sectionType}${counter.index}dateBegin">Дата начала</label>
                                            <input class="form-control" type="text"
                                                   id="${sectionType}${counter.index}dateBegin"
                                                   name="${sectionType}${counter.index}dateBegin" size=10
                                                   value="<%=DateUtil.format(position.getDateBegin())%>"
                                                   placeholder="MM/yyyy">
                                            <label for="${sectionType}${counter.index}dateEnd">Дата окончания</label>
                                            <input class="form-control" type="text" id="${sectionType}${counter.index}dateEnd"
                                                   name="${sectionType}${counter.index}dateEnd"
                                                   value="<%=DateUtil.format(position.getDateEnd())%>"
                                                   placeholder="MM/yyyy">

                                            <label for="${sectionType}${counter.index}title">Должность</label>
                                            <input class="form-control" type="text" id="${sectionType}${counter.index}title"
                                                   name="${sectionType}${counter.index}title" value="${position.title}">
                                            <label for="${sectionType}${counter.index}description">Описание</label>
                                            <input class="form-control" type="text"
                                                   id="${sectionType}${counter.index}description"
                                                   name="${sectionType}${counter.index}description"
                                                   value="${position.description}">
                                        </c:forEach>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <button type="submit" class="btn btn-primary mb-2">Сохранить</button>
                    <a href="#" type="cancel" onclick="window.history.back()" class="btn btn-primary mb-2">Отмена</a>
                </form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
