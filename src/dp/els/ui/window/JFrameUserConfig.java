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
 * 用户设置面板
 * @author DP
 *
 */
public class JFrameUserConfig extends JFrame {
	private static final long serialVersionUID = 1L;
	private final static String PATH="data/control.dat";
	private JButton btnOK=new JButton("确定");
	private JButton btnCancel=new JButton("取消");
	private JButton btnUser=new JButton("应用");
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	private  TextCtrl[] keyTexts=new TextCtrl[8];
	private  final static  String[] METHOD_NAMES={"keyRight","keyUp","keyLeft","keyDown","keyFunLeft","keyFunUp","keyFunRight","keyFunDown"};
	private JPanel skinView=null;
	@SuppressWarnings("rawtypes")
	private DefaultListModel skinData=new DefaultListModel();
	private JLabel  errorMsg=new JLabel();//姓名输入错误提示
	@SuppressWarnings("rawtypes")
	private JList skinList=null;
	private Image[] skinViewList=null;
	private GameControl gameControl;
	
	public JFrameUserConfig(GameControl gameControl){
		//获得游戏控制器对象
		this.gameControl=gameControl;
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
					gameControl.setOver();
				}
			}
		});
		
		//取消键
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);//关闭窗口
				gameControl.setOver();
			}
		});
		//应用键
		this.btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
				gameControl.repaint();//刷新窗口
			}
		});
		//设置错误信息前景色
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.btnUser);
		jp.add(this.errorMsg);
		jp.add(this.btnOK);
		jp.add(this.btnCancel);
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
		try {
			//切换皮肤this.skinData.get(this.skinList.getSelectedIndex());得到选项名即文件名
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString());
			//通过对象流将这个配置映射写到配置文件中
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
		jtp.addTab("控制设置", this.createControlPane());
		jtp.addTab("皮肤设置", this.createSkinPanel());
		return jtp;
	}
	
	//玩家皮肤面板
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Component createSkinPanel() {
		JPanel panel=new JPanel(new BorderLayout());
		//TODO 添加内容
		File fir=new File(Img.GRAPHICS_PATH);
		File[] files=fir.listFiles();
		this.skinViewList=new Image[files.length];
		for (int i=0;i<files.length;i++) {
			//添加选项，把文件名作为选项
			this.skinData.addElement(files[i].getName());
			//将预览图添加到skinViewList里面
			this.skinViewList[i]=new ImageIcon(files[i].getPath()+"/view.png").getImage();
		}
		
		this.skinList=new JList(this.skinData);
		this.skinList.setSelectedIndex(0);//默认选择第一项
		this.skinList.addMouseListener(new MouseAdapter() {//查看下一个皮肤，鼠标点击后拿起后的事件
			@Override
			public void mouseReleased(MouseEvent e) {//刷新画面
					repaint();
			}
		});
		this.skinView=new JPanel(){
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g){
				//这里这个this指的是 
				g.drawImage(skinViewList[skinList.getSelectedIndex()], 0, 0,this.getWidth(),this.getHeight(),null);
			}
		};
		panel.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		panel.add(this.skinView,BorderLayout.CENTER);
		return panel;
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
}
