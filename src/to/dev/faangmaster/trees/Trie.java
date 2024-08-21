package to.dev.faangmaster.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie
 *
 * @link <a href="https://dev.to/faangmaster/riealizatsiia-priefiksnogho-dierievatrie-na-java-5618">Реализация префиксного дерева(Trie) на Java</a>
 */
public final class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
        }
        current.terminates = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return current.terminates;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return false;
            }
            current = current.children.get(c);
        }
        return true;
    }

    final class TrieNode {
        final Map<Character, TrieNode> children;
        boolean terminates;

        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Hello");
        System.out.println(trie.search("Hello"));
    }
}
