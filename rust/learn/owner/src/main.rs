fn main() {
    println!("Hello, world!");

    let mut s = String::from("hello");
    s.push_str(", world");
    let len = cal_len(&s);  // s still own its heap, so its not moved
    let len1 = cal_mut_len(&mut s);  // cannot borrow data as mutable more than once in one scope, so you cant do something like : let s1 = &mut s, let s2 = &mut s;
    // meanwhile you cannot have mutable and immutable reference at the same time
    // you can have several immutable references at the same time

    // let s1 = s;  s is moved and unavailable to avoid double free
    // other simple datatypes like integer are in stack , we can clone them like let y = x; , rust has a trait named Copy to handle it
    let s1 = s.clone();  // s1 and s are available
    println!("{}", s);
}

// reference
fn cal_len(s: &String) -> usize{  // this s in func doesnt own this heap, it's borrowed from s in main
    // s.push_str("kevin") you can mut the data that is borrowed
    s.len()
}
// mutable reference
fn cal_mut_len(s: &mut String) -> usize{
    s.push_str("kevin");
    s.len()
}