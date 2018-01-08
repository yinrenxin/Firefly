import java.awt.AWTException;
import java.io.IOException;


public class Main {
	
	static int Firefly_max = 20;//实例化萤火虫群落的数组的最大值
	static int temp = 20;//迭代次数
	
	public static void main(String[] args) throws InterruptedException, AWTException, IOException{
		F_Frame a = new F_Frame();//实例化绘图窗口a
		Firefly b[] = new Firefly[Firefly_max];//实例化一个含有20个萤火虫群落组b，
		Firefly.FireflyCreat(b);//对每个群落进行初始化
		a.drawFirefly(b);//绘制初始情况
		Thread.sleep(2500);
		a.getImage(1);
		Thread.sleep(1500);//窗体暂停5s
		while(temp-- >= 0){
			Thread.sleep(600);//每次移动的间隔
			a.getContentPane().removeAll();//清除窗口中的图形以便于下次绘制
			Firefly.FireflyTouch(b);//对可行动的萤火虫群落进行吸引判定
			for(int i = 0;i < b.length;i++)
					if(b[i].flag){
						Firefly.move(i, b);//根据不同群落的情况进行移动
						Firefly.Lmax = b[i].light >Firefly.Lmax?b[i].light:Firefly.Lmax;//记录当前的光强极值点
					}
			System.out.println("MaxLigh: "+Firefly.Lmax);//输出得到的光强极值点
			a.drawFirefly(b);//进行图形的绘制
			a.validate();//重置窗口
			if(temp == 10)a.getImage(2);
		}
		a.getImage(3);
	}

}
