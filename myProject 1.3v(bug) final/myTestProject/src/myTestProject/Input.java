package myTestProject;

import java.util.Scanner;

public class Input {
	static int input(int num){  //�Է¹��� ������ ����
		Scanner scan = new Scanner(System.in);
		int input = 0;
		String sTmp = "";
		while(true){
			System.out.print("�Է� >>  ");
			sTmp = scan.next();
			try{
				input = Integer.parseInt(sTmp);
			}catch(Exception e){
				System.out.println("���⿡ �ִ°Ϳ��� �Է����ּ���.");
				continue;
			}
			for(int i=1;i<=num;i++){  // �޴°� ����
				if(input == i){
					return i;
				}
			}
			System.out.println("���⿡ �ִ°͸� �Է����ּ���");
		}
	}
}
