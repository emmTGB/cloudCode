pub mod front_of_house{
    pub mod hosting{
        pub fn add_to_waitlist(){}
    }
}

use crate::front_of_house::hosting;

pub fn eat_at_restuarant(){
    crate::front_of_house::hosting::add_to_waitlist();  // 绝对路径

    front_of_house::hosting::add_to_waitlist();  // 相对路径

    hosting::add_to_waitlist();  // after use

    let mut meal = back_of_house::Breakfast::summer("Rye");
    meal.toast = String::from("Wheat");
    println!("like {} toast", meal.toast);
    // meal.seasonal_fruit xx
}

pub use front_of_house::hosting as pub_hosting;

fn serve_orde(){}

mod back_of_house{
    pub struct Breakfast{
        pub toast: String,
        seasonal_fruit: String,
    }

    impl Breakfast{
        pub fn summer(toast: &str)->Breakfast{
            Breakfast {
                toast: String::from(toast),
                seasonal_fruit: String::from("peaches"),
            }
        }
    }

    fn fix_incorrect_order(){
        cook_order();
        super::serve_orde();
        crate::serve_orde();
    }

    fn cook_order(){}
}

mod mid_of_house;