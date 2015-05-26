
public class Prove {

	public static void main(String[] args) {
		String strA[] = {"ok","no ","Si","ok!!","ok!!"};
		Integer intA[] = {1,3,5,3,87,2,8,2};
		Utils.DPair<String,Integer> pairA =  Utils.DPair.rep(strA);
		Utils.DPair<Integer,Integer>  pairB=  Utils.DPair.rep(intA);
		System.out.println(pairA.getFirst()+" "+pairA.getSecond());
		System.out.println(pairB.getFirst()+" "+pairB.getSecond());
	}

}
