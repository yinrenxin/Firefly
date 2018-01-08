import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class F_Frame extends JFrame {
	
	private static final long serialVersionUID = 8763692551589834346L;
	static MyPanel temp = null;
	
	public F_Frame() {
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		/** 窗口居中的设置 **/
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width / 2; // 获取屏幕的宽
		int screenHeight = screenSize.height / 2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);
	}
	
	public void getImage(int temp) throws AWTException, IOException{
		Rectangle screenRectangle = this.getBounds();
		Robot r = new Robot();
		BufferedImage image = r.createScreenCapture(screenRectangle);
		File f;
		if(temp == 1)f = new File("fri.png");
		else if(temp == 2)f = new File("mid.png");
			else f = new File("end.png");
		f.createNewFile();
		ImageIO.write(image, "png", f);
	}
	
	public void drawFirefly(Firefly[] b) {
		MyPanel temp = new MyPanel(b);
		this.add(temp);
	}//绘制图形的函数
	
}

class MyPanel extends JPanel {
	Firefly b[];
	// 定义一个MyPanel面板，用于绘图区域
	// 覆盖JPanel
	// Graphics 是绘图的重要类，可以理解成一支画笔
	public MyPanel(Firefly b[]){
		this.b= b;
	}
	public void Change(Firefly b[]){
		this.b = b;
	}
	public void paint(Graphics g) {
		super.paint(g);// 调用父类函数完成初始化任务 ,这句话不可以少
		g.setColor(Color.blue);//画笔上色
		for(int i = 0;i < b.length;i++){
			if(b[i].flag)
			g.fillOval((int)b[i].x, (int)b[i].y, ((int)b[i].light*3)/2, ((int)b[i].light*3)/2);
		}//对每个萤火虫群落进行绘制
	}
}//创建图形绘制类