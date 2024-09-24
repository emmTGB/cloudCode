#include<bits/stdc++.h>
using namespace std;

class SubrectangleQueries {
    vector<vector<int>> history;
    vector<vector<int>> rectangle;
public:
    SubrectangleQueries(vector<vector<int>>& rectangle) : rectangle(rectangle) {}

    void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        history.push_back({row1, col1, row2, col2, newValue});
    }

    int getValue(int row, int col) {
        for(int i = history.size() - 1; i >= 0; i--){
            if(row >= history[i][0] && row <= history[i][2] && col >= history[i][1] && col <= history[i][3]){
                return history[i][4];
            }
        }
        return rectangle[row][col];
    }
};

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries* obj = new SubrectangleQueries(rectangle);
 * obj->updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj->getValue(row,col);
 */