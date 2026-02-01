const MAX_TRIES: usize = 10;

enum Frame10 {
    InProgress(Vec<u16>),
    NotReached,
}

impl Frame10 {
    fn accepts_next(&self) -> bool {
        match self {
            Frame10::InProgress(r) => match r.len() {
                0 | 1 => true,
                2 => r[0] == 10 || r[0] + r[1] == 10,
                _ => false,
            },
            Frame10::NotReached => true,
        }
    }
}

struct Game {
    rolls: Vec<u16>,
}

impl Game {
    fn new() -> Self {
        Self { rolls: Vec::new() }
    }

    fn frame_roll_count(&self, i: usize) -> Option<usize> {
        if self.rolls.len() <= i {
            return None;
        }
        if self.rolls.get(i) == Some(&10) {
            return Some(1);
        }
        if self.rolls.len() <= i + 1 {
            return None;
        }
        Some(2)
    }

    fn frame_10_state(&self) -> Frame10 {
        let mut i = 0;
        for _ in 1..=MAX_TRIES - 1 {
            match self.frame_roll_count(i) {
                None => return Frame10::NotReached,
                Some(f) => i += f,
            }
        }
        Frame10::InProgress(self.rolls[i..].to_vec())
    }

    fn get_score(&self) -> u16 {
        let mut result: u16 = 0;
        let mut i = 0;

        for _ in 1..=MAX_TRIES {
            let current = self.rolls.get(i).unwrap_or(&0);
            let next = self.rolls.get(i + 1).unwrap_or(&0);
            let next_next = self.rolls.get(i + 2).unwrap_or(&0);

            if *current == 10u16 {
                result += 10 + next + next_next;
                i += 1;
                continue;
            }

            if current + next == 10u16 {
                result += 10 + next_next;
                i += 2;
                continue;
            }
            result += current + next;
            i += 2;
        }

        result
    }

    fn roll(&mut self, value: u16) -> Result<(), &'static str> {
        if self.frame_10_state().accepts_next() {
            self.rolls.push(value);
            return Ok(());
        }

        Err("You cannot play too much!")
    }
}

fn main() {}

#[cfg(test)]
mod tests {
    use crate::Game;

    #[test]
    fn test_number_of_frames_20() {
        let mut game = Game::new();
        for _ in 1..=20 {
            game.roll(1).unwrap();
        }
        assert_eq!(game.rolls.len(), 20);
    }

    #[test]
    fn test_number_of_frames_21() {
        let mut game = Game::new();
        for _ in 1..=20 {
            game.roll(1).unwrap();
        }
        assert!(game.roll(1).is_err());
    }

    #[test]
    fn test_number_of_frames_with_spare_at_last() {
        let mut game = Game::new();
        for _ in 1..=18 {
            game.roll(0).unwrap();
        }
        game.roll(7).unwrap();
        game.roll(3).unwrap();
        game.roll(1).unwrap();

        // not include in rolls
        assert!(game.roll(1).is_err());
    }

    #[test]
    fn test_number_of_frames_with_strike_at_last() {
        let mut game = Game::new();
        for _ in 1..=18 {
            game.roll(0).unwrap();
        }
        game.roll(10).unwrap();
        game.roll(3).unwrap();
        game.roll(1).unwrap();

        // not include in rolls
        assert!(game.roll(1).is_err());
    }

    #[test]
    fn test_null_score() {
        let mut game = Game::new();
        for _ in 1..=20 {
            game.roll(0).unwrap();
        }
        assert_eq!(game.get_score(), 0);
    }

    #[test]
    fn test_classic_score() {
        let mut game = Game::new();
        for _ in 1..=20 {
            game.roll(1).unwrap();
        }
        assert_eq!(game.get_score(), 20);
    }

    #[test]
    fn test_spare_score() {
        let mut game = Game::new();
        game.roll(5).unwrap();
        game.roll(5).unwrap();
        game.roll(3).unwrap();
        game.roll(1).unwrap();
        assert_eq!(game.get_score(), 17);
    }

    #[test]
    fn test_strike_score() {
        let mut game = Game::new();
        game.roll(10).unwrap();
        game.roll(3).unwrap();
        game.roll(1).unwrap();
        assert_eq!(game.get_score(), 18);
    }

    #[test]
    fn test_with_extra_ball_score_from_spare() {
        let mut game = Game::new();
        for _ in 1..=18 {
            game.roll(0).unwrap();
        }
        game.roll(5).unwrap();
        game.roll(5).unwrap();
        game.roll(3).unwrap();

        assert_eq!(game.get_score(), 13);
    }

    #[test]
    fn test_with_extra_balls_score_from_strike() {
        let mut game = Game::new();
        for _ in 1..=18 {
            game.roll(0).unwrap();
        }
        game.roll(10).unwrap();
        game.roll(3).unwrap();
        game.roll(3).unwrap();

        assert_eq!(game.get_score(), 16);
    }

    #[test]
    fn test_max_score() {
        let mut game = Game::new();
        for _ in 1..=10 {
            game.roll(10).unwrap();
        }
        game.roll(10).unwrap();
        game.roll(10).unwrap();

        assert_eq!(game.get_score(), 300);
    }
}
