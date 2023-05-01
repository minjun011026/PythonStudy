import java.util.Scanner;

public class practice8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("인원수>>");
        int number = sc.nextInt();
        PhoneBook Phone_Book = new PhoneBook(number);
        for(int i = 0; i<number; i++){
            System.out.print("이름과 전화번호(이름과 번호는 빈 칸없이 입력)>>");
            String name = sc.next();
            String tel = sc.next();
            Phone_Book.Insert(name, tel);
        }
        System.out.println("저장되었습니다...");
        while(true){
            System.out.print("검색할 이름>>");
            String Input_name = sc.next();
            if(Input_name.equals("그만")) break;
            Phone_Book.Search(Input_name);
        }
        sc.close();
    }
}
class Phone{
    String name;
    String tel;
    public Phone(String name, String tel){
        this.name = name;
        this.tel = tel;
    }
}
class PhoneBook{
    Phone[] Phones;
    int index=0;
    public PhoneBook(int number){
        Phones = new Phone[number];
    }
    public void Insert(String name, String tel){
        Phones[index] = new Phone(name, tel);
        index++;
    }
    public void Search(String name){
        for(int i = 0; i<index; i++){
            if(Phones[i].name.equals(name)){
                System.out.println(name+"의 번호는 "+Phones[i].tel+" 입니다.");
                return;
            }
        }
        System.out.println(name+" 이 없습니다.");
    }
}