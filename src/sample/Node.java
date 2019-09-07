package sample;
import java.util.*;

class Node {

    private char key;
    private boolean endofword;
    private Nodes children = new Nodes();


    public Node() { this.key = '/'; }
    public Node(char key) { this.key = key; }


    // Add a child node containing the given character
    Node addChildNode(char c) {
        Node node = getChildNode(c);
        if (node==null) {
            node = new Node(c);
            children.addNode(c, node);
        }
        return node;
    }

    // Add a word starting at this node by iterating through child nodes
    void addWord(String s) {
        Node n = this;
        int nchars = s.length();
        for (int i=0; i < nchars; i++) {
            char c = s.charAt(i);
            n = n.addChildNode(c);
            if (i==nchars-1) n.endofword = true;
        }
    }

    // Get the child node associated with the given character.
    // Map returns null if not found, so we'll follow that convention.
    Node getChildNode(char c) {
        return children.get(c);
    }

    // Find node by following characters in string.
    // If passed empty string, will return this node.
    Node findNode(String s) {
        Node n = this;
        int nchars = s.length();
        for (int i=0; i < nchars; i++) {
            char c = s.charAt(i);
            n = n.getChildNode(c);
            if (n==null) break;
        }
        return n;
    }

    // Get all words starting with the given prefix.
    // Find the node corresponding to the prefix, then gather up all the child words.
    List<String> getWords(String prefix) {
        List<String> words = new ArrayList<>();
        Node node = findNode(prefix);
        if (node!=null) {
            if (node.endofword) words.add(prefix);
            node.getWordsRecurse(prefix, words);
        }
        return words;
    }

    // Get all words including and below this node, prefixed with given string,
    // and add them to the given list.
    private void getWordsRecurse(String prefix, List<String> words) {
        for (Node n : children.values()) {
            String nodestring = prefix + n.key;
            if (n.endofword) words.add(nodestring);
            if (n.children.size()>0) n.getWordsRecurse(nodestring, words);
        }
    }


    public String toString() { return Character.toString(key); }

}

@SuppressWarnings("serial")
class Nodes extends HashMap<Character, Node> {
    Node addNode(Character k, Node v) { return put(k,v); }
}
