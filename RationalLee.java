//package koko;

import java.util.*;

public class RationalLee {
	private static Scanner in;
	private final static boolean muiltTestCases = true;
	private static int[][][] mem = new int[1001][2][2];

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		var arr = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			arr.add(in.nextInt());
		var g = new ArrayList<Integer>();
		for (int i = 0; i < k; i++)
			g.add( in.nextInt());
		Collections.sort(arr, (a, b) -> b - a);
		Collections.sort(g);
		long ans = 0;
		int start = 0;
		for (int i = 0; i < k; i++) {
			if (g.get(i) == 1) {
				ans += arr.get(start) * 2;
				start++;
			}
		}
		for (int i = start; i < k; i++)
			ans += arr.get(i);

		int index = k - 1;
		for (int i = start; i < k; i++) {
			ans += arr.get(index + g.get(i) - 1);
			index = index + g.get(i) - 1;
		}
		System.out.println(ans);

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

	/**
	 * reenterant | thread safe O(log(sqrt(n))
	 **/
	@SuppressWarnings("unused")
	private static TreeSet<Integer> getDivisors(int n) {
		var st = new TreeSet<Integer>();
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0)
				st.add(i);
			if (n % (n / i) == 0)
				st.add(n / i);
		}
		return st;
	}

	// O(n(log(log(N)))
	@SuppressWarnings("unused")
	private static ArrayList<Integer> primesTill_N(int n) {
		var ans = new ArrayList<Integer>();
		var prime = new ArrayList<Integer>(n + 1);
		for (int i = 0; i < n + 1; i++)
			prime.add(1);

		for (int p = 2; p * p <= n; p++) {
			if (prime.get(p) == 1) {
				for (int i = p * p; i <= n; i += p)
					prime.set(i, 0);
			}
		}

		for (int p = 2; p <= n; p++)
			if (prime.get(p) == 1)
				ans.add(p);
		return ans;
	}

	// O(sqrt(N))
	@SuppressWarnings("unused")
	private static TreeMap<Integer, Integer> getPrimeFactors(int n) {
		var ans = new TreeMap<Integer, Integer>();
		var primes = PRO1.primesTill_N((int) (Math.sqrt(n) + 1));
		for (int p : primes) {
			while (n % p == 0) {
				ans.putIfAbsent(p, 0);
				ans.put(p, ans.get(p) + 1);
				n /= p;
			}
		}
		if (n > 2) {
			ans.putIfAbsent(n, 0);
			ans.put(n, ans.get(n) + 1);
		}
		return ans;
	}
}
