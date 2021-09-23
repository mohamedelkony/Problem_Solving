//https://codeforces.com/contest/388/problem/A
import java.util.*;

public class Fox_and_Box_Accumulation {
	public static void main(String[] str) {
		var in=new Scanner(System.in);
		int n=in.nextInt();
		var mp=new TreeMap<Integer,Integer>();
		for(int i=0;i<n;i++)
			{
			int x=in.nextInt();
			mp.put(x, mp.getOrDefault(x, 0)+1);
			}
	in.close();
	
	var piles=new TreeMap<Integer,Integer>();
	{	
		int min_cap=mp.firstKey();
		int min_cap_count=mp.firstEntry().getValue();
		piles.put(Math.min(min_cap+1,min_cap_count),min_cap_count/(min_cap+1));
		if(min_cap_count%(min_cap+1)>0)
			piles.put(min_cap_count%(min_cap+1),1);
	
		mp.remove(min_cap);
	}
		while(!mp.isEmpty())
		{
			int min_cap=0;
			for(var e:piles.entrySet())
			{
				if(e.getKey()<=mp.firstKey())
					min_cap=e.getKey();
			}
			if(min_cap==0)
				piles.put(1, piles.getOrDefault(1, 0)+1);
			else
			{
				piles.put(min_cap, piles.get(min_cap)-1);
				if(piles.get(min_cap)<=0)
					piles.remove(min_cap);
				piles.putIfAbsent(min_cap+1, 0);
				piles.put(min_cap+1, piles.get(min_cap+1)+1);
			}
			mp.put(mp.firstKey(), mp.get(mp.firstKey())-1);
			if(mp.get(mp.firstKey())<=0)
				mp.remove(mp.firstKey());
			}
		int ans=0;
		for(var e:piles.entrySet())
			ans+=e.getValue();
		System.out.println(ans+"");
	}
	
	
}




