#include <iostream>
#include <ctime>
#include <cassert>

using namespace std;

unsigned long long fibRec(int);
unsigned long long fibNoRec(int);
void unitTest();

int main() {
    unitTest();
    int n = 0;
    clock_t time; time = clock();
    cout << "n = "; cin >> n;
    cout << "Fibonacci with recursion: " << fibRec(n) << endl;
    cout << "Time: " << clock() - time << " ms" << endl;
    time = clock();
    cout << "Fibonacci without recursion: " << fibNoRec(n) << endl;
    cout << "Time: " << clock() - time << " ms" << endl;
    return 0;
}

void unitTest() {
    assert(fibRec(5) == 5);
    assert(fibRec(10) == 55);
    assert(fibRec(13) == 233);
    assert(fibRec(20) == 6765);
    assert(fibRec(25) == 75025);
    assert(fibNoRec(5) == 5);
    assert(fibNoRec(10) == 55);
    assert(fibNoRec(13) == 233);
    assert(fibNoRec(20) == 6765);
    assert(fibNoRec(25) == 75025);
    cout << "Unit-tests passed.\n";
}

unsigned long long fibRec(int n) {
    if(n <= 0) return 0;
    if(n <= 2) return 1;
    return fibRec(n - 1) + fibRec(n - 2);
}

unsigned long long fibNoRec(int n) {
    long temp1 = 1, temp2 = 1, temp3 = 1;
    for(size_t i = 3; i <= n; i++) {
        temp3 = temp1 + temp2;
        temp1 = temp2;
        temp2 = temp3;
    }
    return temp3;
}
