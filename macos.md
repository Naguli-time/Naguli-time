# hello

## Package Management
- homebrew install
1. URL : https://brew.sh/index_ko
2. CLI : /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  2-1. Warning: /opt/homebrew/bin is not in your PATH. -> echo 'export PATH=/opt/homebrew/bin:$PATH' >> ~/.zshrc

## node install
1. brew install nvm
2. CLI : mkdir ~/.nvm
3. CLI : vim ~/.zshenv

export NVM_DIR="$HOME/.nvm"
[ -s "/opt/homebrew/opt/nvm/nvm.sh" ] && \. "/opt/homebrew/opt/nvm/nvm.sh"  # This loads nvm
[ -s "/opt/homebrew/opt/nvm/etc/bash_completion.d/nvm" ] && \. "/opt/homebrew/opt/nvm/etc/bash_completion.d/nvm"  # This loads nvm bash_completion
 
## bash
- option c
  - execute code
  
## homwbrew 사용법

- $ brew install wget
- $ brew install cask
- $ brew install --cask firefox
- $ brew install git  

## nvm
- nvm ls-remote : 설치 가능 목록 확인
- nvm install 18.13.0
