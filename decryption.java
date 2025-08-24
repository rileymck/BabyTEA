import java.util.Scanner;
public class decryption {
  //outside of main method because they are constant int, cant be changed
  public static final int deltaOne = 0x11111111;
  public static final int deltaTwo = 0x22222222;
  
public static void main(String[] args) {
  
    int[] k = new int [4];
    int[] L = new int [3]; 
    int[] R = new int [3];
    
    Scanner obj = new Scanner(System.in);

    //all display a promt for user input, k[0-3], L[2], R[2]
    for(int i=0; i < 4; i++){
      System.out.printf("Please input k[%d] in Hex String (without the \"0x\"):", i); // %d is a placeholder for an integer
      String input = obj.nextLine();
      k[i] = Integer.parseUnsignedInt(input, 16);
    }

    System.out.println("Please input L[2] in Hex String (without the \"0x\"):");
    String input2 = obj.nextLine();
    L[2] = Integer.parseUnsignedInt(input2, 16);
    
    System.out.println("Please input R[2] in Hex String (without the \"0x\"):");
    String input3 = obj.nextLine();
    R[2] = Integer.parseUnsignedInt(input3, 16);
    
    //initalized all to 0
    L[0] = 0x00000000;
    L[1] = 0x00000000;
    R[0] = 0x00000000;
    R[1] = 0x00000000;


    // // Decryption; ALGORITHM WAS PROVIDED, but also used diagram to help with understanding
    R[0] = subtractMod32(R[2], (((L[2] << 4) + k[2]) ^ (L[2] + deltaTwo) ^ ((L[2] >>> 5) + k[3])));
    L[0] = subtractMod32(L[2], (((R[0] << 4) + k[0]) ^ (R[0] + deltaOne) ^ ((R[0] >>> 5) + k[1])));
       // looking at the diagram it shows this
    R[1] = L[2];
    L[1] = R[0];
    
    // Display results
    System.out.println("DeltaOne = 0x" + Integer.toHexString(deltaOne).toUpperCase());
    System.out.println("DeltaTwo = 0x" + Integer.toHexString(deltaTwo).toUpperCase());
    System.out.println("KZero = 0x" + Integer.toHexString(k[0]).toUpperCase());
    System.out.println("KOne = 0x"+Integer.toHexString(k[1]).toUpperCase());
    System.out.println("KTwo = 0x"+Integer.toHexString(k[2]).toUpperCase());
    System.out.println("KThree = 0x"+Integer.toHexString(k[3]).toUpperCase());

    System.out.println("LZero = 0x" + Integer.toUnsignedString(L[0], 16).toUpperCase());
    System.out.println("RZero = 0x" + Integer.toUnsignedString(R[0], 16).toUpperCase());
    System.out.println("LOne = 0x" + Integer.toUnsignedString(L[1], 16).toUpperCase());
    System.out.println("ROne = 0x" + Integer.toUnsignedString(R[1], 16).toUpperCase());

    System.out.println("LTwo = 0x" + Integer.toUnsignedString(L[2], 16).toUpperCase());
    System.out.println("RTwo = 0x" + Integer.toUnsignedString(R[2], 16).toUpperCase());
    
    obj.close();
    
  }
  private static int subtractMod32(int a, int b) {
      return (a-b) & 0xFFFFFFFF;
  }
}
