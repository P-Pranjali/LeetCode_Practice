class Solution {
    public boolean increasingTriplet(int[] nums) {

        int num1 = Integer.MAX_VALUE;  //Consider as smallest no.

        int num2 = Integer.MAX_VALUE;  //Consider as second Smallest NO.

        for(int num : nums){

        if(num <= num1){   //if current no smaller than our smallest no. 

                num1= num;  //make this current no as our smallest no.
            }else if(num <= num2){ //else if surr

                num2= num;
            }else{
             return true;
            }
        }
       
        return false;
    }
}