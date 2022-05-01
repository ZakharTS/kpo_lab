#include <iostream>
#include <cassert>

using namespace std;

int gcd(int, int);
int * coprime(int, int, size_t &);
void unitTest();
bool compare(int *, int *, size_t);

int main() {
    unitTest();
    int n, m;
    cin >> n >> m;
    if (n <= 0 || m <= 0) {
        cout << "Not a natural number." << endl;
        return 1;
    }
    size_t arrSize = 0;
    int * array = coprime(n, m, arrSize);
    for (size_t i = 0; i < arrSize; i++)
        cout << array[i] << endl;
    return 0;
}

int gcd(int a, int b) {
    while (a && b) {
        if(a > b) a %= b;
        else b %= a;
    }
    return a + b;
}

int * coprime(int n, int m, size_t &arrSize) {
    arrSize = 0;
    int * out = new int[n];
    for (int i = 2; i <= n; i++) {
        if (gcd(i, m) == 1) {
            out[arrSize] = i;
            arrSize++;
        }
    }
    return out;
}

bool compare(int *A, int *B, size_t arrSize) {
    for (size_t i = 0; i < arrSize; i++) {
        if(A[i] != B[i]) return 0;
    }
    return 1;
}

void unitTest() {
    int * A, B1[] = {2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19};
    size_t arrSize;
    A = coprime(20, 5, arrSize);
    assert(compare(A, B1, arrSize));
    int B2[] = {3, 5, 7, 9, 11, 13, 15};
    A = coprime(15, 4, arrSize);
    assert(compare(A, B2, arrSize));
    int B3[] = {2, 4, 5, 7, 8, 10, 11, 13, 14, 16};
    A = coprime(16, 3, arrSize);
    assert(compare(A, B3, arrSize));
    int B4[] = {2, 3, 4, 5, 6, 8, 9, 10};
    A = coprime(10, 7, arrSize);
    assert(compare(A, B4, arrSize));
    int B5[] = {2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20, 22, 23};
    A = coprime(23, 3, arrSize);
    assert(compare(A, B5, arrSize));
    cout << "Unit test passed.\n";
}
