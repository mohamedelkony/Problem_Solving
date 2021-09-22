//https://codeforces.com/contest/515/problem/C
import java.util.*;

public class Drazil_and_Factorial {
	private static Scanner in;
	private final static boolean muiltTestCases = false;

	static void solve() {
		int n = in.nextInt();
		String s = in.next();
		var mp = new TreeMap<Integer, Integer>((a, b) -> b - a);
		for (int i = 2; i <= 9; i++)
			mp.put(i, 0);
		for (int i = 0; i < n; i++) {
			int x = s.charAt(i) - '0';
			if (x == 0 || x == 1)
				continue;
			if (x == 4) {
				mp.put(3, mp.get(3) + 1);
				mp.put(2, mp.get(2) + 2);
			} else if (x == 6) {
				mp.put(5, mp.get(5) + 1);
				mp.put(3, mp.get(3) + 1);
			} else if (x == 8) {
				mp.put(7, mp.get(7) + 1);
				mp.put(2, mp.get(2) + 3);
			} else if (x == 9) {
				mp.put(7, mp.get(7) + 1);
				mp.put(2, mp.get(2) + 1);
				mp.put(3, mp.get(3) + 2);
			} else {
				mp.put(x, mp.get(x) + 1);
			}
		}
		for (var v : mp.entrySet()) {
			for (int i = 0; i < v.getValue(); i++)
				System.out.print(v.getKey());
		}
	}

	public static void main(String[] str) {
		in = new Scanner(System.in);
		int n = 1;
		if (PRO1.muiltTestCases)
			n = in.nextInt();
		while (n-- > 0)
			solve();
		in.close();
	}
}
