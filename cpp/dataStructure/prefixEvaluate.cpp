#include<iostream>
#include<iomanip>
using namespace std;

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
    void push(const T &n){
        if(top == maxSize) return;
        stack[++top] = n;
    }
    T pop(){
        if(top == -1) throw out_of_range("haha");
        return stack[top--];
    }
    T peek(){
        if(top == -1) throw out_of_range("haha");
        return stack[top];
    }
};

int main(){
    string s;
    Stack<double> num(1000);
    Stack<string> exp(1000);
    while(cin>>s){
        exp.push(s);
    }
    while(!exp.empty()){
        string tmp = exp.pop();
        if(tmp == "+"){
            num.push(num.pop() + num.pop());
        }else if(tmp == "-"){
            num.push(num.pop() - num.pop());
        }else if(tmp == "*"){
            num.push(num.pop() * num.pop());
        }else if(tmp == "/"){
            double tmp = num.pop();
            if(!num.peek()){
                cout<<"ERROR";
                return 0;
            }
            num.push(tmp / num.pop());
        }else{
            num.push(stod(tmp));
        }
    }
    cout<<fixed<<setprecision(1)<<num.pop();
}