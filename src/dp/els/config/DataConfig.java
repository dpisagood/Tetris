package dp.els.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;


public class DataConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	private final int maxRow;
	private final DataInterfaceConfig dataA; 
	private final DataInterfaceConfig dataB;
	
	public DataConfig(Element data){
		this.maxRow=Integer.parseInt(data.attributeValue("maxRow"));
		this.dataB =new DataInterfaceConfig(data.element("disk").element("dataB"));
		this.dataA=new DataInterfaceConfig(data.element("database").element("dataA"));
	}

	public DataInterfaceConfig getDataA() {
		return dataA;
	}

	public DataInterfaceConfig getDataB() {
		return dataB;
	}

	public int getMaxRow() {
		return maxRow;
	}

	
	public HashMap<String,String> getValueMap(Element dataInterfaceConfig){
		 HashMap<String,String> param=new HashMap<String,String>();
		
		List<Element> params=dataInterfaceConfig.elements("param");
		for(Element e:params){
			param.put(e.attributeValue("key"), e.attributeValue("value"));
		}
		return param;
	}
}
