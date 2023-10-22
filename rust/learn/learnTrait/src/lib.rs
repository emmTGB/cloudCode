use std::fmt::Debug;
use std::fmt::Display;

impl<T: Display> Summary for T {
    fn summarize(&self) -> String {
        String::from("1")
    }
    fn summarize1(&self) {}
}

pub trait Summary {
    fn summarize(&self) -> String;
    fn summarize1(&self);
    fn summarize2(&self) {
        print!("haha");
        self.summarize1()
    } // 这是一个带有默认实现的函数，在实现 trait 时可以选择重载该函数或是不实现该函数
      // 默认实现的方法可以调用 trait 中其他的方法，即使它们没有实现
}

pub struct NewsArticle {
    pub headline: String,
    pub location: String,
    pub author: String,
    pub content: String,
}

impl Summary for NewsArticle {
    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
    fn summarize1(&self) {}
}

pub struct Tweet {
    pub username: String,
    pub content: String,
    pub reply: bool,
    pub retweet: bool,
}

impl Summary for Tweet {
    fn summarize(&self) -> String {
        format!("{}, by {}", self.content, self.username)
    }
    fn summarize1(&self) {}
}

pub fn notify1(item1: impl Summary + Display, item2: impl Summary) {
    println!("111 {}", item1.summarize());
} // f(item: impl trait)可以接受实现了trait的类型

pub fn notify2<T: Summary + Display>(item1: T, item2: T) {
    println!("222 {}", item1.summarize());
} // trait bound 写法，传参情况复杂时相较impl trait写法更为简洁

pub fn notify3<T, U>(item1: T, item2: U) -> String
where
    T: Summary + Display,
    U: Debug + Clone,
{
    format!("{}", item1.summarize())
} // 使用 where 语句简化函数签名
