#include <iostream>
#include <stack>
#include <string>

using namespace std;

int main() {
    string input;
//    cout << (int)('(') << " " << (int)(')') << " " << (int)('[') << " " << (int)(']') << " " << (int)('{') << " " << (int)('}') << " ";
    getline(cin, input);
    stack<char> brackets;
    for (int i = 0; i < input.length(); i++) {
        if (input[i] == '(' || input[i] == '[' || input[i] == '{') {
            brackets.push(input[i]);
        }
        if (input[i] == ')' || input [i] == ']' || input[i] == '}') {
            if (brackets.empty()) {
                cout << "No enough opening brackets.\n";
                return 1;
            }
//            cout << brackets.top() << endl;
            if ((input[i] - brackets.top()) <= 1) {
                brackets.pop();
            } else {
                cout << "Wrong bracket closing.\n";
                return 1;
            }
        }
    }
    if (!brackets.empty()) {
        cout << "No enough closing brackets.\n";
        return 1;
    }
    cout << "Everything is right.\n";
    return 0;
}
