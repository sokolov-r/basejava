package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    SqlStorage storage = (SqlStorage) Config.get().getStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("name");
        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');

        String table = request.getParameter("table");
        PrintWriter writer = response.getWriter();
        writer.write("<table border=\"1\" width=\"30%\">");
        if (table != null) {
            writer.write("" +
                    " <tr>" +
                    "   <td>" + storage.get(table).getUuid() + "</td>" +
                    "   <td>" + storage.get(table).getFullName() + "</td>" +
                    " </tr>");
        } else {
            writer.write("" +
                    "<table border=\"1\" width=\"30%\">");
            for (Resume resume : storage.getAllSorted()) {
                writer.write("" +
                        " <tr>" +
                        "   <td>" + resume.getUuid() + "</td>" +
                        "   <td>" + resume.getFullName() + "</td>" +
                        " </tr>");
            }
        }
        writer.write("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
