#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

int main() {
    ifstream fin("file.txt");
    string temp;
    vector<int> nums;
    vector<string> lines;
    int n = 0, j = 0;
    while (getline(fin, temp)) {
        lines.push_back(temp);
        for (size_t i = 0; i < temp.size(); i++) {
            if (temp[i] >= '0' && temp[i] <= '9') {
                nums.push_back(j);
                n++;
            }
        }
        j++;
    }
    cout << lines[nums[n/2-1]] << endl;
    fin.close();
    return 0;
}
