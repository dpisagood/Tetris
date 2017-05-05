package dp.els.ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dp.els.control.GameControl;
import dp.els.util.FrameUtil;

/**
 * ��Ϸ�����󱣴��������
 * @author DP
 *
 */
public class JFrameSavePoint extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnOk=null;
	private JLabel  lbPoint=null;
	private JTextField txName=null;
	private JLabel errMsg= null;
	private GameControl gameControl=null;
	
	
	public JFrameSavePoint(GameControl gameControl){
		this.gameControl=gameControl;
		this.setTitle("�����¼");	
		this.setSize(256, 128);
		FrameUtil.setFrameCenter(this);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		//��ʼ���ؼ�
		this.createCom();
		//�����¼�����
		this.createAction();
	}
	
	//��ʾ����
	public void show(int point){
		this.lbPoint.setText("���ĵ÷֣�"+point);
		this.setVisible(true);
	}
	
	//�����¼�����
	private void createAction() {
		//lambda���ʽʵ�ּ�����
		this.btnOk.addActionListener(e->{
			String name=txName.getText();
			if(name.trim().equals("")||name==null||name.trim().length()>16){
				errMsg.setText("�����������");
			}else{
				setVisible(false);
				gameControl.savePoint(name);
				
			}
		});
	}
	//��ʼ���ؼ�
	private void createCom(){
		//����������壨��ʽ���֣�
		JPanel north=new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.lbPoint=new JLabel();
		//��ӷ������ֵ��������
		north.add(this.lbPoint);
		//���������ӵ������ı���
		this.add(north, BorderLayout.NORTH);
		
		//����������Ϣ�ؼ�
		this.errMsg=new JLabel();
		this.errMsg.setForeground(Color.red);
		north.add(this.errMsg);
		
		JPanel  center=new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.txName=new JTextField(10);
		//TODO������󳤶�
		center.add(new JLabel("�������֣�"));
		center.add(this.txName);
		this.add(center, BorderLayout.CENTER);
		
		//����ȷ����ť
		this.btnOk=new JButton("ȷ��");
		//�����ϲ���壨��ʧ���֣�
		JPanel south =new JPanel(new FlowLayout(FlowLayout.CENTER));
		//��ť��ӵ��ϲ����
		south.add(btnOk);
		//�ϲ������ӵ������
		this.add(south,BorderLayout.SOUTH);
		
		
	}
	
}
