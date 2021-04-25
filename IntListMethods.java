
public class IntListMethods {
	public static int removeIfDivisible(int n , int k) {
		int start;
		int r;
		int temp;
		if(n != 0) {
			while((IntList.getKey(n) % k) == 0) {
				r = n;//get n to be released
				n = IntList.getNext(n);// set to be its next
				IntList.setNext(r, 0);
				IntList.release(r);
			}
			if(n == 0)//when entire list is divisable
				return 0;
			start = n;
			while(n != 0 && IntList.getNext(n) != 0) {// start (if next is not null if null then rturn
				if(IntList.getKey(IntList.getNext(n))%k == 0){// checking if the next node is divisable
					r = IntList.getNext(n);//saves node that is about to be released
					if(IntList.getNext(IntList.getNext(n)) != 0) {//double check (check if divisable node next is not null
						temp = IntList.getNext(IntList.getNext(n));
						IntList.setNext(n,temp);
					}else {//increment
							IntList.setNext(n,0);
					}//double check
					IntList.setNext(r, 0);
					IntList.release(r);
				}else {//incremement n if n exist(n exist in prior if statement
						n = IntList.getNext(n);
				}	
			}
			return start;
		}else {
			return 0;
		}
	}

	public static int sort(int n) {
		int size;
		int start;
		int length = IntList.getAllocatedNodeCount();
		int arr[] = new int[length];
		for(int i = 0; i < length; i++ ) {
			arr[i] = n;
			n = IntList.getNext(n);
		}
		for(size = 1; size <= length-1; size = 2*size) {
			for(start = 0; start < length-1; start += 2*size) {
				int mid = start +size -1;
				int end =start+ 2*size -1 ;
				if(end > length-1)
					end = length-1;
				if(mid > end) {
					int range = end - start;
					mid = (range%2==0)? (range/2)+start : ((range+1)/2)+start;
				}
				merge(arr,start, mid ,end);
			}
		}
		for(int i = 1; i < length; i++) {
			IntList.setNext(arr[i-1],arr[i]);
		}
		IntList.setNext(arr[length-1], 0);
		return arr[0];
	}
    public static String getAuthorName() {return "You, Allen";}
    public static String getRyersonID() {return "500833035";}
    public static void merge(int x[],int start,int mid,int end) {
    	int a =  mid-start+1;
    	int b = end - mid;
    	int left [] = new int[a];
    	int right[] = new int[b];
    	for(int i = 0; i < a; i++) {
    		left[i] = x[start+i];
    	}
    	for(int j = 0; j < b; j++) {
    		right[j] =x[mid+1+j];
    	}
    	int i = 0;
    	int j = 0;
    	int k = start;
    	while(i<a && j<b) {
    		if(IntList.getKey(left[i]) <= IntList.getKey(right[j]))
    		{
    			x[k] = left[i];
    			i++;
    		}else {
    			x[k] = right[j];
    			j++;
    		}
    		k++;
    	}
    	while(i<a) {
    		x[k]=left[i];
    		i++;
    		k++;
    	}
    	while(j<b) {
    		x[k]=right[j];
    		j++;
    		k++;
    	}
    }
}