package myTestProject;

import java.util.Scanner;

public class GameControl {
	static int pMoney;
	static int aMoney;
	int pBet;
	int aBet;
	int bettingSum;
	static boolean turnPass = false;  //������ ai�� �÷��̾ �׾������ �ݴ��̸� �н��ϱ�����
	static boolean first = true;  //�÷��̾� ���ϋ� true
	
	GameControl(int pMoney, int aMoney){  //������
		GameControl.pMoney = pMoney; 
		GameControl.aMoney = aMoney;
	}
	
	void playerTurn(){
		Scanner scan = new Scanner(System.in);
		if(first == true){
			System.out.println("�������Դϴ� ! ");
			//apMoney();  //���� Ĩ ��
			System.out.printf("��� ī��� %s %d �Դϴ�.\n",
					(Card.card[Card.cardCnt+1].cardName == 0?"�����̵�":"��Ʈ"),
					Card.card[Card.cardCnt+1].cardNum);
			System.out.println("1 . �����Ѵ�. (Ĩ1�� �̻� ����)");
			System.out.println("2 . �״´�. (Ĩ1�� ������ ������ �й�)");
			switch(Input.input(2)){
			case 1:  //���ý�
				while(true){
					System.out.print("���þ� �Է� >> ");
					String sTmp = scan.next(); 
					try{
						pBet = Integer.parseInt(sTmp);
					}catch(Exception e){
						System.out.println("���⿡ �ִ°� �ȿ��� �Է����ּ���.");
						continue;
					}
					
					if(pBet == 0){
						System.out.println("0���� �����Ҽ������ϴ�.");
					}else if(pBet > pMoney){
						System.out.printf("Ĩ�� �����մϴ� (�����Ĩ %d��)\n",pMoney);
					}else if(pBet > aMoney){
						System.out.printf("��밡 ���� Ĩ���� ���� �����Ҽ������ϴ�.(��밡 ���� Ĩ%d��)\n",aMoney);
					}else{  //���� �Ϻ��� ����
						pMoney -= pBet;  //������ ���þ׸�ŭ ����
						System.out.printf("%d���� �����Ͽ����ϴ�.\n",pBet);
						break;
					}
				}
				//����Ȯ��  --------
				break;
			case 2:  //������
				System.out.println("�׾����ϴ� ����� Ĩ�� 1�� �����ǰ� ����� Ĩ�� 1�� �����մϴ�.");
				System.out.printf("����� ī��� %s %d �����ϴ�.\n",
						(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
						Card.card[Card.cardCnt].cardNum);
				pMoney --;  				//1����
				aMoney += (1+bettingSum);	//�����þ�+���ι��þ� �����ֱ� (�������̱⋚���� �����þ��̾���)
				turnPass = true;
				//���⿡�� Ȯ��
				break;
			}
			
		}else if(turnPass == false && first == false){
			System.out.println("�Ĺ����Դϴ� ! ");
			//apMoney();
			System.out.printf("��� ī��� %s %d �Դϴ�.\n",
					(Card.card[Card.cardCnt+1].cardName == 0?"�����̵�":"��Ʈ"),
					Card.card[Card.cardCnt+1].cardNum);
			System.out.println("1 . ���Ѵ�. (����� ���ð� ���� Ĩ����ŭ �Ǵ�)");
			System.out.println("2 . �״´�. (Ĩ 1�� ������ ������ �й�)");
			switch(Input.input(2)){
			case 1:  //���Ͻ�
				pMoney -= aBet;  //��� ���þ׸�ŭ ����
				pBet = aBet;
				//�����ʾ��� ��쿡�� ����Ȯ���� ���ݽô�!!!!!!!!!!!!!
				outCome();
				break;
			case 2:  //������
				System.out.printf("����� ī��� %s %d �̿����ϴ�.\n",
						(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
						Card.card[Card.cardCnt].cardNum);
				pMoney --;  				//1����
				aMoney += (1+aBet+bettingSum);	//�����þ�+�����þ�+���ι��þ� �����ֱ�
				betReset();  //���� ����
				break;
			}
		}
	}
	
	void aiTurn(){
		Rand betRand = new Rand();  //�������� ���� ���� ��Ʈ��
		if(turnPass == false && first == true){  //Ai �Ĺ���
			if(betRand.aiCall() == true){  //���Ͻ�
				System.out.println("��밡 �̹����ÿ� �� �߽��ϴ�.");
				aMoney -= pBet;  //�÷��̾ �����Ѱ͸�ŭ ��
				aBet = pBet;  //p�� �����Ѱ͸�ŭ �ִ´�
				outCome();
			}else{
				System.out.println("��밡 �̹����ÿ��� �׾����ϴ�.");
				System.out.printf("����� ī��� %s %d �Դϴ�.\n",
						(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
						Card.card[Card.cardCnt].cardNum);
				aMoney --;  				//��� 1����
				pMoney += (1+pBet+bettingSum);	//�����þ�+�����þ�+���ι��þ� �����ֱ�
				betReset();  //���� ����
				
			}
		}else if(first == false){  //Ai ������
			if(betRand.aiCall() == true){  //�����ϱ�
				int nTmp = betRand.aiBet();
				System.out.println("��밡 "+nTmp+"�� �����Ͽ����ϴ�.");
				aBet = nTmp;
				aMoney -= aBet;
			}else{  //��������
				System.out.printf("����� ī��� %s %d �Դϴ�.\n",
						(Card.card[Card.cardCnt+1].cardName == 0?"�����̵�":"��Ʈ"),
						Card.card[Card.cardCnt+1].cardNum);
				System.out.println("��밡 ������ �����Ͽ����ϴ�.");
				System.out.printf("������� ī��� %s %d �����ϴ�.\n",
						(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
						Card.card[Card.cardCnt].cardNum);
				aMoney --;  				//1����
				pMoney += (1+bettingSum);	//�����þ�+���ι��þ� �����ֱ� (�������̱⋚���� �����þ��̾���)
				turnPass = true;
			}
		}
	}
	
	int dieCheak(){
		if(pMoney == 0 && aMoney == 0){
			System.out.println("��Ű� ��� �Ѵ� Ĩ�� �����ϴ� �Ѵ� �Ļ��޽��ϴ�.");
			System.out.println("-------- GameOver --------");
			return -1;
		}else if(pMoney == 0){  //�й迩�� Ȯ���� ���κ���
			System.out.println("���̻� Ĩ�� �����ϴ�. ����� �Ļ��߽��ϴ�...");
			System.out.println("-------- GameOver --------");
			return 1;
		}else if(aMoney == 0){
			System.out.println("����� Ĩ�� �����ϴ�. ����� �¸��߽��ϴ�!!!");
			System.out.println("-------- GameOver --------");
			return 2;
		}else{
			return 0;  //�����ο� �����϶�
		}
	}
	
	void apMoney() {  //��Ű� ����� Ĩ ���
		System.out.printf("-------------------------\n"
						+ "����� ���� Ĩ�� ���� : %d\n"
				  		+ "��밡 ���� Ĩ�� ���� : %d\n"
						+ "-------------------------\n"
				  			,pMoney,aMoney);
	}
	
	void outCome(){  //���̵��� ���
		//�÷��̾� 					//���
		if(Card.card[Card.cardCnt].cardNum > Card.card[Card.cardCnt+1].cardNum){
			System.out.printf("�÷��̾��� ī��� %s %d �����ϴ�.\n",
					(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
					Card.card[Card.cardCnt].cardNum);
			System.out.println("�¸��Ͽ����ϴ�.");
			pMoney += pBet;  //�÷��̾� ����
			pMoney += aBet;  //ai ����
			pMoney += bettingSum;  //���� �հ赵 �ֱ�
			bettingSum = 0;  //���� �հ� �ʱ�ȭ
		}else if(Card.card[Card.cardCnt].cardNum < Card.card[Card.cardCnt+1].cardNum){
			System.out.printf("�÷��̾��� ī��� %s %d �����ϴ�.\n",
					(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
					Card.card[Card.cardCnt].cardNum);
			System.out.println("�й��Ͽ����ϴ�.");
			aMoney += aBet;  //ai ���� ��������
			aMoney += pBet;  //�÷��̾� ���� ��������
			aMoney += bettingSum;
			bettingSum = 0;
		}else{  //�������
			System.out.printf("�÷��̾��� ī��� %s %d �����ϴ�.\n",
					(Card.card[Card.cardCnt].cardName == 0?"�����̵�":"��Ʈ"),
					Card.card[Card.cardCnt].cardNum);
			System.out.println("�����ϴ�.");
			bettingSum += pBet;
			bettingSum += aBet;
		}
	}
	
	void betReset(){  //������ ���� �ʱ�ȭ.
		aBet = 0;
		pBet = 0;
	}
	
}
