<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.model.Organization" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Or" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <jsp:include page="fragments/head.jsp"/>
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <h1 class="display-3">${resume.fullName} <a href="resume?uuid=${resume.uuid}&action=edit"><img
                        src="img/edit.png"
                        alt="edit"></a></h1>
                <c:forEach var="contactEntry" items="${HtmlUtil.formatContacts(resume.contacts)}">
                    <jsp:useBean id="contactEntry"
                                 type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                    ${contactEntry.value}<br/>
                </c:forEach>
                <hr/>
                <c:forEach var="sectionEntry" items="${resume.sections}">
                    <jsp:useBean id="sectionEntry"
                                 type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
                    <c:choose>
                        <c:when test="${sectionEntry.key.name() == 'OBJECTIVE' || sectionEntry.key.name() == 'PERSONAL'}">
                            <% String section = ((TextSection) sectionEntry.getValue()).getContent(); %>
                            <c:if test="<%=!HtmlUtil.isEmpty(section)%>">
                                <h1 class="display-5">${sectionEntry.key.title}</h1>
                                <%=section%>
                            </c:if>
                        </c:when>
                        <c:when test="${sectionEntry.key.name() == 'QUALIFICATIONS' || sectionEntry.key.name() == 'ACHIEVEMENT'}">
                            <% java.util.List<String> items = ((ListSection) sectionEntry.getValue()).getItems();
                            %>
                            <c:if test="<%=items.size() != 0%>">
                                <h1 class="display-5">${sectionEntry.key.title}</h1>
                                <ul>
                                    <c:forEach var="item" items="<%=items%>">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </c:when>
                        <c:when test="${sectionEntry.key.name() == 'EXPERIENCE' || sectionEntry.key.name() == 'EDUCATION'}">
                            <% List<Organization> organizations = ((OrganizationSection) sectionEntry.getValue()).getOrganizations(); %>
                            <c:if test="<%=organizations.size() != 0%>">
                                <h1 class="display-5">${sectionEntry.key.title}</h1>

                                <c:forEach var="organization"
                                           items="<%=organizations%>">
                                    <c:set var="organizationName" value="${organization.name.name}"/>
                                    <c:if test="${organization.name.url.length() != 0}">
                                        <c:set var="organizationName"
                                               value="<a href='${organization.name.url}'>${organization.name.name}</a>"/>
                                    </c:if>
                                    <div class="card" style="width: 50rem;">
                                        <div class="card-header">${organizationName}</div>
                                        <div class="card-body">

                                            <c:forEach var="position" items="${organization.positions}">
                                                <jsp:useBean id="position"
                                                             type="ru.javawebinar.basejava.model.Organization.Position"/>
                                                <c:set var="endDate" value="${DateUtil.format(position.dateEnd)}"/>
                                                <c:if test="${endDate == '01-01-3000'}">
                                                    <c:set var="endDate" value="по настоящее время"/>
                                                </c:if>
                                                <div class="card-title">${DateUtil.format(position.dateBegin)}
                                                    - ${endDate} - <b>${position.title}</b></div>
                                                <p class="card-text">${position.description}</p>
                                            </c:forEach>

                                        </div>
                                    </div>
                                    <br/>
                                </c:forEach>

                            </c:if>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <a href="#" type="cancel" onclick="window.history.back()" class="btn btn-primary mb-2">Назад</a>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
