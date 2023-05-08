package cSAProject;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class CSAProject2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("<-------第一题------->");
		
		Monkey mo=new Monkey("一个猴子");
		People pe=new People("一个人类");
		System.out.println("猴子的名字："+mo.name);
		mo.speak();
		System.out.println("人类的名字："+pe.name);
		pe.speak();
		pe.think();
		
		System.out.println("<-------第二题------->");

		int n;
		float w;
		System.out.print("输入车轮的个数：");
		n=in.nextInt();
		if(n==4) {
			Car car=new Car();
			car.setWheels(n);
			System.out.print("输入车重：");
			w=in.nextFloat();
			car.setWeight(w);
			System.out.print("输入实载人数：");
			n=in.nextInt();
			car.setLoader(n);
			car.show();
		}
		else {
			Truck truck =new Truck();
			truck.setWheels(n);
			System.out.print("输入车重：");
			w=in.nextFloat();
			truck.setWeight(w);
			System.out.print("输入实载人数：");
			n=in.nextInt();
			truck.setLoader(n);
			System.out.print("输入装载重量：");
			w=in.nextFloat();
			truck.setPayload(w);
			truck.show();
		}
		
		System.out.println("<-------第三题------->");
		
		System.out.print("输入第一个数:");
		String a=in.next();
		System.out.print("输入第二个数:");
		String b=in.next();
		System.out.println("结果为:"+getSum(a,b));
		
		System.out.println("<-------第四题------->");
		
		System.out.println("输入地图的行数和列数(以空格分隔，例如“3 4”)");
		int m=in.nextInt();
		int n1=in.nextInt();
		System.out.println(uniquePaths(m,n1));
		System.out.println("<-------第五题------->");
		
		String strs[]=new String[3];
		System.out.println("输入三个字符串:");
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
		//采用bfs算法遍历
		int count=0;
		Point [][]p=new Point[m][n];
		p[0][0]=new Point(0,0);
		Queue<Point> que = new LinkedList<Point>();
		que.add(p[0][0]);
		//给point数组赋初值
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
		System.out.println("咿咿呀呀......");
	}
}

class People extends Monkey{
	People(String s) {
		super(s);
	}
	public void speak() {
		System.out.println("小样儿，不错嘛！会说话了！");
	}
	public void think() {
		System.out.println("别说话！认真思考！");
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
		System.out.printf("车轮的个数是：%d 车重：%.1f\n",wheels,weight);
		System.out.printf("这是一辆小车，能载5人，实载%d人",loader);
		if(loader>5)
			System.out.print("，你超员了！！！");
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
		System.out.printf("车轮的个数是：%d 车重：%.1f\n",wheels,weight);
		System.out.printf("这是一辆卡车，能载3人，实载%d人",loader);
		if(loader>3)
			System.out.print("，你超员了！！！");
		System.out.println();
		System.out.printf("这是一辆卡车，核载5000kg，你已装载%.1fkg",payload);
		if(payload>5000.0)
			System.out.print("，你超载了！！！");
		System.out.println("\n");
	}
}

class Point{   //用于第四题，用于记录一个点的位置
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