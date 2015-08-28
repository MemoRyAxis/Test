package test.test.test.test.test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class MrChinese {

  public static void main(String[] args) {
    Comparator cmp = Collator.getInstance(Locale.CHINA);


    String[] arr = {"张三", "李四", "刘翔", "刘六", "郭晶晶", "姚明"};
    Arrays.sort(arr, cmp);

    for (String s : arr) {
      System.out.println(s);
    }
  }
}
