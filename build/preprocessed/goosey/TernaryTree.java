package goosey;

/**
 * Ternary tree
 */
public class TernaryTree {

    private TreeNode root;
    
     /**
     * Tree node
     */
    private class TreeNode {
        private char data;
        
        private TreeNode left;
        private TreeNode middle;
        private TreeNode right;  
        
        private boolean value;
        
        private TreeNode(char data) {
            this.data = data;
        }
    }
    
    /**
     * Put to tree
     */
    private TreeNode put(TreeNode node, String key, int depth) {  
        if (key.length() == 0)
            return node;
        char data = key.charAt(depth); 
        if (node == null)
            node = new TreeNode(data);
        if (data < node.data)
            node.left = put(node.left, key, depth);
        else if (data > node.data)
            node.right = put(node.right, key, depth);
        else if (depth < key.length() - 1)
            node.middle = put(node.middle, key, depth + 1);
        else
            node.value = true;
        
        return node;
    }

    /**
     * Get from tree
     */
    private TreeNode get(TreeNode node, String key, int depth) {
        if (node == null || key.length() == 0)
            return null;
        
        char data = key.charAt(depth);
        if (data < node.data)
            return get(node.left, key, depth);
        else if (data > node.data)
            return get(node.right, key, depth);
        else if (depth < key.length() - 1)
            return get(node.middle, key, depth + 1);
        else
            return node;
    }
  
    /**
     * Contains
     */
    public boolean contains(String key) {
        TreeNode node = get(root, key, 0);
        return node != null && node.value;
    }
    
    /**
     * Prefix match
     */
    public boolean prefixMatch(String prefix) {
        return get(root, prefix, 0) != null;
    }
    
    /**
     * Put
     */
    public void put(String key) {
        root = put(root, key, 0);
    }
          
}
