//use std::io;  // prelude

fn main(){
    println!("guess one");

    // let foo = 1;
    // let bar = foo;  // immutable

    let mut guess = String::new();

    std::io::stdin().read_line(&mut guess).expect("cannot read");

    println!("you guessed: {}", guess);
}