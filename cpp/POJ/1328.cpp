//AC
#include<iostream>
#include<math.h>
#include<algorithm>
using namespace std;

struct Node{
    double left, right;
};

bool cmp(Node& n1, Node& n2){
    return n1.right < n2.right;
}

Node s[1001];

int main(){
    int n, sum, d, x, y;
    int num = 1;
    while(true){
        sum = 1;
        cin>>n>>d;
        if(n == 0 && d == 0){
            break;
        }
        for(int i = 0; i < n; i++){
            cin>>x>>y;
            if(y > d){
                sum = -1;
            }
            double tmp = sqrt(pow((float)d, 2) - pow((float)y, 2));
            s[i].left = x - tmp;
            s[i].right = x + tmp;
        }
        if(sum != -1){
            sort(s, s + n, cmp);
            double tmp = s[0].right;
            for(int i = 1; i < n; i++){
                if(tmp < s[i].left){
                    sum++;
                    tmp = s[i].right;
                }
            }
        }
        cout<<"Case "<<num++<<": "<<sum<<endl;
    }
}