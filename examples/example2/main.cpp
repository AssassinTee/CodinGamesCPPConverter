#include <iostream>/*cout*/
#include "A.h"/*test*/
#include "B.h"

int main(int argc, const char* stuff[])
{
    A a;
    B b;
    a.test = &b;
    b.test = &a;
    std::cout << a.getBI() << std::endl;
    std::cout << b.getAI() << std::endl;
}
