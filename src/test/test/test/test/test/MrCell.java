package test.test.test.test.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MrCell {

    public static void main(String[] args) throws Exception {

        FileReader reader = new FileReader("D:\\cell.txt");
        BufferedReader br = new BufferedReader(reader);

        FileWriter writer = new FileWriter("D:\\sql.txt");
        BufferedWriter bw = new BufferedWriter(writer);

        String sql = "insert into br_mobile (mobile, province, city) values (%s, %s, %s);\n";

        String str = null;
        String[] strs = null;
        while ((str = br.readLine()) != null) {
            strs = str.split("\\s+");
            str =
                    String.format(sql, "'" + strs[0] + "'", "'" + strs[1] + "'", strs.length < 3
                            ? "''"
                            : "'" + strs[2] + "'");
            bw.write(str);
        }
        br.close();
        bw.close();
    }
}
