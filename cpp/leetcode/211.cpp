#include <bits/stdc++.h>
using namespace std;

class WordDictionary {
private:
    struct node {
        node* child[26] = { nullptr };
        bool isEnd = false;
    };
    node* dict;
public:
    WordDictionary() {
        dict = new node();
        dict->isEnd = false;
    }

    void addWord(string word) {
        node* ptr = dict;
        for (char c : word) {
            int i = c - 'a';
            if (!ptr->child[i]) {
                ptr->child[i] = new node;
            }
            ptr = ptr->child[i];
        }
        ptr->isEnd = true;
    }

    bool search(string word) {
        return dfs(word, 0, dict);
    }

    bool dfs(string& word, int d, node* n) {
        if (d == word.length()) {
            return n->isEnd;
        }
        if (word[d] == '.') {
            for (node* c : n->child) {
                if (c && dfs(word, d + 1, c)) {
                    return true;
                }
            }
        }
        else {
            node* c = n->child[word[d] - 'a'];
            if (c && dfs(word, d + 1, c)) return true;
        }
        return false;
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */