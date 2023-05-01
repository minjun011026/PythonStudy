import java.util.Scanner;

public class practice10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        System.out.println("한영 단어 검색 프로그램입니다.");
        while(true){
            System.out.print("한글 단어?");
            String word = sc.next();
            if(word.equals("그만")){
                break;
            }
            if(dictionary.kor2Eng(word)==null){
                System.out.println(word+"는 저의 사전에 없습니다.");
            }else{
                System.out.println(word+"은 "+dictionary.kor2Eng(word));
            }
        }
        sc.close();
    }

}
class Dictionary{
    private static String [] kor = {"사랑","아기","돈","미래","희망"};
    private static String [] eng = {"love","baby","money","future","hope"};
    public static String kor2Eng(String word){
        for(int i = 0; i<5;i++){
            if(word.equals(kor[i])){
                return eng[i];
            }
        }
        return null;
    }
}