package dp.els.ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import dp.els.control.GameControl;
import dp.els.ui.Img;
import dp.els.util.FrameUtil;

/**
 * �û��������
 * @author DP
 *
 */
public class JFrameUserConfig extends JFrame {
	private static final long serialVersionUID = 1L;
	private final static String PATH="data/control.dat";
	private JButton btnOK=new JButton("ȷ��");
	private JButton btnCancel=new JButton("ȡ��");
	private JButton btnUser=new JButton("Ӧ��");
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	private  TextCtrl[] keyTexts=new TextCtrl[8];
	private  final static  String[] METHOD_NAMES={"keyRight","keyUp","keyLeft","keyDown","keyFunLeft","keyFunUp","keyFunRight","keyFunDown"};
	private JPanel skinView=null;
	@SuppressWarnings("rawtypes")
	private DefaultListModel skinData=new DefaultListModel();
	private JLabel  errorMsg=new JLabel();//�������������ʾ
	@SuppressWarnings("rawtypes")
	private JList skinList=null;
	private Image[] skinViewList=null;
	private GameControl gameControl;
	
	public JFrameUserConfig(GameControl gameControl){
		//�����Ϸ����������
		this.gameControl=gameControl;
		//��ʼ�����������
		this.initKeyText();
		//���ò���Ϊ�߽粼��
		this.setLayout(new BorderLayout());
		this.setTitle("����");
		//��������
		this.add(createMainPanle(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		//���ò��ܱ��С
		this.setResizable(false);
		//���ô��ڴ�С
		this.setSize(1035, 540);
		//����
		FrameUtil.setFrameCenter(this);
	}
	
	//��ʼ�����������Ȼ���ȡ�����ļ��е����ã���ĳ��������Ӧĳ�����ܵ�ӳ��
	private void initKeyText() {
		//0,50,64,20
		int x=20;
		int y=100;
		int w=70;
		int h=20;
		for (int i = 0; i < 4; i++) {
			keyTexts[i]=new TextCtrl(x,y,w,h,METHOD_NAMES[i]);
			y+=50;
		}
		x=940;
		y=105;
		for(int i=4; i<8; i++){
			keyTexts[i]=new TextCtrl(x,y,w,h,METHOD_NAMES[i]); 
			y+=50;
		}
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(PATH));
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> cfgSet=(HashMap<Integer,String>)ois.readObject();
			ois.close();
			Set<Entry<Integer,String>> entryset=cfgSet.entrySet();
			for (Entry<Integer, String> entry : entryset) {
				for (TextCtrl tc : keyTexts) {
					//��Ӧ���ı����ǲ��Ƕ�Ӧ��Ӧ���ܣ�
					if(tc.getMethodName().equals(entry.getValue())){
						tc.setKeyCode(entry.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//������ť���
	private Component createButtonPanel() {
		//������ť����壬��ʽ����
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//Ϊ����ϵļ�����ťע�������
		//ȷ����
		this.btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()){//д�����óɹ��Ļ�
					setVisible(false);//�رմ���
					gameControl.setOver();
				}
			}
		});
		
		//ȡ����
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);//�رմ���
				gameControl.setOver();
			}
		});
		//Ӧ�ü�
		this.btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
				gameControl.repaint();//ˢ�´���
			}
		});
		//���ô�����Ϣǰ��ɫ
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.btnUser);
		jp.add(this.errorMsg);
		jp.add(this.btnOK);
		jp.add(this.btnCancel);
		return jp;
	}
	
	
	//д����Ϸ����
	private boolean writeConfig(){
		HashMap<Integer,String> keySet=new HashMap<Integer,String>();
		for (int i = 0; i < this.keyTexts.length; i++) {//���û����õİ�����Ĭ�ϵİ�����Ӧ�ķ�������ӳ��
			//�������û����ú����Լ��İ���ϰ�ߺ����������Ϸʱ�������ò�ͬ�İ�����Ӧ��Ӧ�Ĺ���
			int keyCode=this.keyTexts[i].getKeyCode();
			if(keyCode==0){//ȥ������Ϊ�յ����
				this.errorMsg.setText("���󰴼�");
				return false;
			}
			keySet.put(this.keyTexts[i].getKeyCode(), this.keyTexts[i].getMethodName());
		}
		if(keySet.size()!=8){//ȥ����ͬ�İ�������
			this.errorMsg.setText("�ظ�����");
			return false;
		}
		try {
			//�л�Ƥ��this.skinData.get(this.skinList.getSelectedIndex());�õ�ѡ�������ļ���
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString());
			//ͨ�����������������ӳ��д�������ļ���
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
			this.errorMsg.setText("");
		} catch (IOException e) {
			e.printStackTrace();
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	//���������
	private JTabbedPane createMainPanle() {
		//ѡ���
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("��������", this.createControlPane());
		jtp.addTab("Ƥ������", this.createSkinPanel());
		return jtp;
	}
	
	//���Ƥ�����
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Component createSkinPanel() {
		JPanel panel=new JPanel(new BorderLayout());
		//TODO �������
		File fir=new File(Img.GRAPHICS_PATH);
		File[] files=fir.listFiles();
		this.skinViewList=new Image[files.length];
		for (int i=0;i<files.length;i++) {
			//���ѡ����ļ�����Ϊѡ��
			this.skinData.addElement(files[i].getName());
			//��Ԥ��ͼ��ӵ�skinViewList����
			this.skinViewList[i]=new ImageIcon(files[i].getPath()+"/view.png").getImage();
		}
		
		this.skinList=new JList(this.skinData);
		this.skinList.setSelectedIndex(0);//Ĭ��ѡ���һ��
		this.skinList.addMouseListener(new MouseAdapter() {//�鿴��һ��Ƥ������������������¼�
			@Override
			public void mouseReleased(MouseEvent e) {//ˢ�»���
					repaint();
			}
		});
		this.skinView=new JPanel(){
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g){
				//�������thisָ���� 
				g.drawImage(skinViewList[skinList.getSelectedIndex()], 0, 0,this.getWidth(),this.getHeight(),null);
			}
		};
		panel.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		panel.add(this.skinView,BorderLayout.CENTER);
		return panel;
	}

	//��ҿ����������
	private JPanel createControlPane() {
		JPanel jpanel=new JPanel(){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		jpanel.setLayout(null);
		//���ò��ֹ�����
		for (TextCtrl s : keyTexts) {
			jpanel.add(s);
		}
		return jpanel;
	}
}
