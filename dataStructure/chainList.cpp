#include<iostream>
using namespace std;

template<class T>
class ChainNode{
private:
    T nodeData;
    ChainNode<T>* next;
public:

    ChainNode(T nodeData){
        this->nodeData = nodeData;
        next = NULL;
    }
    int setNext(ChainNode<T>* nextNode){
        next = nextNode;
        return 1;
    }
    ChainNode<T>* getNext(){
        return next;
    }
    T getData(){
        return nodeData;
    }
};

template<class T>
class ChainList{
private:
    ChainNode<T>* head,* rear;
    int length;
public:
    ChainList<T>(){
        head = NULL;
        rear = NULL;
        length = 0;
    }

    ChainNode<T>* getHead(){
        return head;
    }
    ChainNode<T>* getRear(){
        return rear;
    }
    int getLength(){
        return length;
    }

    int printAll(){
        ChainNode<T>* tmp = head;
        while(tmp != NULL){
            cout<<tmp->getData()<<"\t";
            tmp = tmp->getNext();
        }
        return 1;
    }

    virtual int doInsert(T &data){
        ChainNode<T>* node = new ChainNode<T>(data);
        if(head == NULL){
            head = node;
            rear = node;
        }else{
            rear->setNext(node);
            rear = node;
        }
        length++;
        return 1;
    }
    virtual int doDelete(){
    }

    ChainNode<T>* doReverse(ChainNode<T>* head){
        if(head == NULL){
            return NULL;
        }
        if(head->getNext() == NULL){
            this->head = head;
            return head;
        }
        doReverse(head->getNext())->setNext(head);
        head->setNext(NULL);
        return head;
    }
};


int main(){
    ChainList<int> list1;
    for(int i = 0; i < 10; i++){
        list1.doInsert(i);
    }
    cout<<(*list1.getHead()).getData()<<endl;
    cout<<(*list1.getRear()).getData()<<endl;
    list1.printAll();
    list1.doReverse(list1.getHead());
    list1.printAll();
}