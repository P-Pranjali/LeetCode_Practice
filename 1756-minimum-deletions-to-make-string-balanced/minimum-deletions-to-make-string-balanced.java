class Solution {
    public int minimumDeletions(String s) {

        int deletation =0;
        int bCount =0;

        char a = 'a';
        char b= 'b';

        for(char ch: s.toCharArray()){

            if(ch==b){
                bCount++;
            }else{
            deletation = Math.min(deletation+1, bCount);
        }
        }
        return deletation;
    }
}