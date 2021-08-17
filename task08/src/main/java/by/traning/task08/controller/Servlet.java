package by.traning.task08.controller;

import by.traning.task08.bean.Gem;
import by.traning.task08.service.builder.DOMBuilder;
import by.traning.task08.service.builder.Director;
import by.traning.task08.service.builder.SAXBuilder;
import by.traning.task08.service.builder.StAXBuilder;
import by.traning.task08.service.exception.*;
import by.traning.task08.service.validation.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet("/parsing")
@MultipartConfig
public class Servlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Servlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent1 = filePart.getInputStream();
        InputStream fileContent = filePart.getInputStream();
        XMLValidator xmlValidator = XMLValidator.getInstance();
        boolean isValid = true;
        try {
            isValid = xmlValidator.isValid(new StreamSource(fileContent1) , "Gems.xsd");
        } catch (ServiceException invalidInputStream) {
            invalidInputStream.printStackTrace();
        }
        if (isValid) {
            String table = null;
            String parserName = request.getParameter("parserChooser");
            List<String> columnList = formColumnName();
            try {
                if (parserName.equalsIgnoreCase("dom")) {
                    table = formTable(columnList, Director.createGemList(new DOMBuilder(fileContent)));
                } else if (parserName.equalsIgnoreCase("sax")) {
                    table = formTable(columnList, Director.createGemList(new SAXBuilder(fileContent)));
                } else {
                    table = formTable(columnList, Director.createGemList(new StAXBuilder(fileContent)));
                }
                request.setAttribute("table", table);
                request.setAttribute("parser", parserName);
                request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
            } catch (ServiceException e) {
                LOGGER.fatal(e);
            }
        } else {
            request.getRequestDispatcher("/jsp/fileError.jsp").forward(request , response);
        }
    }

    private String formTable(List<String> columnList , List<Gem> gemList) {
        String table = "";
        table += "<table border=\"1\" align=\"center\"><tr>";
        for (String str : columnList) {
            table += "<td>" + str + "</td>";
        }
        table += "</tr>";
        for (Gem gem : gemList) {
            table += "<tr>";
            StringTokenizer stringTokenizer = new StringTokenizer(gem.toString());
            while (stringTokenizer.hasMoreElements()) {
                table += "<td>" + stringTokenizer.nextToken() + "</td>";
            }
            table += "</tr>";
        }
        table += "</table>";
        return table;
    }

    private List<String> formColumnName() {
        List<String> columnList = new ArrayList<>();
        columnList.add("Name");
        columnList.add("Preciousnes");
        columnList.add("Origin");
        columnList.add("Color");
        columnList.add("Transparency");
        columnList.add("Count of edges");
        columnList.add("Value");
        columnList.add("Date");
        columnList.add("Time");
        return columnList;
    }
}