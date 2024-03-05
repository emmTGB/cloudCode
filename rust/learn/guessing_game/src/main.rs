//use std::io;  // prelude
use rand::Rng;  // trait
use std::cmp::Ordering;

fn main(){
    
    // let foo = 1;
    // let bar = foo;  // default immutable
    
    let secret_number = rand::thread_rng().gen_range(1..101);
    println!("secret_number is: {}", secret_number);

    loop{
        println!("guess one");

        let mut guess = String::new();
        std::io::stdin().read_line(&mut guess).expect("cannot read");
        // io::Result   Ok Err

        let guess: u32 = match guess.trim()/* delete spaces */.parse()/* transfer to other type */{
            Ok(num) => num,
            Err(_) => continue,
        };  // shadow

        println!("you guessed: {}", guess);

        match guess.cmp(&secret_number){  // arm
            Ordering::Less => println!("too small"),
            Ordering::Greater => println!("too big"),
            Ordering::Equal => {
                println!("you win");
                break;
            },
        }
    }
}