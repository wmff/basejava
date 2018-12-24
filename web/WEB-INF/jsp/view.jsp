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
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <h2>${resume.fullName} <a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"
                                                                                             alt="edit"></a></h2>
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
                            <c:if test="<%=section != null && !section.isEmpty()%>">
                                <h3>${sectionEntry.key.title}</h3>
                                <%=section%>
                            </c:if>
                        </c:when>
                        <c:when test="${sectionEntry.key.name() == 'QUALIFICATIONS' || sectionEntry.key.name() == 'ACHIEVEMENT'}">
                            <% java.util.List<String> values = ((ListSection) sectionEntry.getValue()).getItems();
                                List<String> items = new ArrayList<>();
                                if (values != null) {
                                    for (String value : values) {
                                        if (value != null && !value.isEmpty()) {
                                            items.add(value);
                                        }
                                    }
                                }
                            %>
                            <c:if test="<%=items.size()!=0%>">
                                <h3>${sectionEntry.key.title}</h3>
                                <ul>
                                    <c:forEach var="item" items="<%=items%>">
                                        <c:if test="${item.length() !=0}">
                                            <li>${item}</li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </c:when>
                        <c:when test="${sectionEntry.key.name() == 'EXPERIENCE' || sectionEntry.key.name() == 'EDUCATION'}">
                            <% List<ru.javawebinar.basejava.model.Organization> organizations = ((OrganizationSection) sectionEntry.getValue()).getOrganizations();
                                List<Organization> listOrgs = new ArrayList<>();
                                if (organizations != null) {
                                    for (Organization organization : organizations) {
                                        if (organization != null) {
                                            List<Organization.Position> listPos = organization.getPositions();
                                            if (listPos != null && !listPos.isEmpty()) {
                                                listOrgs.add(organization);
                                            }
                                        }
                                    }
                                }%>
                            <c:if test="<%=organizations != null && listOrgs.size() != 0%>">
                                <h3>${sectionEntry.key.title}</h3>
                                <c:forEach var="organization"
                                           items="<%=organizations%>">
                                    <c:set var="organizationName" value="${organization.name.name}"/>
                                    <c:if test="${organization.name.url.length() != 0}">
                                        <c:set var="organizationName"
                                               value="<a href='${organization.name.url}'>${organization.name.name}</a>"/>
                                    </c:if>
                                    <h5>${organizationName}</h5>
                                    <c:forEach var="position" items="${organization.positions}">
                                        <jsp:useBean id="position"
                                                     type="ru.javawebinar.basejava.model.Organization.Position"/>
                                        <c:set var="endDate" value="${DateUtil.format(position.dateEnd)}"/>
                                        <c:if test="${endDate == '01-01-3000'}">
                                            <c:set var="endDate" value="по настоящее время"/>
                                        </c:if>
                                        ${DateUtil.format(position.dateBegin)} - ${endDate}
                                        <br/>
                                        ${position.title} <br/>
                                        ${position.description}<br/>
                                    </c:forEach>
                                </c:forEach>
                            </c:if>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
