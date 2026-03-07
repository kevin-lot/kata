struct Frame {
    first_roll: u16,
    second_roll: Option<u16>,
}
impl Frame {
    pub fn get_total(&self) -> u16 {
        self.first_roll + self.second_roll.unwrap_or(0)
    }

    pub fn is_spare(&self) -> bool {
        self.second_roll.is_some()
            && self.second_roll != Some(0)
            && self.first_roll + self.second_roll.unwrap_or(0) == 10
    }

    pub fn is_strike(&self) -> bool {
        self.first_roll == 10 && self.second_roll == Some(0)
    }
}
const MAX_TRIES: usize = 10;

struct Game {
    frames: Vec<Frame>,
}
impl Game {
    fn get_score(&self) -> u16 {
        let mut score: u16 = 0;

        for i in 0..self.frames.len() {
            let optional_frame = self.frames.get(i);

            score += optional_frame.map(|f| f.get_total()).unwrap_or(0);

            if optional_frame.map(|f| f.is_strike()).unwrap_or(false) {
                score += 10;
                score += self.frames.get(i + 1).map(|f| f.first_roll).unwrap_or(0);
                score += self
                    .frames
                    .get(i + 1)
                    .and_then(|f| f.second_roll)
                    .unwrap_or(0);
                continue;
            }

            if optional_frame.map(|f| f.is_spare()).unwrap_or(false) {
                score += 10;
                score += self.frames.get(i + 1).map(|f| f.first_roll).unwrap_or(0);
                continue;
            }
        }

        score
    }

    fn roll(&mut self, pins: u16) {
        let first_bonus_roll = self.frames.get(9).map(|f| f.is_spare()).unwrap_or(false)
            || self.frames.get(9).map(|f| f.is_strike()).unwrap_or(false);
        let second_bonus_roll = self.frames.get(9).map(|f| f.is_strike()).unwrap_or(false);

        let add_try = if first_bonus_roll || second_bonus_roll {
            1
        } else {
            0
        };

        if self.frames.len() >= MAX_TRIES + add_try {
            panic!("You cannot play too much!");
        }

        if let Some(frame) = self.frames.last_mut() {
            match frame.second_roll {
                None => {
                    frame.second_roll = Some(pins);
                }
                Some(_) => {
                    self.frames.push(Frame {
                        first_roll: pins,
                        second_roll: if pins == 10 { Some(0) } else { None },
                    });
                }
            }
            return;
        }

        // first call
        self.frames.push(Frame {
            first_roll: pins,
            second_roll: None,
        });
    }
}

fn main() {
    let mut game = Game { frames: Vec::new() };

    // frame 1
    game.roll(2);
    game.roll(8);
    // frame 2
    game.roll(3);
    game.roll(3);
    // frame 3
    game.roll(3);
    game.roll(3);
    // frame 4
    game.roll(3);
    game.roll(3);
    // frame 5
    game.roll(3);
    game.roll(3);
    // frame 6
    game.roll(3);
    game.roll(3);
    // frame 7
    game.roll(3);
    game.roll(3);
    // frame 8
    game.roll(3);
    game.roll(3);
    // frame 9
    game.roll(3);
    game.roll(3);
    // frame 10
    game.roll(3);
    game.roll(7);

    println!("{}", game.get_score());
}

#[cfg(test)]
mod tests {
    use crate::Game;

    #[test]
    #[should_panic(expected = "You cannot play too much!")]
    fn test_insert_11_frames() {
        let mut game = Game { frames: Vec::new() };
        for _ in 1..=20 {
            game.roll(1);
        }
        game.roll(1);
    }

    #[test]
    fn test_insert_11_frames_without_panic() {
        let mut game = Game { frames: Vec::new() };
        for _ in 1..=19 {
            game.roll(1);
        }
        game.roll(9);

        game.roll(1);
    }

    // #[test]
    // #[should_panic(expected = "You cannot play too much!")]
    // fn test_insert_22_rolls() {
    //     let mut game = Game { rolls: Vec::new() };
    //     for _ in 1..=19 {
    //         game.roll(1);
    //     }
    //     game.roll(9);
    //     game.roll(1);
    //     game.roll(1);
    // }

    #[test]
    fn test_null_score() {
        let mut game = Game { frames: Vec::new() };
        for _ in 1..=10 {
            game.roll(0);
        }
        assert_eq!(game.get_score(), 0);
    }

    #[test]
    fn test_classic_score() {
        let mut game = Game { frames: Vec::new() };
        for _ in 1..=20 {
            game.roll(1);
        }
        assert_eq!(game.get_score(), 20);
    }

    #[test]
    fn test_max_score() {
        let mut game = Game { frames: Vec::new() };
        for _ in 1..=10 {
            game.roll(10);
        }
        game.roll(10);
        game.roll(10);
        assert_eq!(game.get_score(), 300);
    }
}
