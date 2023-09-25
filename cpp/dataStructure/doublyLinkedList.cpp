#include<iostream>
using namespace std;
#define ERROR -1

template<class T>
struct Node{
    T data;
    Node<T>* prior,* next;
    Node(T& data):data(data), prior(NULL), next(NULL){}
};

template<class T>
class DoublyLinkedList{
private:
    Node<T>* head,* rear;
    int length;
public:
    DoublyLinkedList(){
        head = NULL;
        rear = NULL;
        length = 0;
    }

    bool isEmpty(){
        return length == 0;
    }

    Node<T>* getNode(int idx){
        if(idx < 0 || idx >= length){
            return NULL;
        }else{
            Node<T>* ret = head;
            for(int i = 0; i < idx; i++){
                ret = ret->next;
            }
            return ret;
        }
    }

    int locateNode(T data){
        if(isEmpty()){
            return -1;
        }else {
            int idx = -1;
            Node<T>* it = head;
            for(int i = 0; i < length; i++){
                if(it->data == data){
                    idx = i;
                    return idx;
                }
                it = it->next;
            }
            return -1;
        }
    }

    void doInsert(T& data){
        if(head == NULL){
            head = new Node<T>(data);
            rear = head;
            length++;
            return;
        }
        Node<T>* tmp = new Node<T>(data);
        tmp->prior = rear;
        rear->next = tmp;
        rear = tmp;
        length++;
    }
    void doInsert(T& data, int idx){
        if(idx < 0 || idx > length){
            return;
        }else if(idx == length){
            doInsert(data);
        }else{
            Node<T>* tmp = new Node<T>(data);
            if(idx == 0){
                tmp->next = head;
                head->prior = tmp;
                head = tmp;
            }else{
                Node<T>* pivot = getNode(idx);
                pivot->prior->next = tmp;
                tmp->prior = pivot->prior;
                tmp->next = pivot;
                pivot->prior = tmp;
            }
        }
        length++;
    }

    T& doDelete(int idx){
        if(idx < -1 || idx >=length){
            return (T&)ERROR;
        }
        Node<T>* pivot = getNode(idx);
        if(pivot->prior){
            pivot->prior->next = pivot->next;
        }else{
            head = pivot->next;
        }
        if(pivot->next){
            pivot->next->prior = pivot->prior;
        }else{
            rear = pivot->prior;
        }
        T ret = pivot->data;
        delete pivot;
        return ret;
    }
};

template<class T>
class RoundedDoublyList{
private:
    Node<T>* pivot;
    int length;
public:
    RoundedDoublyList(){
        pivot = NULL;
        length = 0;
    }

    Node<T>* setPivot(int idx){
        if(length == 0){
            return NULL;
        }
        while(idx >= length){
            idx -= length;
        }
        while(-idx >= length){
            idx += length;
        }
        Node<T>* ret = pivot;
        if(idx > 0){
            for(int i = 0; i < idx; i++){
                pivot = pivot->next;
            }
        }else{
            for(int i = 0; i > idx; i--){
                pivot = pivot->prior;
            }
        }
        return ret;
    }

    void doInsert(T& data){
        Node<T>* tmp = new Node<T>(data);
        if(length == 0){
            pivot = tmp;
            pivot->next = pivot;
            pivot->prior = pivot;
            length++;
            return;
        }
        tmp->next = pivot;
        tmp->prior = pivot->prior;
        pivot->prior->next = tmp;
        pivot->prior = tmp;
        length++;
        return;
    }
    void doInsert(T& data, int idx){
        if(length == 0){
            doInsert(data);
            return;
        }
        Node<T>* tmp = setPivot(idx);
        doInsert(data);
        pivot = tmp;
    }

    T& doDelete(int idx){
        if(length == 0){
            return (T&)ERROR;
        }
        Node<T>* tmp = setPivot(idx);
        pivot->prior->next = pivot->next;
        pivot->next->prior = pivot->prior;
        T ret = pivot->data;
        delete pivot;
        length--;
        if(length == 0){
            pivot = NULL;
        }else{
            pivot = tmp;
        }
        return data;
    }
};

int main(){
    DoublyLinkedList<int> list;
    for(int i = 0; i < 10; i++){
        list.doInsert(i);
    }
    int i = 233;
    list.doInsert(i, 5);
    cout<<"hello"<<list.locateNode(i)<<endl;
    RoundedDoublyList<int> rlist;
    for(int i = 10; i < 20; i++){
        rlist.doInsert(i);
    }
    rlist.doInsert(i, 6);
}