fn main() {
    println!("Hello, world!");

    println!("{}", function(100, 128));

    let y/* empty tuple */ = {
        let x = 1;
        x + 4;// the value of this block
    };  // y == 5
}

fn function(x: u32, y: u64) -> u32{
    println!("{} {}", x, y);

    let y = y as u32;
    return x + y;
}