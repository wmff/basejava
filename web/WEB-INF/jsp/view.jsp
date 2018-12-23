<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" rel="stylesheet">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName} <a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png" alt="edit"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${HtmlUtil.formatContacts(resume.contacts)}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            ${contactEntry.value}<br/>
        </c:forEach>
    <hr/>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
        <h3>${sectionEntry.key.title}</h3>
        <c:choose>
            <c:when test="${sectionEntry.key.name() == 'OBJECTIVE' || sectionEntry.key.name() == 'PERSONAL'}">
                <%=((TextSection) sectionEntry.getValue()).getContent()%>
            </c:when>
            <c:when test="${sectionEntry.key.name() == 'QUALIFICATIONS' || sectionEntry.key.name() == 'ACHIEVEMENT'}">
                <ul>
                    <c:forEach var="item" items="<%=((ListSection) sectionEntry.getValue()).getItems()%>">
                        <c:if test="${item.length() != 0}">
                            <li>${item}</li>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${sectionEntry.key.name() == 'EXPERIENCE' || sectionEntry.key.name() == 'EDUCATION'}">
                <c:forEach var="organization"
                           items="<%=((OrganizationSection) sectionEntry.getValue()).getOrganizations()%>">
                    <c:set var="organizationName" value="${organization.name.name}"/>
                    <c:if test="${organization.name.url != ''}">
                        <c:set var="organizationName"
                               value="<a href='${organization.name.url}'>${organization.name.name}</a>"/>
                    </c:if>
                    <h4>${organizationName}</h4>
                    <c:forEach var="position" items="${organization.positions}">
                        <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                        <c:set var="endDate" value="${HtmlUtil.formatDate(position.dateEnd)}"/>
                        <c:if test="${endDate == '01-01-3000'}">
                            <c:set var="endDate" value="по настоящее время"/>
                        </c:if>
                        ${HtmlUtil.formatDate(position.dateBegin)} - ${endDate}
                        <br/>
                        ${position.title} <br/>
                        ${position.description}<br/>
                    </c:forEach>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
