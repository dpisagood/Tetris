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
 * ��Ϸ�������������ʼ��
 * @author DP
 *
 */
@SuppressWarnings("serial")
public class JPanelGame extends JPanel{
	
		private JButton btnStart;
		private JButton btnConfig;
		private GameControl gameControl=null;
		//���ֺܶ���ͬ����Ķ�����ʹ������	
		private ArrayList<Layer> layers=null;
		
	public JPanelGame(GameControl gamecontrol,GameDto dto){
		 this.gameControl=gamecontrol;
		 this.setLayout(null);
		 //��ʼ�����
		 this.initComponent();
		//��ʼ����
		 this.initlayer(dto);
		 //��װ���̼�����
		 this.addKeyListener(new PlayerControl(gameControl));
	 }
		
	//���ư�ť�Ƿ���Ե��
	public void buttonSwitch(boolean onOff){
		this.btnStart.setEnabled(onOff);
		this.btnConfig.setEnabled(onOff);
	}
	 
	 
	/**
	 * ��ʼ������ʼ���͡����á���������ť
	 */
    private void initComponent(){
		//��ʼ����ť
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
			    requestFocus();//JPanelGame��ý���
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
     * ��ʼ��Layer���
     * ��ȡ�����ļ��и�Layer�������ͱ���Ļ�����λ���ã���,x,y,w,h��ͨ��������ʵ����Layer����
     * @param dto ÿ��Layer�б����и���Ϸ���ݶ����Ա�õ�������ʾ��
     */
    private void initlayer(GameDto dto){
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
		 		layer.setDto(dto);
		 		//�Ѵ�����Layer������뼯��
		 			layers.add(layer);
				 	}
				} catch (Exception e) {
					e.printStackTrace();
			}
	}

    /**
     * ѭ��������Ϸ����е�Layer
     */
   @Override
	public  void paintComponent(Graphics g){
		//���û��෽��
		  super.paintComponent(g);
		 //������Ϸ����//�����������paint��������ͬ�������������Լ���paint����
	     for (int i = 0; i < layers.size();layers.get(i++).paint(g));
	   }

}
