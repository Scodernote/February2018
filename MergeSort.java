import java.util.Arrays;
import java.util.Random;

public class MergeSort {
	
	
	public static void mergeSort(int [] arr , int st , int end){
		if(st < end){
			int mid = (st+end)/2;
			mergeSort(arr,st,mid);
			mergeSort(arr,mid+1,end);
			mergeFunc(arr,st,mid,end);
		}
	}
	
	public static void mergeFunc(int [] arr , int st , int mid , int end){
		
		int lsize = mid - st + 1;
		int rsize = end - mid;
		int [] larr = new int[lsize];
		int [] rarr = new int[rsize];
		
		for(int i = 0 ; i < lsize ;i++){
			larr[i] = arr[st+i];
		}
		
		//System.out.println(Arrays.toString(larr));
		for(int  i = 0 ; i < rsize ;i++){
			rarr[i] = arr[mid+1+i];
		}
		
		//System.out.println(Arrays.toString(rarr));
		
		int  l = 0 , r = 0 ,k =st ;
		
		while( l < lsize && r < rsize){
			if(larr[l] <= rarr[r]){
				arr[k++] = larr[l];
				l++;
			} else {
				arr[k++] = rarr[r];
				r++;
			}
		}
		
		while( l < lsize){
			arr[k++] = larr[l];
			l++;
		}
		
		while(r < rsize){
			arr[k++] = rarr[r];
			r++;
		}
	}
	
	
	public static void insertionSort(int [] arr) {
		
		int  j , key;
		
		for(int  k = 0  ; k < arr.length ;k++){
			
			j = k-1; key = arr[k];
			while(j>=0 && arr[j] > key){
				arr[j+1] = arr[j];
				j--;
			}
			
			arr[j+1] = key;
		}
		
	}
	
	public static int binarySearch(int [] arr , int key){
		int lo = 0 , hi = arr.length-1;
		
		while(lo <= hi) {
			int mid = (lo + hi)/2;
			if(arr[mid] == key){
				return mid;
			} else if (key < arr[mid]){
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		return -1;
	}
	
	public static void main(String [] args){
		Random rand = new Random(60);
		int [] arr = new int[50];
		
		for(int  i = 0 ; i < 50 ;i++){
			arr[i] = rand.nextInt(100);
		}
		
		System.out.println(Arrays.toString(arr));
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));
		
		int tp = arr[rand.nextInt(49)];
		
		System.out.println(tp);
		System.out.println(binarySearch(arr,1));
	}
	
}