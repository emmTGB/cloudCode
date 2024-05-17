n=int(input())
d={}
ls= []
a = {}
for i in range (n):
    a[i]=input().split()    #这里运用到动态变量，可以用一个集合a来存储动态变量
    d["name"] = a[i][0]
    d["age"] = a[i][1]
    ls.append(d.copy())   #看下面的说明，必须先开辟空间
def cmp1(a):
    return a['age']

def cmp2(a):
    return a['name']

ls.sort(key = cmp1)
print(ls)

ls.sort(key = cmp2)
print(ls)