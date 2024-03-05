fn main() {
    println!("Hello, world!");

    let v = 0u8;
    match v {
        1 => println!("111"),
        2 => println!("222"),
        _ => (),
    }

    let m = Some(0u8);
    match m{
        Some(3) => println!("333"),
        _ => println!("others"),
    }
    if let Some(3) = m{
        println!("333");
    }else{
        println!("others")
    }  // they are same
}


fn plus_one(x: Option<i32>)->Option<i32>{
    match x{  // need to match all enum or you can use _, see line 4
        None => None,
        Some(i) => Some(i + 1),
    }
}
