//https://codeforces.com/contest/479/problem/C
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

class dsu_interface
{
public:
	virtual void unify(int id1, int id2) = 0;
	virtual int find(int id) = 0;
	virtual int get_components_count() = 0;
	virtual map<int, vector<int>> get_components() = 0;
};
class dsu :public dsu_interface
{
private:
	int compnents_count;
	vector<int>id_of_compnent;
	vector<int>size_of_compnent;
public:
	dsu(int elements_count)
	{
		id_of_compnent.resize(elements_count);
		size_of_compnent.resize(elements_count);
		this->compnents_count = elements_count;

		for (int i = 0; i < elements_count; i++)
		{
			id_of_compnent[i] = i;
			size_of_compnent[i] = 1;
		}
	}
	int find(int element_id)override
	{
		int root = element_id;
		while (root != id_of_compnent[root])
			root = id_of_compnent[root];

		//path comperssion
		while (root != element_id)
		{
			int next = id_of_compnent[element_id];
			id_of_compnent[element_id] = root;
			element_id = next;
		}
		return element_id;
	}
	void unify(int id1, int id2)override
	{
		int root1 = this->find(id1);
		int root2 = this->find(id2);

		if (root1 == root2)return;
		if (size_of_compnent[root1] > size_of_compnent[root2])
		{
			size_of_compnent[root1] += size_of_compnent[root2];
			id_of_compnent[root2] = root1;
		}
		else{
			size_of_compnent[root2] += size_of_compnent[root1];
			id_of_compnent[root1] = root2;
		}
		this->compnents_count--;
	}
	map<int, vector<int>> get_components() override
	{
		set<int> st;
		for (int i : this->id_of_compnent)
			st.insert(i);
		map<int, int> ordinal_id_of;
		int id = 0;
		for (int i : st)
			ordinal_id_of[i] = id++;

		map<int, vector<int>>components;
		for (int i = 0; i < this->id_of_compnent.size(); i++)
			components[ordinal_id_of[this->id_of_compnent[i]]].push_back(i);
		return components;
	}
	virtual int get_components_count() override
	{
		return this->compnents_count;
	}
};

const int prime_mod = (int)1e9 + 7;
const int mem_size = (int)2e5 + 2;
ll mem[mem_size][31];



struct point{
	ll x, y, tag,visited;
	point(ll _x, ll _y,ll t){
		x = _x; y = _y; tag = t;
	}
	point(ll _x, ll _y) :x{ _x }, y{ _y }, tag{ -1 }
	{}
	point():tag{-1}
	{}
	point(std::initializer_list<ll> l)
	{
		auto itr = l.begin();
		x = *itr;
		itr++;
		y = *itr;
		itr++;
		if (itr != l.end())
			tag = *itr;
	}

};
struct point_comper{
	bool operator()(point a, point b){
		if (a.x == b.x)
			return a.y<b.y;
		return a.x<b.x;
	}
};

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
void dfs(int n, vector<vector<int>>&g)
{
	visited[n] = true;
	nodes_count++;
	for (int i : g[n])
	{
		if (!visited[i])
			dfs(i,g);
	}
}

inline void solve(){
	int n; cin >> n;
	vector<pair<int, int>>a(n, pair<int,int>{});
	for (int i = 0; i < n; i++)
		cin >> a[i].first>>a[i].second;

	sort(a.begin(), a.end(), [](pair<int, int>&p1, pair<int, int>&p2){
		if (p1.first == p2.first)
			return p1.second < p2.second;
		return p1.first < p2.first; 
	});
	int current_day = 1;
	for (int i = 0; i < n; i++)
	{
		if (a[i].second >= current_day&&a[i].second < a[i].first)
			current_day = a[i].second;
		else
			current_day = a[i].first;
	}
	cout << current_day;
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
//O(r)
ll nCr(ll n, ll r)
{
	if (r>n)return 0;
	ll res = 1;
	// Since nCr(n, r) = nCr(n, n-r) 
	if (r > n - r)
		r = n - r;

	//nCr = [n * (n-1) * .... * (n-r+1)] / [r * (r-1) * .... * 1]
	for (int i = 0; i < r; ++i) {
		res *= (n - i);
		res /= (i + 1);
	}
	return res;
}


bool is_2lines_intersect(int L1, int R1, int L2, int R2)
	{
		if (L1 > R2 || L2 > R1)
			return false;
		return true;
	}
bool is_2lines_intersect(pair<int, int>&a, pair<int, int>&b){
		return is_2lines_intersect(a.first, a.second, b.first, b.second);
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

int bs_on_most_left(int L, int R, int arr[])
{
		int mn = arr[L]; int LL = L;
		while (L<R)
		{
			int mid = L + (R - L) / 2;
			if (arr[mid] > 2 * mn)
			{
				R = mid;
			}
			else
			{
				L = mid + 1;
			}
		}
		if (!(arr[L] > 2 * mn))
			return -1;
		return L - LL;
	}
int bs_on_most_right(int L, int R, int arr[])
	{
		int mx = arr[R]; int RR = R;
		while (L < R)
		{
			int mid = L + (R - L + 1) / 2;
			if (arr[mid] < ceil(mx / 2.0))
				L = mid;
			else
				R = mid - 1;
		}
		if (arr[R] >= ceil(mx / 2.0))
			return -1;
		return RR - R;
	}
char next_small_char(char c){
	char ans = ((c - 'a') + 26 + 1) % 26;
	return ans + 'a';
}
//O(log(n))
ll gcd(ll a, ll b)
{
	if (b == 0)return a;
	return gcd(b, a%b);
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
void bridges_dfs(vector<vector<int>>& g, int at, int parent, vector<bool>& visited, vector<int>& ids, vector<int>& low, vector<pair<int, int>>& ans)
	{
		/*
		*low:lowest id that node could reach from another path exculding the path it was discoverd from
		*initialze visited and low with time of discover
		*Don't skip visited so,we indicate reaching them from another parent(way)
		*so we update the lowest id that parent can reach.
		*/

		static int id = 0;
		visited[at] = true; ids[at] = low[at] = id++;

		for (auto to : g[at])
		{
			//don't dicover the path it came from 
			if (to == parent)continue;

			if (visited[to])
			{
				//node to was reached second time via another path
				low[at] = min(low[at], ids[to]);
			}
			else
			{
				bridges_dfs(g, to, at, visited, ids, low, ans);
				//after finishing [to] has a its final low value
				//so update value of parent e.g. at 
				low[at] = min(low[at], low[to]);
				if (low[to] > ids[at])
					ans.push_back({ at, to });
			}
		}
	}
//O(V+E) for undirected & directed graphs
	//source https://www.youtube.com/watch?v=aZXi1unBdJA
vector<pair<int, int>> bridges(vector<vector<int>> g)
	{
		vector<bool> visited(g.size());
		vector<pair<int, int>> ans;
		vector<int> low(g.size()), id(g.size());

		for (int i = 0; i < g.size(); i++)
		{
			if (!visited[i])
			{
				bridges_dfs(g, i, -1, visited, id, low, ans);
			}
		}

		return ans;
	}
//O(expontnent)
long long pow_longlong(long long base, long long exponent){
		long long ans = 1;
		for (int i = 0; i < exponent; i++)
			ans = ans*base;
		return ans;
}
vector<ll>get_segments_of_binary_string(string s)

{
	vector<ll>ans;
	bool is_zeros_seg = (s[0] == '0');
	ll crnt_seg_lenght = 1;
	for (int i = 1; i < s.size(); i++)
	{
		if ((is_zeros_seg&&s[i] == '0') || (!is_zeros_seg&&s[i] == '1'))
			crnt_seg_lenght++;
		else
		{
			ans.push_back(crnt_seg_lenght);
			crnt_seg_lenght = 1;
			is_zeros_seg = !is_zeros_seg;
		}
	}
	ans.push_back(crnt_seg_lenght);
	return ans;
}
