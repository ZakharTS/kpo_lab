#include <iostream>
#include "animal.h"

using namespace std;

int main() {
    Animal bison;
    bison.Display();
    char name[] = "Bison", bioClass[] = "Mammals";
    bison.SetName(name);
    bison.SetBioClass(bioClass);
    bison.SetAvWeight(700);
    bison.Display();
    Animal wolf("Wolf", "Mammals", 40);
    wolf.Display();
    Animal wolfClone(wolf);
    wolfClone.Display();
    Animal bisonClone = bison;
    bisonClone.Display();
    Animal * bison_ptr = &bison;
    bison_ptr->SetName("Bison bonasus");
    bison_ptr->Display();
    return 0;
}
