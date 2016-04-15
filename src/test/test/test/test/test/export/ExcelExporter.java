package test.test.test.test.test.export;

public class ExcelExporter implements Exportable {
    
    
    Exportable proxy = null;

    @Override
    public void export() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Exportable getProxy() {
        this.proxy = new ExporterProxy(this);
        return proxy;
    }

}
