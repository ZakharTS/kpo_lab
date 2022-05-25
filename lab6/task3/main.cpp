#include <iostream>
#include <climits>

using namespace std;

int main() {
    int n;
    cin >> n;
    int arr[n];
    for (int i = 0; i < n; i++) cin >> arr[i];
    int curSum = 0, maxSum = INT_MIN, first = 0, firstMax = 0, lastMax = 0;
    for (int i = 0; i < n; i++) {
        if (curSum == 0) first = i;
        curSum += arr[i];
        if (curSum > maxSum) {
            maxSum = curSum;
            firstMax = first;
            lastMax = i;
        }
        if (curSum <= 0) {
            curSum = 0;

        }
    }
    cout << maxSum << endl;
    for (int i = firstMax; i <= lastMax; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
    return 0;
}
