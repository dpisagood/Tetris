package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig {
	
	private final int minX;
	private final int minY;
	private final int maxX;
	private final int maxY;
	private final int levelUP;
	private final List<Point[]> typeConfig;
	private final List<Boolean> typeRound;
	

	public SystemConfig(Element system){
		this.maxX=Integer.parseInt(system.attributeValue("maxX"));
		this.maxY=Integer.parseInt(system.attributeValue("maxY"));
		this.minX=Integer.parseInt(system.attributeValue("minX"));
		this.minY=Integer.parseInt(system.attributeValue("minY"));
		this.levelUP=Integer.parseInt(system.attributeValue("levelup"));
		List<Element> rects=system.elements("rect");
		typeConfig =new ArrayList<Point[]>(rects.size()); 
		typeRound =new ArrayList<Boolean>(rects.size());
		for (Element rect : rects) {
			//是否旋转
			this.typeRound.add(Boolean.parseBoolean(rect.attributeValue("round")));
			//获得坐标对象
			List<Element> pointConfig =rect.elements("Point");
			//创建Point对象数组
			Point[] points =new Point[pointConfig.size()];
			//初始化Point对象数组
			for (int i = 0; i < points.length; i++) {
				int x=Integer.parseInt(pointConfig.get(i).attributeValue("x"));
				int y=Integer.parseInt(pointConfig.get(i).attributeValue("y"));
				points[i] =new Point(x,y);
			}
			//把Point对象数组放到typeConfig中
			typeConfig.add(points);
		}
		
	}

	public int getLevelUP() {
		return levelUP;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}

	public List<Boolean> getTypeRound() {
		return typeRound;
	}
}
