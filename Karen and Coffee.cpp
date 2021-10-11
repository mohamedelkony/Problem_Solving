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

const int prime_mod = (int)1e9 + 7;
const int mem_size = (int)2e5 + 2;
ll mem[mem_size][31];


inline void solve(){
	int n, k, q;
	cin >> n >> k >> q;
	int SZ = 2e5;
	vector<int>arr_delta(SZ+2, 0);
	while (n--)
	{
		int a, b; cin >> a >> b;
	
		arr_delta[a]++;
		arr_delta[b + 1]--;
	}
	vector<int>temp_freq(SZ+1, 0);
	temp_freq[0] = arr_delta[0];
	for (int i = 1; i < SZ+1; i++)
		temp_freq[i] = temp_freq[i - 1] + arr_delta[i];

	
	vector<int>prefix_sum(temp_freq.size()+1, 0);
	for (int i = 1; i < prefix_sum.size(); i++)
		prefix_sum[i] = prefix_sum[i - 1] + (temp_freq[i - 1] >= k ? 1 : 0);

	while (q--)
	{
		int a, b; cin>>a >> b;
		cout << prefix_sum[b + 1] - prefix_sum[a] << endl;
	}
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
