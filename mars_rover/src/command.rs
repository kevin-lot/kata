#[derive(PartialEq, Debug)]
pub enum Command {
    MoveBackward,
    MoveForward,
    PivotLeft,
    PivotRight,
}
impl Command {
    pub fn from(c: char) -> Option<Self> {
        match c {
            'b' => Some(Command::MoveBackward),
            'f' => Some(Command::MoveForward),
            'l' => Some(Command::PivotLeft),
            'r' => Some(Command::PivotRight),
            _ => None,
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::command::Command;
    use rstest::rstest;

    #[rstest]
    #[case('b', Command::MoveBackward)]
    #[case('f', Command::MoveForward)]
    #[case('l', Command::PivotLeft)]
    #[case('r', Command::PivotRight)]
    fn test_from(#[case] input: char, #[case] expected: Command) {
        let command = Command::from(input);
        assert!(command.is_some());
        assert_eq!(command.unwrap(), expected);

        let command = Command::from('a');
        assert!(command.is_none());
    }
}
