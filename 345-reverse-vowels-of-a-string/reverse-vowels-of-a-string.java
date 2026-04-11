class Solution {
    public String reverseVowels(String s) {

char arr[] = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

       while(left<right){

            while("aeiouAEIOU".indexOf(arr[left]) == -1 && left<right){
                left++;
            }

            while("aeiouAEIOU".indexOf(arr[right]) == -1 && left <right){
                right--;
            }

                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        

            return  new String(arr);
        
    }
}