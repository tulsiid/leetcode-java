class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> st=new HashSet<>();
        for(List<String> path:paths)
        {
            st.add(path.get(0));
        }
        for(List<String> path:paths)
        {
            String des=path.get(1);
        
            if(!st.contains(des))
            {
                return des;
            }
        }
        return "";
    }
}