import java.util.HashMap;
import java.util.Map;


public class Utils {
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
	
        public static <T,E> DPair<T, Integer> rep(T[] ss) {
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
    }

    
    
    public static void main(String[] args) { }

}
