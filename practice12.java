import java.util.Scanner;

public class practice12 {
    public static void main(String[] args) {
        boolean isReserving = true;
        SeatSystem seatSystem = new SeatSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("명품콘서트홀 예약 시스템입니다.");
        while(isReserving){
            int seatInput;
            String nameInput;
            int position;
            System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");
            int systemInput = sc.nextInt();
            switch (systemInput) {
                case 1:
                    System.out.print("좌석구분 S(1), A(2), B(3)>>");
                    seatInput = sc.nextInt();
                    if(seatInput>3||seatInput<1){
                        System.out.println("잘못된 입력입니다.");
                        break;
                    }
                    seatSystem.showSeat(seatInput);
                    System.out.print("이름>>");
                    nameInput = sc.next();
                    System.out.print("번호>>");
                    position = sc.nextInt();
                    if(position>10||position<1){
                        System.out.println("잘못된 입력입니다.");
                        break;
                    }
                    seatSystem.reserveSeat(seatInput, position, nameInput);                
                    break;
                case 2:
                    seatSystem.showSeat(1);
                    seatSystem.showSeat(2);
                    seatSystem.showSeat(3);
                    System.out.println("<<<조회를 완료하였습니다.>>>");
                    break;
                case 3:
                    System.out.print("좌석 S:1, A:2, B:3>>");
                    seatInput = sc.nextInt();
                    seatSystem.showSeat(seatInput);
                    System.out.print("이름>>");
                    nameInput = sc.next();
                    seatSystem.deletSeat(seatInput, nameInput);
                    break;
                case 4: 
                    isReserving = false;
                    break;
                default :
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
        sc.close();
    }
}

class SeatSystem{
    String[] S_Seat = {"","---", "---", "---", "---", "---", "---", "---", "---", "---", "---"};
    String[] A_Seat = {"", "---", "---", "---", "---", "---", "---", "---", "---", "---", "---" };
    String[] B_Seat = {"","---", "---", "---", "---", "---", "---", "---", "---", "---", "---" };

    public void showSeat(int x){
        if(x==1){
            System.out.print("S>> ");
            for(int i =1; i<11; i++){
                System.out.print(S_Seat[i]+" ");
            }
        }else if(x==2){
            System.out.print("A>> ");
            for (int i = 1; i < 11; i++) {
                System.out.print(A_Seat[i] + " ");
            }
        }else if(x==3){
            System.out.print("B>> ");
            for (int i = 1; i < 11; i++) {
                System.out.print(B_Seat[i] + " ");
            }
        }
        System.out.println();
    }
    public void reserveSeat(int x,int position, String name){
        if(x==1){
            S_Seat[position] = name;
        }else if(x==2){
            A_Seat[position]=name;
        }else if(x==3){
            B_Seat[position]=name;
        }
    }
    public void deletSeat(int x, String name){
        if (x == 1) {
            for(int i = 1; i<11; i++){
                if(S_Seat[i].equals(name)){
                    S_Seat[i]="---";
                    break;
                }
            }
        } else if (x == 2) {
            for (int i = 1; i < 11; i++) {
                if (A_Seat[i].equals(name)) {
                    A_Seat[i] = "---";
                    break;
                }
            }
        } else if (x == 3) {
            for (int i = 1; i < 11; i++) {
                if (B_Seat[i].equals(name)) {
                    B_Seat[i] = "---";
                    break;
                }
            }
        }
    }
}