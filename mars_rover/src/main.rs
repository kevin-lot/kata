mod command;
mod orientation;
mod planet;
mod position;
mod rover;
mod servo_command;

use crate::orientation::Orientation;
use crate::planet::{Planet, Rock};
use crate::position::Position;
use crate::rover::Rover;
use crate::servo_command::ServoCommand;

fn main() {
    // init
    let rover = match Rover::from(
        Position::from(0, 0, Orientation::North),
        Planet::from((10, 10), vec![Rock::from(1, 1)]),
    ) {
        Ok(r) => r,
        Err(e) => return println!("Failed to create rover: {}", e),
    };
    let servo_command = ServoCommand::from(rover);

    // command
    let commands = "frfffffff";
    let rover = servo_command.move_rover(commands);

    // Print the planet map with the rover's position represented by "R", rocks by "X", and empty spaces by ".".
    let planet = rover.get_planet();
    let (size_x, size_y) = planet.get_size();
    let rocks = planet.get_rocks();

    let rover_x = rover.get_position().get_x();
    let rover_y = rover.get_position().get_y();

    println!("Planet map:");
    for y in (0..size_y).rev() {
        for x in 0..size_x {
            if x == rover_x && y == rover_y {
                print!("R");
                continue;
            }
            if rocks.contains(&Rock::from(x, y)) {
                print!("X");
                continue;
            }
            print!(".");
        }
        println!();
    }
}
