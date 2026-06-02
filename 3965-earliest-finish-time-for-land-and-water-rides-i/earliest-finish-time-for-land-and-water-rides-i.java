class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {

        int a = landStartTime.length;
        int b = waterStartTime.length;

       int timeTaken = Integer.MAX_VALUE;

        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){

                int landFinish = landStartTime[i] + landDuration[i];
                int firstFinish = Math.max(landFinish, waterStartTime[j]) + waterDuration[j];

                int waterFinish = waterStartTime[j] + waterDuration[j];
                int secondFinish = Math.max(waterFinish, landStartTime[i]) + landDuration[i];

                timeTaken = Math.min(timeTaken, Math.min(firstFinish, secondFinish));
            }
        }

        
        
        return timeTaken;
    }
}