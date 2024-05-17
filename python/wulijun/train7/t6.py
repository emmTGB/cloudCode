with open('sale2019.csv','r',encoding='utf-8') as data2019:
	sale2019 = [[line.strip().split(',')[0],float(line.strip().split(',')[1])] for line in data2019]
with open('sale2018.csv','r',encoding='utf-8') as data2018:
	sale2018 = [[line.strip().split(',')[0],float(line.strip().split(',')[1])] for line in data2018]

saleSet2019 = { x[0] for x in sale2019 }
saleSet2018 = { x[0] for x in sale2018 }
n = input()
if n == '1':
	# 1.输入'1'时，以集合形式在两行中分别输出2019年和2018年上榜品牌
	print(sorted(saleSet2019))
	print(sorted(saleSet2018))
elif n == '2':
	# 1.输入'2'时，以集合形式输出2019年和2018年都上榜的品牌
	# 两年都上榜的品牌，交集 s & t
	s2018and2019 = saleSet2019 & saleSet2018
	print(sorted(s2018and2019))
elif n == '3':
	# 1.输入'3'时，以集合形式输出2019年和2018年上榜的所有品牌
	# 两年上榜的所有品牌，并集 s | t
	s20182019all = saleSet2019 | saleSet2018
	print(sorted(s20182019all))
elif n == '4':
	# 1.输入'4'时，以集合形式输出2019年新上榜品牌
	# 新上榜品牌，差补  s - t
	s2019new = saleSet2019 - saleSet2018
	print(sorted(s2019new))  # {'联想', '中兴'}
elif n == '5':
	# 1.输入'5'时，以集合形式输出2019年新上榜和落榜品牌
	# 新上榜与落榜品牌，对称差分， s ^ t
	newAndold = saleSet2019 ^ saleSet2018
	print(sorted(newAndold))  # {'中兴', '联想', '金立'}
