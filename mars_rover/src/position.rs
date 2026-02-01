use crate::orientation::Orientation;

#[derive(Debug, Copy, Clone)]
pub struct Position {
    x: usize,
    y: usize,
    #[cfg_attr(test, allow(unused))]
    pub orientation: Orientation,
}
impl Position {
    pub fn from(x: usize, y: usize, orientation: Orientation) -> Self {
        Self { x, y, orientation }
    }

    // prevent direct modification of properties
    pub fn get_x(&self) -> usize {
        self.x
    }

    // prevent direct modification of properties
    pub fn get_y(&self) -> usize {
        self.y
    }

    pub fn move_backward(self, limit_x: usize, limit_y: usize) -> Self {
        let (x, y) = match self.orientation {
            Orientation::East => (self.x.checked_sub(1).unwrap_or(limit_x), self.y),
            Orientation::North => (self.x, self.y.checked_sub(1).unwrap_or(limit_y)),
            Orientation::South => (self.x, if self.y < limit_y { self.y + 1 } else { 0 }),
            Orientation::West => (if self.x < limit_x { self.x + 1 } else { 0 }, self.y),
        };

        Self {
            x,
            y,
            orientation: self.orientation,
        }
    }

    pub fn move_forward(self, limit_x: usize, limit_y: usize) -> Self {
        let (x, y) = match self.orientation {
            Orientation::East => ((self.x + 1) % (limit_x + 1), self.y),
            Orientation::North => (self.x, (self.y + 1) % (limit_y + 1)),
            Orientation::South => (self.x, if self.y == 0 { limit_y } else { self.y - 1 }),
            Orientation::West => (if self.x == 0 { limit_x } else { self.x - 1 }, self.y),
        };

        Self {
            x,
            y,
            orientation: self.orientation,
        }
    }

    pub fn pivot_left(self) -> Self {
        Self {
            x: self.x,
            y: self.y,
            orientation: self.orientation.pivot_left(),
        }
    }

    pub fn pivot_right(self) -> Self {
        Self {
            x: self.x,
            y: self.y,
            orientation: self.orientation.pivot_right(),
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
        let position = Position::from(5, 5, input);

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
        let position = Position::from(5, 5, input);

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
        let position = Position::from(5, 5, input);

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
        let position = Position::from(5, 5, input);

        let new_position = position.pivot_right();
        assert_eq!(new_position.x, 5);
        assert_eq!(new_position.y, 5);
        assert_eq!(new_position.orientation, expected);
    }
}
