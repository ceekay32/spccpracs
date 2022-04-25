import java.util.*;
import java.io.*;

public class LF {
    public static void main(String[] args){

        System.out.println("Enter production rule :");
        System.out.print("A -> ");

        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();

        String[] prod=s.split("\\|");
       

        int i,minLen = Math.min(prod[0].length(), prod[1].length());
        for (i = 0; i < minLen;++i) 
            if(prod[0].charAt(i)!=prod[1].charAt(i))
                break;
            
        String common = prod[0].substring(0,i);
        String p=prod[0].substring(i) +"|"+ prod[1].substring(i);

        System.out.println("\nModified Grammar : ");
        System.out.println("A -> " +common+ "A'");
        System.out.println("A' -> " +p);
    }
}
// output 
// A -> abBP|ab+c