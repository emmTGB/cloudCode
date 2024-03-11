#include<iostream>
using namespace std;
#define ERROR '\0'

template<class T>
class Stack{
private:
    T* stack;
    int maxSize, top;
public:
    Stack(int maxSize): stack(new T[maxSize]), maxSize(maxSize), top(-1) {}
    ~Stack(){
        delete[] stack;
    }
    bool empty(){
        return top == -1;
    }
    int size(){
        return top + 1;
    }
    void push(T &n){
        if(top == maxSize) return;
        stack[++top] = n;
    }
    T pop(){
        if(top == -1) return ERROR;
        return stack[top--];
    }
    T peek(){
        if(top == -1) return ERROR;
        return stack[top];
    }
};

int main(){
    string eq;
    cin>>eq;
    Stack<char> op(1000);
    bool flag = false;
    for(int i = 0; i < eq.size(); i++){
        if(eq[i] >= '0' && eq[i] <= '9' || eq[i] == '.'){
            if(flag && i && !(eq[i - 1] >= '0' && eq[i - 1] <= '9' || eq[i - 1] == '.'))
                cout<<' ';
            cout<<eq[i];
            flag = true;
        }else if(eq[i] == '('){
            op.push(eq[i]);
        }else if(eq[i] == ')'){
            while(op.peek() != '(' && !op.empty()){
                cout<<' '<<op.pop();
            }
            op.pop();
        }else if(eq[i] == '*' || eq[i] == '/'){
            op.push(eq[i]);
        }else if(eq[i] == '+' || eq[i] == '-'){
            if(!i || eq[i - 1] == '('){
                if(eq[i] == '-'){
                    if(i){
                        cout<<' ';
                    }
                    cout<<eq[i];
                    flag = false;
                }
                continue;
            }
            if(op.peek() == '*' || op.peek() == '/'){
                while(op.peek() != '(' && !op.empty()){
                    cout<<' '<<op.pop();
                }
            }
            op.push(eq[i]);
        }else{
            continue;
        }
    }
    while(!op.empty()){
        cout<<' '<<op.pop();
    }
}