package test.test.test.test.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MrRegex {

    public static void main(String[] args) {

        // testMatchResultNull();
        justSplit();

    }

    public static void testMatchResultNull() {

        // String json =
        // "信用卡储蓄卡开卡数量: 5;";
        String json = "信用卡过去第一个月支出金额: 未知;";
        // String json =
        // "信用卡储蓄卡开卡数量: 5;信用卡过去第一个月支出金额: 未知;信用卡过去第一个月取现金额: 未知;信用卡过去第一个月收入金额: 未知;过去第一个月按时还贷;储蓄卡过去第一个月末可用余额: 未知;储蓄卡过去第一个月支出金额: [0,500];储蓄卡过去第一个月投资金额: 未知;储蓄卡过去第一个月还贷金额: 未知;储蓄卡过去第一个月收入金额: [0,500];过去第一个月贷款金额: 未知;信用卡过去第二个月支出金额: 未知;信用卡过去第二个月取现金额: 未知;信用卡过去第二个月收入金额: 未知;过去第二个月按时还贷;储蓄卡过去第二个月末可用余额: 未知;储蓄卡过去第二个月支出金额: [0,500];储蓄卡过去第二个月投资金额: 未知;储蓄卡过去第二个月还贷金额: 未知;储蓄卡过去第二个月收入金额: 未知;过去第二个月贷款金额: 未知;信用卡过去第三个月支出金额: [0,500];信用卡过去第三个月取现金额: 未知;信用卡过去第三个月收入金额: 未知;过去第三个月按时还贷;储蓄卡过去第三个月末可用余额: 未知;储蓄卡过去第三个月支出金额: [0,500];储蓄卡过去第三个月投资金额: 未知;储蓄卡过去第三个月还贷金额: 未知;储蓄卡过去第三个月收入金额: [1000,1500];过去第三个月贷款金额: 未知;";
        Pattern pattern = Pattern.compile("([.&[^;]]*;)");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group());
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(matcher.group(i + 1));
            }
        }


    }

    public static void justSplit() {
        String json =
                "信用卡储蓄卡开卡数量: 5;信用卡过去第一个月支出金额: 未知;信用卡过去第一个月取现金额: 未知;信用卡过去第一个月收入金额: 未知;过去第一个月按时还贷;储蓄卡过去第一个月末可用余额: 未知;储蓄卡过去第一个月支出金额: [0,500];储蓄卡过去第一个月投资金额: 未知;储蓄卡过去第一个月还贷金额: 未知;储蓄卡过去第一个月收入金额: [0,500];过去第一个月贷款金额: 未知;信用卡过去第二个月支出金额: 未知;信用卡过去第二个月取现金额: 未知;信用卡过去第二个月收入金额: 未知;过去第二个月按时还贷;储蓄卡过去第二个月末可用余额: 未知;储蓄卡过去第二个月支出金额: [0,500];储蓄卡过去第二个月投资金额: 未知;储蓄卡过去第二个月还贷金额: 未知;储蓄卡过去第二个月收入金额: 未知;过去第二个月贷款金额: 未知;信用卡过去第三个月支出金额: [0,500];信用卡过去第三个月取现金额: 未知;信用卡过去第三个月收入金额: 未知;过去第三个月按时还贷;储蓄卡过去第三个月末可用余额: 未知;储蓄卡过去第三个月支出金额: [0,500];储蓄卡过去第三个月投资金额: 未知;储蓄卡过去第三个月还贷金额: 未知;储蓄卡过去第三个月收入金额: [1000,1500];过去第三个月贷款金额: 未知;";

        StringBuilder resultJson = new StringBuilder();

        String[] strs = json.split(";");

        for (int i = 0; i < strs.length; i++) {
            if (!strs[i].contains("未知")) {
                resultJson.append(strs[i]).append(';');
            }
        }
        System.out.println(json);
        System.out.println(resultJson);

    }
}
