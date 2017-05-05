package dp.els.util;

import java.awt.Point;
import java.util.List;

import dp.els.config.GameConfig;

public class ConfigConstant {
	//方块数组，多个Point数组，每个Point数组代表一种类型的方块（其实每个Point就是一个点，多个点就组成了每个类型中方块的坐标）
    public final static  List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
    //方块类型是否可以反转，对应着每一个方块
    public final static List<Boolean> TYPE_ROUND=GameConfig.getSystemConfig().getTypeRound();
	//游戏宽度
	public static  int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	//游戏高度
	public static  int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	
	public static  boolean[][] gameMap=null;
	//游戏开始后初始化一下地图属性。
	public static void init(){
		gameMap=new boolean[GAMEZONE_W][GAMEZONE_H]; 
	}
}
