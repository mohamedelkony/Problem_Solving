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


const int prime_mod = (int)1e9 + 7;
const int mem_size = (int)2e5 + 2;
ll mem[mem_size][31];




inline void solve(){
	int n; cin >> n;
	map<int, int>x_axis,y_axis;
	map<pair<int, int>, int> point_freq;
	for (int i = 0; i < n; i++)
	{
		int x, y; cin >> x >> y;
		point_freq[pair<int, int>{x, y}]++;
		x_axis[x]++;
		y_axis[y]++; 
	}

	ll ans = 0;
	for (auto itr : x_axis)
	{
		ll num = itr.second;
		ans += (num*(num - 1)) / 2;
	}
	for (auto itr : y_axis)
	{
		ll num = itr.second;
		ans += (num*(num - 1)) / 2;
	}
	for (auto itr : point_freq)
	{
		ll num = itr.second;
		ans -= (num*(num - 1)) / 2;
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

