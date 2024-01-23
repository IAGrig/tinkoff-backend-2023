package edu.project3.renderers;

import edu.project3.LogReport;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;

public class AdocRenderer extends AbstractRenderer {
    @Override
    public void render(LogReport logReport, String filepath, boolean addFormatExtensionToFilename) {
        if (logReport == null || filepath == null) {
            throw new IllegalArgumentException();
        }
        if (addFormatExtensionToFilename) {
            filepath += ".adoc";
        }
        super.render(logReport, filepath, addFormatExtensionToFilename);
    }

    protected void writeMainTable(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".Общая информация \n|===\n");
        stringBuilder.append("| Метрика | Значение \n\n");

        stringBuilder.append("|Файл(ы) \n|");
        for (String filename : logReport.getFiles()) {
            stringBuilder.append("`" + filename + "`,");
        }
        stringBuilder.append("\n");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String fromDate = RenderersHelper.getFromDate(logReport, dateTimeFormatter);
        String toDate = RenderersHelper.getToDate(logReport, dateTimeFormatter);

        stringBuilder.append("| Начальная дата \n|" + fromDate + "\n\n");
        stringBuilder.append("| Конечная дата \n|" + toDate + "\n\n");
        stringBuilder.append("| Количество запросов \n|" + logReport.getRequestsCount() + "\n\n");
        stringBuilder.append("| Средний размер ответа \n|" + logReport.getAverageAnswerSize() + "\n\n");
        stringBuilder.append("|===\n\n");
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeRequestedResources(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".Запрашиваемые ресурсы \n|===\n");
        stringBuilder.append("| Ресурс | Количество \n\n");
        logReport.getRequestedResources().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        stringBuilder.append("|===\n");
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeAnswersCodes(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".Коды ответа \n|===\n");
        stringBuilder.append("| Код | Количество \n\n");
        logReport.getRequestedResources().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        stringBuilder.append("|===\n");
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeHttpMethods(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".HTTP методы \n|===\n");
        stringBuilder.append("| Метод | Количество \n\n");
        logReport.getHttpMethods().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        stringBuilder.append("|===\n");
        out.write(stringBuilder.toString().getBytes());
    }

    protected void writeUserAgents(LogReport logReport, OutputStream out) throws IOException {
        if (logReport == null || out == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".User agents \n|===\n");
        stringBuilder.append("| User agent | Количество \n\n");
        logReport.getUserAgents().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        stringBuilder.append("|===\n");
        out.write(stringBuilder.toString().getBytes());
    }

}
