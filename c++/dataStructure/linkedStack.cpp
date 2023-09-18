#include<iostream>
using namespace std;

template<class T>
struct Node{
    T data;
    Node<T>* next;
    Node(T& data): data(data), next(NULL){}
};

template<class T>
class LinkedStack{
private:
    Node<T>* top;
    int length, maxSize;
public:
    LinkedStack(int maxSize){
        top = NULL;
        length = 0;
        this->maxSize = maxSize;
    }

    bool isEmpty(){
        return length == 0;
    }

    int push(T& data){
        if(length == maxSize){
            return -1;
        }
        Node<T>* tmp = new Node<T>(data);
    }
};