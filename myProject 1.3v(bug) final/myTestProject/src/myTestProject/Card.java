package myTestProject;

import java.util.Random;

public class Card {

	public static Icard card[] = new Icard[20];  //ī�屸��ü �迭
	static int cardCnt = 0;  //���� ���� ī�� ��ȣ
	
	void shake_card(){  //�ʱ�ȭ�� ���� ������
		System.out.println("ī�带 �����ϴ�.");
		Random rand = new Random();
		Icard temp;  //�ӽ� ����ü ����
		int i,r; //r�� ������
		for(i=0;i<20;i++){  //���ʴ�� ���
			card[i] = new Icard();
			card[i].cardNum = i%10+1;  //10 ���
			card[i].cardName = i/10; //2  ���
		}
		for(i=0;i<20;i++){  //���� 20��
			r = rand.nextInt(20);  
			temp = card[i];
			card[i] = card[r];
			card[r] = temp;
		}
	}
	
}
class Icard{  //ī�� 1�� ����ü
	int cardName; //0�̸� �����̵� 1�̸� ��Ʈ
	int cardNum;
}