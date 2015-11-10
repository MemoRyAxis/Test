package test.test.test.test.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;

public class MrLambda {

	public static void main(String[] args) {

		/*
		 * test: outer loop(for in) fast than inner loop(forEach)
		 */

		List<LambdaPo> testList1 = new ArrayList<>(1000);
		for (int idx = 0; idx < 1000; idx++) {
			LambdaPo po = new LambdaPo(idx);
			testList1.add(po);
		}
		List<LambdaPo> testList2 = testList1;

		long startTime = System.currentTimeMillis();
		
		for (LambdaPo po : testList1) {
			System.out.print(po.arrorToken);
		}
		
		long endTime1 = System.currentTimeMillis();

		testList2.forEach(item -> System.out.print(item.arrorToken));
		
		long endTime2 = System.currentTimeMillis();
		
		System.out.println("outer loop");
		System.out.println(endTime1 - startTime);
		
		System.out.println("inner loop");
		System.out.println(endTime2 - endTime1);

		/*
		 * example
		 */

		new JButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button1");
			}
		});

		new JButton().addActionListener(e -> System.out.println("button2"));

		List<LambdaPo> list = new ArrayList<>();
		list.add(new LambdaPo(52));
		list.add(new LambdaPo(32));
		list.add(new LambdaPo(2));
		list.add(new LambdaPo(22));
		list.add(new LambdaPo(42));

		List<LambdaPo> list2 = list;

		System.out.println("--->\t inner class sort list:");
		Collections.sort(list, new Comparator<LambdaPo>() {

			@Override
			public int compare(LambdaPo o1, LambdaPo o2) {
				return o1.size - o2.size;
			}
		});
		Iterator<LambdaPo> iterator = list.iterator();
		while (iterator.hasNext()) {
			LambdaPo po = iterator.next();
			System.out.println(po.size);
		}

		System.out.println("--->\t lambda sort list2: ");
		Collections
				.sort(list2, (LambdaPo o1, LambdaPo o2) -> o1.size - o2.size);
		list2.forEach((po) -> System.out.println(po.size));

		System.out.println("=== RunnableTest ===");

		// Anonymous Runnable
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello world one!");
			}
		};

		// Lambda Runnable
		Runnable r2 = () -> System.out.println("Hello world two!");

		// Run em!
		r1.run();
		r2.run();

		/*
		 * use lambda
		 */

		La la = () -> System.out.println("La.func");
		la.func();

		Object[] objs = new Object[] { "2", 1, 0x20, 10001_10010 };
		Lb lb = params -> {
			params.forEach(item -> System.out.println(item));
		};
		lb.func(Arrays.asList(objs));
	}
}

interface L {

}

interface La extends L {
	void func();
}

interface Lb extends L {
	void func(List<Object> list);
}

class LambdaPo {
	List<Object> arguementList;
	String arrorToken;
	String body;
	int size;

	public LambdaPo(int size) {
		super();
		this.size = size;
		this.arrorToken = "";
	}

	public void doL(L l) {

	}
}