class Solution {
    public String reverseWords(String s) {

    //    String[] words = s.trim().split("\\s+");

    // StringBuilder sb = new StringBuilder();

    // for(int i= words.length-1; i>=0; i--){

    //     sb = sb.append(words[i]).append(" ");

    //  }   
    //   return sb.toString().trim();
    //     }


String[] words = s.trim().split("\\s+");

int left =0; int right = words.length-1;

while(left<right){

    String temp = words[left];
    words[left] = words[right];
    words[right] = temp;

    left++;
    right--;

}

    return String.join(" ", words);
    }
        
    }
