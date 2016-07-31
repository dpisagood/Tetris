package dp.els.ui;

import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import dp.els.control.GameControl;
import dp.els.control.PlayerControl;
import dp.els.dto.GameDto;

@SuppressWarnings("serial")
public class JPanelGame extends JPanel{
	
		private static final int BTN_SIZE_W=GameConfig.getFrameConfig().getButtonConfig().getButtonW();
		private static final int BTN_SIZE_H=GameConfig.getFrameConfig().getButtonConfig().getButtonH();
		
		private JButton btnStart;
		private JButton btnConfig;
		private GameControl gameControl=null;
			
		private ArrayList<Layer> layers=null;//出现很多相同的类的对象考虑使用数组
		private GameDto dto =null;
		public JPanelGame(GameDto dto){
		 this.setLayout(null);
		//获得dto对象
		 this.dto =dto;
		//初始化层
		 initlayer();
		 //初始化组件
		 initComponent();
		 initButton();
	 }
	 
	 private void initButton() {
		//初始化按钮
		 this.btnStart=new JButton(Img.BTN_START);
		 this.btnStart.setBounds(
				 GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				 GameConfig.getFrameConfig().getButtonConfig().getStartY(),
				 BTN_SIZE_W, BTN_SIZE_H);
		 this.add(btnStart);
		 this.btnConfig=new JButton(Img.BTN_CONFIG);
		 this.btnConfig.setBounds(
				 GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(),
				 GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(),
				 BTN_SIZE_W, BTN_SIZE_H);
		 this.add(btnConfig);
	}
	/**
	  * 安装玩家控制器
	  * */
	 public void setGameControl(PlayerControl control){
		 this.addKeyListener(control);
	 }
	 
	 public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	/**
	  * 初始化组件
	  * */
    private void initComponent(){
    	//创建一个游戏控制器，把JPanelGame(this)交给GameCtrol监听控制
    	//(传入实现KeyListener接口的监听器)又将GameControl交给玩家控制器控制
	   }
   /**
    * 初始化层
    * */
    private void initlayer(){
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
		    layer.setDto(this.dto);
		    //把创建的Layer对象放入集合
		            layers.add(layer);
				 }
			} catch (Exception e) {
		      e.printStackTrace();
			}
		}

   @Override
	public  void paintComponent(Graphics g){
		//调用基类方法
		  super.paintComponent(g);
		 //绘制游戏画面//父类里添加了paint方法，不同的子类对象调用自己的paint方法
	     for (int i = 0; i < layers.size();layers.get(i++).paint(g));
	     this.requestFocus();//JPanelGame获得焦点
	   }

}
