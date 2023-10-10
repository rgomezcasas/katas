use std::env;

fn main() {
    let args: Vec<String> = env::args().collect();

    match &args[1].parse::<i32>() {
        Ok(number) => match (number % 3, number % 5) {
            (0, 0) => println!("FizzBuzz"),
            (0, _) => println!("Fizz"),
            (_, 0) => println!("Buzz"),
            _ => println!("Nope"),
        },
        Err(_) => println!("Has de enviar un número como argumento"),
    }
}
