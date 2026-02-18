
void main(){
    //Below are valid main signatures
//    static void main(){
//    void main(String[] args){
//public static void main(String[] args){
    IO.println("Welcome to java 25");
    String name = IO.readln("Enter your name: ");
    IO.println("Hello "+name.substring(0,1).toUpperCase()+name.substring(1) +"!");
}