use crate::command::Command;
use crate::rover::Rover;

pub struct ServoCommand {
    rover: Rover,
}

impl From<Rover> for ServoCommand {
    fn from(rover: Rover) -> Self {
        Self { rover }
    }
}

impl ServoCommand {
    pub fn move_rover(&self, commands: &str) -> (Rover, Vec<String>) {
        commands.chars().fold(
            (self.rover.clone(), Vec::new()),
            |(rover, mut collisions), char| {
                let command = match Command::try_from(char) {
                    Ok(cmd) => cmd,
                    Err(_) => return (rover, collisions),
                };

                match rover.clone().apply(command).check_collision() {
                    Ok(new_rover) => (new_rover, collisions),
                    Err((_, message)) => {
                        collisions.push(message);
                        (rover, collisions) // rover stays at previous position
                    }
                }
            },
        )
    }
}
