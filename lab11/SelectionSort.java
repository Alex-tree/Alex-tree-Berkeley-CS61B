import edu.princeton.cs.algs4.Queue;

public class SelectionSort {
    public static <Item extends Comparable> Queue<Item> SelectionSort(Queue<Item> items){
        Queue<Item> result=new Queue<>();
        int m =items.size();
        Object[] t = new Object[m];
        int index=0;
        while (!items.isEmpty()){
            t[index]= items.dequeue();
            index++;
        }

        for(int i = 0; i <t.length;i++){
            Object smallest=t[i];
            for(int j = i+1;j<t.length;j++){
                Object temp=t[j];
                if(((Item)smallest).compareTo((Item)temp)>0){
                    t[j]=smallest;
                    t[i]=temp;
                    smallest=temp;
                }

            }
        }
        for(int q=0;q< t.length;q++)
            result.enqueue((Item)t[q]);
        return result;
    }
}
