//Need to try again Solved only to keep Streak.

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long perimeter = 4L * side;

        long[] pos = new long[n];

        for(int i = 0; i< n; i++){

            int x = points[i][0];
            int y = points[i][1];

            if(y == 0){
                pos[i] = x;
            }else if(x == side){
                pos[i] = side + y;
            }else if(y == side){
                pos[i] = 3L * side - x;
            }else{
                pos[i] = 4L * side - y;
            }   
        }

        Arrays.sort(pos);

        long[] extended = new long[2 * n];
        for(int i = 0; i < n; i++){
            extended[i] = pos[i];
            extended[i + n] = pos[i] + perimeter;

        }

        long low = 0, high = 2L * side;
        long ans = 0;

        while(low <= high){
            long mid = (low + high)/2;

            if(canPlace(extended, n, k, mid, perimeter)){
                ans = mid;
                low = mid +1;
            }else{
                high = mid - 1;
            }
        }
        return (int) ans;
    }

    // private boolean canPlace(long[] arr, int n, int k, long dist, long perimeter){          int j = 0;
    //     for(int start = 0; start < n; start++){
    //         int count = 1;
    //         long last = arr[start];

    //         j = Math.max(j, start + 1);

    //         while(j < start + n && count < k){
    //             if(arr[j] - last >= dist){
    //                 count++; 
    //                 last = arr[j];
    //             }
    //             j++;
    //         }

    //         if(count < k){
    //         continue;

    //         }

    //         if(arr[start] + perimeter - last >= dist) return true;
    //     }
    //     return false;
    // }

//     private boolean canPlace(long[] arr, int n, int k, long dist, long perimeter){

//     for(int start = 0; start < n; start++){

//         int count = 1;
//         long last = arr[start];

//         for(int i = start + 1; i < start + n; i++){
//             if(arr[i] - last >= dist){
//                 count++;
//                 last = arr[i];
//                 if(count == k) break;
//             }
//         }

//         if(count < k) continue;

//         // circular check
//         if(arr[start] + perimeter - last >= dist){
//             return true;
//         }
//     }

//     return false;
// }

private boolean canPlace(long[] arr, int n, int k, long dist, long perimeter){

    for(int start = 0; start < n; start++){

        int count = 1;
        long last = arr[start];
        int idx = start;

        while(count < k){
            long target = last + dist;

            // binary search for next valid point
            int next = lowerBound(arr, target, idx + 1, start + n);

            if(next == -1) break;

            count++;
            last = arr[next];
            idx = next;
        }

        if(count < k) continue;

        if(arr[start] + perimeter - last >= dist){
            return true;
        }
    }

    return false;
}
private int lowerBound(long[] arr, long target, int left, int right){
    int ans = -1;

    while(left <= right){
        int mid = left + (right - left) / 2;

        if(arr[mid] >= target){
            ans = mid;
            right = mid - 1;
        }else{
            left = mid + 1;
        }
    }

    return ans;
}

}
