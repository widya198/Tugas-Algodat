package no2sampai5;

public class ArrayLinearList implements LinearList {
    //data yang masuk
    protected Object [] element, element2; //array dari element
    protected int size; //nomer atau index dari element di array
    
    //Constructor
    //buat sebuah list kapasitas awal dengan initialCapacity
    //@throw ke IllegalArgumentException ketika initialCapacity kurang dari 1
    public ArrayLinearList(int initialCapacity){
        if(initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity harus >= 1");
        element = new Object[initialCapacity];
    }
    
    //buat list kapasitas awal 10
    public ArrayLinearList(){
        this(10);
    }
    
    //return true jika list kosong
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    
    //return jumlah ukuran element di dalam list
    @Override
    public int size(){
        return size;
    }
    
    //throw ke IndexOutOfBoundsException jika 
    //index tidak diantara 0 dan size - 1
    void checkIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index = " +index +"size = " +size);
    }
    
    //return element dengan index yg ditentukan
    //throw ke IndexOutOfBoundsException jika 
    //index tidak diantara 0 dan size - 1
    @Override
    public Object get(int index){
        checkIndex(index);
        return element[index];
    }
    
    //return index dari element
    //return -1 jika theElement tidak di list
    @Override
    public int indexOf(Object theElement){
        //mencari element[] untuk theElement
        for(int i=0; i<size; i++)
            if(element[i].equals(theElement))
                return i;
        
        //jika theElement tidak ada
        return -1;
    }
    
    //menghapus dan mengembalikkan element
    @Override
    public Object remove(int index){
        checkIndex(index);
        
        //index yg valid, geser element dengan index yg lebih tinggi
        Object removedElement = element[index];
        for(int i=index+1; i<size; i++)
            element[i-1] = element[i];
        
        element[--size] = null; //enable garbage collection
        return removedElement;
    }
    
    //menambah element pd index,
    //serta ditentukan letak element tersebut
    @Override
    public void add(int index, Object theElement){
        //index yg tidak valid
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("index = " +index +"size = " +size);
        
        //index yg valid, pastikan kita punya ruang cukup
        if(size == element.length)
            element = ChangeArrayLength.changeLength1D(element, 2*size);
        
        //geser element ke kanan satu posisi
        for(int i=size-1; i>=index; i--)
            element[i+1] = element[i];
        
        element[index] = theElement;
        size++;
    }
    
    //ubah ke String
    @Override
    public String toString(){
        StringBuffer s = new StringBuffer("[");
        
        //taruh element ke buffer
        for(int i=0; i<size; i++)
            if(element[i] == null)
                s.append("null, ");
            else
                s.append(element[i].toString() + ", ");
        if(size > 0)
            s.delete(s.length() - 2, s.length());
        s.append("]");
        
        //buat String yg setara / equivalent
        return new String(s);
    }
    
    @Override
    public void trimToSize(){
        element2 = new Object[size];
        if(size < element.length){
            System.arraycopy(element, 0, element2, 0, size);
            
            element = element2;
        }
    }
    
    @Override
    public Object setSize(int no){
        if(size>no){
            int index = size - no;
            Object removedElement = element[index];
            for(int i=0; i<index; i++){
                for(int j=index+1; j<size; j++)
                    element[j-1] = element[j];
                
            element[--size] = null;
            }
            return removedElement;
        }
        else{
            element2 = new Object[no];
            System.arraycopy(element, 0, element2, 0, size);
            
            element = element2;
        }
        return size;
    }
    
    @Override
    public Object clear(){
        for(int i=0; i<size; i++){
            element[i] = null;
        }
        return size;
    }
    
    //coding soal no 9
    @Override
    public Object removeRange(int index, int index2) {
        checkIndex(index);
        checkIndex(index2);
        if(index<index2){
            for(int i = index2; i>=index; i--){
                remove(i);
            }
        }
        else{
            System.out.println("index pertama harus lebih kecil dari index ke dua");
        }
        return element;
    }

    //coding soal no 12
    @Override
    public Object clone(Object[] a) {
        a = new Object[size];
        a = element.clone();
        return toString();
    }
}
