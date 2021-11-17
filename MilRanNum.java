package Part2;
import java.util.Random;

public class MilRanNum {
	public static long sumThread = 0;
	public static long sumThread2 = 0;
	public static long sumThread3 = 0;

	public static void main(String[] args) {

		Random rand = new Random();
		long sum = 0;
		int[] arr = new int[200000000];/////Array length
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 1 +  rand.nextInt(10);
		}

		/////Calculate time for summing array
		long startTime2 = System.currentTimeMillis();//////Starting time for summing array

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		long endTime2 = System.currentTimeMillis();/////Ending time for summing array

		long finalTime2 = endTime2 - startTime2;//////calculate time for summing array

		System.out.println("The sum of the array is: " + sum);
		System.out.println("Array time = " + finalTime2);  

		////////////////////////Multiple Threads

		Thread t1 = new Thread (new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < arr.length / 2; i++) {/////Run first half numbers of the array
					sumThread += arr[i];
				}
			}
		});

		Thread t2 = new Thread (new Runnable() {

			@Override
			public void run() {
				for (int i = arr.length / 2; i < arr.length; i++) {////Run 2nd half numbers of the array
					sumThread2 += arr[i];
				}
			}
		});
		long startTime = System.currentTimeMillis();//////Start time for threads to run array

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sumThread3 = sumThread + sumThread2;////Sum of array, using two threads
		long endTime = System.currentTimeMillis();/////End time for threads to run array
		long finalTime = endTime - startTime;////Calculate time for threads to run array
		System.out.println("Value: " + sumThread3);
		System.out.println("Thread time = " + finalTime);

	}
}
