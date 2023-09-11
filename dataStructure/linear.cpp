#include<iostream>
#include<unordered_map>
using namespace std;

template <class T>
class sqlList{
private:
    T* elem;
    int length;
    int maxSize;
public:
    sqlList(int maxSize){
        this->maxSize = maxSize;
        elem = new T[maxSize];
        length = 0;
    }

    bool isEmpty(){
        return length == 0;
    }
    T getElem(int i){
        return elem[i];
    }
    int locateElem(T &elem){
        int ret = -1;
        for(int i = 0; i < length; i++){
            if(this->elem[i] == elem){
                ret = i;
                break;
            }
        }
        return ret;
    }
    int getLength(){
        return length;
    }
    virtual int __insert(int i, T e){
        if(i >= 0 && i <= length && length <= maxSize){
            if(i == length){
                elem[i] = e;
            }else{
                for(int j = length; j > i; j--){
                    elem[j] = elem[j - 1];
                }
                elem[i] = e;
            }
            length++;
            return 1;
        }else{
            return 0;
        }
    }
    virtual int __delete(int i, T &e){
        if(i >= 0 && i < length){
            e = elem[i];
            length--;
            for(int j = i; j < length; j++){
                elem[j] = elem[j + 1];
            }
            return 1;
        }else{
            return 0;
        }
    }
    virtual int __insert(T e){
        if(length <= maxSize){
            elem[length] = e;
            length++;
            return 1;
        }
        return 0;
    }
    int __print(){
        if(length > 0){
            for(int i = 0; i < length; i++){
                cout<<elem[i]<<endl;
            }
            return 1;
        }
        return 0;
    }
    int Reverse();

    template<class T1>
    friend sqlList<T1> operator+(sqlList<T1> &, sqlList<T1> &);
};

template<class T>
sqlList<T> operator+(sqlList<T> &al, sqlList<T> &bl){
    unordered_map<T, int> map;
    if(al.getLength() == 0 || bl.getLength() == 0){
        return al.getLength() ? al : bl;
    }else{
        for(int i = 0; i < al.getLength(); i++){
            map.insert(pair<T, int>(al.getElem(i), 1));
        }
        for(int j = 0; j < bl.getLength(); j++){
            map.insert(pair<T, int>(bl.getElem(j), 1));
        }
        sqlList<T> cl(2 * (al.getLength() + bl.getLength()));
        for(auto it = map.begin(); it != map.end(); it++){
            cl.__insert(it->first);
        }
        return cl;
    }
}


int main(){
    sqlList<int> sql1(100), sql2(100);
    for(int i = 0;i < 10; i++){
        sql1.__insert(i);
        sql2.__insert(i * 2);
    }
    sqlList<int> sql3 = sql1 + sql2;
    sql3.__print();
}