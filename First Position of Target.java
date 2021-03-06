/*
    第一个模板 (有一定局限性，根据情况使用，find last position)
    第二个模板 (更加简洁，泛用性高，优先使用)
	Keypoints:
		1. start + 1 < end;  当两个指针相邻或者相交时退出while循环
		2. start = start + (end - start) / 2;
		3. nums[mid] ==, <, > 三种情况如何处理
		4. nums[start], nums[end] ? target.
*/

/*
For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.
If the target number does not exist in the array, return -1.

Example
If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.

Challenge 
If the count of numbers is bigger than 2^32, can your code work properly?

Tags 
Binary Search Array
*/

public class Solution {
    public int bianrySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            //    为什么不写成 mid = (start + end) / 2;
            //    防止溢出，当start与end的值都是接近Integer.MAX_VALUE时，将会发生溢出现象
            if (nums[mid] == target) {
                end = mid;
                //  if you want to find the last position, change it to :
                //	start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = target;
            }
        }
        
        //  find the first position when target occurs
        //  if you want to find the end position
        //  change the order of the two if :
        /*
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        } 
        */
        if (nums[start] == target) {
            return start;
        } 
        if (nums[end] == target) {
            return end;
        }

        return -1;
    }
}

// 二分法第二套模板  Solution 2 更为简洁
// 仍然是 Find the First Position / First Bigger Number  (注释里提供了 Find Last Position 的方法)
// 该模板无需对 nums[start] 和 nums[end] 再进行一次判断，看谁才是答案。
class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;  
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {   // 重点依然是处理好 equals 时, mid 的处理情况。参考模板1中的分析
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        //  if you want to find the last position, change it to :
        //  while (start < end) {
        //      int mid = start + (end - start) / 2;
        //      if (nums[mid] <= target) {
        //          start = mid + 1;
        //      } else {
        //          end = mid;
        //      }
        //  }
        //  return end - 1; （此时初始化时 end = nums.length）
        
        if (nums[start] == target) {
            return start;
        }  
        return -1;
    }
}
