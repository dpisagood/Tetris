package dp.els.util;

import java.awt.Point;
import java.util.List;

import dp.els.config.GameConfig;

public class ConfigConstant {
	//�������飬���Point���飬ÿ��Point�������һ�����͵ķ��飨��ʵÿ��Point����һ���㣬�����������ÿ�������з�������꣩
    public final static  List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
    //���������Ƿ���Է�ת����Ӧ��ÿһ������
    public final static List<Boolean> TYPE_ROUND=GameConfig.getSystemConfig().getTypeRound();
	//��Ϸ���
	public static  int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	//��Ϸ�߶�
	public static  int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	
	public static  boolean[][] gameMap=null;
	//��Ϸ��ʼ���ʼ��һ�µ�ͼ���ԡ�
	public static void init(){
		gameMap=new boolean[GAMEZONE_W][GAMEZONE_H]; 
	}
}
