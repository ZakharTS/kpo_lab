#ifndef ANIMAL_H
#define ANIMAL_H

class Animal {
public:
    Animal(); // конструктор без параметров
    Animal(const char *, const char *, const int); // конструктор с параметрами
    Animal(const Animal &); // конструктор копирования
    ~Animal(); // деструктор
    char * GetName(); // геттер для поля name
    char * GetBioClass(); //геттер для поля bioClass
    int GetAvWeight(); // геттер для поля avWeight
    void SetName(const char *); // сеттер для поля name
    void SetBioClass(const char *); // сеттер для поля bioClass
    void SetAvWeight(const int); // сеттер для поля avWeight
    void Display(); // вывод содержимого класса
private:
    char name[256] = "default"; // имя
    char bioClass[256] = "default"; // класс (биологический)
    int avWeight = 0; // средняя масса
};

#endif
