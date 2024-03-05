pub fn add(left: usize, right: usize) -> usize {
    left + right
}

#[cfg(test)]
mod tests {
    use std::result;

    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }

    #[test]
    #[should_panic]
    fn pnc() {
        panic!();
    }

    #[test]
    #[should_panic(expected = "yea")]
    fn pnce() {
        panic!("yea");
    }

    #[test]
    fn it_works111() -> Result<(), String> {
        if 2 + 3 == 4 {
            Ok(())
        } else {
            Err(String::from("two plus 3 does not equql four"))
        }
    }
}
