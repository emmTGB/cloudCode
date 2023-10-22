use std::fs::File;
use std::io::{ErrorKind, self, Read};
use std::result;

fn main() {
    let f = match File::open("../sb.txt"){
        Ok(f)=>f,
        Err(error)=>match error.kind(){
            ErrorKind::NotFound=>match File::create("../sb.txt") {
                Ok(fc)=>fc,
                Err(e)=>panic!("error creating file: {:?}", e),
            },
            other_error=>panic!("error opening the file: {:?}", other_error),
        },
    };
    // 下面两坨等价
    let f = match File::open("./ea.txt") {
        Ok(file)=>file,
        Err(error)=>panic!("111:{:?}", error),
    };
    let f = File::open("./ea.txt").unwrap();
    // except则与unwrap类似，但是可以设置错误信息

    panic!("gonna die now!");
    println!("Hello, world!");
}

fn read_username_from_file()->Result<String, io::Error>{
    let mut f = File::open("hello.txt");

    let mut f = match f{
        Ok(file)=>file,
        Err(e)=>return Err(e),
    };

    let mut s = String::new();
    match f.read_to_string(&mut s){
        Ok(_)=>Ok(s),
        Err(e)=>Err(e),
    }
}

fn same_read_username_from_file()->Result<String, io::Error>{
    let mut f = File::open("hello.txt")?;
    let mut s = String::new();
    f.read_to_string(&mut s)?;
    Ok(s)
}