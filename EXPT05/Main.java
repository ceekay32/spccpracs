import java.util.*;

class Main {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the productions for S: ");
    String gram = sc.nextLine();

    String[] prod = gram.split(" ");
    

    String common = "";
    int minLength = Math.min(prod[0].length(), prod[1].length());
    for (int i = 0; i < minLength; i++) {
        if (prod[0].charAt(i) != prod[1].charAt(i)) {
            common = prod[0].substring(0, i);
            break;
        } else {
          common = prod[0].substring(0, minLength);
        }
    }

    String p1 = prod[0].substring(minLength - 1, prod[0].length());
    String p2 = prod[1].substring(minLength - 1, prod[1].length());

    System.out.println("\nThe modified grammar is: ");
    System.out.println("S -> "+common+" | X");
    System.out.println("X -> "+p1+" | "+p2);
  }
}