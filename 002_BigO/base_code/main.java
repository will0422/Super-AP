import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	static final long createdNano = System.nanoTime();

	public static void main(String args[]) {
		long start = System.nanoTime();
		long finish = System.nanoTime();
		System.out.println("Created: " + createdNano);

		int[] times = { 10, 100, 1000, 10000, 100000, 1000000, 10000000 };
		int[] nums = new int[100];

		System.out.println("-------------------Test-------------------");
		System.out.println("");
		for (int i : times) {
			System.out.println("Interval: " + i);

			nums = new int[i];
			start = System.nanoTime();

			// Put your method between these two comments
			// randomize(nums);
			search(nums);
			//

			finish = System.nanoTime();
			System.out.println("Started: " + start);
			System.out.println("Finished: " + finish);
			System.out.println("Duration: " + (finish - start));
			System.out.println("");
		}
	}

	public static void randomize(int[] nums) {
		int numLength = nums.length;
		for (int c = 0; c < numLength; c++) {
			int num = (int) (Math.random() * 200000);
			nums[c] = num;
			// System.out.println(nums[c]);
		}
	}

	public static boolean search(int[] nums) {
		int numLength = nums.length;
		boolean found = false;
		int targ = (int) (Math.random() * 200000);
		for (int c = 0; c < numLength; c++) {
			int num = (int) (Math.random() * 200000);
			nums[c] = num;
			if (nums[c] == targ) {
				found = true;
			}
		}
		return found;
	}
}
