//package koko;

import java.util.*;
//https://codeforces.com/contest/1363/problem/B
public class Subsequence_Hate {
	private static Scanner in;
	private final static boolean muiltTestCases = true;

	private static int[][][] mem = new int[1001][2][2];
	private static String s;

	private static int dp(int i, int v0, int v1) {

		if (i >= s.length())
			return 0;

		int ans = mem[i][v0][v1];
		if (ans != -1)
			return ans;

		int v2 = s.charAt(i) == '1' ? 1 : 0;

		boolean mustFlip = false;
		if (v0 == 0 && v1 == 1 && s.charAt(i) == '0')
			mustFlip = true;
		if (v0 == 1 && v1 == 0 && s.charAt(i) == '1')
			mustFlip = true;
		if (i <= 1)
			mustFlip = false;

		boolean cantFlip = false;
		if (v0 == 0 && v1 == 1 && s.charAt(i) == '1')
			cantFlip = true;
		if (v0 == 1 && v1 == 0 && s.charAt(i) == '0')
			cantFlip = true;
		if (i <= 1)
			cantFlip = false;

		int d1 = v1;
		int fd1 = v1;
		if (i > 1) {
			if (v1 == v2)
				d1 = v0;
			else
				fd1 = v0;
		}
		int c1 = Integer.MAX_VALUE;
		int c2 = Integer.MAX_VALUE;
		if (!mustFlip)
			c1 = dp(i + 1, d1, v2);
		if (!cantFlip)
			c2 = 1 + dp(i + 1, fd1, v2 == 0 ? 1 : 0);
		ans = Math.min(c1, c2);
		mem[i][v0][v1] = ans;
		return ans;

	}

	private static void build(int i, int v0, int v1) {

		if (i >= s.length())
			return;

		int v2 = s.charAt(i) == '1' ? 1 : 0;

		boolean mustFlip = false;
		if (v0 == 0 && v1 == 1 && s.charAt(i) == '0')
			mustFlip = true;
		if (v0 == 1 && v1 == 0 && s.charAt(i) == '1')
			mustFlip = true;
		if (i <= 1)
			mustFlip = false;

		boolean cantFlip = false;
		if (v0 == 0 && v1 == 1 && s.charAt(i) == '1')
			cantFlip = true;
		if (v0 == 1 && v1 == 0 && s.charAt(i) == '0')
			cantFlip = true;
		if (i <= 1)
			cantFlip = false;

		int c1 = Integer.MAX_VALUE;
		int c2 = Integer.MAX_VALUE;
		int d1 = v1;
		int fd1 = v1;
		if (i > 1) {
			if (v1 == v2)
				d1 = v0;
			else
				fd1 = v0;
		}
		if (!mustFlip)
			c1 = dp(i + 1, d1, v2);
		if (!cantFlip)
			c2 = 1 + dp(i + 1, fd1, v2 == 0 ? 1 : 0);
		if (c1 < c2) {
			System.out.print(v2);
			build(i + 1, d1, v2);
		} else {
			System.out.print(v2 == 0 ? 1 : 0);
			build(i + 1, fd1, v2 == 0 ? 1 : 0);
		}
	}

	static void solve() {
		s = in.next();
		mem = new int[1001][2][2];
		for (int i = 0; i < 1001; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					mem[i][j][k] = -1;

		System.out.println(dp(0, 0, 0));
		// build(0, 0, 0);
		// System.out.println("");
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
