package test.test.test.test.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

public class MrCell {

    public static void main(String[] args) throws Exception {


        String testStr = "逾期601-630天";
        Pattern p = Pattern.compile("(\\d*)(天)");
        Matcher m = p.matcher(testStr);
        if (m.find()) {
            // System.out.println(m.group(1));
        }

        // System.out.println("M1M2".indexOf("M", 2));

        blackList();
    }

    public static void blackList() throws Exception {

        Set<String> dualDateSet = new HashSet<>();

        FileReader reader = new FileReader("D:\\blackList.data");
        BufferedReader br = new BufferedReader(reader);

        FileWriter writer = new FileWriter("D:\\dcp.blackList.data");
        BufferedWriter bw = new BufferedWriter(writer);

        String str = null;
        String[] strs = null;

        int mx = 0;
        DateTime timestamp = null;

        while ((str = br.readLine()) != null) {
            if (str.startsWith("案件ID")) {
                continue;
            }

            strs = str.split("\t");

            mx = dealDualDate(strs[strs.length - 1]);

            dualDateSet.add(strs[strs.length - 1]);

            if (mx > 0) {
                timestamp =
                        new DateTime(
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                        .parse(strs[strs.length - 2])).minusMonths(mx);
            } else {
                timestamp = null;
            }


            bw.newLine();
            bw.write(str + "\t" + (mx > 0 ? ("M" + mx) : "-") + "\t"
                    + (timestamp != null ? timestamp.toString("yyyy-MM-dd HH:mm:ss") : "-"));

            // System.out.println(strs[strs.length - 1] + "\t" + (mx > 0 ? ("M" + mx) : "-") + "\t"
            // + (timestamp != null ? timestamp.toString("yyyy-MM-dd HH:mm:ss") : "-"));
        }

        br.close();
        reader.close();
    }

    /**
     * 根据传入的逾期天数计算出对应的逾期月数
     * 
     * 个别无法判断的情况返回 0
     * 
     * 若报错则返回 -1
     * 
     * 所有条件均不符合返回 -2
     * 
     * @author memoryaxis@gmail.com
     */
    private static int dealDualDate(String dualStr) {

        int result;

        final int FAIL = 0;
        final int ERROR = -1;
        final int NON = -2;

        Matcher matcher = null;

        // 仅数字
        // TODO
        if (Pattern.matches("^[0-9]\\d*$", dualStr)) {

            int imint = Integer.parseInt(dualStr);

            if (imint < 1) {
                result = 2;
            } else if (imint < 6) {
                result = imint * 3;
            } else if (imint < 37) {
                result = imint;
            } else if (imint < 365) {
                result = imint / 12;
            } else if (imint < 365 * 5) {
                result = imint / 30;
            } else if (imint < 365 * 10) {
                result = imint / 30;
            } else {
                result = FAIL;
            }

            return result;
        }


        // 数字加减
        matcher = Pattern.compile("^(\\d*)(以.*|\\+|-)$").matcher(dualStr);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1)) / 30;
            } catch (Exception e) {
                return ERROR;
            }
        }


        // M月数
        matcher = Pattern.compile("(M|m)([0-9]{2}|[0-9])").matcher(dualStr);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(2));
            } catch (Exception e) {
                return ERROR;
            }
        }


        // 天数
        matcher = Pattern.compile("(\\d*)(天)").matcher(dualStr);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1)) / 30;
            } catch (Exception e) {
                return ERROR;
            }
        }


        // 天数区间
        matcher = Pattern.compile("^(\\d*)(-)(\\d*)$").matcher(dualStr);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(3)) / 30;
            } catch (Exception e) {
                return ERROR;
            }
        }


        // 年数
        matcher = Pattern.compile("(\\d*)(年)").matcher(dualStr);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1)) * 12;
            } catch (Exception e) {
                return ERROR;
            }
        }


        // 中文手别
        final String[] chinese_nums = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        final int[] normal_nums = {3, 6, 9, 12, 15, 18, 21, 24, 27};
        if (dualStr.contains("手")) {
            try {
                int lastIndex = dualStr.lastIndexOf("手");
                String handCount = dualStr.substring(0, lastIndex);

                result = 0;
                boolean isChinese = false;
                for (int i = 0; i < chinese_nums.length; i++) {
                    if (handCount.equals(chinese_nums[i])) {
                        result = normal_nums[i];
                        isChinese = true;
                        break;
                    }
                }

                // 数字手别
                if (!isChinese) {
                    result = Integer.parseInt(dualStr.substring(0, lastIndex));
                }

                return result;

            } catch (Exception e) {
                return ERROR;
            }
        }


        // 帐户类型
        final String[] account_type = {"A", "B", "C", "D", "E", "F", "G"};
        final String[] account_type2 = {"a", "b", "c", "d", "e", "f", "g"};
        final int[] account_date = {3, 6, 9, 12, 15, 18, 21};
        for (int i = 0; i < account_type.length; i++) {
            if (dualStr.contains(account_type[i])) {
                result = account_date[i];
                return result;
            } else if (dualStr.contains(account_type2[i])) {
                result = account_date[i];
                return result;
            }
        }



        return NON;
    }

    public static void cell() throws Exception {

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
