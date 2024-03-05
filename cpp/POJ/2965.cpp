//AC
#include<iostream>
using namespace std;
int cs[4][4];
int ans = 0x3f3f3f3f;

bool isIn(int x, int y){
    return x >= 0 && x < 4 && y >= 0 && y < 4;
}

bool isClear(){
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            if(cs[i][j] != 0){
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
    for(int i = 0; i < 4; i++){
        rev(x, i);
    }
    for(int i = 0; i < 4; i++){
        rev(i, y);
    }
}

bool dfs(int x, int y, int z){
    if(isClear()){
        if(ans == 0x3f3f3f3f){
            ans = z;
            cout<<ans<<endl;
            return true;
        }
        return false;
    }

    if(!isIn(x, y)) return false;

    int newx = x - (y == 0);
    int newy = (y - 1 + 4) % 4;

    bool tmp1 = dfs(newx, newy, z);
    op(x, y);
    bool tmp2 = dfs(newx, newy, z + 1);
    if(tmp2) cout<<x + 1<<" "<<y + 1<<endl;
    op(x, y);

    return tmp1 || tmp2;
}

int main(){
    for(int i = 0; i < 4; i++){
        string t;
        cin>>t;
        for(int j = 0; j < 4; j++){
            if(t[j] == '-'){
                cs[i][j] = 0;
            }else{
                cs[i][j] = 1;
            }
        }
    }

    dfs(3, 3, 0);
}