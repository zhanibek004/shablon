public interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    Report getReport();
}
class TextReportBuilder implements IReportBuilder {
    private String header;
    private String content;
    private String footer;

    @Override
    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public Report getReport() {
        return new Report(header, content, footer);
    }
}
class HtmlReportBuilder implements IReportBuilder {
    private String header;
    private String content;
    private String footer;

    @Override
    public void setHeader(String header) {
        this.header = "<h1>" + header + "</h1>";
    }

    @Override
    public void setContent(String content) {
        this.content = "<p>" + content + "</p>";
    }

    @Override
    public void setFooter(String footer) {
        this.footer = "<footer>" + footer + "</footer>";
    }

    @Override
    public Report getReport() {
        return new Report(header, content, footer);
    }
}
class ReportDirector {
    public void constructReport(IReportBuilder builder) {
        builder.setHeader("Отчет");
        builder.setContent("Это содержимое отчета.");
        builder.setFooter("Конец отчета");
    }
}
class Report {
    private String header;
    private String content;
    private String footer;

    public Report(String header, String content, String footer) {
        this.header = header;
        this.content = content;
        this.footer = footer;
    }
    
    public String toString() {
        return header + "\n" + content + "\n" + footer;
    }
}
public class Main {
    public static void main(String[] args) {
        ReportDirector director = new ReportDirector();


        TextReportBuilder textBuilder = new TextReportBuilder();
        director.constructReport(textBuilder);
        Report textReport = textBuilder.getReport();
        System.out.println("Текстовый отчет:\n" + textReport);
        
        HtmlReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.constructReport(htmlBuilder);
        Report htmlReport = htmlBuilder.getReport();
        System.out.println("\nHTML отчет:\n" + htmlReport);
    }
}

