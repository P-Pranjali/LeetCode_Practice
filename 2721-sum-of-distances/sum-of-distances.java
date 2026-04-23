class Solution {
    public long[] distance(int[] nums) {
  int n = nums.length;

  long[] result = new long[n];

  HashMap<Integer, Long> count = new HashMap<>();
    HashMap<Integer, Long> sum = new HashMap<>();

    for(int i = 0; i< n; i++){
        int val = nums[i];

        long c = count.getOrDefault(val, 0L);
        long s = sum.getOrDefault(val, 0L);

        result[i] += i * c -s;

        count.put(val, c + 1);
        sum.put(val, s + i);
    }

    count.clear();
    sum.clear();

    for(int i = n-1; i >= 0; i--){

        int val = nums[i];

        long c = count.getOrDefault(val, 0L);
        long s = sum.getOrDefault(val, 0L);

        result[i] += s - i * c;

        count.put(val, c + 1);
        sum.put(val, s + i); 
    }

return result;
}
}