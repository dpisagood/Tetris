package dp.els.config;

import java.io.Serializable;


/**
 * 保存每个按钮所需要的所有数据，或者说封装了按钮的所有的数据
 * @author DP
 *
 */
public class ButtonInterfaceConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final String name;//按钮名字
	private final int startX;//起始x坐标
	private final int startY;//起始y坐标
	private final int buttonW;//按钮长
	private final int buttonH;//按钮高

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
