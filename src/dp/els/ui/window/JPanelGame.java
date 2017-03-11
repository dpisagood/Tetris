package dp.els.ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import dp.els.config.FrameConfig;
import dp.els.config.GameConfig;
import dp.els.config.LayerConfig;
import dp.els.control.GameControl;
import dp.els.control.PlayerControl;
import dp.els.dto.GameDto;
import dp.els.ui.Img;
import dp.els.ui.Layer;

/**
 * 游戏主面板各个组件初始化
 * @author DP
 *
 */
@SuppressWarnings("serial")
public class JPanelGame extends JPanel{
	
		private JButton btnStart;
		private JButton btnConfig;
		private GameControl gameControl=null;
		//出现很多相同的类的对象考虑使用数组	
		private ArrayList<Layer> layers=null;
		
	public JPanelGame(GameControl gamecontrol,GameDto dto){
		 this.gameControl=gamecontrol;
		 this.setLayout(null);
		 //初始化组件
		 this.initComponent();
		//初始化层
		 this.initlayer(dto);
		 //安装键盘监听器
		 this.addKeyListener(new PlayerControl(gameControl));
	 }
		
	//控制按钮是否可以点击
	public void buttonSwitch(boolean onOff){
		this.btnStart.setEnabled(onOff);
		this.btnConfig.setEnabled(onOff);
	}
	 
	 
	/**
	 * 初始化“开始”和“设置”这两个按钮
	 */
    private void initComponent(){
		//初始化按钮
		 this.btnStart=new JButton(Img.BTN_START);
		 this.btnStart.setBounds(
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("start").getStartX(),
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("start").getStartY(),
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("start").getButtonW(),
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("start").getButtonH()
				 );
		 this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			    requestFocus();//JPanelGame获得焦点
			}
		});
		 this.add(btnStart);
		 this.btnConfig=new JButton(Img.BTN_CONFIG);
		 this.btnConfig.setBounds(
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("userconfig").getStartX(),
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("userconfig").getStartY(),
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("userconfig").getButtonW(),
				 GameConfig.getBUTTON_CONFIG().getButtonMap().get("userconfig").getButtonH()
				 );
		 this.btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		 this.add(btnConfig);
    }
 
    /**
     * 初始化Layer组件
     * 读取配置文件中各Layer的类名和本类的基本方位配置（即,x,y,w,h）通过反射来实例化Layer对象
     * @param dto 每个Layer中必须有个游戏数据对象，以便得到数据显示。
     */
    private void initlayer(GameDto dto){
	 try {
			//获得游戏配置（使用工厂类实例化GameConfig对象）
		 	FrameConfig fCfg=GameConfig.getFrameConfig();
		 	//获得层配置（调用GameConfig中的getLayersconfig方法的得到被LayerConfig封装的配置文件的对象数组）
		 	List<LayerConfig> layersCfg=fCfg.getLayersConfig();
			//创建游戏层数组（创建Layer对象数组）
		 	layers=new ArrayList<Layer>(layersCfg.size());
		 	//循环创建层对象
		 	for(LayerConfig layerCfg: layersCfg){
		 		//获得类对象（利用反射方法获得具体的窗口对象）//layerCfg.getClassName()的到完整包名
		 		Class<?> cls=Class.forName(layerCfg.getClassName());
		 		//获得构造函数
		 		Constructor<?> ctr =cls.getConstructor(int.class,int.class,int.class,int.class);
		 		//调用构造函数创建对象
		 		Layer layer =(Layer)ctr.newInstance(
			    		layerCfg.getX(),
			    		layerCfg.getY(),
			    		layerCfg.getW(),
			    		layerCfg.getH()
		    		);
		 		//设置游戏数据对象
		 		layer.setDto(dto);
		 		//把创建的Layer对象放入集合
		 			layers.add(layer);
				 	}
				} catch (Exception e) {
					e.printStackTrace();
			}
	}

    /**
     * 循环绘制游戏面板中的Layer
     */
   @Override
	public  void paintComponent(Graphics g){
		//调用基类方法
		  super.paintComponent(g);
		 //绘制游戏画面//父类里添加了paint方法，不同的子类对象调用自己的paint方法
	     for (int i = 0; i < layers.size();layers.get(i++).paint(g));
	   }

}
