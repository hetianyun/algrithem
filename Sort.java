package code;

import java.util.Arrays;
import java.util.LinkedList;

public class Sort {

	public static void main(String[] args) {
	}

	/**
	 * BubboSort
	 */
	public static void bubboSort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 1; j < nums.length - i; j++) {
				if (nums[j - 1] > nums[j]) {
					int tmp = nums[j - 1];
					nums[j - 1] = nums[j];
					nums[j] = tmp;
				}
			}
		}
	}

	/**
	 * SelectSort
	 */
	public static void selectSort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int minIdx = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[minIdx]) {
					minIdx = j;
				}
			}
			if (minIdx != i) {
				int tmp = nums[minIdx];
				nums[minIdx] = nums[i];
				nums[i] = tmp;
			}
		}
	}

	/**
	 * insertSort
	 */
	public static void insertSort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int tmp = nums[i];
			int idx = i - 1;
			while (idx >= 0 && nums[idx] > tmp) {
				nums[idx + 1] = nums[idx];
				idx--;
			}
			nums[idx + 1] = tmp;
		}
	}

	/**
	 * shellSort
	 */
	public static void shellSort(int[] nums) {
		for (int gap = nums.length / 2; gap > 0; gap /= 2) {
			for (int i = 0; i < gap; i++) {
				for (int j = i + gap; j < nums.length; j += gap) {
					int temp = nums[j];
					int idx = j - gap;
					while (idx >= 0 && nums[idx] > temp) {
						nums[idx + gap] = nums[idx];
						idx -= gap;
					}
					nums[idx + gap] = temp;
				}
			}
		}
	}

	/**
	 * mergeSort
	 */
	public static void mergeSort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}

	private static void sort(int[] nums, int low, int high) {
		if (low >= high)
			return;
		int mid = low + (high - low) / 2;
		sort(nums, low, mid);
		sort(nums, mid + 1, high);
		merge(nums, low, mid, high);
	}

	public static void merge(int[] nums, int low, int mid, int high) {
		int[] proxy = new int[nums.length];
		for (int i = low; i <= high; i++) {
			proxy[i] = nums[i];
		}
		int l = low;
		int r = mid + 1;
		int idx = low;
		while (l <= mid && r <= high) {
			if (nums[l] < nums[r]) {
				nums[idx++] = proxy[l++];
			} else {
				nums[idx++] = proxy[r++];
			}
		}
		while (l <= mid) {
			nums[idx++] = proxy[l++];
		}
		while (r <= high) {
			nums[idx++] = proxy[r++];
		}
	}

	/**
	 * quickSort
	 */
	public static void quickSort(int[] nums) {
		quickSort1(nums, 0, nums.length - 1);
	}
	private static void quickSort1(int[] nums, int l, int r) {
		if (l >= r)
			return;
		int idx = l;
		for (int i = l + 1; i <= r; i++) {
			if (nums[i] < nums[l]) {
				idx++;
				int temp = nums[idx];
				nums[idx] = nums[i];
				nums[i] = temp;
			}
		}
		int temp = nums[l];
		nums[l] = nums[idx];
		nums[idx] = temp;
		quickSort1(nums, l, idx - 1);
		quickSort1(nums, idx + 1, r);
	}

	/**
	 * heapSort
	 */
	public static void maxHeapSort(int[] nums) {
		maxHeapify(nums, nums.length);
		for (int i = nums.length - 1; i >= 0; i--) {
			int temp = nums[0];
			nums[0] = nums[i];
			nums[i] = temp;
			maxHeapify(nums, i);
		}
	}
	private static void maxHeapify(int[] nums, int size) {
		for (int i = size / 2; i >= 0; i--) {
			int half = size >> 1;
			int k = i;
			while (k < half) {
				int index = k;
				int l = k * 2 + 1;
				if (l < size && nums[l] < nums[index]) {
					index = l;
				}
				int r = l + 1;
				if (r < size && nums[r] < nums[index]) {
					index = r;
				}
				if (index == k)
					break;
				int temp = nums[index];
				nums[index] = nums[k];
				nums[k] = temp;
				k = index;
			}
		}
	}
	/**
	 * BucketSort
	 */
	 public static void bucketSort(int[] nums) {  
	        int n = nums.length;     
	        /** 
	         * 创建链表（桶）集合并初始化，集合中的链表用于存放相应的元素 
	         */  
	        int bucketNum = 10; // 桶数  
	        LinkedList<LinkedList<Integer>> buckets = new LinkedList<LinkedList<Integer>>();  
	        for(int i = 0; i < bucketNum; i++){  
	            LinkedList<Integer> bucket = new LinkedList<Integer>();  
	            buckets.add(bucket);  
	        }  
	        // 把元素放进相应的桶中  
	        for(int i = 0; i < n; i++){  
	            int index =  nums[i] * bucketNum;  
	            buckets.get(index).add(nums[i]);  
	        }  
	        // 对每个桶中的元素排序，并放进a中  
	        int index = 0;  
	        for (LinkedList<Integer> linkedList : buckets) {  
	            int size = linkedList.size();  
	            if (size == 0) {  
	                continue;  
	            }  
	            /** 
	             * 把LinkedList<Double>转化为Double[]的原因是，之前已经实现了 
	             * 对数组进行排序的算法 
	             */  
	            int[] temp = new int[size];  
	            for (int i = 0; i < temp.length; i++) {  
	                temp[i] = linkedList.get(i);  
	            }  
	            // 利用插入排序对temp排序  
	           Arrays.sort(temp);  
	            for (int i = 0; i < temp.length; i++) {  
	                nums[index] = temp[i];  
	                index++;  
	            }  
	        }  
	    }  
}
