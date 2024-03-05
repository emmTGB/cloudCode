use core::slice;

fn main() {
    println!("Hello, world!");
    let s = String::from("hello world");
    let index = first_space(&s);

    // slice
    let hello = &s[..5];  // range [0,5)
    let world = &s[6..];  // range [6,11)
    
    // we can do these with slice
    slice_space(&s[..]);
    slice_space("kevin");

    // slice also works when handle array
    let a = [1, 2, 3, 4, 5];
    let slice = &a[1..3];

    println!("{}", index);
}

fn first_space(s: &String) -> usize{
    let bytes = s.as_bytes();

    for(i, &item) in bytes.iter().enumerate(){
        if item == b' '{
            return i;
        }
    }
    s.len()
}

fn slice_space(s: &str) -> &str{
    let bytes = s.as_bytes();

    for(i, &item) in bytes.iter().enumerate(){
        if item == b' '{
            return &s[..i];
        }
    }
    &s[..]
}