//https://codeforces.com/contest/804/problem/B
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




map<int, ll>powers_of_two;
//O(expontnent)
long long pow2_longlong( long long exponent){
	long long ans = 1;
	powers_of_two[0] = 1;
	for (int i = 0; i < exponent; i++)
	{
		powers_of_two[i+1] = (powers_of_two[i]*2)%prime_mod;
	}
	return ans;
}
inline void solve(){
	string s; cin >> s;
	ll ans = 0;
	int pre_a = 0;
	int i = 0;
	while (i < s.size() && s[i] == 'b')
		i++;
	pow2_longlong(1e6 + 2);
	while (i < s.size())
	{
		if (s[i] == 'a')
			pre_a++;
		else
		{
			ans = ans + (powers_of_two[pre_a] - 1);
			ans %= prime_mod;
		}
		i++;
	}
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



ll extended_eculidean(ll a, ll b, ll& x, ll& y) {
	if (b == 0) {
		x = 1;
		y = 0;
		return a;
	}
	ll x1, y1;
	ll d = extended_eculidean(b, a % b, x1, y1);
	x = y1;
	y = x1 - y1 * (a / b);
	return d;
}
ll mod_inverse(ll a, ll mod){
	ll x, y;
	ll gcd_ = extended_eculidean(a, mod, x, y);
	if (gcd_ != 1)return -1;
	return (x%mod + mod) % mod;
}
//O(expontnent)
long long pow_longlong(long long base, long long exponent){
		long long ans = 1;
		for (int i = 0; i < exponent; i++)
			ans = ans*base;
		return ans;
}
