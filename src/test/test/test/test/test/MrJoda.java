package test.test.test.test.test;

import org.joda.time.DateTime;

public class MrJoda {

  public static void main(String[] args) {
    String dateStr1 = "2013/3/13";
    String dateStr2 = "2013/03/13";
    String dateStr3 = "2013/12/13";

    String[] strs = dateStr2.split("/");
    for (int i = 0; i < strs.length; i++)
      System.out.println(Integer.parseInt(strs[i]));
    DateTime d1 = new DateTime();
  }
}