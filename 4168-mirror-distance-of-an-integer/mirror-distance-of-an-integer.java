class Solution {
    public int mirrorDistance(int n) {

int temp = n;

        int result =0;

        while(temp > 0){
            int reminder = temp % 10;
            result = result * 10 + reminder;

            temp = temp/10;
        }

        if(n > result){
            return Math.abs(n - result);
        }

        return Math.abs(result - n);
        
    }
}