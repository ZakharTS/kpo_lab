#include <iostream>
#include <string>
#include <set>
#include <sstream>
using namespace std;

int main() {
    set<string> words;
    string input, temp;
    getline(cin, input);
    stringstream sstr(input);
    while (sstr >> temp) {
        if (!((temp[0] >= 65 && temp[0] <= 90) || (temp[0] >= 97 && temp[0] <= 122))) {
            temp.erase(0);
        }
        if (!((temp[temp.length()-1] >= 65 && temp[temp.length()-1] <= 90) || (temp[temp.length()-1] >= 97 && temp[temp.length()-1] <= 122))) {
            temp.erase(temp.length()-1);
        }
        for (int i = 0; i < temp.length(); i++) {
            if (temp[i] >= 65 && temp[i] <= 90) {
                temp[i] += 32;
            }
        }
        words.insert(temp);
    }

    for (set<string>::iterator itr = words.begin(); itr != words.end(); itr++) {
        cout << *itr << endl;
    }
    return 0;
}
