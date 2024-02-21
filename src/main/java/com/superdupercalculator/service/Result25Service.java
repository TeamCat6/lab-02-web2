package com.superdupercalculator.service;

public class Result25Service {

    public String getResult(double a, double b, double c, double d) {
        double result = getCalculate(a, b, c, d);
        return getCalculateView(a, b, c, d, result);
    }

    private String getCalculateView(double a, double b, double c, double d, double result) {
        return String.format("<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\">\n" +
                "  <mfrac>\n" +
                "    <msup>\n" +
                "      <mi>%1$s</mi>\n" +
                "      <mi>%2$s</mi>\n" +
                "    </msup>\n" +
                "    <mrow>\n" +
                "      <mi>ch</mi>\n" +
                "      <mo data-mjx-texclass=\"NONE\">\u2061</mo>\n" +
                "      <mo stretchy=\"false\">(</mo>\n" +
                "      <mrow>\n" +
                "        <mo stretchy=\"false\">|</mo>\n" +
                "      </mrow>\n" +
                "      <mi>%2$s</mi>\n" +
                "      <mrow>\n" +
                "        <mo stretchy=\"false\">|</mo>\n" +
                "      </mrow>\n" +
                "      <mo stretchy=\"false\">)</mo>\n" +
                "    </mrow>\n" +
                "  </mfrac>\n" +
                "  <mo>+</mo>\n" +
                "  <mn>4</mn>\n" +
                "  <mo>⋅</mo>\n" +
                "  <mfrac>\n" +
                "    <mrow>\n" +
                "      <mi>lg</mi>\n" +
                "      <mo data-mjx-texclass=\"NONE\">\u2061</mo>\n" +
                "      <mo stretchy=\"false\">(</mo>\n" +
                "      <mi>%3$s</mi>\n" +
                "      <mo stretchy=\"false\">)</mo>\n" +
                "    </mrow>\n" +
                "    <mroot>\n" +
                "      <mi>%4$s</mi>\n" +
                "      <mn>5</mn>\n" +
                "    </mroot>\n" +
                "  </mfrac>\n" +
                "  <mo>=</mo>\n" +
                "  <mi>%5$s</mi>\n" +
                "</math>", a, b, c, d, result);
    }

    private double getCalculate(double a, double b, double c, double d) {
        return Math.pow(a, b) / Math.cosh(Math.abs(b)) + 4 * Math.log10(c) / Math.pow(d, 1.0 / 5.0);
    }
}
