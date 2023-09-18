#include<iostream>
using namespace std;
#define ERROR -1

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

    int locateNode(T& data){
        if(length == 0){
            return -1;
        }
        int idx = -1;
        Node<T>* pivot = top;
        for(int i = 0; i < length; i++){
            if(pivot->data == data){
                idx = i;
                return idx;
            }
            pivot = pivot->next;
        }
        return idx;
    }
    Node<T>* getNode(int idx){
        if(idx < 0 || idx >= length){
            return NULL;
        }
        Node<T>* ret = top;
        for(idx; idx > 0; idx--){
            ret = ret->next;
        }
        return ret;
    }

    int push(T& data){
        if(length == maxSize){
            return -1;
        }
        Node<T>* tmp = new Node<T>(data);
    }

    T& pop(){
        if(length == 0){
            return (T)ERROR;
        }
        T ret = top->data;
        Node<T>* tmp = top;
        top = top->next;
        delete tmp;
        length--;
        return ret;
    }
};