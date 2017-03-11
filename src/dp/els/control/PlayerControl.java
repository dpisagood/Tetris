package dp.els.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 这个监听器是用来监听玩家键盘的操作的，唯一的游戏过程中的交互
 * @param gameControl 监听器通过持有游戏控制器对象，当玩家有操作时（按键）时，用这个对象调用处理方法
 * @author DP
 *
 */
public class PlayerControl extends KeyAdapter {//实现监听器接口并覆写其方法进行监听操作
             
	private GameControl gameControl;
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	
	//KeyAdapter继承了KeyListener接口，继承这个类之后我们就不用再覆写//监听键入//监听放开按键两个方法
	/**
	 * 键盘按下调用gameControl的actionByKeyCode()方法，并传入键盘编号，来处理这个事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());
	}
	

}
