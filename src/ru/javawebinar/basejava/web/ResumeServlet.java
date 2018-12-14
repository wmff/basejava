package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//TODO get resume by uuid
// or not param get all resumes in table

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("name");
        writeln(response, name == null ? "Hello Resumes!" : "Hello " + name + "!");

        writeln(response, "<html>");
        writeln(response, "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<link href=\"css/style.css\" rel=\"stylesheet\">");
        writeln(response, "</head>");
        writeln(response, "<body>");
        writeln(response, "<table border=0>");
        writeln(response, "<tbody>");
        writeln(response, "<tr>");
        writeln(response, "<th>Name</th>");
        writeln(response, "<th>Contacts</th>");
        writeln(response, "</tr>");

        List<Resume> list = storage.getAllSorted();
        for (Resume resume : list) {
            Map<ContactType, String> map = resume.getContacts();

            String rowspan = map.size() > 1 ? "rowspan=" + (map.size()+1) : "";

            writeln(response, "<tr>");
            writeln(response, "<td " + rowspan + ">" + resume.getFullName() + "</td>");
            writeln(response, map.size() == 0 ? "<td>No Contacts</td>" : "");
            writeln(response, "</tr>");
            for (Map.Entry<ContactType, String> entry : map.entrySet()) {
                writeln(response, "<tr>");
                writeln(response, "<td>");
                writeln(response, entry.getKey().getTitle() + ": " + entry.getValue());
                writeln(response, "</td>");
                writeln(response, "</tr>");
            }
        }

        writeln(response, "</tbody>");
        writeln(response, "</table>");
        writeln(response, "</body>");
        writeln(response, "</html>");
    }

    private void writeln(HttpServletResponse response, String string) throws IOException {
        response.getWriter().write(string + "\n");
    }

}
