def selfDivisor(num):
    t=str(num)
    if '0' in t:
        return 0
    for i in t:
        if num%int(i)!=0:
            return 0
    return 1
    
n=int(input())
ls=[]
for i in range(1,n+1):
    if selfDivisor(i):
        print(i,end=" ")
