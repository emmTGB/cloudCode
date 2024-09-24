#include<bits/stdc++.h>
using namespace std;

bool cmp_size(string a, string b){
    return a.length() < b.length();
}

class Solution {
public:
    vector<string> stringMatching(vector<string>& words) {
        sort(words.begin(), words.end(), cmp_size);
        vector<string> ans;
        for(int i = 0; i < words.size(); i++){
            for(int j = i + 1; j < words.size(); ++j){
                if(words[j].find(words[i]) != string::npos){
                    ans.push_back(words[i]);
                    break;
                }
            }
        }
        return ans;
    }
};