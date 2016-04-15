package test.test.test.test.test.export;

public class ExporterProxy implements Exportable {

    Exportable exportable;

    public ExporterProxy(Exportable exportable) {
        super();
        this.exportable = exportable;
    }

    @Override
    public void export() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Exportable getProxy() {
        // TODO Auto-generated method stub
        return null;
    }

}
