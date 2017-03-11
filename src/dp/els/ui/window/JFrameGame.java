package dp.els.ui.window;

import javax.swing.JFrame;

import dp.els.config.FrameConfig;
import dp.els.config.GameConfig;
import dp.els.util.FrameUtil;

/**
 * 游戏主窗口
 * @author DP
 *
 */
@SuppressWarnings("serial")
public class JFrameGame extends JFrame{
	
	
     public JFrameGame(JPanelGame panelGame){
    	 //获得游戏配置（使用工厂类实例化FrameConfig对象）
		 FrameConfig fCfg=GameConfig.getFrameConfig();
    	 //设置标题
    	 this.setTitle(fCfg.getTitle());
    	 //设置默认的关闭操作(程序结束)
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 //设置窗口大小
    	 this.setSize(fCfg.getWidth(), fCfg.getHeight());
    	 //不允许用户拖动窗口大小
    	 this.setResizable(false);
    	 //居中属性
    	FrameUtil.setFrameCenter(this);
        //设置默认Panel
        this.setContentPane(panelGame);
        //默认该窗口为显示
        this.setVisible(true);
        
     }
}
