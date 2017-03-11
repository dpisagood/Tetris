package dp.els.config;

import java.io.Serializable;


/**
 * ����ÿ����ť����Ҫ���������ݣ�����˵��װ�˰�ť�����е�����
 * @author DP
 *
 */
public class ButtonInterfaceConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final String name;//��ť����
	private final int startX;//��ʼx����
	private final int startY;//��ʼy����
	private final int buttonW;//��ť��
	private final int buttonH;//��ť��

	public ButtonInterfaceConfig(String name, int startX, int startY ,int buttonW, int buttonH) {
		this.name = name;
		this.startX = startX;
		this.startY = startY;
		this.buttonW = buttonW;
		this.buttonH = buttonH;
	}
	public String getName() {
		return name;
	}
	public int getButtonW() {
		return buttonW;
	}
	public int getButtonH() {
		return buttonH;
	}
	public int getStartX() {
		return startX;
	}
	public int getStartY() {
		return startY;
	}
}
