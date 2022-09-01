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
			randomize(nums);
			start = System.nanoTime();

			// Put your method between these two comments

			// search(nums);
			// bubble(nums);
			// insertion(nums);
			selection(nums);
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

	public static void bubble(int[] arr) {
		int n = arr.length;
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					// swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}

			}
		}
	}

	public static void insertion(int[] array) {
		int n = array.length;
		for (int j = 1; j < n; j++) {
			int key = array[j];
			int i = j - 1;
			while ((i > -1) && (array[i] > key)) {
				array[i + 1] = array[i];
				i--;
			}
			array[i + 1] = key;
		}
	}

	public static void selection(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[index]) {
					index = j;// searching for lowest index
				}
			}
			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		}
	}
}
