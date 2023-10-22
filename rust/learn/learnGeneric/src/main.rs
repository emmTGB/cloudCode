use std::fmt::Display;

fn main() {
    println!("Hello, world!");
    let float = Point { x: 1.2, y: 2.3 };
}

fn largest<T: PartialOrd + Clone>(list: &[T]) -> &T {
    let mut largest = &list[0];
    for item in list.iter() {
        if item > largest {
            largest = item;
        }
    }
    largest
}

struct Point<T> {
    x: T,
    y: T,
}

enum Option<T> {
    Some(T),
    None,
}

impl<T> Point<T> {
    fn x(&self) -> &T {
        &self.x
    }
}

impl Point<i32> {
    fn y(&self) -> &i32 {
        &self.y
    }
}

struct PointP<T, U> {
    x: T,
    y: U,
}

impl<T> PointP<T, f64> {
    fn mixup<V, W>(self, other: PointP<V, W>) -> PointP<T, W> {
        PointP {
            x: self.x,
            y: other.y,
        }
    }
}
