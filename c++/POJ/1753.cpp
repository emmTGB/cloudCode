//AC
#include<iostream>
#include<string>
using namespace std;
int cs[4][4];
int ans = 0x3f3f3f3f;

bool isIn(int x, int y){
    return x >= 0 && x < 4 && y >= 0 && y < 4;
}

bool isClear(){
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            if(cs[i][j] != cs[0][0]){
                return false;
            }
        }
    }
    return true;
}

void rev(int x, int y){
    if(cs[x][y]){
        cs[x][y] = 0;
    }else{
        cs[x][y] = 1;
    }
}

void op(int x, int y){
    rev(x, y);
    if(isIn(x - 1, y)) rev(x - 1, y);
    if(isIn(x, y - 1)) rev(x, y - 1);
    if(isIn(x + 1, y)) rev(x + 1, y);
    if(isIn(x, y + 1)) rev(x, y + 1);
}

void dfs(int x, int y, int z){
    if(isClear()){
        ans = min(ans, z);
        return;
    }

    if(!isIn(x, y)) return;

    int newx = x + (y + 1) / 4;
    int newy = (y + 1) % 4;

    dfs(newx, newy, z);
    op(x, y);
    dfs(newx, newy, z + 1);
    op(x, y);
}

int main(){
    for(int i = 0; i < 4; i++){
        string t;
        cin>>t;
        for(int j = 0; j < 4; j++){
            if(t[j] == 'w'){
                cs[i][j] = 0;
            }else{
                cs[i][j] = 1;
            }
        }
    }

    dfs(0, 0, 0);
    if(ans == 0x3f3f3f3f){
        cout<<"Impossible"<<endl;
    }else{
        cout<<ans<<endl;
    }
}