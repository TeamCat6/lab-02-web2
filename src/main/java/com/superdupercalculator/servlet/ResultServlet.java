package com.superdupercalculator.servlet;

import com.superdupercalculator.model.Function;
import com.superdupercalculator.model.Parameter;
import com.superdupercalculator.service.CalculateService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Makasyeyeva
 **/
@WebServlet(name = "ResultServlet", value = "/result")
public class ResultServlet extends HttpServlet {

    CalculateService calculateService = new CalculateService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String result;

        try {
            Function function = getFunction(request);
            double a = getValue(request, Parameter.A, response);
            double b = getValue(request, Parameter.B, response);
            double c = getValue(request, Parameter.C, response);
            double d = getValue(request, Parameter.D, response);

            result = String.valueOf(calculateService.getResult(function, a, b, c, d));
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }

        out.println("<html>" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Результат</title>" +
                "   <script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>" +
                "   <script id=\"MathJax-script\" async\n" +
                "          src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\">" +
                "  </script>" +
                "</head>" +
                "<body style=\"line-height: 150%\">" +
                "<p>Here is your formula:</p>" +
                result.replace("NaN", "not defined") +
                "<button style=\"display:block\" onclick=\"history.back()\">Back</button>" +
                "</body></html>");
    }

    private double getValue(HttpServletRequest request, Parameter parameter, HttpServletResponse response) {
        try {
            double value = Double.parseDouble(request.getParameter(parameter.name()));
            setCookie(parameter, value, response);

            return value;
        } catch (Exception e) {
            throw new IllegalArgumentException("Parameter " + parameter + " is not valid");
        }
    }

    private void setCookie(Parameter parameter, double value, HttpServletResponse response) {
        Cookie cookie = new Cookie(parameter.name(), Double.toString(value));
        cookie.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    private Function getFunction(HttpServletRequest request) {
        try {
            return Function.valueOf(request.getParameter(Parameter.FUNCTION.name()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Parameter " + Parameter.FUNCTION + " is not valid");
        }
    }
}