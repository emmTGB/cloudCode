use learnTrait::Summary;
use learnTrait::Tweet;

fn main() {
    println!("Hello, world!");
    let tt = Tweet{
        username: String::from("牛马"),
        content: String::from("你就是歌姬吧"),
        reply: false,
        retweet: false,
    };
    println!("{}", tt.summarize());
}

/*
    对于一个类型和 trait
    若要实现 impl trait for struct
    那么类型和 trait 至少有一个是在本地的 crate 里定义的

    rust 无法为外部类型实现来自外部的 trait
    其存在是为了程序的一致性
    我们将此规则称为孤儿规则
    它可以防止出现多个 crate 实现同一个 trait 所引起的混乱
*/