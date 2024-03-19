
# 以下内容不要修改
ls = input().split()  # 根据空格切分输入字符串为
if len(ls) == 2:
    ls.append("宝马")

print(f"这是一辆{ls[0]}年生产，型号是{ls[1]}的{ls[2]}牌汽车。")