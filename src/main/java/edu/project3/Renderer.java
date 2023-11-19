package edu.project3;

import java.time.format.DateTimeFormatter;

public class Renderer {
    public void render(LogReport logReport, OutputFormat format) {
        if (format == null) {
            format = OutputFormat.ADOC;
        }
        switch (format) {
            case MARKDOWN -> {
                System.out.println("If you put it in MarkDown Editor, you'll get more beautiful result");
                printMainTableMD(logReport);
                printRequestedResourcesMD(logReport);
                printAnswersCodesMD(logReport);
                printHttpMethodsMD(logReport);
                printUserAgentsMD(logReport);
            }
            case ADOC -> {
                printMainTableADOC(logReport);
                printRequestedResourcesADOC(logReport);
                printAnswersCodesADOC(logReport);
                printHttpMethodsADOC(logReport);
                printUserAgentsADOC(logReport);
            }
        }
    }

    private void printMainTableMD(LogReport logReport) {
        System.out.println("#### Общая информация");
        System.out.println("| Метрика | Значение |");
        System.out.println("|:-:|:-:|");

        StringBuilder temp = new StringBuilder();
        temp.append("| Файл(ы) | ");
        for (String filename : logReport.getFiles()) {
            temp.append("`" + filename + "`,");
        }
        temp.append("\b |");  // deleting last comma
        System.out.println(temp);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String fromDate = "-";
        String toDate = "-";
        if (logReport.getFromDate() != null) {
            fromDate = logReport.getFromDate().format(dateTimeFormatter);
        }
        if (logReport.getToDate() != null) {
            toDate = logReport.getToDate().format(dateTimeFormatter);
        }
        System.out.println("| Начальная дата | " + fromDate + "|");
        System.out.println("| Конечная дата | " + toDate + "|");
        System.out.println("| Количество запросов | " + logReport.getRequestsCount() + " |");
        System.out.println("| Средний размер ответа | " + logReport.getAverageAnswerSize() + " |\n");
    }

    public void printRequestedResourcesMD(LogReport logReport) {
        System.out.println("#### Запрашиваемые ресурсы");
        System.out.println("| Ресурс | Количество |");
        System.out.println("|:-:|:-:|");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getRequestedResources().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        System.out.println(stringBuilder);
    }

    private void printAnswersCodesMD(LogReport logReport) {
        System.out.println("#### Коды ответа");
        System.out.println("| Код | Количество |");
        System.out.println("|:---:|:-:|");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getAnswerCodes().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        System.out.println(stringBuilder);
    }

    private void printHttpMethodsMD(LogReport logReport) {
        System.out.println("#### Http методы");
        System.out.println("| Метод | Количество |");
        System.out.println("|:-----:|:----------:|");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getHttpMethods().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        System.out.println(stringBuilder);
    }

    private void printUserAgentsMD(LogReport logReport) {
        System.out.println("#### User agents");
        System.out.println("| User Agent | Количество |");
        System.out.println("|:----------:|:----------:|");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getUserAgents().entrySet().stream().forEach(es -> {
            stringBuilder.append("| " + es.getKey() + " | " + es.getValue() + " |\n");
        });
        System.out.println(stringBuilder);
    }

    private void printMainTableADOC(LogReport logReport) {
        System.out.println(".Общая информация \n|===");
        System.out.println("| Метрика | Значение \n");

        StringBuilder temp = new StringBuilder();
        temp.append("|Файл(ы) \n|");
        for (String filename : logReport.getFiles()) {
            temp.append("`" + filename + "`,");
        }
        System.out.println(temp);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String fromDate = "-";
        String toDate = "-";
        if (logReport.getFromDate() != null) {
            fromDate = logReport.getFromDate().format(dateTimeFormatter);
        }
        if (logReport.getToDate() != null) {
            toDate = logReport.getToDate().format(dateTimeFormatter);
        }
        System.out.println("| Начальная дата \n|" + fromDate + "\n");
        System.out.println("| Конечная дата \n|" + toDate + "\n");
        System.out.println("| Количество запросов \n|" + logReport.getRequestsCount() + "\n");
        System.out.println("| Средний размер ответа \n|" + logReport.getAverageAnswerSize() + "\n");
        System.out.println("|===\n");
    }

    public void printRequestedResourcesADOC(LogReport logReport) {
        System.out.println(".Запрашиваемые ресурсы \n|===");
        System.out.println("| Ресурс | Количество \n");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getRequestedResources().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        System.out.println(stringBuilder + "|===\n");
    }

    private void printAnswersCodesADOC(LogReport logReport) {
        System.out.println(".Коды ответа \n|===");
        System.out.println("| Код | Количество \n");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getRequestedResources().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        System.out.println(stringBuilder + "|===\n");
    }

    private void printHttpMethodsADOC(LogReport logReport) {
        System.out.println(".HTTP методы \n|===");
        System.out.println("| Метод | Количество \n");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getHttpMethods().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        System.out.println(stringBuilder + "|===\n");
    }

    private void printUserAgentsADOC(LogReport logReport) {
        System.out.println(".User agents \n|===");
        System.out.println("| User agent | Количество \n");
        StringBuilder stringBuilder = new StringBuilder();
        logReport.getUserAgents().entrySet().stream().forEach(es -> {
            stringBuilder.append("|" + es.getKey() + "\n|" + es.getValue() + "\n\n");
        });
        System.out.println(stringBuilder + "|===\n");
    }
}
