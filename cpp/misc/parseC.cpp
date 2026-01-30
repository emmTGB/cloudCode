#include <iostream>
#include <vector>
#include <regex>
#include <string>
#include <stdexcept>
#include <fstream>

// Token 类型
struct Token {
    std::string type;
    std::string value;
};

const std::vector<std::pair<std::string, std::string>> tokens = {
    {"IDENTIFIER", R"([a-zA-Z_][a-zA-Z0-9_]*)"},          // 标识符
    {"NUMBER", R"(\d+)"},                              // 数字
    {"ASSIGN", R"(=)"},                                // 赋值符号
    {"COMMA", R"(,)"},                                 // 逗号
    {"OPERATOR", R"(\+|\-|\*|\/)",},                   // 运算符
    {"SEMICOLON", R"(;)"},                             // 分号
    {"WHITESPACE", R"(\s+)"},                          // 空白符（将忽略）
};

// 词法分析器
class Lexer {
private:
    std::string code;
    std::vector<Token> tokensList;
    size_t currentPos = 0;

public:
    explicit Lexer(const std::string& code) : code(code) {}

    std::vector<Token> tokenize() {
        while (currentPos < code.length()) {
            bool matched = false;
            for (const auto& [type, regexStr] : tokens) {
                std::regex pattern(regexStr);
                std::smatch match;
                std::string remainingCode = code.substr(currentPos);
                if (std::regex_search(remainingCode, match, pattern) && match.position() == 0) {
                    if (type != "WHITESPACE") {
                        tokensList.push_back({type, match.str()});
                    }
                    currentPos += match.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                throw std::runtime_error("Unexpected character: " + std::string(1, code[currentPos]));
            }
        }
        return tokensList;
    }
};

// 语法分析器
class Parser {
private:
    std::vector<Token> tokens;
    size_t currentTokenIndex = 0;
    size_t lastTokenIndex = 0;

    Token getCurrentToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens[currentTokenIndex];
        }
        return {"", ""};
    }

    void consume(const std::string& expectedType) {
        Token currentToken = getCurrentToken();
        if (currentToken.type == expectedType) {
            currentTokenIndex++;
        } else {
            throw std::runtime_error("Expected " + expectedType + ", got " + currentToken.type);
        }
    }

    void parseDeclaration() {
        // consume("KEYWORD");  // 数据类型
        parseAssignmentList();
        consume("SEMICOLON");  // 分号
    }

    void parseAssignmentList() {
        parseAssignment();
        while (getCurrentToken().type == "COMMA") {
            consume("COMMA");
            parseAssignment();
        }
    }

    void parseAssignment() {
        consume("IDENTIFIER");  // 变量名
        consume("ASSIGN");      // 等号
        parseExpression();       // 表达式
    }

    void parseExpression() {
        consume("NUMBER");      // 数字
        while (getCurrentToken().type == "OPERATOR") {
            consume("OPERATOR");
            consume("NUMBER");
        }
    }

public:
    explicit Parser(const std::vector<Token>& tokens) : tokens(tokens) {}

    void parse() {
        while (currentTokenIndex < tokens.size()) {
            lastTokenIndex = currentTokenIndex;
            parseDeclaration();
            for(int i = lastTokenIndex; i < currentTokenIndex; ++i){
                std::cout << tokens[i].type << ' ';
            }
            std::cout<<std::endl;
            for(int i = lastTokenIndex; i < currentTokenIndex; ++i){
                std::cout << tokens[i].value << ' ';
            }
            std::cout<<std::endl;
            std::cout << "---line parse finished---" << std::endl;
        }
    }
};

int main() {
    std::ifstream inputFile("./code.c");
    if (!inputFile) {
        std::cerr << "Failed to open file ./code.c" << std::endl;
        return 1;
    }

    std::string code((std::istreambuf_iterator<char>(inputFile)), std::istreambuf_iterator<char>());
    
    try {
        Lexer lexer(code);
        auto tokens = lexer.tokenize();

        std::cout << "Tokens:" << std::endl;
        for (const auto& token : tokens) {
            std::cout << "(" << token.type << ", " << token.value << ")\n";
        }

        std::cout<< "Start parsing..." << std::endl;

        Parser parser(tokens);
        parser.parse();

        std::cout << "Syntax analysis completed successfully!" << std::endl;
    } catch (const std::exception& e) {
        std::cerr << "Syntax error: " << e.what() << std::endl;
    }

    return 0;
}
