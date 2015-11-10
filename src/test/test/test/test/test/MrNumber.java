package test.test.test.test.test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MrNumber {

    public static void main(String[] args) throws IOException {

        double ap = 0.3;
        double bp = 0.7;

        List<JobCard> jobCards = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 100; i++) {
            double amount = Double.parseDouble(new DecimalFormat("#.##").format(Math.random() * 10000));
            JobCard j = new JobCard();
            j.setId(i);
            j.setMoney(amount);
            jobCards.add(j);
            count += amount;
        }

        System.out.println("total: " + count);

        double allotA = count * ap;
        double allotB = count * bp;

        System.out.println(allotA);
        System.out.println(allotB);

        Collections.sort(jobCards, new Comparator<JobCard>() {
            @Override
            public int compare(JobCard o1, JobCard o2) {
                return new Double(o1.getMoney()).compareTo(new Double(o2.getMoney()));
            }
        });

        boolean plus = true;
        List<JobCard> listA = new ArrayList<JobCard>();
//        List<JobCard> listB = new ArrayList<JobCard>();
        while (allotA > 0) {
            JobCard temp = jobCards.remove((plus ? 0 : (jobCards.size() - 1)));
            listA.add(temp);
            allotA -= temp.getMoney();
            plus = plus ? false : true;
        }

        allotA = 0;
        System.out.println("list A :");
        for (JobCard j : listA) {
            allotA += j.getMoney();
            System.out.println("[" + j.getId() + "]: " + j.getMoney());
        }

        allotB = 0;
        System.out.println("list B :");
        for (JobCard j : jobCards) {
            allotB += j.getMoney();
            System.out.println("[" + j.getId() + "]: " + j.getMoney());
        }


        System.out.println("A 单数: " + listA.size() + ", 钱数: " + allotA + ", 钱数占比: " + (allotA / count));
        System.out.println("B 单数: " + jobCards.size() + ", 钱数: " + allotB + ", 钱数占比: " + (allotB / count));;


        // System.out.println(BigDecimal.ROUND_HALF_UP);
        // System.out.println(Math.rint(123.1233));
        // System.out.println("四舍五入取整:(2)="
        // + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP));
        // System.out.println("四舍五入取整:(2.1)="
        // + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
        // System.out.println("四舍五入取整:(2.5)="
        // + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
        // System.out.println("四舍五入取整:(2.9)="
        // + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));
        //
        // char[] nums = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        // for (int i = 0; i < 1000; i++) {
        // for (int j = 0; j < 300; j++) {
        // System.out.print(nums[(int) (Math.random() * nums.length)]);
        // }
        // System.out.println();
        // }
        //
        // double d1 = 123.0000;
        // System.out.println(d1 == (int) d1);
    }
}


class JobCard {

    private int id;

    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
