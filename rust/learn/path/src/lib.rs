pub mod front_of_house{
    pub mod hosting{
        pub fn add_to_waitlist(){}
    }
}

pub fn eat_at_restuarant(){
    crate::front_of_house::hosting::add_to_waitlist();

    front_of_house::hosting::add_to_waitlist();
}