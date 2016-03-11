/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleranking.pages;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author tranthanhan
 */
public class Ranking extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        Map data = request.getParameterMap();
        if (data.get("domain") != null && data.get("keyWord") != null) {
            // show result pages
        } else {
            // show form page
        }
    }
}
