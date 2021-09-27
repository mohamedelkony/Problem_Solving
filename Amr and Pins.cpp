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


ll A1A = 0;


const int prime_mod = (int)1e9 + 7;
const int mem_size = (int)2e5 + 2;
ll mem[mem_size][31];

//#define MULTITESTS
inline void solve(){
	ll r, x1, y1, x2, y2;
	cin >> r >> x1 >> y1 >> x2 >> y2;
	ll dx, dy;
	dx =abs(x2 - x1); dy = abs(y2 - y1);
	
	long double dist =sqrt( dx*dx + dy*dy);
	
	cout << (ll)(ceil(dist/(2*r)));


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
