import string
s=input().lower()
s=s.replace("n't",' not')
s=s.replace("'s",' is')
for x in string.punctuation:
    if x in s:
        s=s.replace(x,' ')
 
ls=s.split()
 
dic={}
 
with open('step15/dicts.txt', 'r', encoding='utf-8') as f:
    for line in f:
        ls1=line.split()
        dic[ls1[0]]=' '.join(ls1[1:])
 
for i in ls:
    if i in dic:
        print(i,dic[i])
    else:
        print(i,'自己猜')