#include "A.h"

A::A(int x){
    y = x;
}

int A::getY() {
    return y;
}

int main(int argc, char* test[])
{
    A a(5);
    std::cout << a.getY() << std::endl;
}
