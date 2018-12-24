package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume;
        boolean isNewResume = uuid.isEmpty();
        if (isNewResume) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && !value.trim().isEmpty()) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        for (SectionType sectionType : SectionType.values()) {
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.addSection(sectionType, new TextSection(request.getParameter(sectionType.name())));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    resume.addSection(sectionType, new ListSection(request.getParameter(sectionType.name()).split("\n")));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    List<Organization> organizations = new ArrayList<>();
                    String[] urls = request.getParameterValues(sectionType.name() + "URL");
                    String[] values = request.getParameterValues(sectionType.name());
                    for (int i = 0; i < values.length; i++) {
                        String name = values[i];
                        if (name != null && !name.isEmpty()) {
                            List<Organization.Position> positions = new ArrayList<>();
                            String pfx = sectionType.name() + i;
                            String[] startDates = request.getParameterValues(pfx + "dateBegin");
                            String[] endDates = request.getParameterValues(pfx + "dateEnd");
                            String[] titles = request.getParameterValues(pfx + "title");
                            String[] descriptions = request.getParameterValues(pfx + "description");
                            for (int j = 0; j < titles.length; j++) {
                                if (titles[j] != null && !titles[j].isEmpty()) {
                                    positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                }
                            }
                            organizations.add(new Organization(new Link(name, urls[i]), positions));
                        }
                    }
                    resume.addSection(sectionType, new OrganizationSection(organizations));
                    break;
                default:
                    throw new IllegalArgumentException();
            }

        }
        if (isNewResume) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = uuid != null ? storage.get(uuid) : new Resume();
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " id illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
