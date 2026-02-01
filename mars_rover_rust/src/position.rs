use crate::orientation::Orientation;

#[derive(Debug, Copy, Clone)]
pub struct Position {
    pub x: usize,
    pub y: usize,
    pub orientation: Orientation,
}
impl Position {
    pub fn new(x: usize, y: usize, orientation: Orientation) -> Self {
        Self { x, y, orientation }
    }

    fn wrap(value: usize, delta: isize, limit: usize) -> usize {
        let size = (limit + 1) as isize;
        (((value as isize + delta) % size + size) % size) as usize
    }

    pub fn move_backward(self, limit_x: usize, limit_y: usize) -> Self {
        let (x, y) = match self.orientation {
            Orientation::East => (Self::wrap(self.x, -1, limit_x), self.y),
            Orientation::North => (self.x, Self::wrap(self.y, -1, limit_y)),
            Orientation::South => (self.x, Self::wrap(self.y, 1, limit_y)),
            Orientation::West => (Self::wrap(self.x, 1, limit_x), self.y),
        };

        Self {
            x,
            y,
            orientation: self.orientation,
        }
    }

    pub fn move_forward(self, limit_x: usize, limit_y: usize) -> Self {
        let (x, y) = match self.orientation {
            Orientation::East => (Self::wrap(self.x, 1, limit_x), self.y),
            Orientation::North => (self.x, Self::wrap(self.y, 1, limit_y)),
            Orientation::South => (self.x, Self::wrap(self.y, -1, limit_y)),
            Orientation::West => (Self::wrap(self.x, -1, limit_x), self.y),
        };

        Self {
            x,
            y,
            orientation: self.orientation,
        }
    }

    pub fn pivot_left(self) -> Self {
        Self {
            orientation: self.orientation.pivot_left(),
            ..self
        }
    }

    pub fn pivot_right(self) -> Self {
        Self {
            orientation: self.orientation.pivot_right(),
            ..self
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::orientation::Orientation;
    use crate::position::Position;
    use rstest::rstest;

    #[rstest]
    #[case(Orientation::North, (5, 4, Orientation::North))]
    #[case(Orientation::West, (6, 5, Orientation::West))]
    #[case(Orientation::South, (5, 6, Orientation::South))]
    #[case(Orientation::East, (4, 5, Orientation::East))]
    fn test_move_backward(
        #[case] input: Orientation,
        #[case] expected: (usize, usize, Orientation),
    ) {
        let position = Position::new(5, 5, input);

        let new_position = position.move_backward(100, 100);
        assert_eq!(new_position.x, expected.0);
        assert_eq!(new_position.y, expected.1);
        assert_eq!(new_position.orientation, expected.2);
    }

    #[rstest]
    #[case(Orientation::North, (5, 6, Orientation::North))]
    #[case(Orientation::West, (4, 5, Orientation::West))]
    #[case(Orientation::South, (5, 4, Orientation::South))]
    #[case(Orientation::East, (6, 5, Orientation::East))]
    fn test_move_forward(
        #[case] input: Orientation,
        #[case] expected: (usize, usize, Orientation),
    ) {
        let position = Position::new(5, 5, input);

        let new_position = position.move_forward(100, 100);
        assert_eq!(new_position.x, expected.0);
        assert_eq!(new_position.y, expected.1);
        assert_eq!(new_position.orientation, expected.2);
    }

    #[rstest]
    #[case(Orientation::North, Orientation::West)]
    #[case(Orientation::West, Orientation::South)]
    #[case(Orientation::South, Orientation::East)]
    #[case(Orientation::East, Orientation::North)]
    fn test_pivot_left(#[case] input: Orientation, #[case] expected: Orientation) {
        let position = Position::new(5, 5, input);

        let new_position = position.pivot_left();
        assert_eq!(new_position.x, 5);
        assert_eq!(new_position.y, 5);
        assert_eq!(new_position.orientation, expected);
    }

    #[rstest]
    #[case(Orientation::North, Orientation::East)]
    #[case(Orientation::West, Orientation::North)]
    #[case(Orientation::South, Orientation::West)]
    #[case(Orientation::East, Orientation::South)]
    fn test_pivot_right(#[case] input: Orientation, #[case] expected: Orientation) {
        let position = Position::new(5, 5, input);

        let new_position = position.pivot_right();
        assert_eq!(new_position.x, 5);
        assert_eq!(new_position.y, 5);
        assert_eq!(new_position.orientation, expected);
    }
}
