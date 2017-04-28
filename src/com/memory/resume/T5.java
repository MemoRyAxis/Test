package com.memory.resume;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author memoryaxis@gmail.com
 */
public class T5 {

    public static void main(String[] args) throws IOException {
        new T5().createFile2();
//        new T5().readFile();
    }

    public void readFile() throws IOException {
        String file1 = "E:\\tmp\\T5.file.1";
        String file2 = "E:\\tmp\\T5.file.2";

        List<String> strings = new ArrayList<>();
        Map<Integer, String> integerStringMap = new HashMap<>();
        Reader reader = new FileReader(file1);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
//            integerStringMap.put(count, line);
            strings.add(line);
            if (++count % 1000000 == 0) {
                System.out.println(count);
            }
        }
    }

    public void createFile() throws IOException {
        String file1 = "E:\\tmp\\T5.file.1";
        String file2 = "E:\\tmp\\T5.file.2";
        OutputStream os = new FileOutputStream(file2);

//        LineNumberReader lnr = new LineNumberReader(new FileReader(file1));
//        lnr.skip(Long.MAX_VALUE);
//        System.out.println(lnr.getLineNumber() + 1);
//        lnr.close();

        for (int i = 0; i < 100000000; i++) {
            os.write((UUID.randomUUID().toString() + "\n").getBytes());
        }
        os.flush();
        os.close();
    }


    public void createFile2() throws IOException {
        String file1 = "E:\\tmp\\T5.file.1.min";
        String file2 = "E:\\tmp\\T5.file.2.min";
        OutputStream os = new FileOutputStream(file2);

        for (int i = 0; i < 1000000; i++) {
            os.write((UUID.randomUUID().toString() + "\n").getBytes());
        }
        os.flush();
        os.close();

        os = new FileOutputStream(file1);

        for (int i = 0; i < 1000000; i++) {
            os.write((UUID.randomUUID().toString() + "\n").getBytes());
        }
        os.flush();
        os.close();
    }
}
