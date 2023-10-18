use std::collections::HashMap as Map;

fn main() {
    let mut scores: Map<&str, i32> = Map::new();
    scores.insert("blue", 10);
    println!("Hello, world!");
    let k = vec![String::from("yellow"), String::from("blue")];
    let v = vec![10, 20];
    let mut eee: Map<_, _> = k.iter().zip(v.iter()).collect();

    scores.entry("green").or_insert(30);
    scores.entry("blue").or_insert(33);
    let e = scores.entry("yellow");
    e.or_insert(100);
    println!("{:?}", scores);

    let text = "hellow world wonderful world";
    let mut map = Map::new();
    for wd in text.split_whitespace(){
        let count = map.entry(wd).or_insert(0);
        *count += 1;
    }
    println!("{:?}", map);
}
