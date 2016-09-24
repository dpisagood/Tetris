package dp.els.ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
//KeyPressed是键被按下，KeyReleased是键被弹起，这两个都是更底层一些的事件。
//KeyTypede是指有字符被输入，比如按住shift，再按A键，如果当时Caps Lock不亮，就产生一个输入大写A的事件。 
public class TextCtrl extends JTextField{
	private static final long serialVersionUID = 1L;
		private int keyCode;
		private final String methodName;
	
	public TextCtrl(int x,int y,int w,int h ,String methodName){
		//设置文本框位置
		this.setBounds(x,y,w,h);
		//初始化方法名
		this.methodName=methodName;
		//添加事件监听
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {}
			//键盘松开，失去焦点
			@Override
			public void keyPressed(KeyEvent e) {
				//按键之后会将对应键对应的Code写入本文本框的keyCode记录，用于写入配置文件
				setKeyCode(e.getKeyCode());
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		//初始化keyCode
		this.keyCode=keyCode;
		//通过设置好的keyCode得到对应键盘按钮字符串然后写到文本框中
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}
}
