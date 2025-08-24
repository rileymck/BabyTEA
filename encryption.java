import java.util.Scanner;
public class encryption {
    public static void main(String[] args){
    	long delta1 = 0x11111111L;
    	long delta2 = 0x22222222;
	


	//THIS SECTION IS FOR K[0]-K[3]
	Scanner scan = new Scanner(System.in);
	String[] K = new String[4];//OG user input array
	long[] intK = new long[4];//array size for convered base 16

	//this loop takes the inputs for K0-k3(str)  and convers it to base 16(int)
	for(int i = 0; i < 4; i++){
		System.out.println("Please input K[" + i + "] in Hex String(without 0x): ");
		K[i] = scan.next();
		intK[i]  = Long.parseUnsignedLong(K[i], 16);
	}

	//this loop prints out the k0-k3 as that converted base 16 int
	/*
	for(int n = 0; n < 4; n++){
		//System.out.println(K[n]);
		System.out.println("k[" + n + "]" + intK[n]);
	}
	*/


	//THIS SECTION IS FOR L[0](in a loop for later uses)
	String[] L = new String[3];//OG user input array
        long[] intL = new long[3];//array size for convered base 16
	L[1] = "00000000";
	L[2] = "00000000";


        //this loop takes the inputs for L0(str)  and convers it to base 16(int)
        for(int t = 0; t < 1; t++){
                System.out.println("Please input L[" + t + "] in Hex String(without 0x): ");
                L[t] = scan.next();
                intL[t]  = Long.parseUnsignedLong(L[t], 16);
        }

        //this loop prints out the L0 as that converted base 16 int
        /*
	for(int g = 0; g < 1; g++){
                System.out.println("L[" + g + "]" + intL[g]);
        }
	*/


	//THIS SECTION IS FOR R[0](in a loop for later uses)
        String[] R = new String[3];//OG user input array
        long[] intR = new long[3];//array size for convered base 16
	R[1] = "00000000";
        R[2] = "00000000";


        //this loop takes the inputs for L0(str)  and convers it to base 16(int)
        for(int e = 0; e < 1; e++){
                System.out.println("Please input R[" + e + "] in Hex String(without 0x): ");
                R[e] = scan.next();
                intR[e]  = Long.parseUnsignedLong(R[e], 16);
        }

        //this loop prints out the L0 as that converted base 16 int
        /*
	for(int q = 0; q < 1; q++){
                System.out.println("R[" + q + "]" + intR[q]);
 	}
	*/
	//System.out.println("R1 is:" + R[1] + "R2 is:" +  R[2]);
	//System.out.println("L1 is:" + L[1] + "L2 is:" +  L[2]);
	


	//THIS SECTION IS FOR THE CALCULATIONS
	//Round One
	//LSL R[0] by 4
	int fo = 4;
	int fi = 5;
	long o = intR[0] << fo;
	long one = o + intK[0];
	long mr = one & 0xFFFFFFFFL; //makes sure that only the lower 8 digits show
	String resulto = Long.toHexString(mr); //CONVERTS BACK TO HEX
	//System.out.println("LSL: " + resulto);
	
	//delta1 + R[0]
	long t = intR[0] + delta1;
	long tm = t & 0xFFFFFFFFL; //makes sure that only the lower 8 digits show
	String resultt = Long.toHexString(tm); //CONVERTS BACK TO HEX
        //System.out.println("delta1 + R[0]: " + resultt);
	
	//LSR R[0] by 5
	long oy = intR[0] >>> fi;
        long two = oy + intK[1];
        long m = two & 0xFFFFFFFFL; //makes sure that only the lower 8 digits show
        String result = Long.toHexString(m); //CONVERTS BACK TO HEX
	while (result.length() < 8){
		result = "0" + result;
	}
        //System.out.println("LSR: " + result);

	//XOR all 3 numbers together for the next round of calculations
	long RO = m ^ tm ^ mr;
	long ro = RO & 0xFFFFFFFFL;
	String roundOne = Long.toHexString(ro);
	//System.out.println("XOR: " + roundOne);

	//L[0] + XOR
	long a = ro + intL[0];
	long ap = a & 0xFFFFFFFFL;
	String add = Long.toHexString(ap);
	//System.out.println("add: " + add);

	//Round Two
	intL[1] = intR[0];
	intR[1] = ap;
	//System.out.println("This should print OG r[0]: " + Long.toHexString(intL[1]));
	//System.out.println("This should print b72599b2 l[0]: " + Long.toHexString(intR[1]));
	
	//LSL R[1] by 4
	long too = intR[1] << fo;
	//System.out.println("after logic shift: " + Long.toHexString(too));
	long t_w_o = too + intK[2];
	long mt = t_w_o & 0xFFFFFFFFL; //makes sure that only the lower 8 digits show
	String resultto = Long.toHexString(mt);
	//System.out.println("LSL R[1]: " + resultto);

	//delta2 + R[1]
	long h = intR[1] + delta2;
	long he = h & 0xFFFFFFFFL; //makes sure that only the lower 8 digits show
	String hel = Long.toHexString(he);
	//System.out.println("delta2 + R[1]: " + hel);

	//LSR R[1] by 5
	long l = intR[1] >>> fi;
	long lo = l + intK[3];
	long w = lo & 0xFFFFFFFFL; //makes sure that only the lower 8 digits show
	String wo = Long.toHexString(w);
	//System.out.println("LSR R[1]: " + wo);

	//XOR all 3 numbers together for the next round of calculations
        long ie = w ^ he ^ mt;
        long TWO = ie & 0xFFFFFFFFL;
        String roundTwo = Long.toHexString(TWO);
        //System.out.println("XOR R[1]: " + roundTwo);

	//L[0] + XOR
        long d = TWO + intL[1];
        long don = d & 0xFFFFFFFFL;
        String addtwo = Long.toHexString(don);
        //System.out.println("add: " + addtwo);

	//End
	intL[2] = intR[1]; 
	intR[2] = don;	


	//Printing everything out 
	for(int i = 0; i < 3; i++){
		System.out.println("L[" + i + "]: " + Long.toHexString(intL[i]) + "     R[" + i + "]: " + Long.toHexString(intR[i]));
	}

}
}
