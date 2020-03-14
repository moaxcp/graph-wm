with import <nixpkgs> {};
let
  unstable = import <nixos-unstable> {config = config.nixpkgs.config;};
  nur = import <nur> {inherit pkgs; };
in with pkgs;
mkShell {
  buildInputs = [
    unstable.micronaut
  ];
}
