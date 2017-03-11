package dp.els.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


/**
 * 窗口居中
 * @author DP
 *
 */
public class FrameUtil {
	public static void setFrameCenter(JFrame jf){
		Toolkit toolkit=Toolkit.getDefaultToolkit();//采用工厂类获得实例化对象
        Dimension screen =toolkit.getScreenSize();//获得显示器大小
        //知道显示器和自己窗口的高度来计算窗口居中时的坐标
        int x=screen.width-jf.getWidth()>>1;
        int y=(screen.height-jf.getHeight()>>1) -32;//高度提高一点
        jf.setLocation(x,y);//根据得来的数据设置窗口位置  
	}
}
