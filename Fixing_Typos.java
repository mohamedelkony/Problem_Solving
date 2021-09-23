//https://codeforces.com/contest/363/problem/C
import java.util.*;

public class Fixing_Typos {
	public static void main(String[] str) {
		var in=new Scanner(System.in);
		String s=in.next();
		in.close();
		if(s.length()<=2)
		{
			System.out.println(s);
			return;
		}
		StringBuilder ans=new StringBuilder();
		ans.append(s.substring(0, 2));
		var q=new ArrayDeque<Character>();
		q.add(s.charAt(0));
		q.add(s.charAt(1));
		int i=2;
		while(i<s.length())
		{
			if(q.size()>=4)
				q.pollFirst();
			q.addLast(s.charAt(i));
			var arr=new ArrayList<Character>();
			for(var c:q)
				arr.add(c);
			
			char c1=arr.get(arr.size()-1);
			char c2=arr.get(arr.size()-2);
			char c3=arr.get(arr.size()-3);
			boolean accepted=true;
			if(c1==c2 &&c2==c3)
				accepted=false;
			else if(arr.size()==4)
			{
				if((arr.get(0)==arr.get(1))&&(arr.get(2)==arr.get(3)))
					accepted=false;
			}
			
			if(accepted)
				ans.append(s.charAt(i));	
			else
				q.pollLast();
			
			//System.out.println(q+" @ "+ans);
			i++;
		}
		System.out.println(ans);
	}
}




