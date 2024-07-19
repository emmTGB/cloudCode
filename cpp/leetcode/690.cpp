#include<bits/stdc++.h>
using namespace std;

// Definition for Employee.
class Employee {
public:
    int id;
    int importance;
    vector<int> subordinates;
};

class Solution {
public:
    int getImportance(vector<Employee*> employees, int id) {
        map<int, Employee*> mp;
        for(auto e : employees){
            mp[e->id] = e;
        }
        return dfs(id, mp);
    }

    int dfs(int id, map<int, Employee*>&employees) {
        id;
        int res = employees[id]->importance;
        for(int e : employees[id]->subordinates){
            res += dfs(e, employees);
        }
        return res;
    }
};