#[derive(PartialEq, Debug)]
pub enum Command {
    MoveBackward,
    MoveForward,
    PivotLeft,
    PivotRight,
}

impl TryFrom<char> for Command {
    type Error = ();

    fn try_from(c: char) -> Result<Self, Self::Error> {
        match c {
            'b' => Ok(Command::MoveBackward),
            'f' => Ok(Command::MoveForward),
            'l' => Ok(Command::PivotLeft),
            'r' => Ok(Command::PivotRight),
            _ => Err(()),
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
    fn test_try_from(#[case] input: char, #[case] expected: Command) {
        let command = Command::try_from(input);
        assert!(command.is_ok());
        assert_eq!(command.unwrap(), expected);

        let command = Command::try_from('a');
        assert!(command.is_err());
    }
}
