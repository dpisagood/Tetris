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
			
		private ArrayList<Layer> layers=null;//���ֺܶ���ͬ����Ķ�����ʹ������
		private GameDto dto =null;
		public JPanelGame(GameDto dto){
		 this.setLayout(null);
		//���dto����
		 this.dto =dto;
		//��ʼ����
		 initlayer();
		 //��ʼ�����
		 initComponent();
		 initButton();
	 }
	 
	 private void initButton() {
		//��ʼ����ť
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
	  * ��װ��ҿ�����
	  * */
	 public void setGameControl(PlayerControl control){
		 this.addKeyListener(control);
	 }
	 
	 public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	/**
	  * ��ʼ�����
	  * */
    private void initComponent(){
    	//����һ����Ϸ����������JPanelGame(this)����GameCtrol��������
    	//(����ʵ��KeyListener�ӿڵļ�����)�ֽ�GameControl������ҿ���������
	   }
   /**
    * ��ʼ����
    * */
    private void initlayer(){
				 try {
					 //�����Ϸ���ã�ʹ�ù�����ʵ����GameConfig����
		 FrameConfig fCfg=GameConfig.getFrameConfig();
		 //��ò����ã�����GameConfig�е�getLayersconfig�����ĵõ���LayerConfig��װ�������ļ��Ķ������飩
		 List<LayerConfig> layersCfg=fCfg.getLayersConfig();
		 //������Ϸ�����飨����Layer�������飩
		 layers=new ArrayList<Layer>(layersCfg.size());
		 //ѭ�����������
		 for(LayerConfig layerCfg: layersCfg){
			 //�����������÷��䷽����þ���Ĵ��ڶ���//layerCfg.getClassName()�ĵ���������
		    Class<?> cls=Class.forName(layerCfg.getClassName());
		    //��ù��캯��
		    Constructor<?> ctr =cls.getConstructor(int.class,int.class,int.class,int.class);
		    //���ù��캯����������
		    Layer layer =(Layer)ctr.newInstance(
		    		layerCfg.getX(),
		    		layerCfg.getY(),
		    		layerCfg.getW(),
		    		layerCfg.getH()
		    		);
		    //������Ϸ���ݶ���
		    layer.setDto(this.dto);
		    //�Ѵ�����Layer������뼯��
		            layers.add(layer);
				 }
			} catch (Exception e) {
		      e.printStackTrace();
			}
		}

   @Override
	public  void paintComponent(Graphics g){
		//���û��෽��
		  super.paintComponent(g);
		 //������Ϸ����//�����������paint��������ͬ�������������Լ���paint����
	     for (int i = 0; i < layers.size();layers.get(i++).paint(g));
	     this.requestFocus();//JPanelGame��ý���
	   }

}
