package dp.els.config;

import java.io.Serializable;

/**
 * 各个layer所需要基本数据，className存有每个layer类的全限定类名，用于反射实例化对象
 * 使用封装类来管理读取的数据，使得数据更加便于管理，在谁用反射来创建对象时很容易的到具体的数据
 * @author DP
 *
 */
public class LayerConfig implements Serializable{//封装读取的配置数据
	private static final long serialVersionUID = 1L;
	private String className;
	private int x;
	private int y;
	private int w;
	private int h;

 public LayerConfig(String className, int x, int y, int w, int h) {
	this.className = className;
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
}

public String getClassName() {
	return className;
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

public int getW() {
	return w;
}

public int getH() {
	return h;
}

}
