package dp.els.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

/**
 * 初始化游戏中的Button，将它存入HashMap中，button的name为key，ButtonInterfaceConfig为value 
 * @author DP
 */
public class ButtonConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	//所有的button的数据的实例化对象都存在这个map中
	public final HashMap<String, ButtonInterfaceConfig> buttonMap;
	public ButtonConfig(Element element){
		List<Element> buttons=element.elements("button");//得到名为button的元素
		buttonMap=new HashMap<String,ButtonInterfaceConfig>();
		for (Element button : buttons) {
			ButtonInterfaceConfig b=new ButtonInterfaceConfig(
						button.attributeValue("name"),
						Integer.parseInt(button.attributeValue("x")),
						Integer.parseInt(button.attributeValue("y")),
						Integer.parseInt(button.attributeValue("w")),
						Integer.parseInt(button.attributeValue("h"))
					);
			buttonMap.put(button.attributeValue("name"),b);
		}
	}
	
	public HashMap<String, ButtonInterfaceConfig> getButtonMap() {
		return buttonMap;
	}
}
