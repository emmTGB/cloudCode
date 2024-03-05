fn main() {
    println!("Hello, world!");
    let Ip = IpAddr::V4(192, 168, 1, 2);
}

enum IpAddr{
    domain,
    V4(u8, u8, u8, u8),
    v6(String),
    time{y: u32, m: u32, d: u32},
}

impl IpAddr {
    fn visit(&self){}
}

enum Option<T>{
    Some(T),
    None,  // 'null'
}