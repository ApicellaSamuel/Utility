import java.util.HashMap;
import java.util.Map;


public class Utils {
    public class DPair<F, S> {
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
    public static <E, T> DPair<T, Integer>  rep(T[] ss) {
    	Map<T, Integer> map = new HashMap<>();
        for(T s : ss){
        	if (map.containsKey(s)){
        		Integer add=map.get(s);
        		map.put(s,add+1);
        	}else{
        		map.put(s,0);
        	}
        }
        
        Integer max = 0;
        T val = ss[0];
       
        for(T s : ss){
        	if (map.get(s)>max){
        		max =map.get(s);
        		val =  s;
        		//pair.setFirst(s);
        		//pair.setSecond(max);
        		}
        	}
        DPair<T,E> pair =new DPair<T,E>(val,(E) map.get(val));
        
        return (DPair<T, Integer>) pair;
    }
    
	
    
    
    
    public static void main(String[] args) { }

}
