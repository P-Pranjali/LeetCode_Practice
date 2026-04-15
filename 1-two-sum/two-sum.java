
class Solution {
    public int[] twoSum(int[] nums, int target) {
 
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length;i++){
            int diff = target - nums[i];

            if(map.containsKey(diff)){   // If diff present in map 
                return new int[] {map.get(diff), i};  //get its value i. e. index of that No.
            }

            map.put(nums[i], i); //If not then store tht no. {as key} & its index {as value} in map.
        }

        return new int[] { -1, -1};
    }
}