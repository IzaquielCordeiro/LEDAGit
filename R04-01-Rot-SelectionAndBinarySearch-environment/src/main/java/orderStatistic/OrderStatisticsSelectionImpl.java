package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T>
{

   /**
    * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia
    * de usar o selection sem modificar o array original. Note que seu algoritmo vai
    * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem
    * desejada sem modificar o array original.
    * <p>
    * Restricoes:
    * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no
    * array original
    * - Nenhum array auxiliar deve ser criado e utilizado.
    * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
    * elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
    * como o selectionsort mas sem modificar nenhuma posicao do array).
    * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null.
    * - Considerar que k varia de 1 a N
    * - Sugestao: o uso de recursao ajudara sua codificacao.
    */
   @Override
   public T getOrderStatistics(T[] array, int k)
   {
      T lower = array[0];
      T nextGreater = null;

      for (int i = 0; i < array.length; i++)
      {
         if (array[i].compareTo(lower) < 0) lower = array[i];
      }

      for (int i = 0; i < k - 1; i++)
      {
         for (int j = 0; j <= array.length - 1; j++)
         {
            if (lower == null) break;
            if (nextGreater == null && array[j].compareTo(lower) > 0) nextGreater = array[j];

            else if (array[j].compareTo(lower) > 0 && array[j].compareTo(nextGreater) < 0) nextGreater = array[j];
         }
         lower = nextGreater;
         nextGreater = null;
      }
      return lower;
   }
}
