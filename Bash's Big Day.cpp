//https://codeforces.com/contest/757/problem/B
#include<iomanip> 
#include<iostream>
#include<sstream>
#include<cstdlib>
#include<vector>
#include<string>
#include<array>
#include<list>
#include<set>
#include<bitset>
#include<map>
#include<unordered_set>
#include<unordered_map>
#include<stack>
#include<queue>
#include<algorithm>
#include<regex>
#include<random>
#include<new>
#include<cassert>
#include<stdexcept>
#include<memory>
#include<functional>
#include<numeric>
#include<limits>
#include<cstddef>
#if defined(_WIN32)||defined(_WIN64) 
#include<windows.h>
#include<Lmcons.h>
#endif

using namespace std;
using namespace std;
#define _CRT_SECURE_NO_WARNINGS
#define endl '\n'
#define rep(rep_iterator_qq12,qwer154_w) for(int qwer154_w=0;qwer154_w<rep_iterator_qq12;qwer154_w++)
#define ll long long
#define ld long double
#define FAST_IO_1699C ios_base::sync_with_stdio(false); std::cin.tie(NULL); std::cout.tie(NULL);
#define IOVIATXTFILE freopen("input.txt","r",stdin);freopen("output.txt","w",stdout);
#define OO ~(1LL << 63)
#define oo ~(1<<31)

ll A1A = 0;
ll dsu_find(ll id);
void dsu_unify(ll id1, ll id2);
void initialize_dsu(ll n);
vector<ll>primes_till_n(ll n);
set<ll>get_divisors(ll n);
map<ll, ll> prime_factors(ll n);
ll gcd(ll a, ll b);
ll pow_longlong(ll base, ll exponent);
bool is_2lines_intersect(int L1, int R1, int L2, int R2);
bool is_2lines_intersect(pair<int, int>&a, pair<int, int>&b);
vector<ll> dsu_ids;
vector<ll> dsu_size;
ll dsu_compnents;
vector<ll>get_segments_of_binary_string(string s);
ll extended_eculidean(ll a, ll b, ll& x, ll& y);
ll mod_inverse(ll a, ll mod);
ll nCr(ll n, ll r);
const int prime_mod = (int)1e9 + 7;
const int mem_size = (int)2e5 + 2;
ll mem[mem_size][31];

inline void solve(){
	int n; cin >> n;
	int sz = 1e5;
	auto primes = primes_till_n(sz);
	map<int, int>mp;

	while (n--)
	{
		int x; cin >> x;
		auto f = prime_factors(x);
		for (auto i : f)
			mp[i.first]++;
	}
	int ans = 1;
	for (auto i : mp)
		ans = max(ans, i.second);
	
	cout << ans;
	
}



int main(){
	FAST_IO_1699C;
	int t = 1;
	//cin >> t;
	while (t--){
		solve();
		cout << '\n' << '\n';
	}
#if defined(_WIN32)||defined(_WIN64) 
	char username[UNLEN + 1];
	DWORD username_len = UNLEN + 1;
	GetUserName(username, &username_len);
	if (string(username) == "konerfucker")
	{
		ll N1N = 1;
		cout << "\n\n\n\n\n\n\n\n------------------------------------------------------";
		cout << scientific << A1A << " " << float(A1A) / N1N << "N " << float(A1A) / pow(N1N, 2) << "N^2 " << float(A1A) / pow(N1N, 3) << "N^3" << endl;
		cout << sizeof(mem) / (1 << 20) << " MB" << endl;
		A1A = 0;
		cin >> N1N;
	}
#endif
	return 0;
}
//O(n(log(log(N)))
vector<ll> primes_till_n(ll n){
	vector<ll> ans;
	vector<ll> prime(n + 1, 1);
	for (ll p = 2; p*p <= n; p++){
		if (prime[p])
		{
			for (ll i = p*p; i <= n; i += p)
				prime[i] = false;
		}
	}

	for (ll p = 2; p <= n; p++)
		if (prime[p]>0)
			ans.push_back(p);
	return ans;
}
//O(sqrt(N))
set<ll>get_divisors(ll n)
{
	set<ll>divs;
	for (ll i = 1; i*i <= n; i++)
	{
		if (n%i == 0)
			divs.insert(i);
		if (n % (n / i) == 0)
			divs.insert(n / i);
	}
	return divs;
}
//O(sqrt(N))
map<ll, ll> prime_factors(ll n){
	auto primes = primes_till_n(sqrt(n) + 1);
	map<ll, ll> mp;
	for (ll i : primes)
	{
		while (n%i == 0)
		{
			n /= i;
			mp[i]++;
		}
	}
	if (n > 2)
		mp[n]++;
	return mp;
}
