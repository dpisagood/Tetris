package dp.els.ui.window;

import javax.swing.JFrame;

import dp.els.config.FrameConfig;
import dp.els.config.GameConfig;
import dp.els.util.FrameUtil;

/**
 * ��Ϸ������
 * @author DP
 *
 */
@SuppressWarnings("serial")
public class JFrameGame extends JFrame{
	
	
     public JFrameGame(JPanelGame panelGame){
    	 //�����Ϸ���ã�ʹ�ù�����ʵ����FrameConfig����
		 FrameConfig fCfg=GameConfig.getFrameConfig();
    	 //���ñ���
    	 this.setTitle(fCfg.getTitle());
    	 //����Ĭ�ϵĹرղ���(�������)
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 //���ô��ڴ�С
    	 this.setSize(fCfg.getWidth(), fCfg.getHeight());
    	 //�������û��϶����ڴ�С
    	 this.setResizable(false);
    	 //��������
    	FrameUtil.setFrameCenter(this);
        //����Ĭ��Panel
        this.setContentPane(panelGame);
        //Ĭ�ϸô���Ϊ��ʾ
        this.setVisible(true);
        
     }
}
