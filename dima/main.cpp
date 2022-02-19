#include <iostream>
using namespace std;

void main() {
    int n, sum = 0, sol = 0;
    cin >> n;
    for (size_t i = 0; i < n; i++) {
        int a;
        cin >> a;
        sum += a;
    }
    sum--;
    n++;
    for(short i = 1; i <= 5; i++) {
        if((sum + i) % n != 0) sol++;
    }
    cout << sol << endl;
    //current branch - main
    return 0;
}
