#[derive(Debug, Clone, PartialEq)]
pub struct Rock {
    pub x: usize,
    pub y: usize,
}
impl Rock {
    pub fn from(x: usize, y: usize) -> Self {
        Self { x, y }
    }
}

#[derive(Debug, Clone)]
pub struct Planet {
    size: (usize, usize),
    rocks: Vec<Rock>,
}
impl Planet {
    pub fn from(size: (usize, usize), rocks: Vec<Rock>) -> Self {
        Self { size, rocks }
    }
    pub fn get_rocks(&self) -> Vec<Rock> {
        self.rocks.clone()
    }
    pub fn get_size(&self) -> (usize, usize) {
        self.size
    }
}
