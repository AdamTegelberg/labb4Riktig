import java.util.Scanner;  


public class lab4 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("search <pattern> <file>");

        String userInput = scanner.nextLine();
        String pattern = userInput.split("\\s+")[1];
        String fileName = userInput.split("\\s+")[2]; 
        TextReader reader = new TextReader();
        reader.read(fileName, pattern);

        scanner.close();
    }
}
