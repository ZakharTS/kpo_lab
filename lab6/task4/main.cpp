#include <iostream>
#include <algorithm>

using namespace std;

int max_subseq (int *arr, int n);

int main() {
    int n;
    cin >> n;
    int arr[n];
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    cout << "Length of max subsequence: " << max_subseq(arr, n) << endl;
}

int max_subseq (int *arr, int n) {
    int ln[n];
    for (int i = 0; i < n; i++) {
        ln[i] = 1;
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i]) {
                ln[i] = max(ln[i], ln[j] + 1);
            }
        }
    }
    return *max_element(ln, ln + n);
}