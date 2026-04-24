fn main() {
    for i in 0..=100 {
        let text = match (is_fizz(i), is_buzz(i)) {
            (true, true) => "FizzBuzz".to_string(),
            (true, false) => "Fizz".to_string(),
            (false, true) => "Buzz".to_string(),
            _ => i.to_string(),
        };
        println!("{}", text);
    }
}

fn is_buzz(i: i32) -> bool {
    i % 5 == 0
}

fn is_fizz(i: i32) -> bool {
    i % 3 == 0
}
