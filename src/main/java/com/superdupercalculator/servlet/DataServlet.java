package com.superdupercalculator.servlet;

import com.superdupercalculator.model.Function;
import com.superdupercalculator.model.Parameter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.superdupercalculator.model.Function.*;

/**
 * @author Makasyeyeva
 **/
@WebServlet(name = "DataServlet", value = "/data")
public class DataServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println(
                "<head>" +
                        "    <meta charset=\"UTF-8\">" +
                        "    <title>Форма обчислення</title>" +
                        "</head>" +
                        "<body>" +
                        "<h1>Welcome to Super Duper Calculator!</h1>" +
                        "<p>Please enter the value of your variables:</p>" +

                        "<form action=\"/result\">" +

                        getNumberHtml(Parameter.A, cookies) +
                        getNumberHtml(Parameter.B, cookies) +
                        getNumberHtml(Parameter.C, cookies) +
                        getNumberHtml(Parameter.D, cookies) +

                        "<button type='submit'>Calculate</button>" +
                        "<p>Choose the calculation method:</p>" +

                        getVariationHtml(CALCULATION_25) +
                        getVariationHtml(CALCULATION_26) +
                        getVariationHtml(CALCULATION_27) +

                        "</form>" +
                        "</body>" +
                        "</html>");
    }

    private String getNumberHtml(Parameter parameter, Cookie[] cookies) {
        return getNumberHtml(parameter.name(), cookies);
    }

    private String getNumberHtml(String cookieName, Cookie[] cookies) {
        return "<label for=\"" + cookieName + "\">" + cookieName + ":</label>" +
                "<input id=\"" + cookieName + "\" name=\"" + cookieName + "\" value=\"" + getCookieValue(cookies, cookieName) + "\">";
    }

    private String getCookieValue(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    private String getVariationHtml(Function function) {
        return "<input type=\"radio\" id=\"" + function + "\" name=\"" + Parameter.FUNCTION + "\" value=\"" + function + "\">" +
                "<label for=\"" + function + "\">" + function + "</label><br>";
    }
}