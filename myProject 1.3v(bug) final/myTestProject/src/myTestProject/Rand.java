package myTestProject;

import java.util.Random;

public class Rand {
	boolean percent(int per){  //�ۼ�Ʈ�� �޾� true Ȥ�� false�� ����
		Random rand = new Random();
		int nTmp = rand.nextInt(100)+1;
		return (nTmp >= per ? true : false);
	}
	
	int aiBet() {  //���� ���� ��Ʈ�� (ai �������϶�)
		Random rand = new Random();
		int nTmp = 0;
//		if(GameControl.first == true){ //�������϶�
//			
//		}
		//--------------�ӽ�----------------
		nTmp = rand.nextInt(GameControl.pMoney > GameControl.aMoney ? GameControl.aMoney : GameControl.pMoney)+1;  //���� ���� ������ ���� �����Ҽ� ����.
		//--------------�ӽ�----------------
		
		return nTmp;
	}
	
	boolean aiCall(){  //�� ���� ��Ʈ�� (ai �Ĺ����϶�)
		//�ӽ� -----------
		return percent(50);
	}
	
}
