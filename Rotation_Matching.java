//https://codeforces.com/contest/1365/problem/C
import java.util.*;

public class Rotation_Matching {
	private static Scanner in;
	private final static boolean muiltTestCases = false;

	private static int[][][] mem = new int[1001][2][2];

	static void solve() {
		int n = in.nextInt();
		var a = new int[n];
		var b = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		var indexOf=new int[n+1];
		for (int i = 0; i < n; i++) 
		{	b[i] = in.nextInt();
			indexOf[b[i]]=i;
		}	
		
		
		var mp=new int[n];
		for(int i=0;i<n;i++)mp[i]=0;
		for (int i = 0; i < n; i++) {
			int diff=(i-indexOf[a[i]]+n)%n;
			mp[diff]++;
		}
		int ans = 0;
		for(int i=0;i<n;i++)
			ans=Math.max(ans, mp[i]);
		
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
