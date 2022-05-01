#include "Animal.h"
#include <iostream>
#include <string.h>

Animal::Animal() {
    std::cout << "Object " << this << " created." << std::endl;
}
Animal::Animal(const char * curName, const char * curClass, const int curWeight): avWeight(curWeight) {
    strcpy(name, curName);
    strcpy(bioClass, curClass);
    std::cout << "Object " << this << " created with parameters." << std::endl;
}
Animal::Animal(const Animal & current) {
    strcpy(name, current.name);
    strcpy(bioClass, current.bioClass);
    avWeight = current.avWeight;
    std::cout << "Object " << this << " created with copying." << std::endl;
}
Animal::~Animal() {
    std::cout << "Object " << this << " deleted." << std::endl;
}
char * Animal::GetName() {
    return name;
}
char * Animal::GetBioClass() {
    return bioClass;
}
int Animal::GetAvWeight() {
    return avWeight;
}
void Animal::SetName(const char * curName) {
    strcpy(name, curName);
}
void Animal::SetBioClass(const char * curClass) {
    strcpy(bioClass, curClass);
}
void Animal::SetAvWeight(const int curWeight) {
    avWeight = curWeight;
}

void Animal::Display() {
    std::cout << "Name: " << name << "; Class: " << bioClass << "; Average weight: " << avWeight << " kg." << std::endl;
}
