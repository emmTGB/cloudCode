use std::str::FromStr;

fn main() {
    println!("Hello, world!");

    let mut stu = Student{
        id: 39,
        name: String::from("hatsune"),
        gender: 'A',
    };

    let stufn = Student::new_stu(&stu);

    let mut stu1 = Student{
        gender: 'B',
        ..stu
    };

    stu1.print_name();
}

#[derive(Debug)]
struct Student{
    name: String,
    id: i32,
    gender: char,
}

impl Student{
    fn print_name(&self){
        println!("{}", self.name);
    }

    fn new_stu(stu: &Student)->Student{
        Student{
            id: stu.id + 1,
            name: String::from("111"),
            ..(*stu)
        }
    }
}