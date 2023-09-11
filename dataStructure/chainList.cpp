#include<iostream>
using namespace std;

template<class T>
class chainList{
private:
    chainNode<T>* head;
    int length;
public:
    chainList(){
        head = NULL;
        length = 0;
    }

    virtual int doInsert(int i, T &node){
    }
    virtual int doInsert(int i, chainNode<T> &node){

    }
    virtual int doDelete(){
    }

};

template<class T>
class chainNode{
private:
    T nodeData;
    chainNode<T>* next;
public:
    chainNode(T nodeData){
        this->nodeData = nodeData;
    }
    int setNext(chainNode<T>& nextNode){
        if(nextNode){
            next = &nextNode;
            return 1;
        }
        return 0;
    }
};