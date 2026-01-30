#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <regex>
#include <cctype>
#include <fstream>

using namespace std;

// 定义C语言关键字、运算符、分隔符
const set<string> keywords = {
    "auto", "break", "case", "char", "const", "continue", "default", "do", "double",
    "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register",
    "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef",
    "union", "unsigned", "void", "volatile", "while"
};
const set<string> operators = { "+", "-", "*", "/", "%", "=", ">", "<", "!", "&", "|", "^", "~", "++", "--", "==", "!=", ">=", "<=", "&&", "||" };
const set<char> delimiters = { '(', ')', '{', '}', '[', ']', ';', ',', '.' };

// 正则表达式匹配规则
const regex identifier_regex("[a-zA-Z_][a-zA-Z0-9_]*");
const regex number_regex("\\b\\d+(\\.\\d+)?\\b");

// 转换单词为属性字
string get_attribute(const string& word) {
    if (keywords.find(word) != keywords.end()) {
        return "KEYWORD";
    }
    else if (operators.find(word) != operators.end()) {
        return "OPERATOR";
    }
    else if (delimiters.find(word[0]) != delimiters.end() && word.size() == 1) {
        return "DELIMITER";
    }
    else if (regex_match(word, identifier_regex)) {
        return "IDENTIFIER";
    }
    else if (regex_match(word, number_regex)) {
        return "NUMBER";
    }
    else {
        return "UNKNOWN";
    }
}

// 对源代码进行词法分析
vector<pair<string, string>> lex_analyze(const string& source_code) {
    vector<pair<string, string>> tokens; // 存储结果 (单词, 属性字)
    string word = "";                   // 用于拼接字符成为单词
    size_t i = 0;

    while (i < source_code.size()) {
        char ch = source_code[i];

        // 跳过空白字符
        if (isspace(ch)) {
            if (!word.empty()) {
                tokens.emplace_back(word, get_attribute(word));
                word = "";
            }
            i++;
            continue;
        }

        // 检查运算符
        string two_char_op = i + 1 < source_code.size() ? source_code.substr(i, 2) : "";
        if (operators.find(string(1, ch)) != operators.end() || operators.find(two_char_op) != operators.end()) {
            if (!word.empty()) {
                tokens.emplace_back(word, get_attribute(word));
                word = "";
            }
            if (operators.find(two_char_op) != operators.end()) {
                tokens.emplace_back(two_char_op, "OPERATOR");
                i += 2;
            }
            else {
                tokens.emplace_back(string(1, ch), "OPERATOR");
                i++;
            }
            continue;
        }

        // 检查分隔符
        if (delimiters.find(ch) != delimiters.end()) {
            if (!word.empty()) {
                tokens.emplace_back(word, get_attribute(word));
                word = "";
            }
            tokens.emplace_back(string(1, ch), "DELIMITER");
            i++;
            continue;
        }

        // 拼接字符成单词
        word += ch;
        i++;
    }

    // 最后处理剩余的单词
    if (!word.empty()) {
        tokens.emplace_back(word, get_attribute(word));
    }

    return tokens;
}

int main() {

    string filename = "./source_code.c";

    // 打开文件
    ifstream file(filename);
    if (!file.is_open()) {
        cerr << "Failed to open file: " << filename << endl;
        return 1;
    }

    // 读取文件内容
    string source_code((istreambuf_iterator<char>(file)), istreambuf_iterator<char>());
    file.close();

    vector<pair<string, string>> tokens = lex_analyze(source_code);

    // 输出结果
    cout << "Word\tAttribute" << endl;
    for (const auto& token : tokens) {
        cout << token.first << "\t" << token.second << endl;
    }

    return 0;
}
