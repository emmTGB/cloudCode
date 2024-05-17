dict1 = {'赵小明': '13299887777', '特明朗': '814666888', '普希京': '522888666', '吴小京': '13999887777'}
name=input()
phone=input()
 
if name in dict1:
    print("您输入的姓名在通讯录中已存在")
else:
    dict1[name] = phone
    for key, value in dict1.items():
        print(key+':'+value)