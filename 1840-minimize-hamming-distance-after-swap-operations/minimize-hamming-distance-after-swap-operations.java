class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

        int n = source.length;

        //Graph
        List<Integer>[] graph = new ArrayList[n];

        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] e : allowedSwaps){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for(int i = 0; i < n; i++){

            if(!visited[i]){
                List<Integer> comp = new ArrayList<>();
                dfs(i, graph, visited, comp);

                List<Integer> sList = new ArrayList<>();
                List<Integer> tList = new ArrayList<>();

                //collect values from source and target array
                for(int idx : comp){
                    sList.add(source[idx]);
                    tList.add(target[idx]);
                }

                //sorting both lists
                Collections.sort(sList);
                Collections.sort(tList);

                Map<Integer, Integer> freq = new HashMap<>();

                for(int val : sList){
                    freq.put(val, freq.getOrDefault(val, 0)+ 1);
                }

                for(int val : tList){
                    if(freq.getOrDefault(val, 0)> 0){
                        freq.put(val, freq.get(val) - 1);
                    }else{
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] visited, List<Integer> comp){
        visited[node] = true;
        comp.add(node);

        for(int neighbourNode : graph[node]){
            if(!visited[neighbourNode]){
                dfs(neighbourNode, graph, visited, comp);
            }
        }
    }
}