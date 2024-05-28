def fopen():  
    '''本函数不允许修改，功能说明：
    fopen()读取文件并返回二维列表l,数据格式为：    
    [['Rank', 'Name', 'Pay', 'Salary/Winnings', 'Endorsements', 'Sport', 'Year'],
     ['1', 'Lionel Messi', '$127 M', '$92 M', '$35 M', 'Soccer', '2019']
     ['2', 'Cristiano Ronaldo', '$109 M', '$65 M', '$44 M', 'Soccer', '2019'], ...]
    '''
    l=[]
    with open('step7/2012-19sport.csv','r',encoding='UTF-8') as f:
        for i in f.readlines():
            l.append(i.strip().strip('#').split(','))
    return l

i = input()
fl = fopen()
l = []
for it in fl:
    if it[6] == i:
        if it[5] not in l:
            l.append(it[5])

l.sort()
ls = list(enumerate(l))
if len(ls) <= 0:
    print('No Record of '+ i)
else:
    for it in ls:
        print('' + str(it[0] + 1) + ' ' + it[1])