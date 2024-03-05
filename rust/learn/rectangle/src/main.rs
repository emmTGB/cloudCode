fn main() {
    println!("Hello, world!");

    let rect = Rect{
        width: 30,
        length: 50,
    };
    println!("{:?}", rect);
    println!("{:#?}", rect);
}

fn area(width: u32, length: u32)->u32{
    width * length
}

fn tuple_area(dim: (u32, u32))->u32{
    dim.0 * dim.1
}

#[derive(Debug)]  // to output struct
struct Rect{
    width: u32,
    length: u32,
}

fn rect_area(rect: &Rect)->u32{
    rect.width * rect.length
}