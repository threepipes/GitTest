import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a = 0;
		for(int i=0; i<n; i++){
			if(in.nextInt()%2 == 0) a++;
		}
		System.out.println(a);
	}
}
