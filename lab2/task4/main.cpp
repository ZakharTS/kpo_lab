#include <iostream>
#include <cassert>

using namespace std;

void unitTest();
int lastHundred(int , int *);

int main() {
    unitTest();
    int n;
    cin >> n;
    int *a = new int[n];
    for (size_t i = 0; i < n; i++) {
        cin >> a[i];
    }
    cout << endl << lastHundred(n, a);
    return 0;
}

int lastHundred(int n, int * a) {
    int last = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] == 100) {
            last = i + 1;
        }
    }
    return last;
}

void unitTest() {
    int A1[] = {100, 54, 23, 100};
    assert(lastHundred(4, A1) == 4);
    int A2[] = {432, 123, 875};
    assert(lastHundred(3, A2) == 0);
    int A3[] = {100, 100, 123, 543, 25};
    assert(lastHundred(5, A3) == 2);
    int A4[] = {100, 54, 236, 100, 100, 100, 1};
    assert(lastHundred(7, A4) == 6);
    int A5[] = {867, 54, 100,  23, 10};
    assert(lastHundred(5, A5) == 3);
    cout << "Unit test passed.\n";
}
