
public interface IDocument {
    void Open();

}

class Report implements IDocument {
    public void Open() {
        System.out.println("report open");
    }
}

class Resume implements IDocument {
    public void Open() {
        System.out.println("Resume open");
    }
}

class Letter implements IDocument {
    public void Open() {
        System.out.println("Letter open");
    }
}

abstract class DocumentCreator {
    public abstract IDocument CreateDocument();

}

class ReportCreator extends DocumentCreator {

    public IDocument CreateDocument() {
        return new Report();
    }

}

class LetterCreator extends DocumentCreator {

    public IDocument CreateDocument() {
        return new Letter();
    }

}

class ResumeCreator extends DocumentCreator {

    public IDocument CreateDocument() {
        return new Resume();
    }

}

public enum DocType {
    Report, Resume, Letter
}


public void GetDocument(DocType docType) {
    DocumentCreator creator = null;
    IDocument document = null;

    switch (docType) {
        case DocType.Report -> {
            creator = new ReportCreator();

            break;
        }
        case DocType.Resume -> {
            creator = new ResumeCreator();

            break;
        }
        case DocType.Letter -> {
            creator = new LetterCreator();
            break;

        }
        document = creator.CreateDocument();
        return document;

    }

}
