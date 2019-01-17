class trie {
    
    boolean isEnd;
    Map<Character, trie> m;
    
    trie() {
        m = new HashMap<Character, trie>();
    }
    
    trie createNode() {
        trie t = new trie();
        t.isEnd = false;
        return t;
    }
    
    void insert(trie root, String str) {
        
        trie curr = root;
        
        for(int i = 0; i < str.length(); i++) {
            
            if(!curr.m.containsKey(str.charAt(i)))
                curr.m.put(str.charAt(i), createNode());
                
            curr = curr.m.get(str.charAt(i));
        }
        
        curr.isEnd = true;  
    }
    
    boolean search(trie root, String s, int level) {
        
        if(level == s.length())
            return root.isEnd;
        if(root == null)
            return false;
        if(!root.m.containsKey(s.charAt(level)))
            return false;
        return search(root.m.get(s.charAt(level)), s, level + 1);
    }
}

public class Solution {
    
    class forSort {
        int index;
        int count;
        
        forSort(int i, int c) {
            this.index = i;
            this.count = c;
        }
    }
    
    ArrayList<forSort> al = new ArrayList<>();
    
    public ArrayList<Integer> solve(String A, ArrayList<String> B) {
        
        trie root = new trie();
        
        String arr[] = A.split("_");
        
        for(int i = 0; i < arr.length; i++) {
            
            root.insert(root, arr[i]);
        }
        
        for(int i = 0; i < B.size(); i++) {
            
            int count = 0;
            String a[] = B.get(i).split("_");
            
            for(int j = 0; j < a.length; j++) {
                
                if(root.search(root, a[j], 0) == true) {
                    
                    count++;
                }
            }
            al.add(new forSort(i, count));
        }
        
        Collections.sort(al, new Comparator<forSort>() {
            public int compare(forSort a, forSort b) {
                
                if(a.count == b.count)
                    return 0;
                if(a.count < b.count)
                    return 1;
                else
                    return -1;  
            }
        });
        
        ArrayList<Integer> l = new ArrayList<>();
        
        for(int i = 0; i < al.size(); i++) {
            
            l.add(al.get(i).index);
        }
        
        return l;
        
    }
}
