
abstract class ReportGenerator {

 
    public final void generateReport() {
        collectData();
        formatData();
        generateHeader();
        generateBody();
        generateFooter();
        saveReport();
        if (addOptionalStep()) {
            optionalStep();
        }
    }

    protected abstract void collectData();
    protected abstract void formatData();
    protected abstract void generateHeader();
    protected abstract void generateBody();
    protected abstract void generateFooter();
    protected abstract void saveReport();

    protected boolean addOptionalStep() {
        return false;
    }

    // Опциональный шаг
    protected void optionalStep() {
    
    }
}

class PdfReport extends ReportGenerator {

    @Override
    protected void collectData() {
        System.out.println("Collecting data for PDF report...");
    }

    @Override
    protected void formatData() {
        System.out.println("Formatting data for PDF report...");
    }

    @Override
    protected void generateHeader() {
        System.out.println("Generating PDF header...");
    }

    @Override
    protected void generateBody() {
        System.out.println("Generating PDF body...");
    }

    @Override
    protected void generateFooter() {
        System.out.println("Generating PDF footer...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Saving PDF report to file...");
    }
}

class ExcelReport extends ReportGenerator {

    @Override
    protected void collectData() {
        System.out.println("Collecting data for Excel report...");
    }

    @Override
    protected void formatData() {
        System.out.println("Formatting data for Excel report...");
    }

    @Override
    protected void generateHeader() {
        System.out.println("Generating Excel header...");
    }

    @Override
    protected void generateBody() {
        System.out.println("Generating Excel body...");
    }

    @Override
    protected void generateFooter() {
        System.out.println("Generating Excel footer...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Saving Excel report to file...");
    }

    @Override
    protected boolean addOptionalStep() {
        // Включить опциональный шаг для Excel
        return true;
    }

    @Override
    protected void optionalStep() {
        System.out.println("Performing optional step for Excel report...");
    }
}

class HtmlReport extends ReportGenerator {

    @Override
    protected void collectData() {
        System.out.println("Collecting data for HTML report...");
    }

    @Override
    protected void formatData() {
        System.out.println("Formatting data for HTML report...");
    }

    @Override
    protected void generateHeader() {
        System.out.println("Generating HTML header...");
    }

    @Override
    protected void generateBody() {
        System.out.println("Generating HTML body...");
    }

    @Override
    protected void generateFooter() {
        System.out.println("Generating HTML footer...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Saving HTML report to file...");
    }
}

// Клиентский код
public class ReportAppDemo {
    public static void main(String[] args) {
      
        ReportGenerator pdfReport = new PdfReport();
        System.out.println("Generating PDF Report:");
        pdfReport.generateReport();
        System.out.println();

      
        ReportGenerator excelReport = new ExcelReport();
        System.out.println("Generating Excel Report:");
        excelReport.generateReport();
        System.out.println();

       
        ReportGenerator htmlReport = new HtmlReport();
        System.out.println("Generating HTML Report:");
        htmlReport.generateReport();
    }
}
