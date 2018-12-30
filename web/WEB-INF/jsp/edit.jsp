<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <jsp:include page="fragments/head.jsp"/>
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
                                    <% List<Organization> organizations = ((OrganizationSection) section).getAllOrganizations(); %>
                                    <div id="containerOrgs${sectionType}">
                                        <c:forEach var="organization"
                                                   items="<%=organizations%>"
                                                   varStatus="counter">
                                            <div id="containerOrg${sectionType}${counter.index}">
                                                <label for="${sectionType}"><b>Наименование организации</b></label>
                                                <input class="form-control" type="text" id="${sectionType}"
                                                       name="${sectionType}"
                                                       value="${organization.name.name}"/>
                                                <label for="${sectionType}URL">URL</label>
                                                <input class="form-control" type="text" id="${sectionType}URL"
                                                       name="${sectionType}URL"
                                                       value="${organization.name.url}"/>
                                                <div id="container${sectionType}${counter.index}"
                                                     style="margin-left: 2em;">
                                                    <c:forEach var="position" items="${organization.positions}"
                                                               varStatus="counterPos">
                                                        <jsp:useBean id="position"
                                                                     type="ru.javawebinar.basejava.model.Organization.Position"/>

                                                        <label for="${sectionType}${counter.index}${counterPos.index}dateBegin">Дата
                                                            начала</label>
                                                        <div id="${sectionType}${counter.index}${counterPos.index}boxBegin">
                                                            <input class="form-control date" type="text"
                                                                   id="${sectionType}${counter.index}${counterPos.index}dateBegin"
                                                                   name="${sectionType}${counter.index}dateBegin"
                                                                   size=10
                                                                   value="<%=DateUtil.format(position.getDateBegin())%>"
                                                                   placeholder="MM/yyyy"
                                                                   width="234" readonly>
                                                        </div>


                                                        <label for="${sectionType}${counter.index}${counterPos.index}dateEnd">Дата
                                                            окончания</label>
                                                        <div id="${sectionType}${counter.index}${counterPos.index}boxEnd">
                                                            <input class="form-control date" type="text"
                                                                   id="${sectionType}${counter.index}${counterPos.index}dateEnd"
                                                                   name="${sectionType}${counter.index}dateEnd"
                                                                   value="<%=DateUtil.format(position.getDateEnd())%>"
                                                                   placeholder="MM/yyyy"
                                                                   width="234" readonly>
                                                        </div>
                                                        <c:choose>
                                                            <c:when test='<%=DateUtil.format(position.getDateEnd()).equals("Сейчас")%>'>
                                                                <script>
                                                                    $('#${sectionType}${counter.index}${counterPos.index}boxEnd').hide();
                                                                    <c:set var="checked" value="checked"/>
                                                                </script>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <script>
                                                                    $('#${sectionType}${counter.index}${counterPos.index}boxEnd').show();
                                                                    <c:set var="checked" value=""/>
                                                                </script>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div class="form-check">
                                                            <input id="${sectionType}${counter.index}${counterPos.index}checkbox" ${checked}
                                                                   class="form-check-input" type="checkbox"
                                                                   onclick="showHideDateEnd('${sectionType}${counter.index}${counterPos.index}')">
                                                            <label for="${sectionType}${counter.index}${counterPos.index}checkbox">по
                                                                настоящее время</label>
                                                        </div>


                                                        <label for="${sectionType}${counter.index}title">Должность</label>
                                                        <input class="form-control" type="text"
                                                               id="${sectionType}${counter.index}title"
                                                               name="${sectionType}${counter.index}title"
                                                               value="${position.title}">
                                                        <label for="${sectionType}${counter.index}description">Описание</label>
                                                        <input class="form-control" type="text"
                                                               id="${sectionType}${counter.index}description"
                                                               name="${sectionType}${counter.index}description"
                                                               value="${position.description}">

                                                        <img src="img/edit.png" width="0" height="0"
                                                             onload="datepicker('${sectionType}${counter.index}${counterPos.index}')"/>
                                                        <c:set var="cntPos" value="${counterPos}"/>
                                                        <hr/>
                                                    </c:forEach>

                                                </div>
                                                <div>
                                                    <a href="#${sectionType}${counter.index}"
                                                       id="${sectionType}${counter.index}addBtn"
                                                       class="btn btn-primary mb-2"
                                                       onclick="addPosition('${sectionType}', ${counter.index+1}, ${cntPos.index+1})">добавить
                                                        позицию</a>
                                                </div>
                                            </div>
                                            <c:set var="cnt" value="${counter}"/>
                                        </c:forEach>
                                        <div>
                                            <a href="#${sectionType}${cnt.index}" class="btn btn-primary mb-2"
                                               id="${sectionType}${counter.index}addOrgBtn"
                                               onclick="addOrganization('${sectionType}', ${cnt.index+1})">добавить
                                                организацию</a>
                                        </div>
                                    </div>

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
