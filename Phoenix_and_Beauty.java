//https://codeforces.com/contest/1348/problem/B
import java.util.*;

public class Phoenix_and_Beauty {
	private static Scanner in;

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		var arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = in.nextInt();
		var set1 = new TreeSet<Integer>();
		for (int i = 0; i < n; i++)
			set1.add(arr[i]);

		if (set1.size() > k) {
			System.out.println("-1");
			return;
		}
		int rep = n;
		var ans = new ArrayList<Integer>();
		for (var e : set1)
			ans.add(e);
		while (ans.size() < k) {
			for (var e : set1) {
				ans.add(e);
				if (ans.size() == k)
					break;
			}
		}

		System.out.println(rep * k);
		for (int j = 0; j < rep; j++) {
			for (int i = 0; i < k; i++)
				System.out.print(ans.get(i) + " ");
		}
		System.out.println("");
	}

	public static void main(String[] str) {
		in = new Scanner(System.in);
		int n = in.nextInt();
		while (n-- > 0)
			solve();
		in.close();
	}
}
