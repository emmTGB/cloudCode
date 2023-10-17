use path::*;
use std::collections::hash_map as map;
pub use path::front_of_house::hosting;

use rand::Rng;

use std::{cmp::Ordering, io::{self, Write}};

fn main() {
    println!("Hello, world!");
    pub_hosting::add_to_waitlist();
}
