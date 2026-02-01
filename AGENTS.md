# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Kata Collection Overview

This is a collection of programming katas from [Kata Log](https://kata-log.rocks/), implemented in Kotlin, Java, and Rust. Each kata lives in its own subdirectory with its own build system.

## Commands

### Kotlin & Java projects (Gradle)

Run from within the project directory:

```bash
./gradlew test              # Run all tests
./gradlew test --tests "ClassName.testName"  # Run a single test
./gradlew spotlessCheck     # Check formatting
./gradlew spotlessApply     # Auto-fix formatting
./gradlew build             # Full build + tests
```

### Rust projects (Cargo)

Run from within the project directory:

```bash
cargo test                  # Run all tests
cargo test test_name        # Run a single test
cargo fmt -- --check        # Check formatting
cargo fmt                   # Auto-fix formatting
cargo clippy                # Lint
cargo build                 # Debug build
```

## Code Style

Enforced via `.editorconfig` across all projects:
- 4-space indentation
- 120-character max line length
- LF line endings
- UTF-8 encoding

Kotlin/Java use Spotless (ktlint for Kotlin, PalantirJavaFormat for Java). Rust uses `rustfmt`.

Shell scripts use 2-space indentation (enforced by `.editorconfig`). Scripts use `set -o errexit` (and sometimes `nounset`) — no silent failures. Functions are prefixed with `_` if internal helpers.

## Architecture

### Kotlin projects
- Use data classes and sealed classes for domain modeling
- Prefer immutable value objects; mutations return new instances
- Use `Result<T>` for error handling rather than exceptions
- Kotest `FunSpec` or standard JUnit5 `@Test` for tests

### Java projects
- Polymorphic design: abstract base class with concrete subclasses per hand type
- Enums for rank/suit constants; stream API with grouping for analysis
- Factory pattern in `Solver` static methods

### Rust projects
- Structs with `impl` blocks, `Result`/`Option` for error handling
- Inline tests using `#[cfg(test)]` modules
- rstest for parameterized tests

---

## Dev Workstation Scripts Overview

This repo also contains utility scripts for cross-platform developer workstation setup and maintenance. Scripts are meant to be cloned into `~/bin/` on each machine.

### Scripts

| Script | Purpose |
|---|---|
| `bin_install_update` | Bootstrap/update full dev environment (cross-platform) |
| `btrfs_subvolume_diff` | Diff two BTRFS snapshots; outputs changed/added files |
| `docker_clean` | Remove all Docker containers, images, and prune system |
| `reset_jetbrains` | Reset WebStorm trial evaluation state |

### Tool Version Management: mise

All dev tool installs are managed by [mise](https://mise.jdx.dev/) (formerly rtx). mise must be installed before running `bin_install_update`. Tools installed globally via mise include: `rtk`, `act`, `awscli`, `bat`, `claude`, `gemini`, `gh`, `lazygit`, `node`, `poetry`, `rust`, `terraform`, `uv`, and others.

To install/update everything: `./bin_install_update`

### Shell Environment

- Shell: **zsh**
- Prompt: **Starship**
- Plugins installed to `~/.zsh_plugins/`: zsh-autosuggestions, zsh-syntax-highlighting, zsh-history-substring-search

### Platform Support

`bin_install_update` detects and handles: **Fedora**, **Linux Mint**, and **macOS** (Homebrew).

### What AI Agents Should Know

- **Do not run `bin_install_update` non-interactively** — it installs system packages via sudo and modifies `~/.config` and `~/.zsh_plugins`
- **`btrfs_subvolume_diff`** requires two snapshot paths as positional args and a BTRFS filesystem
- **`docker_clean`** is destructive — it removes *all* containers and images without confirmation
- **`reset_jetbrains`** modifies `~/.config/JetBrains/WebStorm*/` — scope is intentionally narrow
- The `.gitignore` excludes symlinks and binaries installed by the scripts themselves; do not add those paths back
- RTK (`rtk`) is installed via mise and is used as a transparent proxy for shell commands to reduce token usage in Claude Code sessions — see `~/.claude/RTK.md` for details
