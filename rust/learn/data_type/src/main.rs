fn main() {
    println!("Hello, world!");

    let guess: u32 = "42"/* str to u32 */.parse().expect("not a number");

    println!("{}", guess);

    // SCALAR_TYPE
    // integer
    // signed : i8, i16, i32, i64, i128, isize->up to ypou computer arch
    // unsigned : u8, u16, u32, u64, u128, usize

    // float
    // f32, f64

    // bool
    // length = 1

    // char
    // use '', length = 4, can use emoji(((

    // operator
    // + - * / %

    //COMPLEX_TYPE
    // tuple
    let tup: (i32, f64, u8) = (500, 6.4, 1);  // length fixed
    println!("{}, {}, {}", tup.0, tup.1, tup.2);
    let(x, y, z) = tup;
    println!("{}, {}, {}", x, y, z);

    // array
    let month = ["January", "February", "March"];
    let a = [3; 5];  // equals [3, 3, 3, 3, 3]
    println!("{}, {}", month[2], a[1]);  // no out of boundary
    
}