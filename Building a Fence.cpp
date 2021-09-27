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

#define MULTITESTS
inline void solve(){	
	ll n, k; cin >> n >> k;
	vector<ll>h(n, 0);rep(n, i)cin >> h[i];
	ll pre_miny = h[0], pre_maxy = h[0];
	for (int i = 1; i < n; i++)
	{
		ll crnt_miny=max(pre_miny - (k - 1),h[i]);
		ll crnt_maxy =min(pre_maxy + k - 1,h[i]+k-1);
		
		if (i == n - 1)
		{
			crnt_maxy = min(pre_maxy + k - 1, h[i]);
			if (!(h[i] >= crnt_miny&&h[i] <= crnt_maxy))
			{
				cout << "NO"; return;
			}
		}
		if (!(h[i]+k-1>=crnt_miny&&h[i]<=crnt_maxy))
		{
			cout << "NO"; return;
		}
		pre_miny = crnt_miny;
		pre_maxy = crnt_maxy;
	}
	cout << "YES";
}




int main(){
	FAST_IO_1699C;
	int t = 1;
	#ifdef MULTITESTS 
		cin >> t;
	#endif
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
