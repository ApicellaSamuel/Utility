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
        
        public <T,Integer> DPair<T, Integer>  rep(T[] ss) { //error
        	Map<T, Integer> map = new HashMap<>();
            for(T s : ss){
            	if (map.containsKey(s)){
            		Integer add=map.get(s);
            		map.put(s,add+1); //error
            	}else{
            		map.put(s,1);  //error
            	}
            }
            
            Integer max = 0; //error
            T val = ss[0];
            for(T s : ss){
            	if (map.get(s)>max){  //error
            		max =map.get(s);
            		val =  s;
            		}
            	}
            
            
            return  new DPair< T,Integer >(val, max);
        }
    }

    
    
    public static void main(String[] args) { }

}

