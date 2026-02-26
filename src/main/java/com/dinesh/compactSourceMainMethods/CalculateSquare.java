
//No explicit class declaration is needed for variable declaration
int number =33;
void main(){
    IO.println("-------------------------------------------");

    IO.println("Square of number 25 :"+ squareOfNumber(25));
    IO.println("Square of number "+ number + " :"+ squareOfNumber(number));
    IO.println("Square of number 30 :"+ squareOfNumber(30));

    IO.println("------------------------------------------");
}

private int squareOfNumber(int i) {
    return i * i;
}