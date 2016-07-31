package dp.els.ui.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import dp.els.util.FrameUtil;

public class FrameConfig extends JFrame {
	private static final long serialVersionUID = 1L;
	private final static String PATH="data/control.dat";
	private JButton btnOK=new JButton("确定");
	private JButton btnCancel=new JButton("取消");
	private JButton btnUser=new JButton("应用");
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	private  TextCtrl[] keyTexts=new TextCtrl[8];
	private  final static  String[] METHOD_NAMES={"keyRight","keyUp","keyLeft","keyDown","keyFunLeft","keyFunUp","keyFunRight","keyFunDown"};
	private JLabel  errorMsg=new JLabel();
	public FrameConfig(){
		//初始化按键输入框
		this.initKeyText();
		//设置布局为边界布局
		this.setLayout(new BorderLayout());
		this.setTitle("设置");
		//添加主面板
		this.add(createMainPanle(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		//设置不能变大小
		this.setResizable(false);
		//设置窗口大小
		this.setSize(1035, 540);
		//居中
		FrameUtil.setFrameCenter(this);
		//TODO p测试用
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}
	
	//初始化按键输入框，然后读取配置文件中的配置，即某个按键对应某个功能的映射
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
					//相应的文本框是不是对应相应功能，
					if(tc.getMethodName().equals(entry.getValue())){
						tc.setKeyCode(entry.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//创建按钮面板
	private Component createButtonPanel() {
		//创建按钮主面板，流式布局
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//为面板上的几个按钮注册监听器
		//确定键
		this.btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()){//写入配置成功的话
					setVisible(false);//关闭窗口
				}
			}
		});
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);
		jp.add(this.btnOK);
		
		
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);//关闭窗口
			}
		});
		jp.add(this.btnCancel);
		
		
		this.btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		jp.add(this.btnUser);
		return jp;
	}
	
	
	//写入游戏配置
	private boolean writeConfig(){
		HashMap<Integer,String> keySet=new HashMap<Integer,String>();
		for (int i = 0; i < this.keyTexts.length; i++) {//将用户设置的按键与默认的按键对应的方法进行映射
			//这样当用户设置好了自己的按键习惯后，他玩这款游戏时就能设置不同的按键对应相应的功能
			int keyCode=this.keyTexts[i].getKeyCode();
			if(keyCode==0){//去除配置为空的情况
				this.errorMsg.setText("错误按键");
				return false;
			}
			keySet.put(this.keyTexts[i].getKeyCode(), this.keyTexts[i].getMethodName());
		}
		if(keySet.size()!=8){//去除相同的按键配置
			this.errorMsg.setText("重复按键");
			return false;
		}
		try {//通过对象流将这个配置映射写到配置文件中
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
	
	
	
	
	//创建主面板
	private JTabbedPane createMainPanle() {
		//选项窗格
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("控制设置", createControlPane());
		jtp.addTab("皮肤设置", new JLabel("皮肤"));
		return jtp;
	}
	
	//玩家控制设置面板
	private JPanel createControlPane() {
		JPanel jpanel=new JPanel(){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		jpanel.setLayout(null);
		//设置布局管理器
		for (TextCtrl s : keyTexts) {
			jpanel.add(s);
		}
		return jpanel;
	}
	public static void main(String[] args) {
		new FrameConfig();
	}
}
