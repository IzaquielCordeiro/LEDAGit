package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;
import java.util.Collections;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer>
{

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex)
	{
		if (array != null && array.length != 0 && rightIndex - leftIndex != 0)
		{

			//Pega o maior elemento dentro do intervalo [leftIndex..rightIndex]
			Integer n = Collections.max(Arrays.asList(Arrays.copyOfRange(array, leftIndex, rightIndex + 1)));

			Integer[] aux = new Integer[n + 1];
			Integer[] out = new Integer[(rightIndex - leftIndex)+1];

			for (int i = 0; i <= n; i++)
			{
				aux[i] = 0;
			}

			for (int i = leftIndex; i <= rightIndex; i++)
			{
				aux[array[i]]++;
			}

			for(int i = 0; i < aux.length-1; i++)
			{
				aux[i+1] = aux[i] + aux[i+1];
			}

			for (int i = rightIndex; i >= leftIndex; i--)
			{
				Integer elem = array[i];
				out[--aux[elem]] = elem;
			}

			for(int i = leftIndex; i <= rightIndex; i++)
			{
				array[i] = out[i];
			}
		}
	}
}
