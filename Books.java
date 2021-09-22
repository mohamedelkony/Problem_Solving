//https://codeforces.com/contest/279/problem/B
import java.util.*;

public class Books {
	public static void main(String[] str) {
		var in = new Scanner(System.in);
		int n = in.nextInt();
		int fund = in.nextInt();
		var arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		in.close();
		var prefix = new long[n + 1];
		prefix[0] = 0;
		for (int i = 1; i < n + 1; i++)
			prefix[i] = prefix[i - 1] + arr[i - 1];
		int best = 0;
		for (int from = 0; from < n; from++) {
			int left = from;
			int right = n - 1;
			while (left <= right) {
				int mid = left + (right - left + 1) / 2;
				long cost = prefix[mid + 1] - prefix[from];
				if (fund >= cost) {
					left = mid;
					best = Math.max(mid - from + 1, best);
				} else
					right = mid - 1;
				if (left == right) {
					if (fund >= arr[left])
						best = Math.max(1, best);
					break;
				}
			}
		}
		System.out.println(best);
	}
}
