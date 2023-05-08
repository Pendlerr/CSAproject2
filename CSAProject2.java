package cSAProject;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class CSAProject2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("<-------��һ��------->");
		
		Monkey mo=new Monkey("һ������");
		People pe=new People("һ������");
		System.out.println("���ӵ����֣�"+mo.name);
		mo.speak();
		System.out.println("��������֣�"+pe.name);
		pe.speak();
		pe.think();
		
		System.out.println("<-------�ڶ���------->");

		int n;
		float w;
		System.out.print("���복�ֵĸ�����");
		n=in.nextInt();
		if(n==4) {
			Car car=new Car();
			car.setWheels(n);
			System.out.print("���복�أ�");
			w=in.nextFloat();
			car.setWeight(w);
			System.out.print("����ʵ��������");
			n=in.nextInt();
			car.setLoader(n);
			car.show();
		}
		else {
			Truck truck =new Truck();
			truck.setWheels(n);
			System.out.print("���복�أ�");
			w=in.nextFloat();
			truck.setWeight(w);
			System.out.print("����ʵ��������");
			n=in.nextInt();
			truck.setLoader(n);
			System.out.print("����װ��������");
			w=in.nextFloat();
			truck.setPayload(w);
			truck.show();
		}
		
		System.out.println("<-------������------->");
		
		System.out.print("�����һ����:");
		String a=in.next();
		System.out.print("����ڶ�����:");
		String b=in.next();
		System.out.println("���Ϊ:"+getSum(a,b));
		
		System.out.println("<-------������------->");
		
		System.out.println("�����ͼ������������(�Կո�ָ������硰3 4��)");
		int m=in.nextInt();
		int n1=in.nextInt();
		System.out.println(uniquePaths(m,n1));
		System.out.println("<-------������------->");
		
		String strs[]=new String[3];
		System.out.println("���������ַ���:");
		strs[0]=in.next();
		strs[1]=in.next();
		strs[2]=in.next();
		System.out.println(longestCommonPrefix(strs));
	}

	public static String getSum(String a, String b) {
		List<Integer> la = new ArrayList<Integer>();
		List<Integer> lb = new ArrayList<Integer>();
		String c = "";
		for (int i = a.length() - 1; i >= 0; --i) {
			la.add(a.charAt(i) - '0');
		}
		for (int i = b.length() - 1; i >= 0; --i) {
			lb.add(b.charAt(i) - '0');
		}
		int ad,ci=0,i;
		for (i =0; i<a.length()||i<b.length(); i++) {
			if(i>a.length()-1)
				ad=lb.get(i)+ci;
			else if(i>b.length()-1)
				ad=la.get(i)+ci;
			else
				ad=la.get(i)+lb.get(i)+ci;
			if(ad>9) {
				ad%=10;
				ci=1;
			}
			else ci=0;
			c=ad+c;
		}
			if(ci==1)
			c=1+c;
		return c;
	}

	public static int uniquePaths(int m, int n) {
		//����bfs�㷨����
		int count=0;
		Point [][]p=new Point[m][n];
		p[0][0]=new Point(0,0);
		Queue<Point> que = new LinkedList<Point>();
		que.add(p[0][0]);
		//��point���鸳��ֵ
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				p[i][j]=new Point(j,i);
			}
		}
		
		Point head=new Point();
		while(!(que.isEmpty())) {
			head=que.remove();
			if(head.x==n-1&&head.y==m-1) {
				count+=1;
				continue;
			}
			if(head.x+1<=n-1) {
				que.offer(p[head.y][head.x+1]);
			}
			if(head.y+1<=m-1) {
				que.offer(p[head.y+1][head.x]);
			}
		}
		return count;
	}
	public static String longestCommonPrefix(String [] strs) {
		String ans = "";
		ans=strs[0];
		for(int i=1;i<strs.length;i++) {
			while(!strs[i].startsWith(ans)) {
				ans=ans.substring(0, ans.length()-1);
			}
		}
		return ans;
		}
}


class Monkey{
	String name;
	Monkey(String s){
		name=s;
	}
	public void speak() {
		System.out.println("����ѽѽ......");
	}
}

class People extends Monkey{
	People(String s) {
		super(s);
	}
	public void speak() {
		System.out.println("С�������������˵���ˣ�");
	}
	public void think() {
		System.out.println("��˵��������˼����");
	}
}

class Vehicle{
	int wheels;
	float weight;
	int loader;
	void setWheels(int wheels){
		this.wheels=wheels;
	}
	void setWeight(float weight){
		this.weight=weight;
	}
	void setLoader(int loader){
		this.loader=loader;
	}
}

class Car extends Vehicle{
	public Car() {
	}
	void show() {
		System.out.println("--------------------------");
		System.out.printf("���ֵĸ����ǣ�%d ���أ�%.1f\n",wheels,weight);
		System.out.printf("����һ��С��������5�ˣ�ʵ��%d��",loader);
		if(loader>5)
			System.out.print("���㳬Ա�ˣ�����");
		System.out.println("\n");
	}
}

class Truck extends Vehicle{
	float payload;
	public Truck() {
	}
	void setPayload(float payload){
		this.payload=payload;
	}
	void show() {
		System.out.println("--------------------------");
		System.out.printf("���ֵĸ����ǣ�%d ���أ�%.1f\n",wheels,weight);
		System.out.printf("����һ������������3�ˣ�ʵ��%d��",loader);
		if(loader>3)
			System.out.print("���㳬Ա�ˣ�����");
		System.out.println();
		System.out.printf("����һ������������5000kg������װ��%.1fkg",payload);
		if(payload>5000.0)
			System.out.print("���㳬���ˣ�����");
		System.out.println("\n");
	}
}

class Point{   //���ڵ����⣬���ڼ�¼һ�����λ��
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point() {
		x=y=0;
	}
	void setx(int x) {
		this.x=x;
	}
	void sety(int y) {
		this.y=y;
	}
	void setxy(int x,int y) {
		this.x=x;
		this.y=y;
	}
}