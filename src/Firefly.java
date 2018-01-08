/*=========================================================================
=============================类萤火虫的创建=================================
=========================================================================*/

import java.util.Random;

public class Firefly {
	
	boolean flag;//是否已被吸收
	int aim;//吸引者，-1的初始值代表未被吸引
	double light;// 发光强度
	double r;// 搜索半径
	double x;// 位置横坐标
	double y;// 位置纵坐标
	
	static Random ran = new Random();//实例化随机类
	static int move_max = 40;// 随机移动最大值
	static int move = 50;//被吸引时的移动值
	static int bound_x = 800;
	static int bound_y = 600;//萤火虫位置初始化的边界
	static double Lmax = 0;////光强极大值
	


	static double distance(double a,double b){
		return Math.sqrt((a-b)*(a-b));
	}//计算两萤火虫群落的距离
	
	public Firefly() {
		FireflyCreat();
		this.check_a();
	}// 默认构建器

	public Firefly(double light, double r, double x, double y){
		aim = -1;
		this.light = light;
		this.r = r;
		this.x = x;
		this.y = y;
	}// 含参构建器，测试用
	
	public void move_random() {
		x += ran.nextInt(move_max*2)-move_max;
		x += ran.nextDouble();
		x = x>800?800:x;
		x = x<0?0:x;
		
		y += ran.nextInt(move_max*2)-move_max;
		y += ran.nextDouble();
		y = y>600?600:y;
		y = y<0?0:y;
	}// 随机移动函数

	public static void move_attract(int i,Firefly a[]) {
		if(a[i].flag//判断该群落是否已被吸收
				&& a[a[i].aim].light > a[i].light//判断该群落的光强是否在中途大于了吸引者
				&& Math.sqrt((a[i].x-a[a[i].aim].x)*(a[i].x-a[a[i].aim].x)) <= move
				&&Math.sqrt((a[i].y-a[a[i].aim].y)*(a[i].y-a[a[i].aim].y)) <= move//判断是否并入萤火虫群落
				){
			a[i].flag = false;//将群落设置为已被吸收
			a[a[i].aim].light += a[i].light/3;
			a[a[i].aim].r += a[i].light/6;//吸收者发生数据变化
			return;
		}
		if(a[i].aim != -1 && a[i].light > a[a[i].aim].light){
			a[i].aim = -1;
			a[i].move_random();
			return;
		}//在移动过冲中若被吸引者的光强发生变化超过了吸引者则吸引消失
		a[i].x = a[i].x < a[a[i].aim].x ? a[i].x+move : a[i].x-move;
		a[i].x = a[i].x>800?750:a[i].x;
		a[i].x = a[i].x<0?0:a[i].x;
		
		a[i].y = a[i].y < a[a[i].aim].y ? a[i].y+move : a[i].y-move;
		a[i].y = a[i].y>600?550:a[i].y;
		a[i].y = a[i].y<0?0:a[i].y;
	}// 吸引移动函数
	
	public static void move(int i,Firefly a[]){
		if(a[i].aim == -1)
			a[i].move_random();
		else 
			move_attract(i,a);
	} //移动整合
	
	public void check_a(){
		System.out.println("check:");
		System.out.println("light:"+this.light + " r:" +this.r+ " x:" + this.x + " y:" + this.y);
	}//打印萤火虫当前位置
	
	public static void check_b(Firefly a[]){
		for(int i = 0;i < a.length;i++){
			System.out.println("check "+(i+1)+":");
			System.out.println("flag:"+a[i].flag+" aim:"+(a[i].aim+1)+" x:"+a[i].x + " y:" + a[i].y);
		}//查看当前所有萤火虫群落的情况，测试用
	}
	
	void FireflyCreat(){
		aim = -1;
		flag = true;
		light = ran.nextInt(40);
		light += ran.nextDouble();
		r = 30;
		r += ran.nextDouble();
		x = ran.nextInt(bound_x);
		x += ran.nextDouble();
		y = ran.nextInt(bound_y);
		y += ran.nextDouble();
	}//对通过默认构建器创建的对象的数据进行初始化
	
	static void FireflyCreat(Firefly a[]){
		for(int i = 0;i < a.length;i++){
			a[i] = new Firefly();
		}
	}//对象组进行初始化
	
	static void FireflyTouch(Firefly a[]){
		for(int i = 0;i < a.length;i++){
			if(a[i].flag)
			for(int j = 0;j < a.length;j++){
				if(j == i)continue;//避免与自身进行判断
				if(a[j].flag && distance(a[i].x-a[j].x,a[i].y-a[j].y) <= a[i].r)
					if(a[j].aim == -1 || a[a[j].aim].light < a[i].light)a[j].aim = i;
			}
		}
	}//个体吸引判定
	
}