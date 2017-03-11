package dp.els.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

/**
 * ��ʼ����Ϸ�е�Button����������HashMap�У�button��nameΪkey��ButtonInterfaceConfigΪvalue 
 * @author DP
 */
public class ButtonConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	//���е�button�����ݵ�ʵ�������󶼴������map��
	public final HashMap<String, ButtonInterfaceConfig> buttonMap;
	public ButtonConfig(Element element){
		List<Element> buttons=element.elements("button");//�õ���Ϊbutton��Ԫ��
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
