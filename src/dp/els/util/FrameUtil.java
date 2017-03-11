package dp.els.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


/**
 * ���ھ���
 * @author DP
 *
 */
public class FrameUtil {
	public static void setFrameCenter(JFrame jf){
		Toolkit toolkit=Toolkit.getDefaultToolkit();//���ù�������ʵ��������
        Dimension screen =toolkit.getScreenSize();//�����ʾ����С
        //֪����ʾ�����Լ����ڵĸ߶������㴰�ھ���ʱ������
        int x=screen.width-jf.getWidth()>>1;
        int y=(screen.height-jf.getHeight()>>1) -32;//�߶����һ��
        jf.setLocation(x,y);//���ݵ������������ô���λ��  
	}
}
