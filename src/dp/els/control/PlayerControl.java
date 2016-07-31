package dp.els.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//控制器(相当于玩家控制),用于控制GameControl
public class PlayerControl extends KeyAdapter {//实现监听器接口并覆写其方法进行监听操作
             
	private GameControl gameControl;
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	
	//KeyAdapter继承了KeyListener接口，继承这个类之后我们就不用再覆写//监听键入//监听放开按键两个方法
	/**
	 *键盘按下事件 
	 */
	public void keyPressed(KeyEvent e) {
	this.gameControl.actionByKeyCode(e.getKeyCode());
  }
	

}
