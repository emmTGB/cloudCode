#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string destCity(vector<vector<string>>& paths) {
        unordered_set<string> spots;
        for (auto& spot : paths) {
            spots.insert(spot[0]);
        }
        for(auto& spot : paths){
            if(!spots.count(spot[1])){
                return spot[1];
            }
        }
        return "";
    }
};