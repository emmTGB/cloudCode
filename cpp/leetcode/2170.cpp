#include<bits/stdc++.h>
using namespace std;

    bool cmp_value(pair<int,int> a, pair<int,int> b){
        return a.second < b.second;
    }
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        map<int,int> sing, doub;

        for(int i = 0; i < nums.size(); i += 2){
            doub[nums[i]]++;
        }
        for (int i = 1; i < nums.size(); i += 2) {
            sing[nums[i]]++;
        }

        auto is = max_element(sing.begin(), sing.end(), cmp_value);
        auto id = max_element(doub.begin(), doub.end(), cmp_value);
        if(is->first == id->first){
            int ms = is->second, md = id->second;
            sing.erase(is->first), doub.erase(id->first);
            auto is = max_element(sing.begin(), sing.end(), cmp_value);
            auto id = max_element(doub.begin(), doub.end(), cmp_value);
            ms = ms + id->second;
            md = md + is->second;
            return nums.size() - max(ms, md);
        }else {
            return nums.size() - is->second - id->second;
        }
    }
};