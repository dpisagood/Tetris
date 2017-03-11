package dp.els.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * 得到了整个游戏Frame的数据
 * 初始化了游戏各个窗口的各项数据，并存到了LayerConfig的对象数组中
 * 初始化了ButtonConfig
 * @author DP
 */
public class FrameConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final String title;
	private final int windowUp;
	private final int width;
	private final int height;
	private final int padding;//填充
	private final int  border;//边界
	private final int window_w;
	private final int window_h;
	private final int sizeRol;
	private final int loseIdx;
    // 图层属性
    private  List<LayerConfig> layersConfig ;//定义一个封装类（LayerConfig）的list数组进行接收
	public FrameConfig(Element frame){
		//图片尺寸左位移偏移量
		this.sizeRol=Integer.parseInt(frame.attributeValue("sizeRol"));
		//获得在素材上截取的边框的素材长度
    	this.window_h=Integer.parseInt(frame.attributeValue("window_h"));
    	this.window_w=Integer.parseInt(frame.attributeValue("window_w"));
        //获取窗口宽度
		this.width=Integer.parseInt(frame.attributeValue("width"));
		//获取窗口高度
		this.height=Integer.parseInt(frame.attributeValue("height"));
		//获取边框粗细
		this.border=Integer.parseInt(frame.attributeValue("border"));
		//获取边框内边距
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		//获得标题
		this.title=frame.attributeValue("title");
		//获得窗口拔高数据
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		//获得窗口属性
		List<Element> layers =frame.elements("layer");
	  	//读取XML中的具体配置文件并用LayerConfig类 进行封装
		layersConfig =new ArrayList<LayerConfig>();//将List数组变为ArrayList数组
		for(Element lay:layers){
			LayerConfig lc=new LayerConfig(
					lay.attributeValue("className"),
					Integer.parseInt(lay.attributeValue("x")),
					Integer.parseInt(lay.attributeValue("y")),
					Integer.parseInt(lay.attributeValue("w")),
					Integer.parseInt(lay.attributeValue("h"))
					);
		//将xml配置文件读取类的对象读取的数据用LayerConfig类来封装（LayerConfig类中有这些属性）
		layersConfig.add(lc);//	将封装完成的LayerConfig对象填入到LayerConfig的list数组里
		}
	}

	public int getLoseIdx() {
		return loseIdx;
	}

	public int getSizeRol() {
		return sizeRol;
	}

	public String getTitle() {
		return title;
	}

	public int getWindowUp() {
		return windowUp;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPadding() {
		return padding;
	}

	public int getBorder() {
		return border;
	}

	public int getWindow_w() {
		return window_w;
	}

	public int getWindow_h() {
		return window_h;
	}

	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}
	
}
