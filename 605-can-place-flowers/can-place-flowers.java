class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count=0;
        int l= flowerbed.length;
    int left,right;
        for(int i=0;i<l;i++){

            if(flowerbed[i]==0){   //current place

             if(i==0){    //left of current
                left=0;  //assuming left boundry as 0 when i=0
             }else{
                left = flowerbed[i-1];
             }
          
            if(i==l-1){   //right of current
                 right = 0;   //assuming right boundry as 0 when i=last index
            }else{
                right= flowerbed[i+1];    
            }

            if(left==0 && right ==0){
                flowerbed[i]=1;
                count++;
            }
        }
        }
    return count>=n;
    }
}