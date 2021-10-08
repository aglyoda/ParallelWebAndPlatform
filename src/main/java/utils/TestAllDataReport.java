package utils;

import java.io.FileWriter;
import java.io.IOException;

public class TestAllDataReport {

    private static ThreadLocal<String> outputString = new ThreadLocal<>();
    private static FileWriter webFileWriter;
    private static FileWriter servicesFileWriter;

    static {
        try {
            webFileWriter = new FileWriter("reports/ScreenTestReport.html");
            servicesFileWriter = new FileWriter("reports/RequestResponseTestReport.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setWebReportData(String uid, String feature, String scenario, String step, String result, String image) {
        String inputString = "<html><body>" +
                "<table border='1'>" +
                "<tr><td><b>uniqueId</b></td><td>^uid^</td></tr>" +
                "<tr><td><b>feature</b></td><td>^feature^</td></tr>" +
                "<tr><td><b>scenario</b></td><td>^scenario^</td></tr>" +
                "<tr><td><b>step</b></td><td>^step^</td></tr>" +
                "<tr><td><b>result</b></td><td>^result^</td></tr>" +
                "<tr><td><b>screenshot</b></td><td><img src='data:image/png;base64,^image^' width='200' height'100'/></td></tr>" +
                "</table>" +
                "</body></html><br>";
        inputString = inputString.replace("^uid^", uid).replace("^feature^", feature).replace("^scenario^", scenario);
        inputString = inputString.replace("^step^", step).replace("^result^", result).replace("^image^", image);
        outputString.set(inputString);
        try {
            webFileWriter.append(outputString.get());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        outputString.remove();
    }

    public static void setServiceReportData(String uid, String feature, String scenario, String step, String result, String request, String response) {
        String inputString = "<html><body>" +
                "<table border='1'>" +
                "<tr><td><b>uniqueId</b></td><td>^uid^</td></tr>" +
                "<tr><td><b>feature</b></td><td>^feature^</td></tr>" +
                "<tr><td><b>scenario</b></td><td>^scenario^</td></tr>" +
                "<tr><td><b>step</b></td><td>^step^</td></tr>" +
                "<tr><td><b>result</b></td><td>^result^</td></tr>" +
                "<tr><td><b>request</b></td><td>^request^</td></tr>" +
                "<tr><td><b>response</b></td><td>^response^</td></tr>" +
                "</table>" +
                "</body></html><br>";
        inputString = inputString.replace("^uid^", uid).replace("^feature^", feature).replace("^scenario^", scenario);
        inputString = inputString.replace("^step^", step).replace("^result^", result).replace("^request^", request).replace("^response^", response);
        outputString.set(inputString);
        try {
            servicesFileWriter.append(outputString.get());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        outputString.remove();
    }

    public static void close() {
        try {
            webFileWriter.close();
            servicesFileWriter.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
