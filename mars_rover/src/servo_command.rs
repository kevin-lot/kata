use crate::command::Command;
use crate::rover::Rover;

pub struct ServoCommand {
    rover: Rover,
}
impl ServoCommand {
    pub fn from(rover: Rover) -> Self {
        Self { rover }
    }

    pub fn move_rover(&self, commands: &str) -> Rover {
        commands
            .chars()
            .try_fold(self.rover.clone(), |rover, char| {
                let command = match Command::from(char) {
                    Some(cmd) => cmd,
                    None => return Ok(rover),
                };

                match rover.apply(command).check_collision() {
                    Ok(r) => Ok(r),
                    Err((r, e)) => {
                        println!("{}", e);
                        Err(r)
                    }
                }
            })
            .unwrap_or_else(|r| r)
    }
}
