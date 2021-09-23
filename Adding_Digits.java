//https://codeforces.com/contest/260/problem/A
import java.util.*;

public class Adding_Digits {
	public static void main(String[] str) {
		var in=new Scanner(System.in);
		int a,b,n;
		a=in.nextInt();
		b=in.nextInt();
		n=in.nextInt();
		in.close();
		var s=String.valueOf(a);
		for(int i=0;i<=9;i++)
		{
			var ss=new StringBuilder(s);
			ss.append((char)(i+'0'));
			int x=Integer.valueOf(ss.toString());
			if(x%b==0)
			{
				n--;
				while(n-->0)
					ss.append('0');
				System.out.println(ss);
				return;
			}
		}
		System.out.println("-1");
	}
}




