//https://codeforces.com/contest/1110/problem/B
import java.util.*;

public class Tape {
	public static void main(String[] str) {
		var in = new Scanner(System.in);
		int n, k, m;
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		var arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		in.close();
		
		if (n == k) {
			System.out.print(n);
			return;
		}
		
		var q = new PriorityQueue<Integer>((a, b) -> {
			return b - a;
		});
		for (int i = 0; i < n - 1; i++)
			q.add(arr[i + 1] - arr[i]);
		for (int i = 0; i < k-1; i++) {
			q.poll();
		}
		int ans=0;
		while(!q.isEmpty()) {
			ans+=q.poll();
		}
		ans+=k;
		System.out.print(ans);
	}
}
