class Solution {
    public int minimumDistance(int[] nums) {


        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int min= Integer.MAX_VALUE;
        
          for(int i=0;i<nums.length;i++){
  
               map.putIfAbsent(nums[i], new ArrayList<>());
                    List<Integer> list = map.get(nums[i]);
                    list.add(i);
     

                      if(list.size()>=3){

                            int n = list.size();
                            int p = list.get(n-3); //first index of list
                            int r = list.get(n-1);  //last index of list

                         min = Math.min(min, 2 * (r-p));

                      }
                } 
                 return min == Integer.MAX_VALUE ? -1:min;      
    }
}