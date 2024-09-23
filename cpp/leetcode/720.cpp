#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string longestWord(vector<string>& words) {
        sort(words.begin(), words.end(), [](string& a, string& b) {
            return a.length() != b.length() ? a.length() < b.length() : a > b;
            });
        string longest;
        unordered_set<string> dict = {""};
        for (auto& word : words) {
            if(dict.count(word.substr(0, word.length() - 1))){
                dict.emplace(word);
                longest = word;
            }
        }
        return longest;
    }
};