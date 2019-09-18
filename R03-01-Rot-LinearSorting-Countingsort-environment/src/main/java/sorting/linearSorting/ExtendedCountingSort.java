package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;
import java.util.Collections;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer>
{

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex)
   {
      if (array != null && array.length != 0 && rightIndex - leftIndex != 0)
      {
         Integer max = Collections.max(Arrays.asList(Arrays.copyOfRange(array, leftIndex, rightIndex + 1)));
         Integer min = Collections.min(Arrays.asList(Arrays.copyOfRange(array, leftIndex, rightIndex + 1)));

         Integer[] aux = new Integer[max-min + 1];
         Integer[] out = new Integer[(rightIndex - leftIndex) + 1];

         for (int i = 0; i <= max-min; i++)
         {
            aux[i] = 0;
         }

         for (int i = leftIndex; i <= rightIndex; i++)
         {
            aux[array[i]-min]+=1;
         }

         for (int i = 0; i < aux.length - 1; i++)
         {
            aux[i + 1] = aux[i] + aux[i + 1];
         }

         for (int i = rightIndex; i >= leftIndex; i--)
         {
            Integer elem = array[i];
            out[--aux[elem-min]] = elem;
         }


         for (int i = leftIndex; i <= rightIndex; i++)
         {
            array[i] = out[i];
         }
      }
   }
}
