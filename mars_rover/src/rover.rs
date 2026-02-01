use crate::command::Command;
use crate::planet::{Planet, Rock};
use crate::position::Position;

pub type CheckCollisionError = (Rover, String);

#[derive(Debug, Clone)]
pub struct Rover {
    position: Position,
    planet: Planet,
}
impl Rover {
    pub fn from(position: Position, planet: Planet) -> Result<Self, String> {
        if position.get_x() >= planet.get_size().0 || position.get_y() >= planet.get_size().1 {
            return Err("out of bounds".to_string());
        }
        let rover = Self { position, planet };

        match rover.check_collision() {
            Ok(r) => Ok(r),
            Err(_) => Err("rover crashes directly on a rock".to_string()),
        }
    }

    pub fn get_planet(&self) -> Planet {
        self.planet.clone()
    }

    pub fn get_position(&self) -> Position {
        self.position
    }

    pub fn check_collision(self) -> Result<Self, CheckCollisionError> {
        let virtual_rock = Rock::from(self.position.get_x(), self.position.get_y());
        let rocks = self.planet.get_rocks();
        if rocks.contains(&virtual_rock) {
            Err((self, "collision with rock".to_string()))
        } else {
            Ok(self)
        }
    }

    pub fn apply(self, command: Command) -> Self {
        let limit_x = self.planet.get_size().0 - 1;
        let limit_y = self.planet.get_size().1 - 1;
        match command {
            Command::MoveBackward => Rover {
                position: self.position.move_backward(limit_x, limit_y),
                planet: self.planet,
            },
            Command::MoveForward => Rover {
                position: self.position.move_forward(limit_x, limit_y),
                planet: self.planet,
            },
            Command::PivotLeft => Rover {
                position: self.position.pivot_left(),
                planet: self.planet,
            },
            Command::PivotRight => Rover {
                position: self.position.pivot_right(),
                planet: self.planet,
            },
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::orientation::Orientation;
    use crate::planet::Rock;
    use crate::rover::{Command, Planet, Position, Rover};
    use rstest::rstest;

    #[test]
    fn test_from() {
        let rover = Rover::from(
            Position::from(20, 0, Orientation::North),
            Planet::from((10, 10), vec![]),
        );
        assert!(rover.is_err());

        let rover = Rover::from(
            Position::from(0, 20, Orientation::North),
            Planet::from((10, 10), vec![]),
        );
        assert!(rover.is_err());

        let rover = Rover::from(
            Position::from(5, 5, Orientation::North),
            Planet::from((10, 10), vec![Rock::from(5, 5)]),
        );
        assert!(rover.is_err());

        let rover = Rover::from(
            Position::from(5, 5, Orientation::North),
            Planet::from((10, 10), vec![]),
        );
        assert!(rover.is_ok());
    }

    #[rstest]
    #[case(( Orientation::North, Command::MoveBackward), (5, 4, Orientation::North))]
    #[case(( Orientation::North, Command::MoveForward), (5, 6, Orientation::North))]
    #[case(( Orientation::North, Command::PivotLeft), (5, 5, Orientation::West))]
    #[case(( Orientation::North, Command::PivotRight), (5, 5, Orientation::East))]
    #[case(( Orientation::West, Command::MoveBackward), (6, 5, Orientation::West))]
    #[case(( Orientation::West, Command::MoveForward), (4, 5, Orientation::West))]
    #[case(( Orientation::West, Command::PivotLeft), (5, 5, Orientation::South))]
    #[case(( Orientation::West, Command::PivotRight), (5, 5, Orientation::North))]
    #[case(( Orientation::South, Command::MoveBackward), (5, 6, Orientation::South))]
    #[case(( Orientation::South, Command::MoveForward), (5, 4, Orientation::South))]
    #[case(( Orientation::South, Command::PivotLeft), (5, 5, Orientation::East))]
    #[case(( Orientation::South, Command::PivotRight), (5, 5, Orientation::West))]
    #[case(( Orientation::East, Command::MoveBackward), (4, 5, Orientation::East))]
    #[case(( Orientation::East, Command::MoveForward), (6, 5, Orientation::East))]
    #[case(( Orientation::East, Command::PivotLeft), (5, 5, Orientation::North))]
    #[case(( Orientation::East, Command::PivotRight), (5, 5, Orientation::South))]
    fn test_apply(
        #[case] input: (Orientation, Command),
        #[case] expected: (usize, usize, Orientation),
    ) {
        let rover = Rover::from(
            Position::from(5, 5, input.0),
            Planet::from((10, 10), vec![]),
        )
        .unwrap();

        let new_rover = rover.apply(input.1);

        assert_eq!(new_rover.position.get_x(), expected.0);
        assert_eq!(new_rover.position.get_y(), expected.1);
        assert_eq!(new_rover.position.orientation, expected.2);
    }

    #[test]
    fn test_check_collision() {
        use crate::orientation::Orientation;
        use crate::planet::Planet;
        use crate::planet::Rock;
        use crate::position::Position;
        use crate::rover::Rover;

        let rover = Rover::from(
            Position::from(2, 2, Orientation::North),
            Planet::from((5, 5), vec![Rock::from(4, 4), Rock::from(1, 3)]),
        );
        assert!(rover.is_ok());
        assert!(rover.unwrap().check_collision().is_ok());

        let rover = Rover::from(
            Position::from(1, 3, Orientation::South),
            Planet::from((5, 5), vec![Rock::from(1, 3), Rock::from(4, 4)]),
        );
        assert!(rover.is_err());
    }
}
