#include <iostream>
#include <cassert>

using namespace std;

int toTen(string);
void binSort(string *, size_t);
void unitTest();
bool compare(string *, string *);

int main() {
    unitTest();
    size_t aSize;
    cin >> aSize;
    string *A = new string[aSize];
    for (size_t i = 0; i < aSize; i++) {
        cin >> A[i];
    }
    cout << endl;
    binSort(A, aSize);
    for (size_t i = 0; i < aSize; i++) {
        cout << A[i] << endl;
    }
    return 0;
}

bool compare(string * A, string * B, size_t arrSize) {
    for (size_t i = 0; i < arrSize; i++) {
        if(toTen(A[i]) != toTen(B[i])) return 0;
    }
    return 1;
}

int toTen(string binNum) {
    int tenNum = 0;
    if (!(binNum[0] == '0' && binNum[1] == 'b')) {
        cout << "Not a binary num." << endl;
        return 0;
    }
    for (size_t i = binNum.length() - 1; i > 1; i--) {
        if (binNum[i] == '1') {
            tenNum += (1 << (binNum.length() - i - 1));
        }
    }
    return tenNum;
}

void binSort(string *A, size_t arraySize) {
    for (size_t i = 0; i < arraySize - 1; i++) {
        for (size_t j = 0; j < arraySize - 1; j++) {
            if (toTen(A[j]) > toTen(A[j+1])) swap(A[j], A[j+1]);
        }
    }
}

void unitTest() {
    string A1[] = {"0b01", "0b00010", "0b000"}, B1[] = {"0b0", "0b1", "0b10"};
    binSort(A1, 3);
    assert(compare(A1, B1, 3));
    string A2[] = {"0b1101", "0b011010", "0b010", "0b0001", "0b0111"}, B2[] = {"0b1", "0b10", "0b111", "0b1101", "0b11010"};
    binSort(A2, 5);
    assert(compare(A2, B2, 5));
    string A3[] = {"0b01", "0b10", "0b11", "0b0"}, B3[] = {"0b0", "0b1", "0b10", "0b11"};
    binSort(A3, 4);
    assert(compare(A3, B3, 4));
    string A4[] = {"0b11", "0b00010"}, B4[] = {"0b10", "0b11"};
    binSort(A4, 2);
    assert(compare(A4, B4, 2));
    string A5[] = {"0b01", "0b0", "0b11"}, B5[] = {"0b0", "0b1", "0b11"};
    binSort(A5, 3);
    assert(compare(A5, B5, 3));
    cout << "Unit-test passed.\n";
}
