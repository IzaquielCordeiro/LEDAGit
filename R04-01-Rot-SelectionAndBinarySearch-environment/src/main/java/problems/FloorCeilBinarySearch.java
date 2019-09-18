package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * <p>
 * Restricoes:
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais)
 * - O tempo de seu algoritmo deve ser O(log n).
 *
 * @author Adalberto
 */
public class FloorCeilBinarySearch implements FloorCeil
{

   @Override
   public Integer floor(Integer[] array, Integer x)
   {
      return floor(array, 0, array.length - 1, x);
   }

   private Integer floor(Integer[] array, int to, int from, Integer x)
   {
      int start = to;
      int middle = (to+from) / 2;
      int end = from;

      if (end - start <= 1) return array[start];

      if (array[middle].compareTo(x) == 0) return x;

      if (x.compareTo(array[middle + 1]) >= 0) return floor(array, middle+1, end, x);
      else return floor(array, start, middle, x);
   }

   @Override
   public Integer ceil(Integer[] array, Integer x)
   {
      return ceil(array, 0, array.length - 1, x);
   }

   private Integer ceil(Integer[] array, int to, int from, Integer x)
   {
      int start = to;
      int middle = (to+from) / 2;
      int end = from;

      if(end-start <= 1) return array[start];

      if(x.compareTo(array[middle])==0) return x;

      if(x.compareTo(array[middle])<=0) return ceil(array, start, middle, x);
      else return ceil(array, middle+1, end, x);
   }

}
