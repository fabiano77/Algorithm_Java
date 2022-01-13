package beakjoon.basic;

import java.util.Scanner;

public class ps_10871 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int x = scanner.nextInt();
		for (int i = 0; i < n; i++)
		{
			int data = scanner.nextInt();
			if (data < x)
			{
				System.out.print(data+" ");
			}
		}
		scanner.close();
	}

}
