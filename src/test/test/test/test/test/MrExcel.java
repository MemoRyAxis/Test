package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MrExcel {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    String xlsFileName = "D:\\dcp\\uploadFile\\1-f20fbeb7bddd0d80771afe8e723c4760-1437562738.xls"; 
    String xlsxFileName = "D:\\dcp\\uploadFile\\1-1b6cdae51c62349801bc6c4a202cff0f-1437563325.xlsx";
    File xlsFile = new File(xlsFileName);
    File xlsxFile = new File(xlsxFileName);
    
    Workbook wb = new HSSFWorkbook(new FileInputStream(xlsFile));
    Workbook wbx = new XSSFWorkbook(new FileInputStream(xlsxFile));
    
    System.out.println(wb.getNumberOfSheets());
    System.out.println(wbx.getNumberOfSheets());
    
    Workbook wbxx = new HSSFWorkbook(new FileInputStream("E:/workspace0319/metlife/src/main/resources/template/EffectiveDataTemplate.xls"));
    Workbook wbxxx = new HSSFWorkbook(new FileInputStream("E:/workspace0319/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/metlife/WEB-INF/classes/template/EffectiveDataTemplate.xls"));
    System.out.println(wbxx.getNumberOfSheets());
    System.out.println(wbxxx.getNumberOfSheets());
    
  }
}
