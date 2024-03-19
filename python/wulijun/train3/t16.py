"""
1.让用户输入半年度加薪的整数百分比，例如输入7表示每半年加薪7%。
2.第6个月后，按该百分比增加薪资。在第12个月、18个月之后，依此类推（只有在第6、12、18……等月份时才加薪）。
写一个程序计算需要多少个月才能攒够钱付首付款。与之前一样，假设所需的首付款百分比为0.30（30%）。
你的程序要给出以下提示并要求用户输入相应的数值：
1. 请输入总房价：total_cost
2. 请输入年薪：annual_salary
3. 请输入月存款比例：portion_saved
4. 每半年加薪比例：semi_annual_raise
测试用例
请输入总房价：1000000
请输入年薪：156800
请输入月存款比例：60
请输入加薪比例：7
输出：需要33 个月可以存够首付
"""
total_cost = float(input()) * 0.3     # total_cost为当前房价
annual_salary = float(input()) / 12       # 年薪
portion_saved = float(input()) / 100  # 月存款比例，输入30转为30%
semi_annual_raise = (float(input()) / 100 + 1)   # 输入每半年加薪比例，输入7转化为7%


###################################Begin###################################
# 补充你的代码                                                       # 首付款
###################################Begin###################################
print('首付',total_cost)

i = 0
res = 0.0
while res < total_cost:
    res += annual_salary * portion_saved
    i += 1
    if i % 6 == 0:
        annual_salary *= semi_annual_raise
    if i % 12 == 0:
        print(f"第{i}个月月末有{format(round(res), ',')}元存款")



print(f'需要{i}个月可以存够首付')