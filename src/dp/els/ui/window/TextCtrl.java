package dp.els.ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
//KeyPressed�Ǽ������£�KeyReleased�Ǽ����������������Ǹ��ײ�һЩ���¼���
//KeyTypede��ָ���ַ������룬���簴סshift���ٰ�A���������ʱCaps Lock�������Ͳ���һ�������дA���¼��� 
public class TextCtrl extends JTextField{
	private static final long serialVersionUID = 1L;
		private int keyCode;
		private final String methodName;
	
	public TextCtrl(int x,int y,int w,int h ,String methodName){
		//�����ı���λ��
		this.setBounds(x,y,w,h);
		//��ʼ��������
		this.methodName=methodName;
		//����¼�����
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {}
			//�����ɿ���ʧȥ����
			@Override
			public void keyPressed(KeyEvent e) {
				//����֮��Ὣ��Ӧ����Ӧ��Codeд�뱾�ı����keyCode��¼������д�������ļ�
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
		//��ʼ��keyCode
		this.keyCode=keyCode;
		//ͨ�����úõ�keyCode�õ���Ӧ���̰�ť�ַ���Ȼ��д���ı�����
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}
}
