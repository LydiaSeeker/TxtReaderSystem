package yabao.novel.system.txtsystem.business.sort;

public class HeapSort {

    static int[] array = new int[]{0, 2, 3, 51, 2, 6, 20, 7,91};

    //左程云写的
    public static void heapify(int index,int heapSize) {
        int left = index * 2 + 1;
        int right = index *2 + 2;
        //堆大小是8 ，左节点是7（从0开始），有节点是8，那么8<8 不成立
        while(left < heapSize) {
            int relativeBigger = index;
            if(right < heapSize) {
                relativeBigger = getMaxIndex(array, left, right);
            } else {
                relativeBigger = left;
            }
            //相等就没必要换了
            if(array[index] == array[relativeBigger]) break;

            if(array[index] < array[relativeBigger]) {
                int temp =  array[relativeBigger];
                array[relativeBigger] = array[index];
                array[index] = temp;
            }
            index = relativeBigger;
            left = relativeBigger * 2 + 1;


        }
    }

    public static int getMaxIndex(int[] array, int i, int j) {
        if(array[i]>=array[j]) {
            return i;
        } else {
            return j;
        }

    }

    //老子写的，我写的更好！
    public static int heapfy(int index,int arrayLength) {
        int n = arrayLength;
        for(int i=index; i<= n;) {
            int left = 2*i+1;
            int right = 2*i+2;

            if( left > n) {break;}

            int relativeLargerIndex = i;

            if(right > n ) {
                relativeLargerIndex = left;
            } else {
                relativeLargerIndex=  array[left] > array[right] ? left : right;
            }

            //一样大
            if(array[i] == array[relativeLargerIndex]) {
                break;
            }
            if(array[relativeLargerIndex]>array[i]) {
                int temp =  array[relativeLargerIndex];
                array[relativeLargerIndex] = array[i];
                array[i] = temp;
            }

            i = relativeLargerIndex;

        }
        return array[0];
    }

    //这玩意写的就有问题
    public static int heapSortMax() {
        int n = array.length - 1;  //数组长度
        for (int i = (n - 1) / 2; i >= 0; i--) {
            int relativeLargerIndex = i;
            if(2*i+2 > n) {
                relativeLargerIndex = 2*i+1;
            } else {
                relativeLargerIndex=  array[2*i+1] > array[2*i+2] ? 2*i+1 : 2*i+2;
            }

            if(array[relativeLargerIndex]>array[i]) {
               int temp =  array[relativeLargerIndex];
               array[relativeLargerIndex] = array[i];
               array[i] = temp;
            }
        }
        return array[0];
    }

    public static void swap(int[] array, int i , int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
//        int i = heapSortMax();
        int n = array.length - 1;
        for (int i = n ; i >= 0; i--) {
            heapfy(i,n);
        }
        System.out.println(array[0]);
        int exchangePos = array.length-1;
        for(int i = 0 ; i<n;i++) {
            swap(array, 0 , exchangePos -- );
            heapfy(0,exchangePos);
        }
        System.out.println(array.toString());

    }


}
