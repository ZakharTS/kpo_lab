#include <iostream>
#include <map>
#include <string>
#include <sstream>

using namespace std;

string key(string);
string value(string);
bool datecmp(string, string);

int main() {
    map<string, string> records;
    int n;
    cout << "Number of records: ";
    cin >> n;
    cin.ignore();
    for (int i = 0; i < n; i++) {
        string temp;
        getline(cin, temp);
        if (records.find(key(temp)) != records.end()) {
            if (datecmp(records.find(key(temp))->second, value(temp))) {
                records.find(key(temp))->second = value(temp);
            } else {
                continue;
            }
        }
        records.insert(make_pair(key(temp), value(temp)));
    }
    for (map<string, string>::iterator itr = records.begin(); itr != records.end(); itr++) {
        cout << itr->first << " - " << itr->second << endl;
    }
    return 0;
}

string key(string line) {
    int l = line.find_first_of('-');
    char k[256];
    k[line.copy(k, l-1, 0)] = '\0';
    return k;
}

string value(string line) {
    int l = line.find_first_of('-');
    char k[256];
    k[line.copy(k, line.size() - l, l+2)] = '\0';
    return k;
}

bool datecmp(string A, string B) {
    for (int i = 0; i < A.length(); i++) {
        if (A[i] == '.') A[i] = ' ';
    }
    for (int i = 0; i < B.length(); i++) {
        if (B[i] == '.') B[i] = ' ';
    }
    stringstream toint(A + ' ' + B);
    short Aday = 0, Amonth = 0, Ayear = 0, Bday = 0, Bmonth = 0, Byear = 0;
    toint >> Aday >> Amonth >> Ayear >> Bday >> Bmonth >> Byear;

    if (Ayear < Byear) {
        return 1;
    } else if (Ayear > Byear) {
        return 0;
    } else if (Ayear == Byear) {
        if (Amonth < Bmonth) {
            return 1;
        } else if (Amonth > Bmonth) {
            return 0;
        } else if (Amonth == Bmonth) {
            if (Aday < Bday) {
                return 1;
            } else if (Aday > Bday) {
                return 0;
            }
        }
    }
    return 0;
}