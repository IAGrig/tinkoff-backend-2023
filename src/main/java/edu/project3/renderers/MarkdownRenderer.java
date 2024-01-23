package edu.project3.renderers;

import edu.project3.LogReport;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;

public class MarkdownRenderer extends AbstractRenderer {
    @Override
    public void render(LogReport logReport, String filepath, boolean addFormatExtensionToFilename) {
        if (logReport == null || filepath == null) {
            throw new IllegalArgumentException();
        }
        if (addFormatExtensionToFilename) {
            filepath += ".md";
        }
        super.render(logReport, filepath, addFormatExtensionToFilename);
    }

    protected void writeMainTable(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("#### Общая информация\n");
        stringBuilder.append("| Метрика | Значение |\n");
        stringBuilder.append("|:-:|:-:|\n");

        stringBuilder.append("| Файл(ы) | ");
        for (String filename : logReport.getFiles()) {
            stringBuilder.append("`" + filename + "`,");
        }
        stringBuilder.append("\b |\n");  // deleting last comma

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String fromDate = RenderersHelper.getFromDate(logReport, dateTimeFormatter);
        String toDate = RenderersHelper.getToDate(logReport, dateTimeFormatter);

        stringBuilder.append("| Начальная дата | " + fromDate + "|\n");
        stringBuilder.append("| Конечная дата | " + toDate + "|\n");
        stringBuilder.append("| Количество запросов | " + logReport.getRequestsCount() + " |\n");
        stringBuilder.append("| Средний размер ответа | " + logReport.getAverageAnswerSize() + " |\n\n");
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeRequestedResources(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#### Запрашиваемые ресурсы\n");
        stringBuilder.append("| Ресурс | Количество |\n");
        stringBuilder.append("|:-:|:-:|\n");
        logReport.getRequestedResources().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeAnswersCodes(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#### Коды ответа\n");
        stringBuilder.append("| Код | Количество |\n");
        stringBuilder.append("|:---:|:-:|\n");
        logReport.getAnswerCodes().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeHttpMethods(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#### Http методы\n");
        stringBuilder.append("| Метод | Количество |\n");
        stringBuilder.append("|:-----:|:----------:|\n");
        logReport.getHttpMethods().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeUserAgents(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#### User agents\n");
        stringBuilder.append("| User Agent | Количество |\n");
        stringBuilder.append("|:----------:|:----------:|\n");
        logReport.getUserAgents().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        out.write(stringBuilder.toString().getBytes());
    }
}
