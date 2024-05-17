l1 = [
    ('dungeon',7),
    ('winterfell',4),
    ('bran',9),
    ('meelo',6)
]

l1.sort(key=lambda x:x[1])

l2 = [[ 'Angle', '0121701100106',99], [ 'Jack', '0121701100107',86], [ 'Tom', '0121701100109',65], [ 'Smith', '0121701100111', 100], ['Bob', '0121701100115',77], ['Lily', '0121701100117', 59]]

l2.sort(key=lambda x:x[0])

m = int(input())
n = int(input())

print(l1[:m])
print(l2[:n])

l2.sort(key=lambda x:x[2])

print(l2[:n])