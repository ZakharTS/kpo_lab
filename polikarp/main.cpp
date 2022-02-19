#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;
    vector<int> a;
    for(int i = 0; i < n; i++) {
        int temp;
        cin >> temp;
        a.push_back(temp);
    }
    sort(a.begin(), a.end());
    int k = 0;
    for (int i = 0; i < n; i++) {
        if (k + 1 <= a[i]) k++;
    }
    cout << k;
    return 0;
}
