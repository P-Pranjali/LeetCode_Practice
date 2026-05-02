class Solution {
    public int rotatedDigits(int n) {

        int count = 0;

        for(int i = 1; i <= n; i++){
            if(isValid(i))
            count++;
        }
        return count;
    }

    boolean isValid(int num){

        boolean changed = false;
        while(num > 0){

          int digit =  num % 10;

            if(digit == 3 || digit == 4 || digit == 7)
                return false;

            if(digit == 2 || digit == 5 || digit == 6 || digit == 9)
                changed = true;

                num /= 10;

        }
        return changed;
    }
}