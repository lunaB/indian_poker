package myTestProject;

public class IndianPoker {

	static int pMoney=0;  //�ʱ⼳��
	static int aMoney=0;
	
	public static void main(String[] args) {
		choose_start();
	}
	
	static void choose_start(){
		Game_info Info = new Game_info();
		while(true){
			System.out.println("------�ε�� ��Ŀ�Դϴ�------");
			System.out.println("1 . ������ �����մϴ�.");
			System.out.println("2 . ���Ӽ��������ϴ�");
			System.out.println("3 . ������ �����մϴ�.");
			switch(Input.input(3)){
			case 1:
				System.out.println("1. ���ٴٵ� ��� �й�(50/50)");
				System.out.println("2. ���� ���(100/50)");
				System.out.println("3. �ϵ� ���(50/150)");
				System.out.println("4. Ŀ���� ���(����)");
				switch(Input.input(4)){
				case 1:
					pMoney = 50;
					aMoney = 50;
					break;
				case 2:
					pMoney = 100;
					aMoney = 50;
					break;
				case 3:
					pMoney = 50;
					aMoney = 100;
					break;
				case 4:
					System.out.println("�÷��̾�Ĩ 1~500");
					pMoney = Input.input(500);
					System.out.println("��Ĩ 1~500");
					aMoney = Input.input(500);
					break;
				}
				System.out.println("������ �����մϴ�.");
				game_board();
				break;
			case 2:
				System.out.println("���Ӽ��������ϴ�");
				Info.intro();
				break;
			case 3:
				System.out.println("������ �����մϴ�.");
				System.exit(0);
			}
		}
	}
	
	static void game_board(){  //������
		GameControl gc = new GameControl(pMoney,aMoney);  
		//���� ��Ʈ�ѷ� ���� (ù���� ���� ��° ���� ��뵷)
		Card card = new Card();  //ī�� ����
		card.shake_card();  //ī�� ����
		int turn = 0;
		for(turn=0;;turn++){
			System.out.printf("--------%d�� ����--------\n",turn+1);  //�� ����â
			gc.apMoney();  //������ Ĩ�� �߰�
			if(GameControl.first == true){  //�÷��̾
				gc.playerTurn();	//�÷��̾���
				gc.aiTurn();  		//�����
				GameControl.first = false;	//���� �ٲٱ�
			}else if(GameControl.first == false){
				gc.aiTurn();
				gc.playerTurn();
				GameControl.first = true;
			}
			Card.cardCnt += 2;	//ī��Ʈ �ø���
			if(Card.cardCnt == 20){
				card.shake_card();  //ī�弯��
				Card.cardCnt = 0;  //ī�� ī��Ʈ �ʱ�ȭ
			}
			
			GameControl.turnPass = false;  	//�Ĺ��� �н� �ʱ�ȭ
			//����Ȯ��
			if(gc.dieCheak() != 0){  //�������� ������ ..
				break;
			}
		}
		System.out.println((turn+1)+"�� ���� ������ �������ϴ�!");
	}
	
}
