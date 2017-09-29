
package no2sampai5;

import java.lang.reflect.Array;

public class ChangeArrayLength {
    public static Object[] changeLength1D(Object [] a, int n, int newLength){
        //a[0:n-1] akan di copy ke array yg baru
        //pastikan panjang yg baru itu memadai
        if(n>newLength){
            throw new IllegalArgumentException("new length is too small");
        }
        //alokasikan array baru dari panjang yg diinginkan dan tipe yg sama
        Object [] newArray = (Object []) Array.newInstance(a.getClass().getComponentType(), newLength);
        
        //copy dari ruang lama ke yang baru
        System.arraycopy(a, 0, newArray, 0, n);
        
        return newArray;
    }
    
    //semua array akan di copy ke array yg baru
    public static Object [] changeLength1D(Object[] a, int newLength){
        return changeLength1D(a, a.length, newLength);
    }
}
