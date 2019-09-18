package Sorting.Comparating;

import Sorting.Code;

import java.util.Arrays;

public class Merge extends Code
{
    public void sort(int[] array, int leftIndex, int rightIndex)
    {
        if(rightIndex-leftIndex != 2)
        {
            this.sort(array, leftIndex, rightIndex/2);
            this.sort(array, rightIndex/2+1, rightIndex);
        }
        merge(array, leftIndex, rightIndex);
    }

    public void merge(int[] array, int leftIndex, int rightIndex)
    {
        int[] aux = Arrays.copyOf(array, array.length);
        int i = leftIndex;
        int j = rightIndex/2+1;
        int k = leftIndex;

        while(i <= rightIndex/2 && j <= rightIndex)
        {
            if(aux[i] <= aux[j])
            {
                array[k] = aux[i];
                i++;
                k++;
            }
            else
            {
                array[k] = aux[j];
                j++;
                k++;
            }
        }

        while(i <= rightIndex/2)
        {
            array[k] = aux[i];
            i++;
            k++;
        }

        while(j < rightIndex)
        {
            array[k] = aux[j];
            j++;
            k++;
        }
    }
}
