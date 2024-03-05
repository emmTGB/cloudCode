//AC
#include<iostream>
#include<iomanip>
using namespace std;
const int inf = 0x3f3f3f3f;
int bp[110][2100];

int main(){
    int t;
    cin>>t;
    for(t; t > 0; t--){
        int n;
        cin>>n;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2100; j++){
                bp[i][j] = inf;
            }
        }
        for(int i = 0; i < n; i++){
            int mi;
            cin>>mi;
            for(int j = 0; j < mi; j++){
                int B, P;
                cin>>B>>P;
                if(i == 0){
                    bp[0][B] = min(bp[0][B], P);
                }else{
                    for(int h = 0; h < 2100; h++){
                        if(bp[i - 1][h] != inf){
                            if(h <= B){
                                bp[i][h] = min(bp[i - 1][h] + P, bp[i][h]);
                            }else{
                                bp[i][B] = min(bp[i - 1][h] + P, bp[i][B]);
                            }
                        }
                    }
                }
            }
        }
        double ret = 0;
        for(int i = 0; i < 2100; i++){
            if(bp[n - 1][i] != inf){
                double tmp = (double)i / bp[n - 1][i];
                if(ret < tmp){
                    ret = tmp;
                }
            }
        }
        cout<<fixed<<setprecision(3)<<ret<<endl;
    }
    return 0;
}