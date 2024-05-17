def read_file(file):
    with open(file, 'r', encoding='utf-8') as f:
        return f.read()

def word_list(txt):
    for c in "!\"'#$%()*+,-.:;<=>?@[\\]^_{|}~/":
        txt = txt.replace(c,' ')
    return txt.split()

def number_of_words(ls):
    return len(ls)

if __name__ == '__main__':
    filename = input()                          # 读入文件名
    text = read_file('step10/'+filename)        # 读取'step10/'文件夹中用户输入的文件名得到文件内容，存入text
    words_list = word_list(text)                # 处理text,得到单词的列表
    words_counts = number_of_words(words_list)  #统计单词列表word_list里的单词数
    print(f'共有{words_counts}个单词')