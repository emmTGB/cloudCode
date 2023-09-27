const MAX_SIZE: u32 = 1000_000;

fn main() {
    println!("Hello, world!");

    let mut x = 5;
    println!("value of x is: {}", x);
    x = 6;
    println!("value of x is: {}", x);

    // shadowing
    let y = 2;
    let y = y + 1;
    let x = 4;  // immutable now
    println!("x:{}y:{}", x, y);
}
