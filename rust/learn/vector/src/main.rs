use std::vec;

fn main() {
    println!("Hello, world!");
    let mut v: Vec<i32> = Vec::new();

    let vv = vec![1, 2, 3];

    let mut vvv = Vec::new();
    vvv.push(1);

    match vvv.get(2){
        Some(third) => println!("{}", third),
        Node => println!("none"),
    }
}
