package dp.els.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * �õ���������ϷFrame������
 * ��ʼ������Ϸ�������ڵĸ������ݣ����浽��LayerConfig�Ķ���������
 * ��ʼ����ButtonConfig
 * @author DP
 */
public class FrameConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final String title;
	private final int windowUp;
	private final int width;
	private final int height;
	private final int padding;//���
	private final int  border;//�߽�
	private final int window_w;
	private final int window_h;
	private final int sizeRol;
	private final int loseIdx;
    // ͼ������
    private  List<LayerConfig> layersConfig ;//����һ����װ�ࣨLayerConfig����list������н���
	public FrameConfig(Element frame){
		//ͼƬ�ߴ���λ��ƫ����
		this.sizeRol=Integer.parseInt(frame.attributeValue("sizeRol"));
		//������ز��Ͻ�ȡ�ı߿���زĳ���
    	this.window_h=Integer.parseInt(frame.attributeValue("window_h"));
    	this.window_w=Integer.parseInt(frame.attributeValue("window_w"));
        //��ȡ���ڿ��
		this.width=Integer.parseInt(frame.attributeValue("width"));
		//��ȡ���ڸ߶�
		this.height=Integer.parseInt(frame.attributeValue("height"));
		//��ȡ�߿��ϸ
		this.border=Integer.parseInt(frame.attributeValue("border"));
		//��ȡ�߿��ڱ߾�
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		//��ñ���
		this.title=frame.attributeValue("title");
		//��ô��ڰθ�����
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		//��ô�������
		List<Element> layers =frame.elements("layer");
	  	//��ȡXML�еľ��������ļ�����LayerConfig�� ���з�װ
		layersConfig =new ArrayList<LayerConfig>();//��List�����ΪArrayList����
		for(Element lay:layers){
			LayerConfig lc=new LayerConfig(
					lay.attributeValue("className"),
					Integer.parseInt(lay.attributeValue("x")),
					Integer.parseInt(lay.attributeValue("y")),
					Integer.parseInt(lay.attributeValue("w")),
					Integer.parseInt(lay.attributeValue("h"))
					);
		//��xml�����ļ���ȡ��Ķ����ȡ��������LayerConfig������װ��LayerConfig��������Щ���ԣ�
		layersConfig.add(lc);//	����װ��ɵ�LayerConfig�������뵽LayerConfig��list������
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
