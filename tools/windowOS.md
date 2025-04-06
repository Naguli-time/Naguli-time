# windows os

## rust install (online)

1. rustup-init.exe
   - MSVC v143 - VS 2022 C++ x64/x86 빌드 도구
   - Windows 11 SDK
   - {HOME}.rustup
     - RUSUP_HOME 환경 변수를 사용하여 수정할 수 있습니다.
   - {HOME}.cargo
     - cargo home
     - CARGO_HOME 환경 변수로 수정할 수 있습니다.
   - {HOME}.cargo\bin
     - The cargo, rustc, rustup and other commands will be added to Cargo's bin directory
     - 이 경로는 다음과 같이 PATH 환경 변수에 추가됨
       - HKEY_CURRENT_USER/Environment/PATH registry key.

```
default host triple: x86_64-pc-windows-msvc
     default toolchain: stable (default)
               profile: default
  modify PATH variable: yes
```

```
info: profile set to 'default'
info: default host triple is x86_64-pc-windows-msvc
info: syncing channel updates for 'stable-x86_64-pc-windows-msvc'
info: latest update on 2023-01-26, rust version 1.67.0 (fc594f156 2023-01-24)
info: downloading component 'cargo'
info: downloading component 'clippy'
info: downloading component 'rust-docs'
info: downloading component 'rust-std'
info: downloading component 'rustc'
 62.7 MiB /  62.7 MiB (100 %)  56.4 MiB/s in  1s ETA:  0s
info: downloading component 'rustfmt'
info: installing component 'cargo'
info: installing component 'clippy'
info: installing component 'rust-docs'
 19.3 MiB /  19.3 MiB (100 %)   1.7 MiB/s in 13s ETA:  0s
info: installing component 'rust-std'
 26.8 MiB /  26.8 MiB (100 %)  12.9 MiB/s in  2s ETA:  0s
info: installing component 'rustc'
 62.7 MiB /  62.7 MiB (100 %)  13.5 MiB/s in  4s ETA:  0s
info: installing component 'rustfmt'
info: default toolchain set to 'stable-x86_64-pc-windows-msvc'

  stable-x86_64-pc-windows-msvc installed - rustc 1.67.0 (fc594f156 2023-01-24)


Rust is installed now. Great!

To get started you may need to restart your current shell.
This would reload its PATH environment variable to include
Cargo's bin directory (%USERPROFILE%\.cargo\bin).
```

## rust install (offline)

1. rustup-init.exe
   - MSVC - VS 2019 C++ x64/x86 build tools
   - Windows 10 SDK
   - rustup-init.exe --no-tool-path --default-toolchain stability-x86_64-pc-windows-msvc
