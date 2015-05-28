import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Import {
	
	public static class DPair<F, S> {
        /** Crea una coppia di valori.
         * @param first  primo valore della coppia
         * @param second  secondo valore della coppia */
        public DPair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() { return first; }
        public S getSecond() { return second; }

        public void setFirst(F x) { first = x; }
        public void setSecond(S x) { second = x; }

        private F first;
        private S second;
        
    }
    public static <T,E> DPair<T, Integer> CountValue(T[] ss) {
    	Map<T, Integer> map = new HashMap<>();
        for(T s : ss){
        	if (map.containsKey(s)){
        		Integer add=map.get(s);
        		map.put(s,add+1);
        	}else{
        		map.put(s,1);
        	}
        }
        Integer max = map.get(ss[0]) ;
        T val = ss[0];
        for(T s : ss){
        	if (map.get(s) >= max){
        		max = map.get(s);
        		val =  s;
        		}
        	}
        return  new DPair< T,Integer >(val, max);
    }
    public static class Pair<T> extends DPair<T,T>{
    	public Pair(T first, T second) {
			super(first, second);
		}
    }
    public static class MSet<T>{
    	public MSet(){
    		this.mSet=new HashMap<T, Integer >();// HashMap -- Map
    		}
    	public void addElem(T elem){
    		if (mSet.containsKey(elem)){
        		Integer add=mSet.get(elem);
        		mSet.put(elem,add+1);
        	}else{
        		mSet.put(elem,1);
        	}
    	}
    	public void subElem(T elem){
    		if (mSet.containsKey(elem)){
        		Integer sub=mSet.get(elem);
        		mSet.put(elem,sub-1);
        	}
    		if (mSet.get(elem)==0){
    			mSet.remove(elem);
    		}
    	}
    	public Integer ritValue(T elem){
    		if (mSet.containsKey(elem)){
    			return mSet.get(elem);
    		}
    		return 0;
    	}
        
    	public void printMSet(){
    		Iterator<Entry<T, Integer>> iter = mSet.entrySet().iterator();
    		
    		while (iter.hasNext()){
    			Map.Entry<T,Integer> entry = (Map.Entry<T,Integer>)iter.next();
    			System.out.print( entry.getKey()+": ");
    		    System.out.print( entry.getValue()+"; ");
    		}
    	}
    	
    	private HashMap<T,Integer> mSet ;
    }
    public static <T> MSet<T> toMSet(T[] elemVet){
		MSet<T> mSet = new MSet<T>();
		for(T elem : elemVet){
			mSet.addElem(elem);
		}		
		return mSet;
	}
    public static <T> Set<T> mergeSet(Set<T> set1,Set<T> set2){
    	Set<T> newSet= new HashSet<T>();
    	set1.forEach(newSet::add);
    	set2.forEach(newSet::add);
    	return null;
    }
    public static <T> void printSet(Set<T> set){
    	set.forEach(System.out::print);
    }
    public static <T> List<T> removeArray(List<T> list, T[] rr){
    	for(T r : rr){
    		if(list.contains(r)){
    			list.remove(r);
    		}
    	}
    	
    	return list;
    
    	
    }
    public static class HTree<T>{
    	public HTree(T root){
    		this.root=root;
    		parent=null;
    		child.put(root,new ArrayList<T>());
    	}
    	public void addChild(T elemP, T...elemC){//***Type safety: Potential heap pollution 
    		for (T e : elemC ) {				 //   via varargs parameter elemC***
    			parent=elemP;
    			child.get(elemP).add(e);
    			child.put(e,new ArrayList<T>()); 
    		}
    	}
    	public List<T> getChild(T root){
    		return child.get(root);
    	}
    	public void printHTree(T root){
    		System.out.println(ricVisit(root,""));
    		
    	}
    	private String ricVisit(T root, String indent){
    			String rit = indent + " ";
    			rit += root+"\n";
    		    for (T c : getChild(root)){
    		    	 rit += ricVisit(c,indent+"   |");
    		    }
    		    return rit;
    	}
    	public T getRoot() { return root; }
    	@SuppressWarnings("unused")
		private T parent;
    	private T root;
    	private Map<T,List<T>> child=new HashMap<>();
    	
    }
    public static HTree<Path> giveDirectoryPc(String root){
    	Path rootPath = Paths.get(root).toAbsolutePath();
    	return DirectoryPc(rootPath);
    }
    public static HTree<Path> giveDirectoryPc(Path root){
    	Path rootPath = root;
    	return DirectoryPc(rootPath);
    }
    public static HTree<Path> DirectoryPc(Path root){
    	class visitor extends SimpleFileVisitor<Path>{
    		@Override
    		public FileVisitResult preVisitDirectory(Path dir,
    				BasicFileAttributes attrs) throws IOException {
    			if (!(dir==root)){
    			treePc.addChild(dir.getParent(),dir);}
    			return super.preVisitDirectory(dir, attrs);
    		}
    		@Override
    		public FileVisitResult visitFile(Path file,
    				BasicFileAttributes attrs) throws IOException {
    			treePc.addChild(file.getParent(),file);
    			return super.visitFile(file, attrs);
    		}
    		
    		HTree<Path> treePc= new HTree<Path>(root);
    	}
    	
    	visitor vis = new visitor();
    	
    	try {
			Files.walkFileTree(root, vis);
		}catch (IOException e){System.out.println("Errore: cartella(root) inesistente");}
    	
    	return vis.treePc;
    }
    public static String read(InputStream in, Charset cs) {
        BufferedReader r = new BufferedReader(new InputStreamReader(in, cs));
        return r.lines().collect(Collectors.joining("\n"));
    }
    public static String loadPage(String url,Charset cs) throws IOException{
    	URL urlA;  URLConnection urlB;
    	urlA = new URL(url);
    	urlB = urlA.openConnection();
    	urlB.setRequestProperty("User-Agent", "Mozilla/5.0");
        urlB.setRequestProperty("Accept", "text/html;q=1.0,*;q=0");
        urlB.setRequestProperty("Accept-Encoding", "identity;q=1.0,*;q=0");
        urlB.setConnectTimeout(5000);
        urlB.setReadTimeout(10000);
        urlB.connect();
    	return read(urlB.getInputStream(), cs);
    }
    public static void asyncLoad(String[] urlSS){
    	
		for (String urlS: urlSS){
			Thread asyncLoad = new Thread(() -> {
				String resources;
				try {
					resources = Import.loadPage(urlS, StandardCharsets.UTF_8);
					System.out.println(resources.length());
				} catch (IOException e) {
					System.out.println("Errore nel caricamento della pagina");
				}
			});
			asyncLoad.start();
		}
    }
    
}
