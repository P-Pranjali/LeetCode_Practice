class Solution {
    public int compress(char[] chars) {

        int i= 0;

        int index = 0;

        while(i< chars.length){


            int count =0;
            char currentChar = chars[i];

            while(i< chars.length && currentChar==chars[i]){

                i++;
                count++;
            }

        //to write new String with char and its count
            chars[index] = currentChar;    //index is index of new String first it's value is 0;
            index++;                     //keep incrementing after writing char to write its count

            if(count>1){   //If count is one no need to write 1

                String countStr = String.valueOf(count);  //But if it's > 1 write it

                for(char c : countStr.toCharArray()){    //If count is two digit convert wvwry digit in single char

                    chars[index] = c;
                    index++;
                }
            }

        }
        return index;
        
    }
}