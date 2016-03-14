/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleranking.pages;

import googleranking.processing.GoogleData;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author tranthanhan
 */
public class Form extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        if (request.getParameter("keyword") == null || request.getParameter("domain") == null){
            request.getRequestDispatcher("/WEB-INF/jsp/form.jsp").forward(request, response);
        } else {
            GoogleData googleData = new GoogleData(request.getParameter("keyword"));
            int ranking = googleData.getRankingForDomain(request.getParameter("domain"));
            request.setAttribute("keyword", request.getParameter("keyword"));
            request.setAttribute("domain", request.getParameter("domain"));
            request.setAttribute("ranking", ranking);
            request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp").forward(request, response);
        }
    }
}
