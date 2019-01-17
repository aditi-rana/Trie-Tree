public class Solution {
    
   static final int MAX = 256;

    class TrieNode {
        TrieNode[] child = new TrieNode[MAX];
        int count = 1;

        TrieNode() {
            for (int i = 0; i < MAX; i++) {
                child[i] = null;
            }
        }
    }

    void insert(String value, TrieNode root) {

        if (value != null && value.trim().length() > 0) {
            TrieNode current = root;
            int length = value.length();

            for (int i = 0; i < length; i++) {
               
                int charVal = value.charAt(i);
                if (current.child[charVal] == null) {
                    current.child[charVal] = new TrieNode();
                } else {
                    current.child[charVal].count++;
                }
                current = current.child[charVal];
            }

        }
    }

    public ArrayList<String> prefix(ArrayList<String> A) {
        ArrayList<String> result = new ArrayList<>();

        TrieNode root = new TrieNode();
        root.count = 0;
        if (A != null) {
            for (String value : A) {
                insert(value, root);
            }

            for (String value : A) {
                int length = value.length();
                TrieNode current = root;
                StringBuilder temp = new StringBuilder("");
                for (int i = 0; i < length; i++) {
                    int charVal = value.charAt(i);
                    temp.append((char) charVal);
                    if (current.child[charVal].count == 1) {
                        result.add(temp.toString());
                        break;
                    }
                current = current.child[charVal];
                }
            }
        }



        return result;
    }
}
