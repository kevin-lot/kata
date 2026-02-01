use crate::orientation::Orientation::{East, North, South, West};

#[derive(Copy, Clone, Debug, PartialEq)]
pub enum Orientation {
    East,
    North,
    South,
    West,
}
impl Orientation {
    pub fn pivot_left(self) -> Self {
        match self {
            North => West,
            East => North,
            South => East,
            West => South,
        }
    }

    pub fn pivot_right(self) -> Self {
        match self {
            North => East,
            West => North,
            South => West,
            East => South,
        }
    }
}

#[cfg(test)]
mod tests {
    use super::Orientation::{East, North, South, West};

    #[test]
    fn test_pivot_left() {
        assert_eq!(North.pivot_left(), West);
        assert_eq!(West.pivot_left(), South);
        assert_eq!(South.pivot_left(), East);
        assert_eq!(East.pivot_left(), North);
    }

    #[test]
    fn test_pivot_right() {
        assert_eq!(North.pivot_right(), East);
        assert_eq!(East.pivot_right(), South);
        assert_eq!(South.pivot_right(), West);
        assert_eq!(West.pivot_right(), North);
    }
}
