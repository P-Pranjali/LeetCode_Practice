class Solution {
    public int minimumDeletions(String s) {

        int bCount = 0;
        int deletation = 0;

        for(int i = 0; i < s.length(); i++){

            if(s.charAt(i) == 'b'){
                bCount++;
            }else{
                deletation = Math.min(bCount, deletation+1);
            }   
        }
        return deletation;
    }
}