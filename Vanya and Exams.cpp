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




const int prime_mod = (int)1e9 + 7;
const int mem_size = (int)2e5 + 2;
ll mem[mem_size][31];


struct pair_comper{
	bool operator()(pair<int, int> a, pair<int, int> b){
		return a.second < b.second;
	}
};
bool comper1(pair<int, int>a, pair<int, int> b)
{
	return a.first > b.first;
}
vector<int>visited;
int nodes_count = 0;
//#define MULTITESTS
inline void solve(){
	int n,mx,avg; cin >> n>>mx>>avg;
	vector<pair<int, int>>a(n, pair<int, int>{});
	ll needed = 0;
	for (int i = 0; i < n; i++)
	{
		cin >> a[i].first >> a[i].second;
		needed += avg-a[i].first;
	}
	if (needed <= 0)
	{
		cout << 0;
		return;
	}
	sort(a.begin(), a.end(), [](pair<int, int>&p1, pair<int, int>&p2){
		if (p1.second == p2.second)
			return p1.first < p2.first;
		return p1.second < p2.second;
	});

	ll cost = 0;
	for (int i = 0; i < n; i++)
	{
		if (a[i].first >= mx)continue;

		if (needed < mx - a[i].first)
		{
			cost += a[i].second*needed;
			break;
		}
		else
		{
			cost += a[i].second*(ll)(mx - a[i].first);
			needed -= mx - a[i].first;
		}
	}
	cout << cost;
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
