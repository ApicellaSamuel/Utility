import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Utils { 
    
    public static void main(String[] args) {
		String strA[] = {"ok","no ","Si","ok!!","ok!!"};
		Integer intA[] = {1,3,5,3,87,2,8,2,3};
		Import.DPair<String,Integer> pairA =  Import.CountValue(strA);
		Import.DPair<Integer,Integer>  pairB=  Import.CountValue(intA);
	    Import.Pair<String> pairC = new Import.Pair<String>("primo","secondo");
		System.out.println(pairA.getFirst()+" "+pairA.getSecond());
		System.out.println(pairB.getFirst()+" "+pairB.getSecond());
		System.out.println(pairC.getFirst()+" "+pairC.getSecond());
		Import.MSet<String> set = new Import.MSet<String>();
		set.addElem("www.linux.org");
		set.addElem("www.python.org");
		set.addElem("www.linux.org");
		System.out.println("www.linux.org : "+set.ritValue("www.linux.org"));
		String strB[] = {"ok","no ","Si","ok!!","ok!!"};
		Import.MSet<String> mSet = Import.toMSet(strB);
		mSet.printMSet();
		Set<String> setA = new HashSet<String>();
		setA.add(strA[0]);setA.add(strA[1]);
		Import.printSet(setA);
		List<String> listA = new ArrayList<String>();
		listA.add(strA[0]);listA.add(strA[1]);listA.add("forse");
		listA = Import.removeArray(listA,strA);
		listA.forEach(System.out::print);
		Import.HTree<String> tree= new Import.HTree<String>("Radice");
		tree.addChild("Radice","1_figlio","3_figlio");
		tree.addChild("1_figlio","2_figlio","4_figlio");
		tree.printHTree("Radice");
    	Path root =Paths.get("/home/sam/Pubblici/").toAbsolutePath();
		Import.HTree<Path> treePc= Import.giveDirectoryPc("/home/sam/Pubblici/");
		Import.HTree<Path> treePcRoot= Import.giveDirectoryPc(root);
		Import.giveDirectoryPc(root).printHTree(root);
		treePc.printHTree(root);
		treePcRoot.printHTree(root);
    }

}
