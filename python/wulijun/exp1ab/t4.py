a = eval(input("请输入每学分学费金额："))
b = eval(input("请输入你每个月生活费："))

print("本学期你能够贷款{:.2f}元".format(0.6 * (17 * a + 5 * b)))