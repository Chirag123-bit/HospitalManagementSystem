package backend;

import java.util.ArrayList;
import java.util.Vector;

public class SearchingSorting {
	static ArrayList<Vector<String>> res;
	static int pos = 0;
	
	public ArrayList<Vector<String>> search(ArrayList<Vector<String>> data, String query, String field) {
		res = new ArrayList<Vector<String>>();
		query = query.toLowerCase();
		if(field.equals("First Name")){
			pos=0;
		}
		else if(field.equals("Last Name")){
			pos=1;
		}
		else if(field.equals("Doctor")){
			pos=5;
		}
		else if(field.equals("Nurse")){
			pos=6;
		}
		else if(field.equals("Ward")){
			pos=7;
		}
	
		
		for(Vector<String> i:data) {
			if(i.get(pos).toLowerCase().equals(query)) {
				res.add(i);
			}
		}
		return res;
		
	}
	public ArrayList<Vector<String>> sort(ArrayList<Vector<String>> data, String field){
		res = new ArrayList<Vector<String>>();
		if(field.equals("Id")){
			pos=8;
		}
		else if(field.equals("First Name")){
			pos=0;
		}
		else if(field.equals("Last Name")){
			pos=1;
		}
		quickSort(data,0,data.size()-1,pos);
		return data;
		
		
	}
	
	static void swap(ArrayList<Vector<String>> arr, int i, int j, int pos)
	{
		Vector<String> temp = arr.get(i);
	    arr.set(i, arr.get(j));
	    arr.set(j, temp);
	}
	
	static int partition(ArrayList<Vector<String>> arr, int low, int high, int pos)
	{
	      
	    // pivot
	    String pivot = arr.get(high).elementAt(pos).toLowerCase(); 
	      
	    // Index of smaller element and
	    // indicates the right position
	    // of pivot found so far
	    int i = (low - 1); 
	  
	    for(int j = low; j <= high - 1; j++)
	    {
	          
	        // If current element is smaller 
	        // than the pivot
	        if (arr.get(j).elementAt(pos).toLowerCase().compareTo(pivot)<0) 
	        {
	              
	            // Increment index of 
	            // smaller element
	            i++; 
	            swap(arr, i, j, pos);
	        }
	    }
	    swap(arr, i + 1, high, pos);
	    return (i + 1);
	}
	  
	/* The main function that implements QuickSort
	          arr[] --> Array to be sorted,
	          low --> Starting index,
	          high --> Ending index
	 */
	static void quickSort(ArrayList<Vector<String>> arr, int low, int high, int pos)
	{
	    if (low < high) 
	    {
	          
	        // pi is partitioning index, arr[p]
	        // is now at right place 
	        int pi = partition(arr, low, high, pos);
	  
	        // Separately sort elements before
	        // partition and after partition
	        quickSort(arr, low, pi - 1, pos);
	        quickSort(arr, pi + 1, high, pos);
	    }
	}
	
	public void reverseSwap(ArrayList<Vector<String>> arr, int i, int j)
	{
		Vector<String> temp = arr.get(i);
	    arr.set(i, arr.get(j));
	    arr.set(j, temp);
	}
	
	public ArrayList<Vector<String>> reverse(ArrayList<Vector<String>> arr){
		int low = 0;
		int high = arr.size()-1;
		while(low<high) {
			reverseSwap(arr, low, high);
			low++;
			high--;
		}
		return arr;
	}


}
