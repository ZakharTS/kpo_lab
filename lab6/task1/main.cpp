#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int maxs[] = {INT_MIN, INT_MIN, INT_MIN, INT_MIN};
    for (int i = 0; i < n; i++) {
        if (a[i] >= maxs[0]) {
            for (int j = 3; j >= 0; j--) {
                if (a[i] >= maxs[j]) {
                    for (int k = 0; k < j; k++) {
                        maxs[k] = maxs[k+1];
                    }
                    maxs[j] = a[i];
                    break;
                }
            }
        }
    }
    for (int i = 0; i < 4; i++) {
        cout << maxs[i] << " ";
    }
    return 0;
}
