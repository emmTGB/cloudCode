#include <iostream>
using namespace std;
#include <string>

double Mid(int a[], int i1, int i2, int b[], int j1, int j2)
{
    //cout << i1 << ", " << i2 << ", " << j1 << ", " << j2 << endl;

    if (i1 >= i2 - 1 && j1 >= j2 - 1)
    {
        int at, bt;
        if (a[i1] > b[j1])
        {
            at = min(a[i1], a[i2]);
            bt = max(b[j1], b[j2]);
        }
        else if (a[i1] < b[j1])
        {
            at = max(a[i1], a[i2]);
            bt = min(b[j1], b[j2]);
        }
        else
        {
            if (a[i2] >= b[j2])
                return (a[i1] + b[j2]) * 1.0 / 2;
            else
                return (a[i1] + a[i2]) * 1.0 / 2;
        }
        return 1.0 * (at + bt) / 2;
    }
    int mida = (i1 + i2) / 2;
    int midb = (j1 + j2) / 2;

    double Mida, Midb;
    if ((i2 - i1) % 2 == 0)
        Mida = a[mida];
    else
        Mida = (a[mida] + a[mida + 1]) * 1.0 / 2;

    if ((j2 - j1) % 2 == 0)
        Midb = b[midb];
    else
        Midb = (b[midb] + b[mida + 1]) * 1.0 / 2;

    //cout << Mida << ", " << Midb << endl;

    if (Mida == Midb)
    {
        return Mida;
    }
    else if (Mida > Midb)
    {
        if ((j2 - j1) % 2 == 1)
            return Mid(a, i1, mida, b, midb + 1, j2);
        else
            return Mid(a, i1, mida, b, midb, j2);
    }
    else
    {
        if ((i2 - i1) % 2 == 1)
            return Mid(a, mida + 1, i2, b, j1, midb);
        else
            return Mid(a, mida, i2, b, j1, midb);
    }
}


int main() {

    int n = 0;
    cin >> n;
    int* a = new int[n];
    int* b = new int[n];
    for (int i = 0; i < n; i++)
        cin >> a[i];
    for (int i = 0; i < n; i++)
        cin >> b[i];

    cout << Mid(a, 0, n - 1, b, 0, n - 1);

    return 0;
}