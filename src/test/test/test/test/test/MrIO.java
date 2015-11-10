package test.test.test.test.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class MrIO {
    
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("D:\\sql.txt");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println(is.available());
        IOUtils.copy(is, baos);
        System.out.println(baos.size());
        
        System.out.println(System.currentTimeMillis());
        byte[] isBytes = baos.toByteArray();
        System.out.println(isBytes.length);
        System.out.println(isBytes.length);
        System.out.println(isBytes.length);
        System.out.println(is.available());
        System.out.println(System.currentTimeMillis());
    }

}
