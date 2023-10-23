fn main() {
    println!("Hello, world!");
}

fn longer<'a>(x: &'a str, y: &'a str) -> &'a str{
    if x.len() > y.len(){W
        x
    }else{
        y
    }
}